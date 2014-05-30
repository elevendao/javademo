/**
 * 版权所有：美创科技
 * 项目名称:capaa-web2.6
 * 创建者: liushuai
 * 创建日期: 2013-5-22
 * 文件说明: 搜索常用参数
 * 最近修改者：liushuai
 * 最近修改日期：2013-5-22
 */
package com.edao.codes.solr.constant;

import java.util.HashMap;


/**
 * @author liushuai
 */
public final class CommonParams {

	private CommonParams() {
	}

	/** 查询条件 */
	public static final String	Q							= "q";
	/** default sort order */
	public static final String	SORT						= "sort";
	/** sort order */
	public static final String	SORTS						= "sorts";
	/** 过滤查询条件 */
	public static final String	FQ							= "fq";
	/** zero based offset of matching documents to retrieve */
	public static final String	START						= "start";
	/** number of documents to return starting at "start" */
	public static final String	ROWS						= "rows";
	/** 分页页码 */
	public static final String	PAGE						= "page";
	/** 审阅类型 */
	public static final String	REVIEWTYPE					= "reviewtype";
	/** 查询时间范围 */
	public static final String	TIMERANGE					= "timerange";
	private static final String	TRANS						= "trans";
	private static final String	TRANS_SUFFIX				= "." + TRANS;
	/** 翻译后的查询条件 */
	public static final String	Q_TRANS						= "q" + TRANS_SUFFIX;
	/** 翻译后的过滤查询条件 */
	public static final String	FQ_TRANS					= "fq" + TRANS_SUFFIX;
	/** sqltext查询条件 */
	public static final String	SQLTEXT						= "sqltext";
	/** 翻译后的sqltext查询条件 */
	public static final String	SQLTEXT_TRANS				= "sqltext" + TRANS_SUFFIX;
	/** 最后一次查询条件 */
	public static final String	LASTQUERY					= "lastquery";
	/** 结果查询条件 */
	public static final String	RESULTQUERY					= "resultquery";
	/** 结果查询翻译条件 */
	public static final String	RESULTQUERY_TRANS			= "resultquery" + TRANS_SUFFIX;
	/** 访问审计登录信息查询条件 */
	public static final String	LOGONADDITION			    = "logonaddtion" + TRANS_SUFFIX;
	/** 访问审计登录信息翻译查询条件 */
	public static final String	LOGONADDITION_TRANS		    = "logonaddtion" + TRANS_SUFFIX;
	
	
	/** 数据源 */
	public static final String	DS							= "ds";
	/** solr core name */
	public static final String  CORE_NAME                   = "core_name";
	
	/** 审计类型：登录事件 */
	public static final String	AUDIT_TYPE_LOGON			= "0";
	/** 审计类型：访问事件 */
	public static final String	AUDIT_TYPE_ACCESS			= "1";
	/** 审计类型：证书登录事件 */
	public static final String	AUDIT_TYPE_CERT				= "2";
	/** 审计类型：安全攻击事件 */
	public static final String	AUDIT_TYPE_ATTACK			= "3";
	/** 审计类型：中间件事件 */
	public static final String	AUDIT_TYPE_MID			= "4";
	
	/** 查询类型 */
	public static final String	QUERYTYPE					= "querytype";
	
	/** 审阅类型：全部 */
	public static final String	REVIEW_TYPE_ALL				= "0";
	/** 审阅类型：未审阅 */
	public static final String	REVIEW_TYPE_NOT				= "1";
	/** 审阅类型：已审阅 */
	public static final String	REVIEW_TYPE_ALREADY			= "2";
	/** 翻译开关 */
	public static final String	SWITCH						= "switch";
	/** 开关前缀 */
	private static final String	SWITCH_PREFIX				= SWITCH + ".";
	/** IP地址翻译开关 */
	public static final String	SWITCH_IP					= SWITCH_PREFIX + "ip";
	/** APPUSER翻译开关 */
	public static final String	SWITCH_APPUSER				= SWITCH_PREFIX + "appuser";
	/** 查询条件翻译开关 */
	public static final String	SWITCH_QUERY				= SWITCH_PREFIX + "query";
	/** 结果查询SQL语句 */
	public static final String	RESULTSQLQUERY				= "resultsqlquery";
	/** 结果查询SQL语句 -- 翻译 */
	public static final String	RESULTSQLQUERY_TRANS		= "resultsqlquery" + TRANS_SUFFIX;
	/** 结果查询开关 */
	public static final String	SWITCH_RESULTQUERY			= SWITCH_PREFIX + "resultquery";
	/** 最后一次SQL查询条件 */
	public static final String	LASTSQLQUERY				= "lastsqlquery";
	/** 访问审计填充SQL语句开关 */
	public static final String	SWITCH_FILL_SQLTEXT	        = SWITCH_PREFIX + "fill_sqltext";
	/** 过滤查询条件翻译开关 */
	public static final String	SWITCH_FILTERQUERY	        = SWITCH_PREFIX + "filterquery";
	/** 是否对结果排序开关 */
	public static final String	SWITCH_SORT	                = SWITCH_PREFIX + "sort";
	
	
	/** JJTREE 结束符 */
	public static final String	JJTREE_EOF					= ";";
	
	/** solr查询字段类型：时间 */
	public static final String	FILED_TYPE_DATE				= "date";
	/** solr查询字段类型：字符串*/
	public static final String	FILED_TYPE_STRING			= "string";
	/** solr查询字段类型：SQL语句 */
	public static final String	FILED_TYPE_SQLTEXT			= "sqltext";
	
	/**
	 * 规则类型 0:搜索
	 */
	public static final Integer	ISSUE_TYPE_SEARCH			= 0;
	/**
	 * 规则类型 1：报表
	 */
	public static final Integer	ISSUE_TYPE_REPORT			= 1;
	/**
	 * 规则类型 2：告警
	 */
	public static final Integer	ISSUE_TYPE_ALERT			= 2;
	/**
	 * 规则类型 3:数据丢弃规则
	 */
	public static final Integer	ISSUE_TYPE_DROP_IN			= 3;
	/**
	 * 规则类型 4:数据入库归档规则
	 */
	public static final Integer	ISSUE_TYPE_ARCHIVE_IN		= 4;
	/**
	 * 规则类型 5:告警数据入库规则
	 */
	public static final Integer	ISSUE_TYPE_ALERT_IN			= 5;
	
	public static HashMap<String, String> accessFieldMap = new HashMap<String, String>(16);
	static {
		accessFieldMap.put("cmdtype", "cmdtype");
		accessFieldMap.put("rule_name", "rule_name");
		accessFieldMap.put("action_level", "combined_level");
		accessFieldMap.put("appuser", "terminal_info");
	}
	
}
