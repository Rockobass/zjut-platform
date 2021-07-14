package org.whatever.aha.zjut.base.handler;

import cn.dev33.satoken.exception.SaTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.base.dto.ErrorDetail;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
@Order(10)
@Slf4j
public class SaExceptionHandler {

    @ExceptionHandler(SaTokenException.class)
    @ResponseBody
    public AjaxResult<Object> handleSaTokenException(SaTokenException e, HttpServletRequest request) {
        ErrorDetail errorDetail = ErrorDetail.builder()
                .requestId(request.getAttribute("requestId").toString())
                .data(e.getMessage()).path(request.getRequestURI())
                .timestamp(Instant.now()).build();
        log.error(errorDetail.toString());
        return AjaxResult.FAIL("token验证失败", errorDetail);
    }
}
