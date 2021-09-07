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
import org.whatever.aha.zjut.platform.message.MessageService;
import org.whatever.aha.zjut.platform.service.SMSService;
import org.whatever.aha.zjut.platform.service.UserService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
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
    final MessageService messageService;

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

    @GetMapping("/5")
    @ApiOperation("lua测试添加")
    public Object ttt(){
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String utc = s.format(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime());
        messageService.createUserMessage(100, 101, utc, "测试标题", "测试内容", "杨归一");
        return null;
    }

    @GetMapping("/6")
    @ApiOperation("lua测试")
    public Object ttt1(){
        return messageService.getMsgContent(101, "30a17831-2b3");
    }

}
