/**
 * 版权所有：美创科技
 * 项目名称:capaa-web2.6
 * 创建者: liushuai
 * 创建日期: 2013-5-22
 * 文件说明: 搜索参数类
 * 最近修改者：liushuai
 * 最近修改日期：2013-5-22
 */
package com.edao.codes.solr.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.common.SolrException;
import org.apache.solr.common.util.StrUtils;

import com.edao.codes.solr.beans.AccessAudit;
import com.edao.codes.solr.beans.AttackAudit;
import com.edao.codes.solr.beans.CertAudit;
import com.edao.codes.solr.beans.LogonAudit;
import com.edao.codes.solr.constant.CommonParams;
import com.edao.codes.solr.constant.DataSource;
import com.edao.codes.solr.constant.QueryType;

/**
 * @author liushuai
 */
public class SearchParam {
	
	Map<String, String[]> vals = null;
	Class<?> clazzType;
	List<Query> queries = null;
	List<Query> filterQueries = null;
	Query lastQuery = null;
	PivotFields pivotFields;
	
	// mc$sql_searchform id
	String sqlRuleId;
	// 注册搜索ID
	String solrId;
	// suspectedSql
	String ruleFlag;
	// 翻译后的规则
	String rule;
	
	/**
	 * 初始化
	 */
	public SearchParam() {
		vals = new LinkedHashMap<String, String[]>();
		queries = new ArrayList<Query>();
		filterQueries = new ArrayList<Query>();
		setPageSize(10); // 设置默认分页大小为 10
		setPage(1); // 设置默认页码为 1
	}
	
	/**
	 * 
	 * @param dataSource 数据源
	 */
	public SearchParam(DataSource dataSource) {
		this();
		setDataSource(dataSource);
	}
	
	/**
	 * 
	 * @param dataSource 数据源
	 * @param queryType 查询类型
	 */
	public SearchParam(DataSource dataSource, QueryType queryType) {
		this();
		setDataSource(dataSource);
		setQueryType(queryType);
	}
	
	/**
	 * 
	 * @param dataSource 数据源
	 * @param queryType 查询类型
	 * @param page 页码
	 */
	public SearchParam(DataSource dataSource, QueryType queryType, int page) {
		this(dataSource, queryType);
		setPage(page);
	}
	
	/**
	 * 
	 * @param dataSource 数据源
	 * @param queryType 查询类型
	 * @param page 页码
	 * @param pageSize 页面大小
	 */
	public SearchParam(DataSource dataSource, QueryType queryType, int page, int pageSize) {
		this(dataSource, queryType);
		setPage(page);
		setPageSize(pageSize);
	}
	
	// 为某个键添加值
	private SearchParam add(String name, String... val) {
		String[] old = vals.put(name, val);
		if (old != null) {
			if (val == null || val.length < 1) {
				String[] both = new String[old.length + 1];
				System.arraycopy(old, 0, both, 0, old.length);
				both[old.length] = null;
				vals.put(name, both);
			} else {
				String[] both = new String[old.length + val.length];
				System.arraycopy(old, 0, both, 0, old.length);
				System.arraycopy(val, 0, both, old.length, val.length);
				vals.put(name, both);
			}
		}
		return this;
	}
	
	private SearchParam remove(String name) {
		String[] old = vals.get(name);
		old = null;
		vals.put(name, new String[]{});
		return this;
	}
	
	// 设置键值对
	private SearchParam set(String name, String... val) {
		if (val == null || (val.length == 1 && val[0] == null)) {
			vals.remove(name);
		} else {
			vals.put(name, val);
		}
		return this;
	}

	// 设置键值对 值为数字型
	private SearchParam set(String name, int val) {
		set(name, String.valueOf(val));
		return this;
	}
	
	// 设置键值对 值为布尔型
	private SearchParam set(String name, boolean val) {
		set(name, String.valueOf(val));
		return this;
	}
	
	// 根据键名获取值，值为字符串数组
	private String[] getParams(String param) {
		return vals.get(param);
	}
	
	// 根据键名获取值，值为字符串
	private String get(String param) {
		String[] v = vals.get(param);
		if (v != null && v.length > 0) {
			return v[0];
		}
		return null;
	}
	
