package com.edao.codes.jdbc.datatransport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author liushuai
 *
 */
public class Main {
	
	public static final Logger LOG = Logger.getLogger(Main.class);
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws SQLException, IOException {
		Properties dest = new Properties();
		dest.put("url", "jdbc:oracle:thin:@172.16.6.141:1521:mc10g");
		dest.put("user", "asset26");
		dest.put("password", "asset26");
		
		Properties src = new Properties();
		src.put("url", "jdbc:oracle:thin:@172.16.10.114:1578:capaa");
		src.put("user", "asset");
		src.put("password", "hzmc321#");
		
		String logonTableName = "MC$AUDIT_SESSION_TRAIL";
		String accessTableName = "MC$AUDIT_TRAIL";
		//getColumns(dest, tableName);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean isRunning = true;
		boolean lf = false;
		boolean af = false;
		String start = "start ";
		while (isRunning) {
			String line = br.readLine();
			line = line.trim();
			System.out.println(line);
			if (line.startsWith("start ")) {
				String n = line.substring(start.length());
				int type = Integer.valueOf(n);
				if (type == 0 && !lf) {
					trasportLogon(src, dest, logonTableName);
					lf = true;
				} else if (type == 1 && !af) {
					trasportAccess(src, dest, accessTableName);
					af = true;
				}
			} else if ("exit".equals(line)) {
				isRunning = false;
			}
		}
		if (!isRunning) {
			System.exit(1);
		}
	}
	
	public static void trasportLogon(Properties src, Properties dest, String tableName) throws SQLException {
		StringBuffer buffer = new StringBuffer(256);
		File f = new File("wherel.txt");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String line = "";
			
			while ((line = br.readLine()) != null) {
				buffer.append(line).append(" ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String wheres = buffer.toString();
		System.out.println(wheres);
		trasport(src, dest, tableName, wheres);
	}
	
	public static void trasportAccess(Properties src, Properties dest, String tableName) throws SQLException {
		File f = new File("wherea.txt");
		StringBuffer buffer = new StringBuffer(256);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String line = "";
			
			while ((line = br.readLine()) != null) {
				buffer.append(line).append(" ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String wheres = buffer.toString();
		System.out.println(wheres);
		trasport(src, dest, tableName, wheres);
	}
	
	public static void trasport(Properties src, Properties dest, String tableName, String wheres) throws SQLException {
		Connection connsrc = ConnectionUtil.getConnection(src.getProperty("url"),
				src.getProperty("user"), src.getProperty("password"));
		Connection conndest = ConnectionUtil.getConnection(dest.getProperty("url"),
				dest.getProperty("user"), dest.getProperty("password"));
		
		String query = "select * from " + tableName + " " + wheres;
		Statement stmt = connsrc.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		ResultSetMetaData meta = rs.getMetaData();
		int columnCount = meta.getColumnCount();
		List<ColumnInfo> columnList = new ArrayList<ColumnInfo>();
		StringBuffer buffer = new StringBuffer(256);
		buffer.append("insert into " + tableName + "(");
		for (int i=1; i<columnCount; i++) {
			String column = meta.getColumnName(i);
			String typeName = meta.getColumnTypeName(i);
			columnList.add(new ColumnInfo(column, typeName));
			buffer.append(column).append(",");
		}
		buffer.replace(buffer.length()-1, buffer.length(), ") values (");
		buffer.append("?");
		for (int i=1; i<columnList.size(); i++) {
			buffer.append(",?");
		}
		buffer.append(")");
		
		System.out.println(buffer.toString());
		PreparedStatement ps = conndest.prepareStatement(buffer.toString());
		int count = 0;
		while (rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i=0; i<columnList.size(); i++) {
				ColumnInfo column = columnList.get(i);
				String colName = column.getColumnName();
				switch(column.getColumnType()) {
				case NUMBER:
					map.put(colName, rs.getBigDecimal(colName));
					break;
				case DATE:
					map.put(colName, rs.getTimestamp(colName));
					break;
				default: //VARCHAR2
					map.put(colName, rs.getString(colName));
				}
			}
			
			for (int i=0; i<columnList.size(); i++) {
				ColumnInfo column = columnList.get(i);
				String colName = column.getColumnName();
				Object v = map.get(colName);
				if ("ID".equals(colName)) {
					LOG.info(v);
				}
				if (v == null) {
					ps.setNull(i+1, java.sql.Types.NULL);
				} else {
					switch(column.getColumnType()) {
					case NUMBER:
						ps.setBigDecimal(i+1, (BigDecimal) v);
						break;
					case DATE:
						ps.setTimestamp(i+1, (Timestamp) v);
						break;
					default: //VARCHAR2
						ps.setString(i+1, (String) v);
					}
				}
			}
			
			
			ps.addBatch();
			if (count % 100 == 0) {
				ps.executeBatch();
			}
			
		}
		if (count % 100 != 0) {
			ps.executeBatch();
		}
	}
	
	public static List<String> getColumns(Properties p, String tableName) throws SQLException {
		Connection conn = ConnectionUtil.getConnection(p.getProperty("url"),
				p.getProperty("user"), p.getProperty("password"));
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet rs = meta.getColumns(null, "%", tableName, "%");
		List<String> list = new ArrayList<String>();
		while (rs.next()) {
			String column = rs.getString("COLUMN_NAME");
			System.out.println(column);
			list.add(column);
		}
		conn.close();
		return list;
	}

}

enum ColumnType {
	DATE,VARCHAR2,NUMBER
}
class ColumnInfo {
	String columnName;
	String columnType;
	public ColumnInfo() {
		
	}
	public ColumnInfo(String name, String type) {
		columnName = name;
		columnType = type;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
//	public String getColumnType() {
//		return columnType;
//	}
	public ColumnType getColumnType() {
		return ColumnType.valueOf(columnType);
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	
}
