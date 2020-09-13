package com.css.rookie.exception;

import com.css.rookie.common.CommonConstants;
import com.css.rookie.common.CommonResponse;

/**
 * <b>类名称：<b/>DBEXception <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年8月14日 9:56 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public class DBException extends Exception {

    private static final long serialVersionUID = 5074581026654341708L;

    public DBException() {
    }

    public DBException(String message) {
        super(message);
    }

    public CommonResponse setResponseErrCodeAndErrMsg(CommonResponse response) {
        response.setCode(CommonConstants.EXCEPTION_SQL_CODE);
        response.setMessage(CommonConstants.EXCEPTION_SQL_MSG);
        return response;
    }
}
