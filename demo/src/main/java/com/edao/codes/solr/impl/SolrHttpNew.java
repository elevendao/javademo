/**
 * 版权所有：美创科技
 * 项目名称:capaa-web2.6
 * 创建者: liushuai
 * 创建日期: 2012-7-20
 * 文件说明: solr搜索工具类
 * 最近修改者：liushuai
 * 最近修改日期：2012-7-20
 */
package com.edao.codes.solr.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.edao.codes.solr.beans.AccessAudit;
import com.edao.codes.solr.constant.CommonParams;
import com.edao.codes.solr.constant.DataSource;
import com.edao.codes.solr.constant.QueryType;
import com.edao.codes.solr.util.TextHandler;

/**
 * @author liushuai
 */
public class SolrHttpNew {
	
	private static final Log LOG = LogFactory.getLog(SolrHttpNew.class);
	
	private long numFound = 0;
	SolrServer solrServer = null;
	private SolrConfig solrConfig;
	boolean isCommit = false;
	//String dataSource;
	DataSource ds;
	private AuditDataSource auditDs;
	
	/**
	 * 默认构造函数
	 */
	public SolrHttpNew() {
	}
	
	/**
	 * 使用SolrConfig初始化SolrHttp
	 * @param solrConfig   
	 */
	public SolrHttpNew(SolrConfig solrConfig) {
		setSolrConfig(solrConfig);
	}
	
	/**
	 * 设置SolrConfig
	 * @param solrConfig solr配置
	 */
	public void setSolrConfig(SolrConfig solrConfig) {
		this.solrConfig = solrConfig;
	}
	
	/**
	 * 返回SolrConfig
	 * @return SolrConfig
	 */
	public SolrConfig getSolrConfig() {
		return this.solrConfig;
	}
	
	private QueryResponse query(SolrQuery query) throws SolrServerException {
		QueryResponse rsp = null;
		try {
			rsp = solrServer.query(query, METHOD.POST); //查询
		} catch (SolrServerException e) {
			LOG.error(e + ", query : " + query);
			throw e;
		}
		
		return rsp;
	}
	
