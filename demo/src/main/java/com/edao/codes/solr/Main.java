/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-4-17
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-4-17
 */
package com.edao.codes.solr;

import java.util.List;

import com.edao.codes.beans.GroupResult;
import com.edao.codes.solr.constant.DataSource;
import com.edao.codes.solr.constant.QueryType;
import com.edao.codes.solr.impl.PivotFields;
import com.edao.codes.solr.impl.Query;
import com.edao.codes.solr.impl.SearchParam;
import com.edao.codes.solr.impl.SearchResult;

/**
 * @author liushuai
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long d1 = System.currentTimeMillis();
		Main main = new Main();
		main.testPivot();
		long d2 = System.currentTimeMillis();
		System.out.println("time : " + (d2-d1) + "ms");
	}
	
	public void testPivot() {
		SolrJClient client = new SolrJClient();
		DataSource ds = DataSource.LOGON;
		ds = DataSource.ACCESS;
		SearchParam param = new SearchParam(ds);
		String q = "*:*";
		String lgAdditon = "*:*";
		String fields = "dbuser,host,instance_name,dbname,appname,ip_address,action_level";
//		fields = "appuser,host";
//		fields = "rule_name";
//		fields = "action_level";
		PivotFields pfields = new PivotFields(ds, fields);
		Query query = new Query(false, QueryType.ADVANCED, q, lgAdditon);
		param.setQuery(query);
		param.setPivotFields(pfields);
		SearchResult<GroupResult> result = client.group(param);
		List<GroupResult> groups = result.getItems();
		if (groups != null) {
			showGroups(groups);
			System.out.println("record count=" + result.getTotalCount());
		} else {
			System.out.println("groups is null.");
		}
	}
	
	private void showGroups(List<GroupResult> groups) {
		for (int i=0; i<groups.size(); i++) {
			GroupResult g = groups.get(i);
			System.out.println(i + ", \t" + g);
		}
		System.out.println("group count=" + groups.size());
		
	}
	
}
