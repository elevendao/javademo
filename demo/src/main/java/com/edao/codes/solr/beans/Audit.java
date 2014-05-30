/**
 * 版权所有：美创科技
 * 项目名称:capaa-web
 * 创建者: liushuai
 * 创建日期: 2013-2-26
 * 文件说明: 审计记录父类
 * 最近修改者：liushuai
 * 最近修改日期：2013-2-26
 */
package com.edao.codes.solr.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @author liushuai
 *
 */
public abstract class Audit {
	
	@Field("last_modified")
	private Date lastModified;
	private boolean lastmodifiedFlag = false;
	/**
	 * 返回事件ID
	 * @return 事件ID
	 */
	public abstract String getId();
	
	/**
	 * 返回数据源
	 * @return 数据源
	 */
	public abstract String getDataSource();
	
	/**
	 * 入库时间戳
	 * @return Date
	 */
	public Date getLastModified() {
		if (!lastmodifiedFlag) {
			lastModified = subtract(lastModified);
			lastmodifiedFlag = true;
		}
		return lastModified;
	}
	
	/**
	 * 入库时间戳
	 * @param lastModified Date
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	/**
	 * 从全文搜索中搜索返回的结果时间会差8小时，进行差额处理
	 * @param date Date
	 * @return Date
	 */
	protected Date subtract(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, -8);
		return calendar.getTime();
	}
	
	/**
	 * 从全文搜索中搜索返回的结果时间会差8小时，进行差额处理
	 * @param date Date
	 * @return Date
	 */
	protected Date add(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, 8);
		return calendar.getTime();
	}
	
	/**
	 * @return 返回用户
	 */
	public abstract String getUser();
}
