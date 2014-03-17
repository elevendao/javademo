/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-3-4
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-3-4
 */
package com.edao.codes.jdbc;

import java.sql.Connection;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author liushuai
 *
 */
public class JdbcClientTest {
	
	JdbcClient client = null;
	Connection conn = null;
	@Before
	public void setUp() throws Exception {
		String jdbcUrl = "jdbc:oracle:thin:@172.16.4.118:1521:capaa";
		String jdbcUser = "asset";
		String jdbcPassword = "hzmc321#";
		conn = ConnectionUtil.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
		client = new JdbcClient(conn);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * Test method for {@link com.edao.codes.jdbc.JdbcTemplate#groupQuery(com.edao.codes.solr.PivotQuery)}.
	 */
	//@Test
	public void testQueryByStatement() {
		String sql = "select order_id, name, typeid, orderdate from t_order where order_id";
		client.queryByStatement(sql);
	}
	
	//@Test
	public void testQueryByPreparedStatement() {
		String sql = "select order_id, name, typeid, orderdate from t_order where order_id = ?";
		HashMap<Object, Object> params = new HashMap<Object, Object>();
		params.put("orderid", 2);
		client.queryByPreparedStatement(sql, params);
	}
	
	//@Test
	public void testExecProcedure() {
		String sql = "{ call my_pack.order_update_ename(2, 'cde') }";
		client.execProcedure(sql);
		testQueryByPreparedStatement();
	}
	@Test
	public void testExecFunction() {
		String sql = "{ ? = call my_pack.get_order_name( ? ) }";
		HashMap<Object, Object> params = new HashMap<Object, Object>();
		params.put(2, 3);
		client.execFunction(sql, params);
	}
}
