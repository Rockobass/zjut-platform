package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.temp.SaTempUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.base.exception.AppException;
import org.whatever.aha.zjut.base.exception.app.InvalidCredentialException;
import org.whatever.aha.zjut.base.util.RegexUtil;
import org.whatever.aha.zjut.platform.entity.User;
import org.whatever.aha.zjut.platform.service.CaptchaService;
import org.whatever.aha.zjut.platform.service.SMSService;
import org.whatever.aha.zjut.platform.service.UserService;

import java.util.Map;

/**
 * @author Baby_mo
 */
@Api(tags = "登录和token分发相关接口")
@RestController
@RequestMapping("/v1/sa")
@RequiredArgsConstructor
public class SaController {

    final UserService userService;
    final CaptchaService captchaService;
    final PasswordEncoder passwordEncoder;
    final SMSService smsService;


    @ApiOperation("通过密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名、学号或手机号"),
            @ApiImplicitParam(name = "code", value = "验证码"),
            @ApiImplicitParam(name = "fingerPrint", value = "设备或浏览器指纹"),
            @ApiImplicitParam(name = "loginType", value = "用户类型 0 学生、1 评委、2 院级管理员、3 校级管理员")
    })
    @PostMapping("/passwordLogin")
    public Object doLogin(@RequestParam String username, @RequestParam String password, @RequestParam int loginType, @RequestParam String code, @RequestParam String fingerPrint) {

        captchaService.verify(fingerPrint, code);
        User user = userService.getUserByUsername(username);

        if (user == null || !passwordEncoder.matches(password, user.getPassword()) || loginType != user.getLoginType()){
            throw new InvalidCredentialException();
        }
        userService.checkAccount(user);

        StpUtil.login(user.getUserId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return AjaxResult.SUCCESS(Map.of("token_name", tokenInfo.getTokenName(),
                "token_value", tokenInfo.getTokenValue(), "login_device", tokenInfo.getLoginDevice()));
    }

    @ApiOperation("判断是否登录")
    @GetMapping("/isLogin")
    public Object isLogin() {
        return AjaxResult.SUCCESS(StpUtil.isLogin());
    }

    @ApiOperation("注销当前账号")
    @GetMapping("/logout")
    public Object logout() {
        StpUtil.logout();
        return AjaxResult.SUCCESS("注销成功");
    }


    @ApiOperation("获取base64格式验证码图片")
    @ApiImplicitParam(name = "fingerPrint", value = "设备或浏览器指纹")
    @GetMapping("/verifyCode")
    public String getVerifyCode(@RequestParam String fingerPrint) {
        return captchaService.getBase64(fingerPrint);
    }

    @ApiOperation(value = "获取短信验证码", notes = "手机号须已经注册")
    @ApiImplicitParam(name = "phoneNumber", value = "手机号码")
    @PostMapping("/getSmsCode")
    public Object getSMSCode(@RequestParam String phoneNumber) {
        if (!userService.exist(phoneNumber)){
            throw new AppException(ErrorCode.PHONE_NUMBER_NONE_EXIST);
        }
        smsService.sendMessage(phoneNumber, "validation");
        return AjaxResult.SUCCESS(null);
    }

    @ApiOperation("短信验证码登陆")
    @PostMapping("/phoneLogin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber", value = "手机号"),
            @ApiImplicitParam(name = "code", value = "验证码"),
            @ApiImplicitParam(name = "loginType", value = "用户类型 0 学生、1 评委、2 院级管理员、3 校级管理员")
    })
    public Object doLogin(@RequestParam String phoneNumber, @RequestParam int loginType, @RequestParam String code) {

        smsService.verify(phoneNumber, code, "validation");
        User user = userService.getUserByUsername(phoneNumber);

        if (user == null || loginType != user.getLoginType()){
            throw new InvalidCredentialException();
        }
        userService.checkAccount(user);

        StpUtil.login(user.getUserId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return AjaxResult.SUCCESS(Map.of("token_name", tokenInfo.getTokenName(),
                "token_value", tokenInfo.getTokenValue(), "login_device", tokenInfo.getLoginDevice()));
    }

    @ApiOperation(value = "注册时获取短信验证码", notes = "手机号须未注册")
    @ApiImplicitParam(name = "phoneNumber", value = "手机号码")
    @PostMapping("/register/getSmsCode")
    public Object getSMSCodeWhenRegister(@RequestParam String phoneNumber) {
        if (userService.exist(phoneNumber)){
            throw new AppException(ErrorCode.PHONE_NUMBER_USED);
        }
        RegexUtil.checkPhoneNumber(phoneNumber);
        smsService.sendMessage(phoneNumber, "register");
        return AjaxResult.SUCCESS(null);
    }

    @ApiOperation(value = "注册时校验短信验证码", notes = "校验成功会返回token，携带token访问注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber", value = "手机号"),
            @ApiImplicitParam(name = "code", value = "验证码"),
    })
    @PostMapping("/register/verifySmsCode")
    public Object verifySMSCodeWhenRegister(@RequestParam String phoneNumber, @RequestParam String code) {
        RegexUtil.checkPhoneNumber(phoneNumber);
        long timeout = 300;
        smsService.verify(phoneNumber, code, "register");
        String token = SaTempUtil.createToken(phoneNumber, timeout);
        return AjaxResult.SUCCESS(Map.of("token", token, "time_out", timeout));
    }

    @ApiOperation(value = "学生注册", notes = "需要带token访问该接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "验证码校验时返回的token"),
            @ApiImplicitParam(name = "password", value = "密码"),
    })
    @PostMapping("/register/do")
    public Object register(@RequestParam String token, @RequestParam String password) {
        String phoneNumber = SaTempUtil.parseToken(token, String.class);
        Integer userId = userService.insertStudent(phoneNumber, password);
        return AjaxResult.SUCCESS(Map.of("user_id", userId));
    }

}
