/**
 * 版权所有：美创科技
 * 项目名称:capaa-web
 * 创建者: liushuai
 * 创建日期: 2013-3-7
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2013-3-7
 */
package com.edao.codes.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;


/**
 * @author liushuai
 *
 */
public final class DateUtil {
	
	/** date format : yyyy-MM-dd HH:mm:ss */
	public static DateFormat DATEFORMAT_DATABASE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** date format : HH:mm */
	public static DateFormat DATEFORMAT_HHMI = new SimpleDateFormat("HH:mm");
	/** date format : yyyyMMddHHmmss */
	public static DateFormat DATEFORMAT_NUMBERIC = new SimpleDateFormat("yyyyMMddHHmmss");
	/** date format : yyyy-MM-dd HH:mm */
	public static DateFormat DATEFORMAT_YMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/** date format : yyyy-MM-dd */
	public static DateFormat DF_YMD = new SimpleDateFormat("yyyy-MM-dd");
	/** date format : yyyyMM */
	public static DateFormat DF_YYYYMM = new SimpleDateFormat("yyyyMM");
	/** date format : yyyyMMdd */
	public static DateFormat DF_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	
	private DateUtil() {
	}
	
	/**
	 * 返回日期所在天
	 * @param dateStr 日期
	 * @return 天
	 */
	public static String getDay(String dateStr) {
		//2013-03-06 22:00:10 
		return dateStr.substring(dateStr.lastIndexOf('-') + 1, dateStr.indexOf(' '));
	}
	
	/**
	 * 返回日期所在小时
	 * @param dateStr 日期
	 * @return 小时
	 */
	public static String getHour(String dateStr) {
		return dateStr.substring(dateStr.indexOf(' ')+1, dateStr.lastIndexOf(':'));
	}
	
	/**
	 * 返回日期所在月份
	 * @param dateStr 日期
	 * @return 月
	 */
	public static String getMonth(String dateStr) {
		return dateStr.substring(dateStr.indexOf('-')+1, dateStr.lastIndexOf('-'));
	}
	
	/**
	 * 返回数组，分别为yyyy,mm,dd,hh,mi,ss,weekday
	 * token[0] -- yyyy
	 * token[1] -- mm
	 * token[2] -- dd
	 * token[3] -- hh
	 * token[4] -- mi
	 * token[5] -- ss
	 * token[6] -- weekday
	 * @param date 
	 * @return 日期各部分数组
	 */
	public static String[] getTimeToken(Date date) {
		String dateStr = DATEFORMAT_DATABASE.format(date);
		String[] token = new String[7];
		String[] parts = dateStr.split(" ");
		String[] ymd = parts[0].split("-");
		String[] hms = parts[1].split(":");
		token[0] = ymd[0]; // yyyy
		token[1] = ymd[1]; // mm
		token[2] = ymd[2]; // dd
		token[3] = hms[0]; // hh
		token[4] = hms[1]; // mi
		token[5] = hms[2]; // ss
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		token[6] = "" + c.get(Calendar.DAY_OF_WEEK); // week day
		
		return token;
	}
	
	/**
	 * 返回日期字符串
	 * @param date 日期
	 * @param format 格式
	 * @param field 偏移单位
	 * @param amount 偏移量
	 * @return 格式化日期字符串
	 */
	public static String date2String(Date date, DateFormat format, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		Date newDate = calendar.getTime();
		return format.format(newDate);
	}
	
	/**
	 * 返回日期字符串 格式为YYYYMM，偏移单位为MONTH
	 * @param date 日期
	 * @param amount 偏移量
	 * @return 格式化日期字符串
	 */
	public static String date2YyyyMm(Date date, int amount) {
		return date2String(date, DF_YYYYMM, Calendar.MONTH, amount);
	}
	
	/**
	 * 返回日期字符串 格式为YYYYMMDD，偏移单位为DAY_OF_MONTH
	 * @param date 日期
	 * @param amount 偏移量
	 * @return 格式化日期字符串
	 */
	public static String date2YyyyMmDd(Date date, int amount) {
		return date2String(date, DF_YYYYMMDD, Calendar.DAY_OF_MONTH, amount);
	}
	
	/**
	 * 传入时间范围，返回timeRange前此刻的时间
	 * 时间范围格式:数字+单位
	 * 单位:y(年)、m(月)、w(星期)、d(天)、h(小时)
	 * 例如，timeRange为3m，则返回值为3个月前的当前时刻Date
	 * @param timeRange 时间范围
	 * @return Date
	 */
	public static Date dateBefore(String timeRange) {
		Date start = null;
		Calendar calendar = Calendar.getInstance();
		if (StringUtils.isNotEmpty(timeRange)
				&& StringUtils.isNotBlank(timeRange) 
				&& !"*".equals(timeRange)) {
			char unit = timeRange.charAt(timeRange.length() - 1);
			int number = timeRange.length() > 1 ? Integer.valueOf(timeRange
					.substring(0, timeRange.length() - 1)) : Integer
					.valueOf(timeRange);
			switch (unit) {
			case 'y' : // 单位 年
				calendar.add(Calendar.YEAR, -number);
				break;
			case 'm' : // 单位 月
				calendar.add(Calendar.MONTH, -number);
				break;
			case 'w' : // 单位 星期
				calendar.add(Calendar.WEEK_OF_YEAR, -number);
				break;
			case 'd' : // 单位 天
				calendar.add(Calendar.DAY_OF_YEAR, -number);
				break;
			case 'h' : // 单位 小时
				calendar.add(Calendar.HOUR_OF_DAY, -number);
				break;
			default: // 默认单位 天
				calendar.add(Calendar.DAY_OF_YEAR, -Integer.valueOf(timeRange));
			}
			
			start = calendar.getTime();
		}
		return start;
	}
	
}
