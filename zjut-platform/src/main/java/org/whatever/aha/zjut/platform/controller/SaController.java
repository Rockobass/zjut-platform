package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.temp.SaTempUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.constant.RegexPattern;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.base.exception.AppException;
import org.whatever.aha.zjut.base.exception.app.InvalidCredentialException;
import org.whatever.aha.zjut.platform.entity.User;
import org.whatever.aha.zjut.platform.service.CaptchaService;
import org.whatever.aha.zjut.platform.service.SMSService;
import org.whatever.aha.zjut.platform.service.StudentInfoService;
import org.whatever.aha.zjut.platform.service.UserService;

import javax.validation.constraints.Pattern;
import java.util.Map;

/**
 * @author Baby_mo
 */
@Api(tags = "登录注册和token分发相关接口")
@Validated
@RestController
@RequestMapping("/v1/sa")
@RequiredArgsConstructor
public class SaController {

    final UserService userService;
    final CaptchaService captchaService;
    final PasswordEncoder passwordEncoder;
    final SMSService smsService;
    final StudentInfoService studentInfoService;


    @ApiOperation("登陆 通过密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名、学号或手机号", dataTypeClass = String.class),
            @ApiImplicitParam(name = "code", value = "验证码", dataTypeClass = String.class, required = false),
            @ApiImplicitParam(name = "fingerPrint", value = "设备或浏览器指纹", dataTypeClass = String.class, required = false),
            @ApiImplicitParam(name = "loginType", value = "用户类型 0 学生、1 评委、2 院级管理员、3 校级管理员", dataTypeClass = Integer.class)
    })
    @PostMapping("/passwordLogin")
    public Object doLogin(@RequestParam String username, @RequestParam String password,
                          @Range(min = 0, max = 3)@RequestParam int loginType,
                          @RequestParam(required = false) String code, @RequestParam String fingerPrint) {
//        captchaService.verify(fingerPrint, code);
        User user = userService.getUserByUsernameOrPhone(username);

        if (user == null || !passwordEncoder.matches(password, user.getPassword()) || loginType != user.getLoginType()){
            throw new InvalidCredentialException();
        }

        userService.checkAccount(user);
        StpUtil.logoutByLoginId(user.getUserId());
        StpUtil.login(user.getUserId(), fingerPrint);
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
    @ApiImplicitParam(name = "fingerPrint", value = "设备或浏览器指纹", dataTypeClass = String.class)
    @GetMapping("/verifyCode")
    public String getVerifyCode(@RequestParam String fingerPrint) {
        return captchaService.getBase64(fingerPrint);
    }

    @ApiOperation(value = "登陆 获取短信验证码", notes = "手机号须已经注册")
    @ApiImplicitParam(name = "phoneNumber", value = "手机号码", dataTypeClass = String.class)
    @PostMapping("/getSmsCode")
    public Object getSMSCode(@RequestParam String phoneNumber) {
        if (!userService.exist(phoneNumber)){
            throw new AppException(ErrorCode.PHONE_NUMBER_NONE_EXIST);
        }
        smsService.sendMessage(phoneNumber, "login");
        return AjaxResult.SUCCESS();
    }

    @ApiOperation("登陆 短信验证码登陆")
    @PostMapping("/phoneLogin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber", value = "手机号", dataTypeClass = String.class),
            @ApiImplicitParam(name = "code", value = "验证码", dataTypeClass = String.class),
            @ApiImplicitParam(name = "loginType", value = "用户类型 0 学生、1 评委、2 院级管理员、3 校级管理员", dataTypeClass = Integer.class)
    })
    public Object doLogin(@Pattern (regexp = RegexPattern.PHONE_NUMBER)@RequestParam String phoneNumber, @RequestParam int loginType, @RequestParam String code) {

        smsService.verify(phoneNumber, code, "login");
        User user = userService.getUserByUsernameOrPhone(phoneNumber);

        if (user == null || loginType != user.getLoginType()){
            throw new InvalidCredentialException();
        }
        userService.checkAccount(user);
        StpUtil.logoutByLoginId(user.getUserId());
        StpUtil.login(user.getUserId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return AjaxResult.SUCCESS(Map.of("token_name", tokenInfo.getTokenName(),
                "token_value", tokenInfo.getTokenValue(), "login_device", tokenInfo.getLoginDevice()));
    }

    @ApiOperation(value = "注册step1 获取短信验证码", notes = "手机号须未注册")
    @ApiImplicitParam(name = "phoneNumber", value = "手机号码", dataTypeClass = String.class)
    @PostMapping("/register/getSmsCode")
    public Object getSMSCodeWhenRegister(@Pattern (regexp = RegexPattern.PHONE_NUMBER)@RequestParam String phoneNumber) {
        if (userService.exist(phoneNumber)){
            throw new AppException(ErrorCode.PHONE_NUMBER_USED);
        }
        smsService.sendMessage(phoneNumber, "register");
        return AjaxResult.SUCCESS();
    }

    @ApiOperation(value = "注册step2 校验短信验证码", notes = "校验成功会返回token，携带token访问注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber", value = "手机号", dataTypeClass = String.class),
            @ApiImplicitParam(name = "code", value = "验证码", dataTypeClass = String.class),
    })
    @PostMapping("/register/verifySmsCode")
    public Object verifySMSCodeWhenRegister(@Pattern (regexp = RegexPattern.PHONE_NUMBER)@RequestParam String phoneNumber, @RequestParam String code) {
        /*
         * 测试用代码
         */
        if (userService.exist(phoneNumber)){
            throw new AppException(ErrorCode.PHONE_NUMBER_USED);
        }

        long timeout = 300;
        smsService.verify(phoneNumber, code, "register");
        String token = SaTempUtil.createToken(phoneNumber, timeout);
        return AjaxResult.SUCCESS(Map.of("token", token, "time_out", timeout));
    }

    @ApiOperation(value = "注册step3 上传信息", notes = "需要带token访问该接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "验证码校验时返回的token", dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "密码", dataTypeClass = String.class),
            @ApiImplicitParam(name = "sex", value = "0男 1女", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "degree", value = "0本科 1硕士", dataTypeClass = Integer.class)
    })
    @PostMapping("/register/do")
    public Object register(
            @RequestParam String token, @RequestParam String password,
            @RequestParam String realName, @Range(min = 0, max = 1)@RequestParam int sex,
            @Range(min = 0, max = 1) @RequestParam int degree, @Pattern(regexp = RegexPattern.GRADE)@RequestParam String grade,
            @RequestParam int academyId, @RequestParam int majorId, @RequestParam String studentNumber) {
        String phoneNumber = SaTempUtil.parseToken(token, String.class);
        int userId = studentInfoService.insertStudent(password, realName, sex, degree, grade, academyId, majorId, phoneNumber, studentNumber);
        StpUtil.login(userId);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return AjaxResult.SUCCESS(Map.of("token_name", tokenInfo.getTokenName(),
                "token_value", tokenInfo.getTokenValue(), "login_device", tokenInfo.getLoginDevice()));
    }

    @ApiOperation(value = "忘记密码step1 获取短信验证码")
    @GetMapping("/forget/getSmsCode")
    public Object getSMSCodeWhenForget(@Pattern(regexp = RegexPattern.PHONE_NUMBER)@RequestParam String phoneNumber) {
        if (!userService.exist(phoneNumber)){
            throw new AppException(ErrorCode.PHONE_NUMBER_NONE_EXIST);
        }
        smsService.sendMessage(phoneNumber, "forget");
        return AjaxResult.SUCCESS();
    }

    @ApiOperation(value = "忘记密码step2 校验短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber", value = "手机号", dataTypeClass = String.class),
            @ApiImplicitParam(name = "code", value = "验证码", dataTypeClass = String.class),
    })
    @PostMapping("/forget/verifySmsCode")
    public Object verifySMSCodeWhenForget(@Pattern (regexp = RegexPattern.PHONE_NUMBER)@RequestParam String phoneNumber, @RequestParam String code) {
        long timeout = 300;
        smsService.verify(phoneNumber, code, "forget");
        String token = SaTempUtil.createToken(phoneNumber, timeout);
        return AjaxResult.SUCCESS(Map.of("token", token, "time_out", timeout));
    }

    @ApiOperation(value = "重置密码", notes = "需要带token访问该接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "验证码校验时返回的token", dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "新密码", dataTypeClass = String.class),
    })
    @PostMapping("/reset/forget")
    public Object resetWhenForget(@RequestParam String token, @RequestParam String password) {
        String phoneNumber = SaTempUtil.parseToken(token, String.class);
        User user = userService.getUserByUsernameOrPhone(phoneNumber);
        userService.resetPassword(user, password);
        return AjaxResult.SUCCESS();
    }

    @SaCheckLogin
    @ApiOperation(value = "修改密码 获取短信验证码")
    @GetMapping("/reset/getSmsCode")
    public Object getSMSCodeWhenReset() {
        User user = userService.getLoginUser();
        smsService.sendMessage(user.getPhoneNumber(), "reset");
        return AjaxResult.SUCCESS();
    }