	// 根据键名获取值，值为数字
	private Integer getInt(String param) {
		String val = get(param);
		try {
			return val == null ? null : Integer.valueOf(val);
		} catch (Exception ex) {
			throw new SolrException(SolrException.ErrorCode.BAD_REQUEST,
					ex.getMessage(), ex);
		}
	}
	
	// 根据键名获取值，值为布尔型，设置默认返回值
	private boolean getBool(String param, boolean def) {
		String val = get(param);
		return val == null ? def : StrUtils.parseBool(val);
	}
	
	/**
	 * 设置数据源
	 * @param ds 数据源
	 * @return SearchParam
	 */
	public SearchParam setDataSource(String ds) {
		
		DataSource dataSource = DataSource.getEnum(ds);
		return setDataSource(dataSource);
	}
	
	/**
	 * 设置数据源
	 * @param dataSource 数据源
	 * @return SearchParam
	 */
	public SearchParam setDataSource(DataSource dataSource) {
		this.set(CommonParams.DS, dataSource.toString());
		
		String sortField = null;
		switch(dataSource) {
		case LOGON:
			clazzType = LogonAudit.class;
			sortField = "logon_time";
			break;
		case ACCESS:
			clazzType = AccessAudit.class;
			sortField = "optime";
			break;
		case CERT_ACTIVE:
		case CERT_HISTORY:
			clazzType = CertAudit.class;
			sortField = "logon_time";
			break;
		case ATTACK:
			clazzType = AttackAudit.class;
			sortField = "attack_time";
			break;
		default:
			sortField = "last_modified";	
		}
		this.set(CommonParams.SORT, sortField);
		
		return this;
	}
	
	/**
	 * 返回数据源
	 * @return 数据源
	 */
	public String getDataSource() {
		return this.get(CommonParams.DS);
	}
	
	/**
	 * 设置查询条件--未翻译
	 * @param query 查询条件
	 * @return SearchParam
	 */
	public SearchParam setQuery(String query) {
		return setQuery(new Query(true, query));
	}
	
	// 清空Query条件
	private void clearQueries() {
		queries.clear();
	}
	
	/**
	 * 设置查询语句
	 * @param query 查询语句
	 * @return SearchParam
	 */
	public SearchParam setQuery(Query query) {
		clearQueries();
		this.addQuery(query);
		this.set(CommonParams.Q, query.getQuery());
		return this;
	}
	
	/**
	 * 返回查询条件--未翻译
	 * @return 查询条件--未翻译
	 */
	public String getQuery() {
		return this.get(CommonParams.Q);
	}
	
	/**
	 * 设置查询条件--翻译
	 * 采用setQuery(new Query(false, null, queryTrans))取代
	 * @param queryTrans 查询条件--翻译
	 * @return SearchParam
	 */
	@Deprecated
	public SearchParam setQueryTrans(String queryTrans) {
		this.setQuery(new Query(false, null, queryTrans));
		return this.set(CommonParams.Q_TRANS, queryTrans);
	}
	
	/**
	 * 设置过滤查询条件--未翻译
	 * @param fq 过滤查询条件--未翻译
	 * @return SearchParam
	 */
	public SearchParam addFilterQuery(String ... fq) {
		QueryType queryType = QueryType.ADVANCED;
		if (!StringUtils.isEmpty(this.getQueryType())) {
			queryType = QueryType.getEnum(this.getQueryType());
		}
		
		for (int i=0; i<fq.length; i++) {
			addFilterQuery(new Query(true, queryType, fq[i]));
		}
		return this;
	}
	
	/**
	 * 清空过滤Query
	 * @return SearchParam
	 */
	public SearchParam clearFilterQuery() {
		this.filterQueries.clear();
		return this;
	}
	
	/**
	 * 返回过滤查询条件
	 * @return String[]
	 */
	public List<Query> getFilterQueries() {
		return this.filterQueries;
	}
	
