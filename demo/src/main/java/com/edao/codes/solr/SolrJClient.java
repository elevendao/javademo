package com.edao.codes.solr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.PivotField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.NamedList;

import com.edao.codes.beans.GroupResult;
import com.edao.codes.beans.GroupResultComparator;
import com.edao.codes.solr.beans.LogonAudit;
import com.edao.codes.solr.constant.CommonParams;
import com.edao.codes.solr.constant.DataSource;
import com.edao.codes.solr.impl.AuditDataSource;
import com.edao.codes.solr.impl.PivotFields;
import com.edao.codes.solr.impl.Query;
import com.edao.codes.solr.impl.SearchParam;
import com.edao.codes.solr.impl.SearchResult;
import com.edao.codes.solr.impl.SolrConfig;
import com.edao.codes.solr.impl.SolrHttpNew;
import com.edao.codes.util.StringUtil;

/**
 * @author liushuai
 *
 */
public class SolrJClient {

	SolrConfig solrConfig = new SolrConfig();
	{
		solrConfig.setHost("172.16.4.118");
		solrConfig.setPort("8983");
		solrConfig.setInstance1("(dataSource=1000,0,2,3,4,5)(host=172.16.4.118)(port=8983)");
		solrConfig.setInstance2("(dataSource=1)(host=172.16.4.118)(port=8983)");
		solrConfig.init();
	}
	
	SolrHttpNew solrHttp = new SolrHttpNew(solrConfig);
	
	public SolrConfig getSolrConfig() {
		return solrConfig;
	}
	
	// 对单个字段的统计
	private List<GroupResult> facetField(SearchParam param) {
		QueryResponse rsp = solrHttp.getFacetFieldResponse(param);
		String field = param.getPivotFields().getMainFields().get(0);
		FacetField ff = rsp.getFacetField(field);
		List<Count> countList = null;
		List<GroupResult> results = new ArrayList<GroupResult>();
		if (ff != null) {
			countList = ff.getValues();
			for (Count c : countList){
				if (c.getCount() > 0) {
					if ("combined_level".equals(c.getFacetField().getName())){
						if (isActionLevel(c.getName())) {
							GroupResult result = new GroupResult();
							result.put("action_level", c.getName());
							result.setCount((int)c.getCount());
							results.add(result);
						}
					} else if ("terminal_info".equals(c.getFacetField().getName())) {
						if (!StringUtil.isIpAddress(c.getName())) {
							GroupResult result = new GroupResult();
							result.put("appuser", c.getName());
							result.setCount((int)c.getCount());
							results.add(result);
						}
					} else {
						GroupResult result = new GroupResult();
						result.put(field, c.getName());
						result.setCount((int)c.getCount());
						results.add(result);
					}
				}
			}
		}
		
		return results;
	}
	
	private List<GroupResult> facetQuery(SearchParam param) {
		PivotFields pivotFields = param.getPivotFields();
		if (pivotFields.isSingle()){
			return facetField(param);
		} else {
			return facetPivot(param);
		}
	}
	
	
	/**
	 * 对多个字段统计
	 * facet.pivot查询，即支点查询，类似group by
	 * @param pivotQuery
	 */
	private List<GroupResult> facetPivot(SearchParam param) {
		QueryResponse rsp = solrHttp.getFacetPivotResponse(param);
		
		List<GroupResult> results = new ArrayList<GroupResult>();
		if (rsp != null) {
			// 获取facet结果
			NamedList<List<PivotField>> facetPivot = rsp.getFacetPivot();
			Iterator<Entry<String, List<PivotField>>> it = facetPivot.iterator();
			// 遍历每个field，得到最终的分组结果
			while (it.hasNext()) {
				Entry<String, List<PivotField>> entry = it.next();
				List<PivotField> pfList = entry.getValue();
				
				GroupResult result = new GroupResult();
				traversalPivotFieldList(pfList, result, results);
			}
			
			System.out.println("queryTime : " + rsp.getQTime());
		}
		
//		showNewGroupResultList(results);
		
		return results;
	}
	
	private void traversalPivotFieldList(List<PivotField> pfList,
			GroupResult result,
			List<GroupResult> results) {
		for (PivotField pf : pfList) {
			if ("combined_level".equals(pf.getField())) {
				if (isActionLevel((String) pf.getValue())) {
					result.put("action_level", pf.getValue());
					traversal(pf, result, results);
				}
			} else if ("terminal_info".equals(pf.getField())) {
				if (!StringUtil.isIpAddress((String) pf.getValue())) {
					result.put("appuser", pf.getValue());
					traversal(pf, result, results);
				}
			} else {
				result.put(pf.getField(), pf.getValue());
				traversal(pf, result, results);
			}
		}
	}

	// 遍历facet的树结果，获得当前field的所有值，并把值作为下个field的前缀，
	// 当最后一个field被遍历完（没有下个field），遍历结束
	private void traversal(PivotField pf, GroupResult result, List<GroupResult> results) {
		List<PivotField> list = pf.getPivot();
		
		if (list!=null && list.size() > 0) {
			traversalPivotFieldList(list, result, results);
		} else {
			result.setCount(pf.getCount());
			results.add((GroupResult) result.clone());
		}
		
	}
	
	public static void showNewGroupResultList(List<GroupResult> list) {
		for (int i=0; i<list.size(); i++) {
			GroupResult gr = list.get(i);
			System.out.println(i + ", \t" + gr);
		}
	}
	
