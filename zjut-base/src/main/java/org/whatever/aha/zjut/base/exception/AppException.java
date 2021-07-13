package org.whatever.aha.zjut.base.exception;

import lombok.Getter;
import org.whatever.aha.zjut.base.constant.ErrorCode;

import java.util.Map;

@Getter
public abstract class AppException extends RuntimeException{
    private final ErrorCode code;
    private final Map<String, Object> data;

    protected AppException(ErrorCode code, Map<String, Object> data) {
        this.code = code;
        this.data = data;
    }
}
