package org.whatever.aha.zjut.base.constant;

import lombok.Getter;

/**
 * @author GuiYi Yang
 */

@Getter
public enum ResponseCode {
    SUCCESS(200),
    FAIL(400),
    UN_AUTH(401),
    FORBIDDEN(403);

    private final Integer code;

    ResponseCode(Integer code) {
        this.code = code;
    }
}