	/**
	 * 设置过滤查询条件--翻译
	 * 采用 addFilterQuery(Query ... filterQuery)取代
	 * @param fq 过滤查询条件--翻译
	 * @return SearchParam
	 */
	@Deprecated
	public SearchParam addFilterQueryTrans(String ... fq) {
		for (int i=0; i<fq.length; i++) {
			addFilterQuery(new Query(false, null, fq[i]));
		}
		return this;
	}
	
	/**
	 * 设置过滤查询条件
	 * @param filterQuery 过滤查询条件
	 * @return SearchParam
	 */
	public SearchParam addFilterQuery(Query ... filterQuery) {
		for (int i=0; i<filterQuery.length; i++) {
			if (!this.filterQueries.contains(filterQuery[i])) {
				this.queries.add(filterQuery[i]);
			}
		}
		return this;
	}
	
	/**
	 * 清空过滤Query
	 * @return SearchParam
	 */
	public SearchParam clearFilterQueryTrans() {
		this.filterQueries.clear();
		return this.remove(CommonParams.FQ_TRANS);
	}
	
	/**
	 * 设置分页页码
	 * @param page 分页页码
	 * @return SearchParam
	 */
	public SearchParam setPage(String page) {
		page = page == null || "".equals(page) ? "1" : page;
		this.set(CommonParams.PAGE, page);
		return this;
	}
	
	/**
	 * 设置分页页码
	 * @param page 分页页码
	 * @return SearchParam
	 */
	public SearchParam setPage(int page) {
		return this.setPage(String.valueOf(page));
	}
	
	/**
	 * 返回分页页码
	 * @return String
	 */
	public String getPage() {
		return this.get(CommonParams.PAGE);
	}
	
	/**
	 * 设置分页大小
	 * @param pageSize 分页大小
	 * @return SearchParam
	 */
	public SearchParam setPageSize(String pageSize) {
		return this.set(CommonParams.ROWS, pageSize);
	}
	
	/**
	 * 设置分页大小
	 * @param pageSize 分页大小
	 * @return SearchParam
	 */
	public SearchParam setPageSize(int pageSize) {
		return this.set(CommonParams.ROWS, String.valueOf(pageSize));
	}
	
	/**
	 * 返回分页大小
	 * @return Integer
	 */
	public Integer getPageSize() {
		return this.getInt(CommonParams.ROWS);
	}
	
	/**
	 * 设置查询类型
	 * @param queryType 查询类型
	 * @return SearchParam
	 */
	public SearchParam setQueryType(String queryType) {
		return this.set(CommonParams.QUERYTYPE, queryType);
	}
	
	/**
	 * 设置查询类型
	 * @param queryType 查询类型
	 * @return SearchParam
	 */
	public SearchParam setQueryType(QueryType queryType) {
		return setQueryType(queryType.toString());
	}
	
	/**
	 * 返回查询类型
	 * @return String
	 */
	public String getQueryType() {
		return this.get(CommonParams.QUERYTYPE);
	}
	
	/**
	 * 设置审阅类型
	 * @param reviewType 审阅类型
	 * @return SearchParam
	 */
	public SearchParam setReviewType(String reviewType) {
		return this.set(CommonParams.REVIEWTYPE, reviewType);
	}
	
	/**
	 * 返回审阅类型
	 * @return String
	 */
	public String getReviewType() {
		return this.get(CommonParams.REVIEWTYPE);
	}
	
	/**
	 * 设置时间范围
	 * @param timeRange 时间范围
	 * @return SearchParam
	 */
	public SearchParam setTimeRange(String timeRange) {
		this.set(CommonParams.TIMERANGE, timeRange);
		return this;
	}
	
	/**
	 * 返回时间范围
	 * @return String
	 */
	public String getTimeRange() {
		String timeRange = this.get(CommonParams.TIMERANGE);
		timeRange = timeRange == null || "".equals(timeRange) ? "*" : timeRange;
		return timeRange;
	}
	
	/**
	 * 返回查询开始处
	 * @return Integer
	 */
	public Integer getStartRow() {
		Integer pageNum = this.getInt(CommonParams.PAGE);
		if (pageNum < 1) {
			pageNum = 1;
		}
		int start = (pageNum - 1) * getRows();
		return start;
	}
	
