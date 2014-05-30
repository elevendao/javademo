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

import com.edao.codes.beans.NewGroupResult;
import com.edao.codes.beans.NewGroupResultComparator;
import com.edao.codes.solr.beans.LogonAudit;
import com.edao.codes.solr.constant.CommonParams;
import com.edao.codes.solr.constant.DataSource;
import com.edao.codes.solr.constant.QueryType;
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
	
	public SearchResult<NewGroupResult<String, Object>> facetField(SearchParam param) {
		QueryResponse rsp = solrHttp.getFacetFieldResponse(param);
		List<FacetField> list = rsp.getFacetFields();
		List<NewGroupResult<String, Object>> results = new ArrayList<NewGroupResult<String, Object>>();
		String field = param.getPivotFields().getMainFields().get(0);
		if ("combined_level".equals(field)){
			for (FacetField ff : list){
				List<Count> l = ff.getValues();
				for (Count c : l) {
					if (isActionLevel(c.getName()) && c.getCount()>0){
						NewGroupResult<String, Object> result = new NewGroupResult<String, Object>();
						result.put("action_level", c.getName());
						result.setCount((int)c.getCount());
						results.add(result);
					}
				}
			}
		} else if ("terminal_info".equals(field)) {
			for (FacetField ff : list){
				List<Count> l = ff.getValues();
				for (Count c : l) {
					if (!StringUtil.isIpAddress(c.getName()) && c.getCount()>0){
						NewGroupResult<String, Object> result = new NewGroupResult<String, Object>();
						result.put("appuser", c.getName());
						result.setCount((int)c.getCount());
						results.add(result);
					}
				}
			}
		} else {
			for (FacetField ff : list){
				List<Count> l = ff.getValues();
				for (Count c : l) {
					if (c.getCount() > 0) {
						NewGroupResult<String, Object> result = new NewGroupResult<String, Object>();
						result.put(field, c.getName());
						result.setCount((int)c.getCount());
						results.add(result);
					}
				}
			}
		}
		
		SearchResult result = new SearchResult();
		result.setItems(results);
		result.setTotalCount(results.size());
		
		return result;
	}
	
	public SearchResult<NewGroupResult<String, Object>> facetQuery(SearchParam param) {
		PivotFields pivotFields = param.getPivotFields();
		if (pivotFields.isSingle()){
			return facetField(param);
		} else {
			return facetPivot(param);
		}
	}
	
	
	/**
	 * facet.pivot查询，即支点查询，类似group by
	 * @param pivotQuery
	 */
	public SearchResult<NewGroupResult<String, Object>> facetPivot(SearchParam param) {
		QueryResponse rsp = solrHttp.getFacetPivotResponse(param);
		
		List<NewGroupResult<String, Object>> resultss = new ArrayList<NewGroupResult<String, Object>>();
		int groupCount = 0;
		if (rsp != null) {
			// 获取facet结果
			NamedList<List<PivotField>> facetPivot = rsp.getFacetPivot();
			Iterator<Entry<String, List<PivotField>>> it = facetPivot.iterator();
			// 遍历每个field，得到最终的分组结果
			while (it.hasNext()) {
				Entry<String, List<PivotField>> entry = it.next();
				List<PivotField> pfList = entry.getValue();
				
				NewGroupResult<String, Object> result = new NewGroupResult<String, Object>();
				for (PivotField pf : pfList) {
					if("combined_level".equals(pf.getField())) {
						if (isActionLevel((String)pf.getValue())) {
							result.put("action_level", pf.getValue());
							traversal(pf, result, resultss);
						}
					} else if ("terminal_info".equals(pf.getField())) {
						if (!StringUtil.isIpAddress((String)pf.getValue())) {
							result.put("appuser", pf.getValue());
							traversal(pf, result, resultss);
						}
					} else {
						result.put(pf.getField(), pf.getValue());
						traversal(pf, result, resultss);
					}
				}
			}
			
			Collections.sort(resultss, new NewGroupResultComparator(ORDER.desc));
			System.out.println("queryTime : " + rsp.getQTime());
		}
		
		SearchResult result = new SearchResult();
		result.setItems(resultss);
		result.setTotalCount(resultss.size());
		
		showNewGroupResultList(resultss);
		
		return result;
	}
	
	public static void showNewGroupResultList(List<NewGroupResult<String, Object>> list) {
		for (int i=0; i<list.size(); i++) {
			NewGroupResult<String, Object> gr = list.get(i);
			System.out.println(i + ", \t" + gr);
		}
	}
	
	boolean isAuditLevel(String val) {
//		return str.matches("(high|middle|low)");
		return "high".equals(val) || "middle".equals(val) || "low".equals(val);
	}
	
	boolean isActionLevel(String val) {
		return "allow".equals(val) || "reject".equals(val) || "simulreject".equals(val);
	}
	
	// 遍历facet的树结果，获得当前field的所有值，并把值作为下个field的前缀，
	// 当最后一个field被遍历完（没有下个field），遍历结束
	private void traversal(PivotField pf, NewGroupResult<String, Object> result, List<NewGroupResult<String, Object>> results) {
		List<PivotField> list = pf.getPivot();
		
		if (list!=null && list.size() > 0) {
			for (PivotField pff : list) {
				if ("combined_level".equals(pff.getField())) {
					if (isActionLevel((String) pff.getValue())) {
						result.put("action_level", pff.getValue());
						traversal(pff, result, results);
					}
				} else if ("terminal_info".equals(pff.getField())) {
					if (!StringUtil.isIpAddress((String) pff.getValue())) {
						result.put("appuser", pff.getValue());
						traversal(pff, result, results);
					}
				} else {
					result.put(pff.getField(), pff.getValue());
					traversal(pff, result, results);
				}
			}
		} else {
			result.setCount(pf.getCount());
			results.add((NewGroupResult<String, Object>) result.clone());
		}
		
	}
	
	// 对整个访问审计统计
	public GroupList groupAccess(SearchParam param) {
		DataSource dataSource = DataSource.getEnum(param.getDataSource());
		GroupList glist = null;
		if (DataSource.ACCESS == dataSource) {
			AuditDataSource ads = solrConfig.getAuditDataSource(DataSource.ACCESS);
			DataSource ds = DataSource.ACCESS;
			String q = "*:*";
			String lgAdditon = "*:*";
			String fields = "dbuser,host,instance_name,dbname,appname,ip_address,action_level";
			fields = "appuser,host";
			PivotFields pfields = new PivotFields(ds, fields);
			Query query = new Query(false, QueryType.ADVANCED, q, lgAdditon);
			param.setQuery(query);
			param.setPivotFields(pfields);
			
			List<GroupList> list = new ArrayList<GroupList>();
			for (String coreName : ads.coreSet()) {
				System.out.println(coreName);
				glist = groupAccess(param, coreName);
				list.add(glist);
			}
			glist = list.get(0);
			for (int i=1; i<list.size(); i++) {
				glist.addAll(list.get(i));
			}
		}
		return glist;
	}
	
	// 对单core访问审计统计
	public GroupList groupAccess(SearchParam param, String coreName) {
		param.setSolrCoreName(coreName);
		PivotFields pivotFields = param.getPivotFields();
		SearchResult<NewGroupResult<String, Object>> result = facetQuery(param);
		List<NewGroupResult<String, Object>> items = result.getItems();
		Map<String, LogonAudit> map = new HashMap<String, LogonAudit>(32);
		int gtotal = 0;
		for (int i=0; i<items.size(); i++) {
			NewGroupResult<String, Object> gr = items.get(i);
			gtotal += gr.getCount();
			map.put((String)gr.get("hashid"), null);
		}
		System.out.println("group count : " + result.getTotalCount());
		System.out.println("record count : " + gtotal);
		
		long d1 = System.currentTimeMillis();
		fillLogonAuditMap(map);
		long d2 = System.currentTimeMillis();
		System.out.println("hashid total : " + map.size());
		System.out.println("get hashid map time : " + (d2-d1) + "ms");
		
		
		GroupList glist = new GroupList(pivotFields.getFields());
		for (int i=0; i<items.size(); i++) {
			NewGroupResult<String, Object> gr = items.get(i);
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
			gr.put("hashid", null);
			glist.add(gr);
		}
		System.out.println("===============");
		
		return glist;
	}
	
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
	
	class GroupList {
		String[] groupBys;
		Map<NewGroupResult<String, Object>, Integer> groups;
		
		public GroupList(String[] groupBys) {
			this.groupBys = groupBys;
			groups = new HashMap<NewGroupResult<String, Object>, Integer>();
		}
		
		public void addAll(GroupList glist) {
			for (Map.Entry<NewGroupResult<String, Object>, Integer> entry : glist.groups.entrySet()) {
				NewGroupResult<String, Object> key = entry.getKey();
				Integer count = entry.getValue();
				if (groups.containsKey(key)) {
					Integer oldCount = (Integer) groups.get(key);
					groups.put(key, oldCount+count);
				} else {
					groups.put(key, count);
				}
			}
		}
		
		public void add(NewGroupResult<String, Object> e) {
			NewGroupResult<String, Object> key = new NewGroupResult<String, Object>();
			for (String field : groupBys) {
				key.put(field, e.get(field));
			}
			
			if (groups.containsKey(key)) {
				Integer oldCount = (Integer) groups.get(key);
				groups.put(key, oldCount+e.getCount());
			} else {
				groups.put(key, e.getCount());
			}
		}
		
		public List<NewGroupResult<String, Object>> getGroups() {
			List<NewGroupResult<String, Object>> list = new ArrayList<NewGroupResult<String, Object>>();
			for (Map.Entry<NewGroupResult<String, Object>, Integer> entry : groups.entrySet()) {
				NewGroupResult<String, Object> g = entry.getKey();
				NewGroupResult<String, Object> clone = (NewGroupResult<String, Object>) g.clone();
				clone.setCount(entry.getValue());
				list.add(clone);
			}
			Collections.sort(list, new NewGroupResultComparator(ORDER.desc));
			return list;
		}
	}
	
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