//    @SaCheckLogin
//    @ApiOperation(value = "修改密码step2 校验短信验证码")
//    @ApiImplicitParam(name = "code", value = "验证码", dataTypeClass = String.class)
//    @PostMapping("/reset/verifySmsCode")
//    public Object verifySMSCodeWhenReset(@RequestParam String code) {
//        User user = userService.getLoginUser();
//        long timeout = 300;
//        smsService.verify(user.getPhoneNumber(), code, "reset");
//        String token = SaTempUtil.createToken(user.getPhoneNumber(), timeout);
//        return AjaxResult.SUCCESS(Map.of("token", token, "time_out", timeout));
//    }

    @SaCheckLogin
    @ApiOperation(value = "通过手机号修改密码", notes = "非忘记密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "验证码", dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "新密码", dataTypeClass = String.class)
    })
    @PostMapping("/reset/do")
    public Object resetPassword(@RequestParam String code, @RequestParam String password) {
        User user = userService.getLoginUser();
        String phoneNumber = user.getPhoneNumber();
        smsService.verify(phoneNumber, code, "reset");
        userService.resetPassword(user, password);
        StpUtil.logout();
        return AjaxResult.SUCCESS("密码修改成功");
    }

    @SaCheckLogin
    @ApiOperation(value = "通过旧密码重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", dataTypeClass = String.class),
            @ApiImplicitParam(name = "newPassword", value = "新密码", dataTypeClass = String.class)
    })
    @PostMapping("/reset/password")
    public Object resetByPassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        User user = userService.getLoginUser();
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new InvalidCredentialException();
        }
        userService.resetPassword(user, newPassword);
        StpUtil.logout();
        return AjaxResult.SUCCESS();
    }

    @GetMapping("/rememberMe")
    @ApiOperation(value = "自动登陆接口")
    @SaCheckLogin
    public Object rememberMe() {
        int userId = StpUtil.getLoginIdAsInt();
        int loginType = userService.getLoginType(userId);
        return AjaxResult.SUCCESS(Map.of("userId", userId, "loginType", loginType));
    }
}
