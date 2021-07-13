package org.whatever.aha.zjut.base.consts;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_CREDENTIAL(1000, "身份认证失败");

    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
