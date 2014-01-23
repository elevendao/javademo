/**
 * 版权所有：美创科技
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2013-7-19
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2013-7-19
 */
package com.edao.codes.format;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author liushuai
 *
 */
public class DateUtils {
	
	private Map<String, SimpleDateFormat> formatMap = new HashMap<String, SimpleDateFormat>();
	
	String format(Date date, String format) {
		SimpleDateFormat sdf = getFormat(format);
		return sdf.format(date);
	}
	
	private SimpleDateFormat getFormat(String format) {
		SimpleDateFormat sdf = formatMap.get(format);
		if (sdf == null) {
			sdf = new SimpleDateFormat(format);
			formatMap.put(format, sdf);
		}
		
		return sdf;
	}
	
	private void showFormatMap() {
		Set<String> patternSet = formatMap.keySet();
		for (String pattern : patternSet) {
			System.out.println(pattern);
		}
	}
	
	public static void main(String[] args) {
		DateUtils dateUtils = new DateUtils();
		
		Date date = new Date();
		String str;
		try {
			str = dateUtils.format(date, "yyyy-MM-ddfsdfs");
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dateUtils.showFormatMap(); //empty
		try {
			str = dateUtils.format(date, "yyyy-MM-dd");
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace(); // not happen
		}
		dateUtils.showFormatMap(); // have one
		try {
			str = dateUtils.format(date, "");
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace(); // not happen
		}
		dateUtils.showFormatMap(); // have one
	}
}
