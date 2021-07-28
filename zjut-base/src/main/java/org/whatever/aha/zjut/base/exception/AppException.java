package org.whatever.aha.zjut.base.exception;

import lombok.Getter;
import org.whatever.aha.zjut.base.constant.ErrorCode;

/**
 * 自定义异常
 */
@Getter
public class AppException extends RuntimeException{
    private final ErrorCode code;
    private final Object data;

    public AppException(ErrorCode code) {
        this.code = code;
        this.data = null;
    }

    public AppException(ErrorCode code, Object data) {
        this.code = code;
        this.data = data;
    }
}
