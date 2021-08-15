package org.whatever.aha.zjut.base.handler;

import lombok.RequiredArgsConstructor;
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
 * 未被其余handler处理，则最终进入该handler处理，处理Exception子类
 * @author Baby_mo
 */
@ControllerAdvice
@Order(1000)
@RequiredArgsConstructor
public class GlobalExceptionHandler {


    final ProfileConfig profileConfig;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResult<Object> handleGlobalException(Exception e, HttpServletRequest request) {
        if (profileConfig.isDev()) { // dev环境时返回的异常信息
            ErrorDetail errorDetail = ErrorDetail.builder()
                    .requestId(request.getAttribute("requestId").toString())
                    .data(e.getMessage()).path(request.getRequestURI())
                    .timestamp(Instant.now()).build();
            e.printStackTrace();
            return AjaxResult.FAIL("全局异常", errorDetail);
        } else { // 其他环境时返回的异常信息
            ErrorDetail errorDetail = ErrorDetail.builder()
                    .timestamp(Instant.now()).build();

            return AjaxResult.FAIL("请求失败", errorDetail);
        }

    }

}
