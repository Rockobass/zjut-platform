package org.whatever.aha.zjut.base.handler;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.base.dto.ErrorDetail;
import org.whatever.aha.zjut.base.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
@Order(100)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResult<Object> handleGlobalException(Exception e, HttpServletRequest request) {
        ErrorDetail errorDetail = ErrorDetail.builder()
                .requestId(request.getAttribute("requestId").toString())
                .data(e.getMessage()).path(request.getRequestURI())
                .timestamp(Instant.now()).build();
        return AjaxResult.FAIL("全局异常", errorDetail);
    }

}
