/**
 * 版权所有：美创科技
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-2-17
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-2-17
 */
package com.edao.codes.jdbc;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.edao.codes.beans.GroupResult;
import com.edao.codes.solr.PivotQuery;
import com.edao.codes.solr.SearchResult;
import com.edao.codes.solr.SolrJClient;

/**
 * @author liushuai
 *
 */
public class JdbcTemplateTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.edao.codes.jdbc.JdbcTemplate#groupQuery(com.edao.codes.solr.PivotQuery)}.
	 */
	@Test
	public void testGroupQuery() {
		//fail("Not yet implemented");
		PivotQuery query = new PivotQuery();
		query.setQuery("*:*");
		//query.setPivots("ip_address,appname,cmdtype");
		//query.setPivots("ip_address,appname");
		//query.setPivots("ip_address,appname,cmdtype,rule_name");
		//query.setPivots("ip_address,appname,cmdtype,rule_name,dbuser");
		//query.setPivots("ip_address,appname,cmdtype,rule_name,dbuser,host");
		//query.setPivots("ip_address,appname,cmdtype,rule_name,dbuser,host,mac_address");
		//query.setPivots("host,cmdtype");
		query.setPivots("host,mac_address,cmdtype");
		
		JdbcTemplate t = new JdbcTemplate();
		SearchResult result1 = null;
		try {
			result1 = t.groupQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<GroupResult> items1 = result1.getItems();
		int gtotal = 0;
		for (int i=0; i<items1.size(); i++) {
			GroupResult gr = items1.get(i);
			gtotal += gr.getCount();
			System.out.println(i + ", \t" + gr);
		}
		System.out.println("total1 : " + result1.getTotalCount());
//		
//		String url = "http://172.16.4.121:8983/solr/0";
//		SolrJClient client = new SolrJClient(url);
//		SearchResult result2 = client.pivotQuery(query);
//		List<GroupResult> items2 = result2.getItems();
//		int gtotal2 = 0;
//		for (int i=0; i<items2.size(); i++) {
//			GroupResult gr = items2.get(i);
//			gtotal2 += gr.getCount();
//			System.out.println(i + ", " + gr);
//		}
//		
//		System.out.println("total1 : " + result2.getTotalCount());
//		for (int i = 0; i < items1.size(); i++){
//			assertEquals(items1.get(i), items2.get(i));
//		}
	}

}
