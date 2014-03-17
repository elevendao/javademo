/**
 * 版权所有：美创科技
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-2-10
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-2-10
 */
package com.edao.codes.solr;

import java.util.List;

import junit.framework.TestCase;

import com.edao.codes.beans.GroupResult;

/**
 * @author liushuai
 *
 */
public class SolrJClientTest extends TestCase {
	
	public void testPivotQuery() {
		String url = "http://172.16.4.118:8983/solr/1";
		//String url = "http://172.16.6.115:8983/solr/0";
		//String url = "http://172.16.6.115:8983/solr/access_201402";
		//String url = "http://172.16.4.121:8983/solr/1";
		SolrJClient client = new SolrJClient(url);
		PivotQuery query = new PivotQuery();
		query.setQuery("*:*");
		//query.setQuery("last_modified:[2014-02-11T13:38:59Z TO 2014-02-12T13:38:59Z]");
		//query.setQuery("last_modified:[2013-02-11T13:38:59Z TO 2013-03-12T13:38:59Z]");
		//query.setQuery("last_modified:[2013-02-31T13:52:39Z TO *]");
		//query.setPivots("ip_address,appname,cmdtype");
		//query.setPivots("ip_address,appname,cmdtype,rule_name,dbuser,host,mac_address");
		//query.setPivots("host,mac_address,cmdtype");
		//query.setPivots("host,cmdtype");
		//query.setPivots("host,mac_address,cmdtype");
		query.setPivots("ip_address,cmdtype");
		query.setPivots("ip_address,cmdtype,appname");
		query.setPivots("ip_address,cmdtype,appname,host");
		query.setPivots("ip_address,cmdtype,appname,host,dbuser");
		query.setPivots("ip_address,cmdtype,appname,host,dbuser,rule_name"); // oom
		query.setPivots("cmdtype,appname,host,dbuser,rule_name");  // oom
		query.setPivots("rule_name,host,dbuser,dbname,cmdtype,object_owner,object_name,object_type,os_user");
		SearchResult result = client.pivotQuery(query);
		List<GroupResult> items = result.getItems();
		int total = result.getTotalCount();
		int gtotal = 0;
		for (int i=0; i<items.size(); i++) {
			GroupResult gr = items.get(i);
			gtotal += gr.getCount();
			System.out.println(i + ", \t" + gr);
		}
		System.out.println("total : " + result.getTotalCount());
		System.out.println("gtotal : " + gtotal);
	}
	
	
	
//	public void testPivotQuery1() {
//		String url = "http://172.16.6.115:8983/solr/access_201401";
//		SolrJClient client = new SolrJClient(url);
//		PivotQuery query = new PivotQuery();
//		query.setQuery("*:*");
//		//query.setPivots("rule_name"); // error. pivot facet needs at least two fields.
//		query.setPivots("combined_level,cmdtype");
//		//query.setPivots("rule_name,cmdtype");
//		SearchResult result = client.pivotQuery(query);
//		List<GroupResult> items = result.getItems();
//		int total = result.getTotalCount();
//		int gtotal = 0;
//		for (GroupResult gr : items) {
//			gtotal += gr.count;
//			System.out.println(gr);
//		}
//		System.out.println("total : " + total);
//		assertEquals(total, gtotal);
//	}
	
//	public void testAddDocs() {
//		String url = "http://172.16.4.118:8983/solr/access_201312";
//		SolrJClient client = new SolrJClient(url);
//		List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
//		SolrInputDocument doc = new SolrInputDocument();
//		Map<String, Object> oper = new HashMap<String, Object>();
//		oper.put("set", "y");
//		doc.addField("id", "1510043");
//		doc.addField("review", oper);
//		docs.add(doc);
//		client.add(docs);
//	}
	
	
}
