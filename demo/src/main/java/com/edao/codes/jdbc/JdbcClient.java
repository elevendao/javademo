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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;
import java.util.Map;

/**
 * @author liushuai
 *
 */
public class JdbcClient {
	
	Connection conn;
	
	public JdbcClient(Connection conn) {
		this.conn = conn;
	}

	public void queryByStatement(String sql) {
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
//			ResultSetMetaData rsmd = rs.getMetaData();
//			if (rsmd != null) {
//				int colCount = rsmd.getColumnCount();
//				for (int i=1; i<=colCount; i++) {
//					String colName = rsmd.getColumnName(i);
//					int colType = rsmd.getColumnType(i);
//					System.out.println(colName + ", " + colType);
//				}
//			}
			if (rs != null) {
				while (rs.next()) {
					int orderId = rs.getInt(1);
					String name = rs.getString(2);
					int typeId = rs.getInt(3);
					Date date = rs.getDate(4);
					System.out.println(orderId + ", " + name + ", " + typeId + ", " + date);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void queryByPreparedStatement(String sql, Map<Object, Object> params) {
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, (Integer)params.get("orderid"));
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					int orderId = rs.getInt(1);
					String name = rs.getString(2);
					int typeId = rs.getInt(3);
					Date date = rs.getDate(4);
					System.out.println(orderId + ", " + name + ", " + typeId + ", " + date);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void execProcedure(String sql) {
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void execFunction(String sql, Map<Object, Object> params) {
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR); // 注册输出参数
			cs.setInt(2, (Integer) params.get(2));
			cs.executeQuery();
			String name = cs.getString(1);
			System.out.println(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
