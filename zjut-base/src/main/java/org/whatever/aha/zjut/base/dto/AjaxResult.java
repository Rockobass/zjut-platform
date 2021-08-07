package org.whatever.aha.zjut.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.whatever.aha.zjut.base.constant.ResponseCode;

/**
 * @author Baby_mo
 */
@Data
@AllArgsConstructor
public class AjaxResult<T> {
    private Integer code;
    private String message;
    private T data;

    public static <N> AjaxResult<N> SUCCESS() { return new AjaxResult<>(ResponseCode.SUCCESS.getCode(), "success",null); }

    public static <N> AjaxResult<N> SUCCESS(N data) { return new AjaxResult<>(ResponseCode.SUCCESS.getCode(), "success", data); }

    public static <N> AjaxResult<N> SUCCESS(String msg, N data) { return new AjaxResult<>(ResponseCode.SUCCESS.getCode(), msg, data); }

    public static <N> AjaxResult<N> FAIL() { return new AjaxResult<>(ResponseCode.FAIL.getCode(), "fail", null); }

    public static <N> AjaxResult<N> FAIL(N data) { return new AjaxResult<>(ResponseCode.FAIL.getCode(), "fail", data); }

    public static <N> AjaxResult<N> FAIL(String msg, N data) { return new AjaxResult<>(ResponseCode.FAIL.getCode(), msg, data); }
}
