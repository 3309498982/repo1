package com.example.rbac.entity.vo;

/**
 * <b>类名称：</b>Result<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>刘亚州<br/>
 * <b>修改人：</b>刘亚州<br/>
 * <b>修改时间：</b>2020/7/14 10:06<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0<br />
 */
public class Result<T> {

    private String code;
    private String message;
    private T data;

    public Result() {}

    public Result(T data) {
        this.code = "200";
        this.message = "OK";
        this.data = data;
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
