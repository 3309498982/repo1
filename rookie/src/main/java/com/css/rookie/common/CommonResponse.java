package com.css.rookie.common;

/**
 * <b>类名称：<b/>CommonResponse <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年8月13日 11:01 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public class CommonResponse {

    private String code;
    private String message;
    private Object data;

    public CommonResponse() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
