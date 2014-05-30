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

import java.util.ArrayList;
import java.util.List;

import com.edao.codes.beans.NewGroupResult;
import com.edao.codes.solr.SolrJClient.GroupList;
import com.edao.codes.solr.constant.DataSource;
import com.edao.codes.solr.constant.QueryType;
import com.edao.codes.solr.impl.AuditDataSource;
import com.edao.codes.solr.impl.PivotFields;
import com.edao.codes.solr.impl.Query;
import com.edao.codes.solr.impl.SearchParam;
import com.edao.codes.solr.impl.SolrConfig;

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
		SearchParam param = new SearchParam(DataSource.ACCESS);
		DataSource ds = DataSource.ACCESS;
		String q = "*:*";
		String lgAdditon = "*:*";
		String fields = "dbuser,host,instance_name,dbname,appname,ip_address,action_level";
		fields = "appuser,host";
		PivotFields pfields = new PivotFields(ds, fields);
		Query query = new Query(false, QueryType.ADVANCED, q, lgAdditon);
		param.setQuery(query);
		param.setPivotFields(pfields);
		GroupList glist = null;
		glist = client.groupAccess(param);
		List<NewGroupResult<String, Object>> groups = glist.getGroups();
		showGroups(groups);
	}
	
	private void showGroups(List<NewGroupResult<String, Object>> groups) {
		for (int i=0; i<groups.size(); i++) {
			NewGroupResult<String, Object> g = groups.get(i);
			System.out.println(i + ", \t" + g);
		}
	}
	
}
