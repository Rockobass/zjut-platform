package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.temp.SaTempUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.config.ProfileConfig;
import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.mapper.UserMapper;
import org.whatever.aha.zjut.platform.service.SMSService;
import org.whatever.aha.zjut.platform.service.UserService;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Baby_mo
 */
@RestController
@RequestMapping("/v1/test")
@RequiredArgsConstructor
@Api(tags = "测试用接口")
public class TestController {

    final ProfileConfig profileConfig;
    final UserMapper userMapper;
    final SMSService smsService;
    final UserService userService;

    @PostMapping("/1")
    @ApiOperation(value = "登陆任意账号")
    Object test(@RequestParam int userId) {
        StpUtil.login(userId);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return AjaxResult.SUCCESS(Map.of("token_name", tokenInfo.getTokenName(),
                "token_value", tokenInfo.getTokenValue(), "login_device", tokenInfo.getLoginDevice()));
    }

    @PostMapping("/2")
    @ApiOperation(value = "获取所有账号信息")
    public Object t() {
        return userMapper.selectList(null);
    }

    @GetMapping("/3")
    @ApiOperation(value = "获取所有错误code")
    public Object getCode() {
        ErrorCode[] values = ErrorCode.values();
        return AjaxResult.SUCCESS(Arrays.stream(values).map(e->Map.of("code", e.getCode(), "message", e.getMessage())).collect(Collectors.toList()));
    }
    
    @PostMapping("/sendSMS")
    public Object sendSMS() {
//        Integer userId = userService.insertWithPhoneNumber("13067828119", "123456");
        return AjaxResult.SUCCESS();
    }

    @GetMapping("/tttt")
    public Object testtt(){
        String token = SaTempUtil.createToken("213123", 301);
        System.out.println(token);
        return AjaxResult.SUCCESS();
    }
}
