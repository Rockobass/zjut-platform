package org.whatever.aha.zjut.base.constant;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_CREDENTIAL(1000, "身份认证失败"),
    NOT_PERMITTED(1001, "无此权限"),
    ACCOUNT_BLOCKED(1002, "账号被封禁"),
    INVALID_VERIFYING_CODE(2001, "验证码错误"),
    EXPIRED_VERIFYING_CODE(2002, "验证码过期"),
    UNMATCHED_VERIFYING_CODE(2003, "验证码错误"),
    GENERATE_FAILED(2004, "验证码生成失败");
    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
