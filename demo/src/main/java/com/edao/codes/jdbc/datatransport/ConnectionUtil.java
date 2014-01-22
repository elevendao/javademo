package com.edao.codes.jdbc.datatransport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtil {
	
	public static Connection getConnection() {
		String jdbcUrl = "jdbc:oracle:thin:@172.16.4.121:1521:orcl";
		String jdbcUser = "scott";
		String jdbcPassword = "scott";
//		String jdbcUrl = "jdbc:oracle:thin:@172.16.4.117:1521:ora9i";
//		String jdbcUser = "asset";
//		String jdbcPassword = "asset";
		//String jdbcUrl = "jdbc:oracle:thin:@172.16.5.30:1521:mcora10g";
//		String jdbcUrl = "jdbc:oracle:thin:@172.16.4.200:1522/gg11";
//		String jdbcUser = "asset";
//		String jdbcPassword = "asset";
		
		
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getConnection(String jdbcUrl, String user, String password) {
		if (jdbcUrl == null || jdbcUrl.equals("")) return null;
		if (user == null || user.equals("")) return null;
		if (password == null) return null;
		
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Class.forName("com.mysql.jdbc.Driver");
			
			//conn = DriverManager.getConnection(jdbcUrl, user, password);
			Properties initProps = new Properties();
			initProps.setProperty("user", user);
            initProps.setProperty("password", password);
			conn = DriverManager.getConnection(jdbcUrl, initProps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		Connection conn = ConnectionUtil.getConnection();
	}
}
