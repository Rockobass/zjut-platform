package org.whatever.aha.zjut.base.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.whatever.aha.zjut.base.config.ProfileConfig;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.base.dto.ErrorDetail;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.Instant;

/**
 * 处理参数校验相关异常
 *
 * @author Baby_mo
 */
@Order(10)
@ControllerAdvice
@RequiredArgsConstructor
public class ValidateExceptionHandler {
    final ProfileConfig profileConfig;

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public AjaxResult<Object> handleSaTokenException(ConstraintViolationException e, HttpServletRequest request) {
        ErrorDetail errorDetail;
        if (profileConfig.getActiveProfile().equals("dev")) {
            errorDetail = ErrorDetail.builder()
                    .requestId(request.getAttribute("requestId").toString())
                    .data(e.getMessage()).path(request.getRequestURI())
                    .timestamp(Instant.now()).build();
        } else {
            errorDetail = ErrorDetail.builder()
                    .timestamp(Instant.now()).build();
        }

        return AjaxResult.FAIL("格式校验错误", errorDetail);
    }
}
