/**
 * 版权所有：liushuai
 * 项目名称:test
 * 创建者: liushuai
 * 创建日期: 2012-10-12
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2012-10-12
 */
package com.edao.codes.format;

import java.text.DecimalFormat;


/**
 * @author liushuai
 *
 */
public class NumberFormat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(format(11111, "000000"));
	}
	
	public static String format(Integer num, String format) {
		DecimalFormat df = new DecimalFormat(format);
		String str = df.format(num);
		return str;
	}
}
