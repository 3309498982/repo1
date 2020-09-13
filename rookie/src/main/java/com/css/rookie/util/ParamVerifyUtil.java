package com.css.rookie.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: ParamVerifyUtil
 * @Description: 参数校验工具类
 * @author
 * @date 2015年9月24日 下午5:18:03
 */
public class ParamVerifyUtil {

	/**
	 * 
	 * <b>方法名称：</b>isEmpty(判断参数是否为空)<br/>
	 * <b>方法描述：</b>(判断参数是否为空)<br/>
	 * @param strVal
	 * @return 
	 * boolean
	 * @author 许峰
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isEmpty(String strVal) {
		return (strVal == null || "".equals(strVal)) ? true : false;
	}
	
	public static boolean isNotEmpty(String strVal) {
		return (strVal == null || "".equals(strVal)) ? false : true;
	}
	
	public static boolean isNotBlank(String strVal) {
		return StringUtils.isBlank(strVal) ? false : true;
	}
	
	/**
	 * 
	 * <b>方法名称：</b>isEmpty(判断实体对象是否为空)<br/>
	 * <b>方法描述：</b>(判断实体对象是否为空)<br/>
	 * @param <T>
	 * @param object
	 * @return 
	 * boolean
	 * @author 冯子晨
	 * @exception 
	 * @since  1.0.0
	 */
	public static <T> boolean isObjectEmpty(T object) {
		return (object==null)?false:true;
	}
	/**
	 * 
	 * <b>方法名称：</b>isEmpty(判断参数是否为空)<br/>
	 * <b>方法描述：</b>(判断参数是否为空)<br/>
	 * @param params
	 * @return 
	 * boolean
	 * @author 许峰
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isEmpty(String... params) {
		if (params == null || params.length <= 0) {
			return true;
		}
		for (String param : params) {
			if (isEmpty(param))
				return true;
		}
		return false;
	}

	/**
	 * 
	 * <b>方法名称：</b>isBlank(判断参数是否为空或空白串)<br/>
	 * <b>方法描述：</b>(判断参数是否为空或空白串)<br/>
	 * @param params
	 * @return 
	 * boolean
	 * @author 许峰
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isBlank(String... params) {
		if (params == null || params.length <= 0) {
			return true;
		}
		for (String param : params) {
			if (StringUtils.isBlank(param)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * <b>方法名称：</b>isOverLength(校验字符串是否超过length长度限制 )<br/>
	 * <b>方法描述：</b>(校验字符串是否超过length长度限制 )<br/>
	 * @param str
	 * @param length
	 * @return 
	 * boolean
	 * @author 许峰
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isOverLength(String str, int length) {
		if (str == null || "".equals(str)) {
			return false;
		}
		return str.length() > length;
	}

	/**
	 * 
	 * <b>方法名称：</b>isNumeric(判断字符串是否是数字)<br/>
	 * <b>方法描述：</b>(判断字符串是否是数字)<br/>
	 * @param str
	 * @return 
	 * boolean
	 * @author 许峰
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
}
