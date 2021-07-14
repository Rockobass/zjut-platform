package org.whatever.aha.zjut.base.handler;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Order(1)
public class CustomExceptionHandler {

    @ExceptionHandler(AppException.class)
    @ResponseBody
    public AjaxResult<Object> handleAppException(AppException e, HttpServletRequest request) {
        ErrorDetail errorDetail = ErrorDetail.builder().code(e.getCode())
                .requestId(request.getAttribute("requestId").toString())
                .data(e.getData()).path(request.getRequestURI())
                .timestamp(Instant.now()).build();
        log.error(errorDetail.toString());
        return AjaxResult.FAIL("发生错误", errorDetail);
    }

}
