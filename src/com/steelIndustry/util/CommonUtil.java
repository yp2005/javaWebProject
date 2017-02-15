package com.steelIndustry.util;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

	public static final String DATAFORMAT = "yyyy-MM-dd";
	public static final String DATAFORMAT_YEAR = "yyyy";
	public static final String DATAFORMAT_MONTH = "yyyy-MM";
	public static final String DATAFORMAT_HH = "yyyy-MM-dd HH";
	public static final String DATAFORMAT_MM = "yyyy-MM-dd HH:mm";
	public static final String DATAFORMAT_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 判断对象是否空
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object) {
		boolean isEmpty = false;
		if (object == null || "".equals(object)) {
			isEmpty = true;
		} else if (object instanceof Collection) {
			Collection<?> collection = (Collection<?>) object;
			isEmpty = collection.isEmpty();
		} else if (object instanceof Map) {
			Map<?, ?> map = (Map<?, ?>) object;
			isEmpty = map.isEmpty();
		} else if (object.getClass().isArray()) {
			Object[] objects = (Object[]) object;
			isEmpty = !(objects.length > 0);
		}
		return isEmpty;
	}

	/**
	 * 
	 * TODO 把Obj类型转换为String
	 * @author liuyulong
	 * @date   2016年12月15日-上午11:51:04
	 *
	 * @param object
	 * @return
	 */
	public static String convertObjToStr(Object object) {
		if (!isEmpty(object)) {
			return object.toString().trim();
		} else {
			return "";
		}
	}

	/**
	 * 
	 * TODO 把Obj类型转换为Integer
	 * @author liuyulong
	 * @date   2016年12月15日-上午11:51:40
	 *
	 * @param object
	 * @return
	 */
	public static Integer convertObjToInteger(Object object) {
		if (!isEmpty(object)) {
			return Integer.parseInt(String.valueOf(object).trim());
		} else {
			return 0;
		}
	}
	
	
	/**
	 * 
	 * TODO 把Obj类型转换为Float
	 * @author liuyulong
	 * @date   2016年12月15日-上午11:51:40
	 *
	 * @param object
	 * @return
	 */
	public static Float convertObjToFloat(Object object) {
		if (!isEmpty(object)) {
			return Float.parseFloat(String.valueOf(object).trim());
		} else {
			return 0f;
		}
	}

	/**
	 * 判断对象是否非空
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isNotEmpty(Object object) {
		return !isEmpty(object);
	}

	/**
	 * 获取 request中接收的参数 返回map数组 TODO What the method does
	 * 
	 * @author YuLong
	 * @date 2016年12月2日-下午3:00:10
	 *
	 * @param request
	 * @return
	 */
	public static Map<String, String> getParameters(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String key = e.nextElement();
			String[] list = request.getParameterValues(key);
			if (list.length == 1)
				map.put(key, list[0]);
			else if (list.length > 1) {
				String value = null;
				for (int i = 0; i < list.length; i++) {
					if (i == 0)
						value = list[i];
					else
						value = value + ',' + list[i];
				}
				map.put(key, value);
			}
		}
		return map;
	}

	public static String stringArrayToStrin(String[] str) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			sb.append("'").append(str[i]).append("'").append(",");
		}
		return sb.toString().substring(0, sb.length() - 1);
	}

	/**
	 * 判断集合是否为空 TODO What the method does
	 * 
	 * @author YuLong
	 * @date 2016年12月2日-下午4:03:23
	 * @param list
	 * @return
	 */
	public static Boolean checkList(List<?> list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;

	}
	
	
	
}
