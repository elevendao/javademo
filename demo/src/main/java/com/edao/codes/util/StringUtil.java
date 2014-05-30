/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-5-30
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-5-30
 */
package com.edao.codes.util;

import java.util.regex.Pattern;

/**
 * @author liushuai
 *
 */
public class StringUtil {

	static String regexIpAddr = "^(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])$";
	public static boolean isIpAddress(String val) {
		return val == null || "".equals(val.trim()) ? false : Pattern.matches(
				regexIpAddr, val.trim());
	}
}
