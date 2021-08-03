package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.whatever.aha.zjut.base.config.ProfileConfig;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.mapper.UserMapper;
import org.whatever.aha.zjut.platform.service.SMSService;

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
        smsService.sendMessage("13067828119");
        return AjaxResult.SUCCESS(null);
    }
}