	/**
	 * 返回查询返回条数
	 * @return Integer
	 */
	public Integer getRows() {
		return this.getInt(CommonParams.ROWS);
	}
	
	/**
	 * 返回查询排序字段
	 * @return Integer
	 */
	public String getDefaultSortField() {
		return this.get(CommonParams.SORT);
	}
	
	/**
	 * 设置默认排序字段
	 * @param field 排序字段
	 * @return SearchParam
	 */
	public SearchParam setDefaultSortField(String field) {
		this.set(CommonParams.SORT, field);
		return this;
	}
	
	/**
	 * 添加排序字段
	 * @param sortField 排序字段
	 * @return SearchParam
	 */
	public SearchParam addSortField(String sortField) {
		this.add(CommonParams.SORTS, sortField);
		return this;
	}
	
	/**
	 * @return 排序字段
	 */
	public String[] getSortFields() {
		return this.getParams(CommonParams.SORTS);
	}
	
	/**
	 * 返回搜索结果类型
	 * @return Class
	 */
	public Class getClassType() {
		return this.clazzType;
	}
	
	/**
	 * 设置返回结果类型
	 * @param clzz Class
	 */
	public void setClassType(Class clzz) {
		clazzType = clzz;
	}
	
	/**
	 * 返回上一次查询条件 -- 翻译
	 * @return String
	 */
	public Query getLastQuery() {
		return lastQuery;
	}
	
	/**
	 * 设置上一次查询条件
	 * @param lastQuery 上一次查询条件
	 * @return SearchParam
	 */
	public SearchParam setLastQuery(Query lastQuery) {
		this.lastQuery = lastQuery;
		return this;
	}
	
	/**
	 * 设置IP地址翻译开关
	 * @param flag true：IP会被翻译 false：IP不会被翻译
	 * @return SearchParam
	 */
	public SearchParam setIpTransSwitch(boolean flag) {
		this.set(CommonParams.SWITCH_IP, flag);
		return this;
	}
	
	/**
	 * 返回IP地址翻译开关
	 * @return true or false
	 */
	public boolean getIpTransSwitch() {
		return this.getBool(CommonParams.SWITCH_IP, true);
	}
	
	/**
	 * 设置Appuser翻译开关
	 * @param flag true：Appuser会被翻译 false：Appuser不会被翻译
	 * @return SearchParam
	 */
	public SearchParam setAppuserTransSwitch(boolean flag) {
		this.set(CommonParams.SWITCH_APPUSER, flag);
		return this;
	}
	
	/**
	 * 返回Appuser翻译开关
	 * @return true or false
	 */
	public boolean getAppuserTransSwitch() {
		return this.getBool(CommonParams.SWITCH_APPUSER, false);
	}
	
	
	/**
	 * 设置查询翻译开关
	 * 采用Query替代
	 * @param flag true：查询条件会被翻译 false：查询条件不会被翻译
	 * @return SearchParam
	 */
	@Deprecated
	public SearchParam setQueryTransSwitch(boolean flag) {
		this.set(CommonParams.SWITCH_QUERY, flag);
		return this;
	}
	
	/**
	 * human can see
	 * @return string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder(128);
		boolean first = true;
		for (Map.Entry<String, String[]> entry : vals.entrySet()) {
			String key = entry.getKey();
			String[] valarr = entry.getValue();
			for (String val : valarr) {
				if (!first) {
					sb.append('&');
				}
				first = false;
				sb.append(key);
				sb.append('=');
				if (val != null) {
					sb.append(val);
				}
			}
		}
		
		return sb.toString();
	}

	/**
	 * 返回是否填充SQL语句开关
	 * true 填充SQL语句  false 不填充SQL语句
	 * 默认为true
	 * @return boolean
	 */
	public boolean getSqlTextFillSwitch() {
		return this.clazzType == AccessAudit.class ? this.getBool(
				CommonParams.SWITCH_FILL_SQLTEXT, true) : false;
	}
	
	/**
	 * 设置是否填充SQL语句
	 * @param flag 开关
	 * @return SearchParam
	 */
	public SearchParam setSqlTextFillSwitch(boolean flag) {
		this.set(CommonParams.SWITCH_FILL_SQLTEXT, flag);
		return this;
	}

