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
import org.whatever.aha.zjut.platform.service.CaptchaService;
import org.whatever.aha.zjut.platform.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/v1/sa")
public class SaController {
    @Autowired
    UserService userService;
    @Autowired
    CaptchaService captchaService;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 密码登录
     * @param code 验证码
     * @param fingerPrint 设备或浏览器指纹
     */
    @PostMapping("/passwordLogin")
    public Object doLogin(@RequestParam String username, @RequestParam String password, @RequestParam String code, @RequestParam String fingerPrint) {
        captchaService.verify(fingerPrint, code);
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

    @GetMapping("/isLogin")
    public Object isLogin() {
        return AjaxResult.OK(StpUtil.isLogin());
    }

    /**
     * 获取base64格式验证码图片
     * @param fingerPrint 设备或浏览器指纹
     */
    @GetMapping("/verifyCode")
    public String getVerifyCode(@RequestParam String fingerPrint) {
        return captchaService.getBase64(fingerPrint);
    }
}
