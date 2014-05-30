/**
 * 版权所有：美创科技
 * 项目名称:capaa-web-t
 * 创建者: liushuai
 * 创建日期: 2013-12-4
 * 文件说明: 数据源枚举
 * 最近修改者：liushuai
 * 最近修改日期：2013-12-4
 */
package com.edao.codes.solr.constant;

/**
 * @author liushuai
 *
 */
public enum DataSource {
	/** 首页审计 */
	HOMEPAGE(1000),
	/** 登陆审计 */
	LOGON(0),
	/** 数据源：访问审计 */
	ACCESS(1),
	/** 数据源：证书登陆活动审计 */
	CERT_ACTIVE(2),
	/** 数据源：证书登陆历史审计 */
	CERT_HISTORY(4),
	/** 数据源：假冒应用审计 */
	ATTACK(3),
	/** 数据源：V$SQL */
	SQL(5),
	/** 数据源：SQLTEXT语句 */
	SQLTXT(6),
	/** 数据源：访问审计 -- 活动 */
	ACCESS_ACTIVE(7),
	/** 数据源：登陆审计 -- 归档 */
	LOGON_ARCHIVE(8),
	/** 数据源：访问审计 -- 归档 */
	ACCESS_ARCHIVE(9),
	/** 数据源：安全攻击事件 -- 归档 */
	ATTACK_ARCHIVE(10),
	/** 数据源：证书登录事件 -- 归档 */
	CERT_ARCHIVE(11),
	/** 数据源：中间件登录事件 */
	MIDLOGON(12),
	/** 数据源：告警库 */
	ALERT(13),
	/** 入库使用,登录字段： */
	INTO_DB_LOGON(14),
	/** 入库使用,访问字段： */
	INTO_DB_ACCESS(15);
	
	private int value;
	
	private DataSource(int value) {
		this.value = value;
	}
	
	/**
	 * @return toString()
	 */
	public String toString() {
		return String.valueOf(value);
	}
	
	/**
	 * valueOf
	 * @param value dataSource value
	 * @return DataSource
	 */
	public static DataSource getEnum(int value) {
		for (DataSource a : values()) {
			if (value == a.value) {
				return a;
			}
		}
		throw new IllegalArgumentException("error value");
	}
	
	/**
	 * valueOf
	 * @param value dataSource value
	 * @return DataSource
	 */
	public static DataSource getEnum(String value) {
		int v;
		try {
			v = Integer.valueOf(value);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("error value");
		}
		return getEnum(v);
	}
}
