package org.whatever.aha.zjut.base.constant;

import lombok.Getter;

/**
 * 错误类型枚举类
 * @author Baby_mo
 */
@Getter
public enum ErrorCode {

    ILLEGAL_REQUEST(250, "非法请求"),
    INVALID_CREDENTIAL(1000, "身份认证失败"),
    NOT_PERMITTED(1001, "无此权限"),
    ACCOUNT_BLOCKED(1002, "账号被封禁"),
    INVALID_VERIFYING_CODE(2001, "验证码错误"),
    EXPIRED_VERIFYING_CODE(2002, "验证码过期"),
    UNMATCHED_VERIFYING_CODE(2003, "验证码错误"),
    GENERATE_FAILED(2004, "验证码生成失败"),
    MESSAGE_ALREADY_SENT(3001, "短信验证码已发送，请稍后再试"),
    MESSAGE_FAILED_TO_SEND(3002, "短信验证码发送失败"),
    MESSAGE_NOT_SENT(3003, "未发送短信验证码"),
    INVALID_MESSAGE_CODE(3004, "短信验证码错误"),
    PHONE_NUMBER_NONE_EXIST(3006, "手机号未绑定或账号不存在"),
    PHONE_NUMBER_USED(3007, "手机号已被注册"),
    INVALID_FORMAT(4000, "格式校验错误"),
    STUDENT_NUMBER_REGISTERED(4001, "学号已被注册");




    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