	/**
	 * 搜索主方法
	 * @param param 查询参数
	 * @return 查询结果
	 * @throws Exception 
	 */
	public SearchResult search(SearchParam param) throws Exception {
		SolrQuery query = initQuery(param);
		
		List<Object> list = null;
		SolrDocumentList docs = null;
		QueryResponse rsp = null;
		try {
			String dataSource = param.getDataSource();
			auditDs = solrConfig.getAuditDataSource(dataSource);
			solrServer = auditDs.getHttpSolrServer();
			rsp = query(query);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
		if (rsp != null) {
			docs = rsp.getResults();
			numFound = docs.getNumFound();
			Class beanClass = param.getClassType();
			if (beanClass == null) {
				throw new NullPointerException("未设置返回结果的Class类型.");
			}
			try {
				list = rsp.getBeans(beanClass);
			} catch (Exception e) {
				//LOG.error(e + ", solrUrl : " + solrUrl + ", param : " + param + ", query : " + query);
				throw e;
			}
		}
		
		SearchResult<Object> result = new SearchResult<Object>();
		result.setItems(list);
		result.setTotalCount((int) numFound);
		result.setSearchParam(param);
		
		return result;
	}
	
	/* 初始化查询条件 */
	private SolrQuery initQuery(SearchParam param) {
		SolrQuery solrQuery = new SolrQuery();
		
		DataSource dataSource = DataSource.getEnum(param.getDataSource());
		QueryType queryType = param.getQueryType() == null ? QueryType.SIMPLE
				: QueryType.getEnum(param.getQueryType());
		List<Query> queries = param.getQueries();
		StringBuffer qBuf = new StringBuffer(256);
		StringBuffer lBuf = new StringBuffer(256);
		qBuf.append("( ").append(queries.get(0).getQueryTrans()).append(" )");
		lBuf.append("( ").append(queries.get(0).getLgAdditionTrans()).append(" )");
		for (int i=1; i<queries.size(); i++) {
			Query q = queries.get(i);
			qBuf.append(" AND ( ").append(q.getQueryTrans()).append(" )");
			lBuf.append(" AND ( ").append(q.getLgAdditionTrans()).append(" )");
		}
		solrQuery.setQuery(qBuf.toString()); // 设置查询条件
		Class<?> clzz = param.getClassType();
		if ((dataSource == DataSource.HOMEPAGE && AccessAudit.class.equals(clzz))
				|| (dataSource == DataSource.ACCESS && queryType != QueryType.SIMPLE)) {
			// 查询访问审计时，扩展搜索和高级搜索需要join登录审计
			String fq = "{!join from=id to=sessid fromIndex="
					+ DataSource.LOGON + "}"
					+ lBuf.toString();
			solrQuery.addFilterQuery(fq);
		}
		
		solrQuery.setStart(param.getStartRow()); // 设置记录起始位置
		solrQuery.setRows(param.getRows()); // 设置返回记录数
		if (param.getSortSwitch()) {
			if (param.getDefaultSortField() != null && !"".equals(param.getDefaultSortField())) {
				solrQuery.setSortField(param.getDefaultSortField(), ORDER.desc); // 设置排序字段及排序方式
			}
			
			String[] sortFields = param.getSortFields();
			if (sortFields != null) {
				for (String field : sortFields) {
					solrQuery.addSortField(field, ORDER.desc);
				}
			}
		}
		
		// 设置时间范围
		String timeRange = getTimeRange(param);
		if (!"".equals(timeRange)) {
			solrQuery.addFilterQuery(timeRange);
		}
		
		// 设置审阅类型 查询全部的时候不需要设置过滤条件
		//String datasource = param.getDataSource();
		if (!(DataSource.CERT_ACTIVE == dataSource
				|| DataSource.CERT_HISTORY == dataSource 
				|| DataSource.CERT_ARCHIVE == dataSource)) {
			if (param.getReviewType() != null && !"".equals(param.getReviewType())) {
				if (CommonParams.REVIEW_TYPE_NOT.equals(param.getReviewType())) {//未审阅
					solrQuery.addFilterQuery("-review:*");
				} else if (CommonParams.REVIEW_TYPE_ALREADY.equals(param.getReviewType())) {//已审阅
					solrQuery.addFilterQuery("review:(YES OR y)");
				}
			}
		}
		
		return solrQuery;
	}
	
	/* 根据查询时间范围返回查询条件*/
	private String getTimeRange(SearchParam param) {
		Date start = null;
		Calendar calendar = Calendar.getInstance();
		String timeRange = param.getTimeRange();
		if (timeRange != null && !"".equals(timeRange) && !"*".equals(timeRange)) {
			char unit = timeRange.charAt(timeRange.length() - 1);
			int number = timeRange.length() > 1 ? Integer.valueOf(timeRange
					.substring(0, timeRange.length() - 1)) : Integer
					.valueOf(timeRange);
			switch (unit) {
			case 'y' : // 单位 年
				calendar.add(Calendar.YEAR, -number);
				break;
			case 'm' : // 单位 月
				calendar.add(Calendar.MONTH, -number);
				break;
			case 'w' : // 单位 星期
				calendar.add(Calendar.WEEK_OF_YEAR, -number);
				break;
			case 'd' : // 单位 天
				calendar.add(Calendar.DAY_OF_YEAR, -number);
				break;
			case 'h' : // 单位 小时
				calendar.add(Calendar.HOUR_OF_DAY, -number);
				break;
			default: // 默认单位 天
				calendar.add(Calendar.DAY_OF_YEAR, -Integer.valueOf(timeRange));
			}
			
			start = calendar.getTime();
			String startStr = TextHandler.transDate(start);
			String filterTime = param.getDefaultSortField() + ":[" + startStr + " TO *]";
			return filterTime;
		}
		return "";
	}
	
	public QueryResponse getFacetPivotResponse(SearchParam param) {
		PivotFields fields = param.getPivotFields();
		SolrQuery query = initQuery(param);
		query.setStart(0).setRows(0).setFacet(true);
		query.setFacetMinCount(1).setFacetLimit(10000000);
		query.setParam("facet.pivot", fields.mainFields());
		
		QueryResponse rsp = null;
		try {
			String dataSource = param.getDataSource();
			auditDs = solrConfig.getAuditDataSource(dataSource);
			if (DataSource.ACCESS.toString().equals(param.getDataSource())) {
				solrServer = auditDs.getSolrCore(param.getSolrCoreName()).getHttpSolrServer();
			} else {
				solrServer = auditDs.getHttpSolrServer();
			}
			
			rsp = query(query);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return rsp;
	}
	
	public QueryResponse getFacetFieldResponse(SearchParam param) {
		PivotFields fields = param.getPivotFields();
		SolrQuery query = initQuery(param);
		query.setStart(0).setRows(0).setFacet(true);
		query.setFacetMinCount(1).setFacetLimit(10000000);
		query.setParam("facet.field", fields.mainFields());
		
		QueryResponse rsp = null;
		try {
			String dataSource = param.getDataSource();
			auditDs = solrConfig.getAuditDataSource(dataSource);
			if (DataSource.ACCESS.toString().equals(param.getDataSource())) {
				solrServer = auditDs.getSolrCore(param.getSolrCoreName()).getHttpSolrServer();
			} else {
				solrServer = auditDs.getHttpSolrServer();
			}
			rsp = query(query);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return rsp;
	}
}
