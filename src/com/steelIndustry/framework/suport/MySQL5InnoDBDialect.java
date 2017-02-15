package com.steelIndustry.framework.suport;


import org.hibernate.dialect.function.StandardSQLFunction;
/**
 * 方言扩展
 * TODO
 * @author wanglei
 * @date   2016年11月22日-下午3:22:33
 *
 */
public class MySQL5InnoDBDialect extends org.hibernate.dialect.MySQL5InnoDBDialect {

	public MySQL5InnoDBDialect() {
		super();
		registerFunction( "ifnull", new StandardSQLFunction( "ifnull"));
		registerFunction( "DATE_FORMAT", new StandardSQLFunction( "DATE_FORMAT"));
		registerFunction( "STR_TO_DATE", new StandardSQLFunction( "STR_TO_DATE"));
		registerFunction( "DATE_SUB", new StandardSQLFunction( "DATE_SUB"));
	}

}