	boolean isAuditLevel(String val) {
		return "high".equals(val) || "middle".equals(val) || "low".equals(val);
	}
	
	boolean isActionLevel(String val) {
		return "allow".equals(val) || "reject".equals(val) || "simulreject".equals(val);
	}
	
	// 对审计记录统计分组
	public SearchResult<GroupResult> group(SearchParam param) {
		DataSource dataSource = DataSource.getEnum(param.getDataSource());
		PivotFields pivotFields = param.getPivotFields();
		GroupList glist = new GroupList(pivotFields.getFields());
		if (DataSource.ACCESS == dataSource) { // 对整个访问审计统计
			AuditDataSource ads = solrConfig.getAuditDataSource(DataSource.ACCESS);
			for (String coreName : ads.coreSet()) {
				System.out.println(coreName);
				groupAccessAudit(param, coreName, glist);
			}
		} else { // 对其他数据源的审计记录统计
			List<GroupResult> items = facetQuery(param);
			for (int i=0; i<items.size(); i++) {
				GroupResult gr = items.get(i);
				glist.add(gr);
			}
			System.out.println("===============");
		}
		
		List<GroupResult> groups = glist.getGroups();
		SearchResult<GroupResult> result = new SearchResult<GroupResult>();
		result.setItems(groups);
		int totalCount = 0;
		for (GroupResult g : groups) {
			totalCount += g.getCount();
		}
		result.setTotalCount(totalCount);
		
		return result;
	}
	
	// 对单core访问审计统计
	private GroupList groupAccessAudit(SearchParam param, String coreName, GroupList glist) {
		param.setSolrCoreName(coreName);
		PivotFields pivotFields = param.getPivotFields();
		List<GroupResult> items = facetQuery(param);
		Map<String, LogonAudit> map = new HashMap<String, LogonAudit>(32);
		int gtotal = 0;
		for (int i=0; i<items.size(); i++) {
			GroupResult gr = items.get(i);
			gtotal += gr.getCount();
			map.put((String)gr.get("hashid"), null);
		}
		System.out.println("record count : " + gtotal);
		
		long d1 = System.currentTimeMillis();
		fillLogonAuditMap(map);
		long d2 = System.currentTimeMillis();
		System.out.println("hashid total : " + map.size());
		System.out.println("get hashid map time : " + (d2-d1) + "ms");
		
		if (glist == null) {
			glist = new GroupList(pivotFields.getFields());
		}
		for (int i=0; i<items.size(); i++) {
			GroupResult gr = items.get(i);
			String hashid = (String) gr.get("hashid");
			LogonAudit logon = map.get(hashid);
			if (logon != null) {
				for (String field : pivotFields.getFields()) {
					if (!CommonParams.accessFieldMap.containsKey(field.trim())) {
						gr.put(field, getLogonAttr(logon, field.trim()));
					}
				}
			}
			System.out.println(i + ", \t" + gr);
			gr.put("hashid", null); // 将hashid清空，对分组去重
			glist.add(gr);
		}
		System.out.println("===============");
		
		return glist;
	}
	
	// 根据登录审计的统计字段取登录审计对象的对应属性值
	public Object getLogonAttr(LogonAudit logon, String field) {
		if ("ip_address".equals(field)) {
			return logon.getIpAddress();
		} else if ("appname".equals(field)) {
			return logon.getAppname();
		} else if ("dbname".equals(field)) {
			return logon.getDbname();
		} else if ("instance_name".equals(field)) {
			return logon.getInstanceName();
		} else if ("host".equals(field)) {
			return logon.getHost();
		} else if ("dbuser".equals(field)) {
			return logon.getDbuser();
		} else if ("mac_address".equals(field)) {
			return logon.getMacAddress();
		} else if ("appuser".equals(field)) {
			return logon.getAppuser();
		}
		return null;
	}
	
	// 分组统计结果类，主要对分组结果进行合并，排序
	class GroupList {
		String[] groupBys;
		List<GroupResult> groups;
		
		public GroupList(String[] groupBys) {
			this.groupBys = groupBys;
			groups = new ArrayList<GroupResult>();
		}
		
		public void addAll(GroupList glist) {
			for (GroupResult g : glist.groups) {
				add(g);
			}
		}
		
		public void add(GroupResult e) {
			int index = groups.indexOf(e);
			if (index > -1){
				GroupResult old = groups.get(index);
				old.setCount(old.getCount() + e.getCount());
			} else {
				e = (GroupResult) e.clone();
				groups.add(e);
			}
		}
		
		public List<GroupResult> getGroups() {
			Collections.sort(groups, new GroupResultComparator(ORDER.desc));
			return groups;
		}
	}
	
	// 填充登录信息，以便进一步合并分组
	private void fillLogonAuditMap(Map<String, LogonAudit> map) {
		SearchParam param = new SearchParam();
		param.setDataSource(DataSource.LOGON);
		param.setPageSize(1);
		for (Map.Entry<String, LogonAudit> entry : map.entrySet()) {
			String hashid = entry.getKey();
			param.setQuery(new Query(false, "hashid:"+hashid));
			try {
				SearchResult<LogonAudit> result = solrHttp.search(param);
				if (result.getItems() != null && result.getItems().size() > 0) {
					entry.setValue(result.getItems().get(0));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
