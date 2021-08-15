package org.whatever.aha.zjut.base.handler;

import cn.dev33.satoken.exception.SaTokenException;
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

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
/**
 * 处理token有关的异常，第二个执行
 * @author Baby_mo
 */
@ControllerAdvice
@Order(90)
@Slf4j
@RequiredArgsConstructor
public class SaExceptionHandler {

    final ProfileConfig profileConfig;

    @ExceptionHandler(SaTokenException.class)
    @ResponseBody
    public AjaxResult<Object> handleSaTokenException(SaTokenException e, HttpServletRequest request) {
        ErrorDetail errorDetail;
        if (profileConfig.isDev()) {
            errorDetail = ErrorDetail.builder()
                    .requestId(request.getAttribute("requestId").toString())
                    .data(e.getMessage()).path(request.getRequestURI())
                    .timestamp(Instant.now()).build();
            log.error(errorDetail.toString());
        } else {
            errorDetail = ErrorDetail.builder()
                    .timestamp(Instant.now()).build();
        }

        return AjaxResult.FAIL("token验证失败", errorDetail);
    }
}
