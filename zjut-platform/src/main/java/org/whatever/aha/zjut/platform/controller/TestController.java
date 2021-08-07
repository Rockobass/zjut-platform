package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.temp.SaTempUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.config.ProfileConfig;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.mapper.UserMapper;
import org.whatever.aha.zjut.platform.service.SMSService;
import org.whatever.aha.zjut.platform.service.UserService;

/**
 * @author Baby_mo
 */
@RestController
@RequestMapping("/v1/test")
@RequiredArgsConstructor
public class TestController {

    final ProfileConfig profileConfig;
    final UserMapper userMapper;
    final SMSService smsService;
    final UserService userService;

    @PostMapping("/sa")
    AjaxResult<Object> test(@RequestParam int a) {
        StpUtil.login(1);
        return AjaxResult.SUCCESS("成功");
    }

    @PostMapping("/tt")
    @SaCheckRole("dasd")
    public AjaxResult<Object> t() {
        return AjaxResult.SUCCESS(userMapper.selectList(null));
    }

    @PostMapping("/sendSMS")
    public Object sendSMS() {
        Integer userId = userService.insertStudent("13067828119", "123456");
        return AjaxResult.SUCCESS(userId);
    }

    @GetMapping("/tttt")
    public Object testtt(){
        String token = SaTempUtil.createToken("213123", 301);
        System.out.println(token);
        return AjaxResult.SUCCESS();
    }
}
