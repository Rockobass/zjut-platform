package org.whatever.aha.zjut.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.whatever.aha.zjut.base.consts.ResponseCode;

@Data
@AllArgsConstructor
public class AjaxResult<T> {
    private Integer code;
    private String message;
    private T data;

    public static <N> AjaxResult<N> OK(N data) {
        return new AjaxResult<>(ResponseCode.OK.getCode(), "success", data);
    }

    public static <N> AjaxResult<N> FAIL(N data) { return new AjaxResult<>(ResponseCode.FAIL.getCode(), "fail", data); }
}
