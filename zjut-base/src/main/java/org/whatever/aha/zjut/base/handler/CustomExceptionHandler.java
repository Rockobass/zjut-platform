package org.whatever.aha.zjut.base.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.whatever.aha.zjut.base.config.ProfileConfig;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.base.dto.ErrorDetail;
import org.whatever.aha.zjut.base.exception.AppException;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

/**
 * 处理AppException及其子类，最先执行
 * @author Baby_mo
 */
@ControllerAdvice
@Slf4j
@Order(1)
@RequiredArgsConstructor
public class CustomExceptionHandler {

    final ProfileConfig profileConfig;

    @ExceptionHandler(AppException.class)
    @ResponseBody
    public AjaxResult<Object> handleAppException(AppException e, HttpServletRequest request) {
        ErrorDetail errorDetail;
        if (profileConfig.getActiveProfile().equals("dev")) {
            errorDetail = ErrorDetail.builder().code(e.getCode().getCode())
                    .message(e.getCode().getMessage())
                    .requestId(request.getAttribute("requestId").toString())
                    .data(e.getData()).path(request.getRequestURI())
                    .timestamp(Instant.now()).build();
            log.error(errorDetail.toString());
        } else {
            errorDetail = ErrorDetail.builder().code(e.getCode().getCode())
                    .message(e.getCode().getMessage())
                    .timestamp(Instant.now()).build();
        }

        return AjaxResult.FAIL(errorDetail);
    }

}
