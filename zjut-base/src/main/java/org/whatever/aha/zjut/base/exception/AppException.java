package org.whatever.aha.zjut.base.exception;

import lombok.Getter;
import org.whatever.aha.zjut.base.constant.ErrorCode;

@Getter
public abstract class AppException extends RuntimeException{
    private final ErrorCode code;
    private final Object data;

    protected AppException(ErrorCode code, Object data) {
        this.code = code;
        this.data = data;
    }
}
