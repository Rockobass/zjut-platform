package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.base.exception.app.AccountBlockedException;
import org.whatever.aha.zjut.base.exception.app.InvalidCredentialException;
import org.whatever.aha.zjut.platform.entity.User;
import org.whatever.aha.zjut.platform.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/user/")
public class SaController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("passwordLogin")
    public Object doLogin(@RequestParam String username, @RequestParam String password) {
        User user = userService.getUserByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword()))
            throw new InvalidCredentialException();
        if(user.getDisabled())
            throw new AccountBlockedException(Map.of("username", username, "untie_time", user.getUntieTime()));

        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return AjaxResult.OK(Map.of("token_name", tokenInfo.getTokenName(),
                "token_value", tokenInfo.getTokenValue(), "login_device", tokenInfo.getLoginDevice()));
    }

    @GetMapping("isLogin")
    public String isLogin(String username, String password) {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}
