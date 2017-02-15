package com.steelIndustry.framework.constant;

/**
 * 系统统一常量
 * 
 * @author wanglei
 *
 */
public interface Constant {

	// 数据库统一删除标识
	public static String DELETE_FLAG_NORMAL = "N"; // 正常
	public static String DELETE_FLAG_DELETE = "D"; // 已删除

	public static String METHOD_NAME_TYPE_SAVE = "1"; // 增加
	public static String METHOD_NAME_TYPE_UPDATE = "2";// 修改
	public static String METHOD_NAME_TYPE_DELETE = "3";// 删除

	public static String METHOD_EXE_STATUS_SUCCESS = "1";// 方法执行成功

	public static String METHOD_EXE_STATUS_FAIL = "0";// 方法执行失败

	// 权限管理导航URL
	public static final String REGISTRATION_URL = "/login";
	public static final String NO_AUTHORITY_URL = "/accessDenied";

	public static final String USER_INFO_SESSION = "KEY_USER_INFO_SESSION";
	public static final String DEFALUT_USER_PASSWORD = "111111";
	public static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";
	
	
	//级别根节点标示
	public static final String ROOT = "root";
	
	public static final String STATION_STATUS_NORMAL = "2";//端站状态 ：正常
	
	
	
	//高架源/地面源 	 
		//染源类别 	 
		//污染类型 	 
		//行业类别 
	
	public static final Integer DICTIONARYTYPE_GAOJIADIMIAN=5;//高架源//地面源 	 
	public static final Integer DICTIONARYTYPE_POLLUTIONTYPE_GAOJIAO=6;//高架源
	public static final Integer DICTIONARYTYPE_POLLUTIONTYPE_DIMIAN=7;///地面源 
	public static final Integer DICTIONARYTYPE_INDUSTRY_TYPE=8;//行业类别 
	public static final Integer DICTIONARYTYPE_POLLUTION_CATAGORY=10;//污染类型 	 
	
	public static final Integer DICTIONARYTYPE_SITE_CATAGORY=4;//站点类型
	public static final Integer DICTIONARYTYPE_EQUIPMENT_CATAGORY=11;//设备类型
	public static final Integer DICTIONARYTYPE_EQUIPMENT_STATUS=12;//设备状态
	public static final Integer DICTIONARYTYPE_DEVICE_STATUS=13;//传感器状态
	public static final Integer DICTIONARYTYPE_PRODUCT_TYPE=14;//终端类型
	public static final Integer DICTIONARYTYPE_INDICATORS_TYPE=9;//指标类型
	
	//用户
	public static final String SYSTEM = "system";
	
	/***********告警****************/
	//告警
	public static final String WARN_TYPE_DATA = "2"; //数据异常
	public static final int WARN_TYPE_DATA_VALUE = -1; //数据异常
	//告警数据
	public static final int EXCESS_TYPE_O = 1; //阈值
	public static final int EXCESS_TYPE_S = 2; //尖刺
	public static final int EXCESS_TYPE_R = 3; //相关点
	//告警间隔时间类型
	public static final int DATE_TYPE_S = 1; //分钟
	public static final int DATE_TYPE_H = 2; //小时
	public static final int DATE_TYPE_D = 3; //天
	/***********告警end****************/
}
