package org.whatever.aha.zjut.base.consts;

import lombok.Getter;

@Getter
public enum ResponseCode {
    OK(200),
    FAIL(400),
    UN_AUTH(401),
    FORBIDDEN(403);

    private final Integer code;

    ResponseCode(Integer code) {
        this.code = code;
    }
}