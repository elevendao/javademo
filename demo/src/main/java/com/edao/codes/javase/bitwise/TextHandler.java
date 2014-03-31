/**
 * 版权所有：美创科技
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2012-12-13
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2012-12-13
 */
package com.edao.codes.javase.bitwise;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * @author liushuai
 */
public class TextHandler {

	public static final char space = ' ';
	public static final char quote = '"';
	public static final char star = '*';
	public static final char backslash = '\\';
	public static final String AND = "AND";
	public static final String OR = "OR";
	public static final BitSet filterChar = new BitSet(256);
	public static final BitSet escapse = new BitSet(256);
	static {
		escapse.set('\\');
		escapse.set('+');
		escapse.set('-');
		escapse.set('&');
		escapse.set('|');
		escapse.set('!');
		escapse.set('(');
		escapse.set(')');
		escapse.set('{');
		escapse.set('}');
		escapse.set('[');
		escapse.set(']');
		escapse.set('^');
		escapse.set('~');
		escapse.set('*');
		escapse.set('?');
		escapse.set(':');
		escapse.set('"');
		escapse.set(';');
	}
	static {
		filterChar.set('>');
		filterChar.set('<');
		filterChar.set('=');
		filterChar.set('"');
		filterChar.set('#');
		filterChar.set('$');
		filterChar.set('%');
		filterChar.set('\'');
		filterChar.set(',');
		filterChar.set('/');
		filterChar.set('@');
		filterChar.set('_');
		filterChar.set('”');
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String text = "-P \\\\A";
		// text = args[0];
		System.out.println(text);
		TextHandler handler = new TextHandler();
		
		String column = "SQL语句", op = "like";
		column = "对象名";
		op = "like";
		String trans = handler.getTransCondition(text, column, op);
		System.out.println(trans);
	}
	

	private String getTransCondition(String value, String column, String op) {
		value = transEscapedIdentifier(value);
		boolean isSql = "SQL语句".equalsIgnoreCase(column.trim());
		
		if (isSql) {
			return transSqlText(value);
		} else {
			return transValue(value, op);
		}
	}

	/**
	 * 转化SQL语句为SOLR可查询字符串 
	 * @param sql
	 * @return
	 */
	private String transSqlText(String sql) {
		String[] token = cutWord(clearText(sql, false));
		StringBuffer buffer = new StringBuffer(256);
		for (int i = 0; i < token.length; i++) {
			buffer.append(quote).append(token[i]).append(quote).append(space);
		}
		return buffer.length() > 0 ? buffer.delete(buffer.length() - 1,
				buffer.length()).toString() : "";
	}

	/**
	 * 将特殊字符转义
	 * @param text
	 * @return
	 */
	private String transEscapedIdentifier(String text) {
		char[] chs = text.toCharArray();
		StringBuffer buffer = new StringBuffer(chs.length);
		for (int i = 0; i < chs.length; i++) {
			char ch = chs[i];
			if (escapse.get(ch)) {
				buffer.append(backslash).append(ch);
			} else {
				buffer.append(ch);
			}
		}
		return buffer.toString();
	}

	/**
	 * 将普通值转化为SOLR可查询字符串
	 * @param value
	 * @param op
	 * @return
	 */
	private String transValue(String value, String op) {
		boolean isLike = "like".equalsIgnoreCase(op.trim());
		if (!isLike) {
			return quote + value + quote;
		} else {
			String[] token = cutWord(clearText(value, false));
			StringBuffer buffer = new StringBuffer(256);
			for (int i = 0; i < token.length; i++) {
				buffer.append(star).append(token[i]).append(star).append(space);
			}
			return buffer.length() > 0 ? buffer.delete(buffer.length() - 1,
					buffer.length()).toString() : "";
		}
	}

	/**
	 * 分割单词
	 * 
	 * @param chs
	 * @return
	 */
	private String[] cutWord(char[] chs) {
		List<String> list = new ArrayList<String>(32);
		int len = chs.length;
		int soffset = 0;
		int eoffset = soffset;
		for (int i = 0; i < len; i++) {
			if (chs[i] == space) {
				if (i - 1 >= 0 && chs[i - 1] != space) {
					eoffset = i - 1;
					String word = String.copyValueOf(chs, soffset, eoffset
							- soffset + 1);
					list.add(word);
				}
				if (i + 1 < len && chs[i + 1] != space) {
					soffset = i + 1;
				}
			} else if (i == len - 1) {
				eoffset = len - 1;
				String word = String.copyValueOf(chs, soffset, eoffset
						- soffset + 1);
				list.add(word);
			}
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * 过滤特殊字符
	 * 
	 * @param text
	 * @return
	 */
	private char[] clearText(String text, boolean isFilter) {
		char[] chs = text.toCharArray();
		int len = chs.length;
		if (isFilter) {
			for (int i = 0; i < len; i++) {
				char ch = chs[i];
				if (filterChar.get(ch)) {
					chs[i] = space;
				}
			}
		}
		return chs;
	}
}
