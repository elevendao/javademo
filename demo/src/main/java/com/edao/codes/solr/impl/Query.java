/**
 * 版权所有：美创科技
 * 项目名称:capaa-web-b-2.6.0.0
 * 创建者: liushuai
 * 创建日期: 2014-1-16
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-1-16
 */
package com.edao.codes.solr.impl;

import java.io.Serializable;

import com.edao.codes.solr.constant.QueryType;

/**
 * @author liushuai
 *
 */
public class Query implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3921613435648593798L;
	
	// 主查询条件
	private String query="";
	// 翻译后的查询条件
	private String queryTrans = "*:*";
	// 数据源为访问审计时，登录信息查询条件
	private String lgAddition = "";
	// 翻译后的登录信息查询条件
	private String lgAdditionTrans = "*:*";
	private String sqlAddition = "";
	private String sqlAdditionTrans = "*:*";
	private boolean isTrans = true;
	
	private String queryChs="";
	private String lgAdditionChs = "";
	
	private QueryType queryType;
	
	public String getQueryChs() {
		return queryChs;
	}

	public void setQueryChs(String queryChs) {
		this.queryChs = queryChs;
	}
	
	public String getLgAdditionChs() {
		return lgAdditionChs;
	}
	
	public void setLgAdditionChs(String lgAdditionChs) {
		this.lgAdditionChs = lgAdditionChs;
	}
	
	/**
	 * 
	 * @param isTrans 查询条件是否已经翻译
	 * @param query 查询条件
	 */
	public Query(boolean isTrans, String query) {
		this(isTrans, null, query, "", "");
	}
	
	/**
	 * 
	 * @param isTrans 是否需要对查询条件进行翻译 true--翻译  false--不翻译
	 * @param queryType 查询类型
	 * @param query 查询条件
	 */
	public Query(boolean isTrans, QueryType queryType, String query) {
		this(isTrans, queryType, query, "", "");
	}
	
	/**
	 * 
	 * @param isTrans 是否需要对查询条件进行翻译 true--翻译  false--不翻译
	 * @param queryType 查询类型
	 * @param query 查询条件
	 * @param lgAdditon 登录信息查询条件
	 */
	public Query(boolean isTrans, QueryType queryType, String query, String lgAdditon) {
		this(isTrans, queryType, query, lgAdditon, "");
	}
	
	/**
	 * 
	 * @param isTrans 是否需要对查询条件进行翻译 true--翻译  false--不翻译
	 * @param queryType 查询类型
	 * @param query 查询条件
	 * @param lgAdditon 登录信息查询条件
	 * @param sqlAddition sql条件
	 */
	public Query(boolean isTrans, QueryType queryType, String query, String lgAdditon, String sqlAddition) {
		this.queryType = queryType;
		this.isTrans = isTrans;
		if (isTrans) {
			this.query = query == null ? "" : query;
			this.lgAddition = lgAdditon == null ? "" : lgAdditon;
			this.sqlAddition = sqlAddition == null ? "" : sqlAddition;
			this.queryChs = this.query;
			this.lgAdditionChs = this.lgAddition;
		} else {
			this.queryTrans = query == null || "".equals(query) ? "*:*" :query;
			this.lgAdditionTrans = lgAdditon == null || "".equals(lgAdditon) ? "*:*" :lgAdditon;
			this.sqlAdditionTrans = sqlAddition == null || "".equals(sqlAddition) ? "*:*" :sqlAddition;
		}
	}
	
	/**
	 * 返回主查询条件
	 * @return 主查询条件
	 */
	public String getQuery() {
		return query;
	}
	
	/**
	 * 设置主查询条件
	 * @param query 主查询条件
	 */
	public void setQuery(String query) {
		if (query != null){
			this.query = query;
		}
	}
	
	/**
	 * 返回登录信息查询条件
	 * @return 登录信息查询条件
	 */
	public String getLgAddition() {
		return lgAddition;
	}
	
	/**
	 * 设置登录信息查询条件
	 * @param lgAddition 登录信息查询条件
	 */
	public void setLgAddition(String lgAddition) {
		if (lgAddition != null) {
			this.lgAddition = lgAddition;
		}
	}
	
	/**
	 * 返回主查询条件 -- 翻译
	 * @return 主查询条件 -- 翻译
	 */
	public String getQueryTrans() {
		return queryTrans;
	}

	/**
	 * 设置主查询条件 -- 翻译
	 * @param queryTrans 主查询条件 -- 翻译
	 */
	public void setQueryTrans(String queryTrans) {
		if (queryTrans != null) {
			this.queryTrans = queryTrans;
		}
	}

	/**
	 * 返回登录信息查询条件 -- 翻译
	 * @return 登录信息查询条件 -- 翻译
	 */
	public String getLgAdditionTrans() {
		return lgAdditionTrans;
	}

	/**
	 * 设置登录信息查询条件 -- 翻译
	 * @param lgAdditionTrans 登录信息查询条件 -- 翻译
	 */
	public void setLgAdditionTrans(String lgAdditionTrans) {
		if (lgAdditionTrans != null) {
			this.lgAdditionTrans = lgAdditionTrans;
		}
	}

	/**
	 * @return SQL查询条件
	 */
	public String getSqlAddition() {
		return sqlAddition;
	}

	/**
	 * 设置SQL查询条件
	 * @param sqlAddition SQL查询条件
	 */
	public void setSqlAddition(String sqlAddition) {
		this.sqlAddition = sqlAddition;
	}

	/**
	 * @return SQL查询条件 -- 翻译
	 */
	public String getSqlAdditionTrans() {
		return sqlAdditionTrans;
	}

	/**
	 * 设置SQL查询条件 -- 翻译
	 * @param sqlAdditionTrans SQL查询条件 -- 翻译
	 */
	public void setSqlAdditionTrans(String sqlAdditionTrans) {
		if (sqlAdditionTrans != null){
			this.sqlAdditionTrans = sqlAdditionTrans;
		}
	}

	@Override
	public int hashCode() {
		int h1 = query != null ? query.hashCode() : 0;
		int h2 = sqlAddition != null ? sqlAddition.hashCode() : 0;
		int h3 = queryTrans != null ? queryTrans.hashCode() : 0;
		int h4 = sqlAdditionTrans != null ? sqlAdditionTrans.hashCode() : 0;
		return super.hashCode() ^ h1 ^ h2 ^ h3 ^ h4;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Query)) {
			return false;
		}
		
		Query o = (Query) obj;
		
		boolean b1 = this.query.equals(o.query);
		boolean b11 = this.queryTrans.equals(o.queryTrans);
		boolean b2 = this.lgAddition.equals(o.lgAddition);
		boolean b21 = this.lgAdditionTrans.equals(o.lgAdditionTrans);
		boolean b3 = this.sqlAddition.equals(o.sqlAddition);
		boolean b31 = this.sqlAdditionTrans.equals(o.sqlAdditionTrans);
		
		return isTrans ? b1 && b2 && b3 : b11 && b21 && b31;
	}
	
	@Override
	public String toString() {
		return "_query:" + this.query + ", _queryTrans:" + this.queryTrans
				+ ", _lgAddition:" + this.lgAddition + ", lgAdditionTrans:"
				+ this.lgAdditionTrans;
	}

	/**
	 * 返回查询类型
	 * @return QueryType
	 */
	public QueryType getQueryType() {
		return queryType;
	}

	/**
	 * 设置查询类型
	 * @param queryType 查询类型
	 */
	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	/**
	 * 是否需要翻译查询条件
	 * @return true -- 需要翻译  false -- 不需要翻译
	 */
	public boolean isTrans() {
		return isTrans;
	}
	
}
