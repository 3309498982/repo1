package com.example.demo.entity.vo;

import lombok.Data;

/**
 * <b>类名称：<b/>Result <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月21日 16:07 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@Data
public class CommonResult<T> {

    private String code;
    private String msg;
    private T data;

    protected CommonResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回的结果
     * @param data 获取的数据
     */
    public static <T> CommonResult successResponse(T data) {
        return new CommonResult("200", "OK", data);
    }

    /**
     * @param msg 自定义提示信息
     * @param data    获取的数据
     */
    public static <T> CommonResult successResponse(String msg, T data) {
        return new CommonResult("200", msg, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult failedResponse() {
        return new CommonResult("404", "FAILED", null);
    }

    /**
     * 失败返回结果
     *
     * @param msg 自定义信息
     */
    public static <T> CommonResult failedResponse(String msg) {
        return new CommonResult("404", msg, null);
    }

}
