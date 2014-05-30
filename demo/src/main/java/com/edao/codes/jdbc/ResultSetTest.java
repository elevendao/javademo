/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-4-24
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-4-24
 */
package com.edao.codes.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author liushuai
 *
 */
public class ResultSetTest {

	static String jdbcUrl = "jdbc:oracle:thin:@172.16.4.118:1521:capaa";
	static String jdbcUser = "asset";
	static String jdbcPassword = "hzmc321#";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//select * from mc$audit_attack_trail;
		Connection conn = ConnectionUtil.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
	}
	
	class QueryExecutor {
		private Connection conn;
		private int interval = 30000;
		private long current = System.currentTimeMillis();
		public QueryExecutor(Connection conn) {
			this.conn = conn;
		}
		
		public void run() {
			
		}
		
		public void query() {
			String sql = "select id from mc$audit_attack_trail";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					BigDecimal id = rs.getBigDecimal(1);
					System.out.println(id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	class InsertExecutor {
		private Connection conn;
		private int interval = 30000;
		private long current = System.currentTimeMillis();
		public InsertExecutor(Connection conn) {
			this.conn = conn;
		}
		
		public void run() {
			
		}
		
		public void insert() {
			String sql = "insert into mc$audit_attack_trail(id, euser) values(SEQ_AUDIT_ATTACK_TRAIL, ?)";
			
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				for (int i=0; i<5; i++) {
					ps.setString(1, "euser"+i);
					ps.addBatch();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
