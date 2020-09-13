package com.css.rookie.util;

import com.css.rookie.common.CommonConstants;
import com.css.rookie.common.CommonResponse;
import org.apache.commons.lang3.StringUtils;

public class CreateResponseUtil {

	public CreateResponseUtil() {}

	private static CommonResponse createResponse(String code, Object data, String message) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCode(code);
		commonResponse.setData(data);
		commonResponse.setMessage(message);
		return commonResponse;
	}

	public static CommonResponse createSuccessResponse() {
		return createResponse(CommonConstants.SUCCESS_CODE, StringUtils.EMPTY, CommonConstants.SUCCESS_MSG);
	}

	public static CommonResponse createSuccessResponse(Object data) {
		return createResponse(CommonConstants.SUCCESS_CODE, data, CommonConstants.SUCCESS_MSG);
	}

	public static CommonResponse createErrorResponse(String code, String message) {
		return !StringUtils.isEmpty(code) && !StringUtils.isEmpty(message) ? createResponse(code, StringUtils.EMPTY, message) : createResponse(CommonConstants.FAILED_CODE, StringUtils.EMPTY, CommonConstants.FALIED_MSG);
	}
}