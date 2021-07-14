package org.whatever.aha.zjut.base.constant;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_CREDENTIAL(1000, "身份认证失败"),
    NOT_PERMITTED(1001, "无此权限"),
    ACCOUNT_BLOCKED(1002, "账号被封禁");

    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
