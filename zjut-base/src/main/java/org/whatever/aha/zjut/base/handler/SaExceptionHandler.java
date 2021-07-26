package org.whatever.aha.zjut.base.handler;

import cn.dev33.satoken.exception.SaTokenException;
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

@ControllerAdvice
@Order(10)
@Slf4j
/*拜托帮我抽取一下if判断公共逻辑*/
public class SaExceptionHandler {

    @Autowired
    ProfileConfig profileConfig;

    @ExceptionHandler(SaTokenException.class)
    @ResponseBody
    public AjaxResult<Object> handleSaTokenException(SaTokenException e, HttpServletRequest request) {
        ErrorDetail errorDetail;
        if (profileConfig.getActiveProfile().equals("dev")) {
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