	/**
	 * 设置过滤查询翻译开关
	 * 采用addFilterQuery(Query)取代
	 * true：过滤查询条件会被翻译 false：过滤查询条件不会被翻译
	 * @param b boolean
	 * @return SearchParam
	 */
	@Deprecated
	public SearchParam setFilterQueryTransSwitch(boolean b) {
		this.set(CommonParams.SWITCH_FILTERQUERY, b);
		return this;
	}
	
	/**
	 * 返回过滤查询翻译开关
	 * 采用addFilterQuery(Query)取代
	 * @return true or false
	 */
	@Deprecated
	public boolean getFilterQueryTransSwitch() {
		return this.getBool(CommonParams.SWITCH_FILTERQUERY, true);
	}
	
	/**
	 * 指定core查询
	 * @param coreName core名称
	 * @return SearchParam
	 */
	public SearchParam setSolrCoreName(String coreName) {
		this.set(CommonParams.CORE_NAME, coreName);
		return this;
	}
	
	/**
	 * @return core名称
	 */
	public String getSolrCoreName() {
		String core = this.get(CommonParams.CORE_NAME);
		return core == null ? "" : core;
	}
	
	/**
	 * 设置是否对返回结果排序
	 * @param b true 排序 false 不排序
	 * @return SearchParam
	 */
	public SearchParam setSortSwitch(boolean b) {
		this.set(CommonParams.SWITCH_SORT, b);
		return this;
	}
	
	/**
	 * 是否对返回结果排序
	 * @return true 排序 false 不排序
	 */
	public boolean getSortSwitch() {
		return this.getBool(CommonParams.SWITCH_SORT, true);
	}
	
	/**
	 * 
	 * @return Query列表
	 */
	public List<Query> getQueries() {
		return queries;
	}
	
	/**
	 * 添加查询条件
	 * @param query Query
	 * @return SearchParam
	 */
	public SearchParam addQuery(Query ... query) {
		for (int i=0; i<query.length; i++) {
			if (!this.queries.contains(query[i])) {
				this.queries.add(query[i]);
				this.setLastQuery(query[i]);
			}
		}
//		if (!this.queries.contains(query)) {
//			this.queries.add(query);
//			this.setLastQuery(query);
//		}
		return this;
	}
	
	/**
	 * 批量设置查询条件
	 * @param queries Query列表
	 * @return SearchParam
	 */
	public SearchParam addQueries(List<Query> queries) {
		if (queries != null && queries.size() > 0) {
			for (Query q : queries) {
				addQuery(q);
			}
		}
		return this;
	}
	
	/**
	 * 顺序或倒序放入Query列表
	 * @param queries Query列表
	 * @param order 顺序
	 * @return SearchParam
	 */
	public SearchParam addQueries(List<Query> queries, ORDER order) {
		if (queries != null && queries.size() > 0) {
			if (ORDER.desc == order) { // 第一个为lastQuery
				for (int i=queries.size()-1; i>=0; i--) {
					Query q = queries.get(i);
					addQuery(q);
				}
			} else { // 最后一个为lastQuery
				for (Query q : queries) {
					addQuery(q);
				}
			}
		}
		
		return this;
	}

	
	public String getSqlRuleId() {
		return sqlRuleId;
	}

	
	public void setSqlRuleId(String sqlRuleId) {
		this.sqlRuleId = sqlRuleId;
	}

	
	public String getSolrId() {
		return solrId;
	}

	
	public void setSolrId(String solrId) {
		this.solrId = solrId;
	}

	
	public String getRuleFlag() {
		return ruleFlag;
	}

	
	public void setRuleFlag(String ruleFlag) {
		this.ruleFlag = ruleFlag;
	}

	
	public String getRule() {
		return rule;
	}

	
	public void setRule(String rule) {
		this.rule = rule;
	}

	/**
	 * @return the pivotFields
	 */
	public PivotFields getPivotFields() {
		return pivotFields;
	}

	/**
	 * @param pivotFields the pivotFields to set
	 */
	public void setPivotFields(PivotFields pivotFields) {
		this.pivotFields = pivotFields;
	}
	
	
}
