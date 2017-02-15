package com.steelIndustry.framework.suport;

public enum Operators{
	/**
	 * 等于
	 */
	EQ,
	/**
	 * 不等于
	 */
	NOTEQ,
	/**
	 * 模糊匹配
	 */
	LIKE,
	/**
	 * 排除模糊匹配
	 */
	NOTLIKE,
	/**
	 * 后辍匹配
	 */
	LLIKE,
	/**
	 * 前辍匹配
	 */
	RLIKE,
	/**
	 * 大于
	 */
	GT,
	/**
	 * 小于
	 */
	LT,
	/**
	 * 大于等于
	 */
	GTE, 
	/**
	 * 小于等于
	 */
	LTE,
	/**
	 * 空
	 */
	NULL,
	/**
	 * 非空
	 */
	NOTNULL,
	/**
	 * 范围
	 */
	BETWEEN
}