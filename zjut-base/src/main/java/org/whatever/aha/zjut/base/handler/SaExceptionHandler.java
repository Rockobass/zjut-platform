package org.whatever.aha.zjut.base.handler;

import cn.dev33.satoken.exception.*;
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
    public Object handleSaTokenException(SaTokenException e, HttpServletRequest request) {
        ErrorDetail errorDetail;
        Integer code = null;
        if (e instanceof NotLoginException) {
            code = 10001;
        } else if (e instanceof NotRoleException || e instanceof NotPermissionException) {
            code = 10002;
        } else if (e instanceof DisableLoginException) {
            code = 10003;
        }
        if (profileConfig.isDev()) {
            errorDetail = ErrorDetail.builder()
                    .code(code)
                    .requestId(request.getAttribute("requestId").toString())
                    .message(e.getMessage())
                    .path(request.getRequestURI())
                    .timestamp(Instant.now()).build();
            log.error(errorDetail.toString());
        } else {
            errorDetail = ErrorDetail.builder()
                    .code(code)
                    .message(e.getMessage())
                    .timestamp(Instant.now()).build();
        }
        return errorDetail;
    }
}
