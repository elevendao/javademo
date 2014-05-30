package com.edao.codes.javase.string;
import java.text.MessageFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 版权所有：美创科技
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2013-10-22
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2013-10-22
 */

/**
 * @author liushuai
 *
 */
public class HtmlDecorater {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String template = "<font color='blue'>{0}</font>[{1}]在[{2}]上使用[{3}]登录"
				+ "<font color='#2128b0'>{4}</font>数据库，响应行为<font color='red'>{5}</font>。";
		
		template = "<font color='blue'>{0}</font>[{1}]在[{2}]上进行了<font color='#2128b0'>[{3}]</font>操作，响应行为<font color='red'>{5}</font>。";
		
		Object[] arguments = {new Date(), "user", "172.16.4.121", "sqlplus", "orcl", "通过"};
		String html = fillHtml(template, arguments);
		System.out.println(html);
	}

	public static String fillHtml(String template, Object[] args) {
		
		Pattern pattern = Pattern.compile("(\\{[a-zA-Z0-9_]+\\})");
		Matcher matcher = pattern.matcher(template);
		
		StringBuffer result = new StringBuffer();
		int lastOffset = 0;
		int offset = 0;
		while (matcher.find()) {
			String target = matcher.group();
			String getter = target.substring(1, target.length()-1);
			
			offset = matcher.start();
			result.append(template.substring(lastOffset, offset));
			result.append(args[Integer.valueOf(getter)]);
			lastOffset = matcher.end();
		}
		result.append(template.substring(lastOffset, template.length()));
		
		return result.toString();
	}

}
