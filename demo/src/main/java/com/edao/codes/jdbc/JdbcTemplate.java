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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery.ORDER;

import com.edao.codes.beans.GroupResult;
import com.edao.codes.beans.GroupResultComparator;
import com.edao.codes.solr.PivotQuery;
import com.edao.codes.solr.SearchResult;

/**
 * @author liushuai
 *
 */
public class JdbcTemplate {
	Connection conn = null;
	
	JdbcTemplate() {
		String jdbcUrl = "jdbc:oracle:thin:@172.16.6.115:1578:capaa";
		String jdbcUser = "asset";
		String jdbcPassword = "hzmc321#";
		
		conn = ConnectionUtil.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
	}
	
	public SearchResult groupQuery(PivotQuery pivotQuery) throws SQLException {
		String[] columns = pivotQuery.getPivots().split(",");
		String sql = "select " + pivotQuery.getPivots()
				+ ", count(*) times from mc$audit_session_trail group by "
				+ pivotQuery.getPivots();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<GroupResult> results = new ArrayList<GroupResult>();
		int groupCount = 0;
		if (rs != null) {
			while (rs.next()) {
				int count = rs.getInt("times");
				StringBuilder gVal = new StringBuilder(64);
				gVal.append(getVal(rs, columns[0]));
				for (int i = 1; i < columns.length; i++) {
					gVal.append(",").append(getVal(rs, columns[i]));
				}
				results.add(new GroupResult(gVal.toString().toUpperCase(), count));
				groupCount++;
			}
			Collections.sort(results, new GroupResultComparator(ORDER.desc));
		}
		SearchResult result = new SearchResult();
		result.setItems(results);
		result.setTotalCount(groupCount);
		return result;
	}
	
	String getVal(ResultSet rs, String column) throws SQLException {
		String val = rs.getString(column);
		return val == null ? "" : val;
	}
}
