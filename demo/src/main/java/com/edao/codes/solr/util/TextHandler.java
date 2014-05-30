/**
 * 版权所有：美创科技
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2012-12-13
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2012-12-13
 */
package com.edao.codes.solr.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.edao.codes.solr.beans.AccessAudit;

/**
 * @author liushuai
 */
public class TextHandler {

	/**
	 * 一天的秒数
	 */
    public static final int SECONDS_ONE_DAY = 24*60*60;
    
    private static Locale locale = Locale.CHINA;
    public static DateFormat DATEFORMAT_DATABASE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static DateFormat DATEFORMAT_SOLR = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    public static DateFormat DATEFORMAT_NUMBERIC = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final char space = ' ';
	public static final char quote = '"';
	public static final char star = '*';
	public static final char backslash = '\\';
	public static final String AND = "AND";
	public static final String OR = "OR";
	public static final BitSet filterChar = new BitSet(256); // filter special character
	public static final BitSet escapse = new BitSet(256); // escape special character
	public static final BitSet transFilterChar = new BitSet(256);
	String and = " AND ";
	String or = " OR ";
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
		escapse.set('/');
	}
	static {
		filterChar.or(escapse);
		filterChar.set(',');
		filterChar.set('>');
		filterChar.set('<');
		filterChar.set('=');
		filterChar.set('#');
		filterChar.set('$');
		filterChar.set('%');
		filterChar.set('\'');
		filterChar.set('@');
		//filterChar.set('_');
		filterChar.set('”');
		
		filterChar.clear('*');
		filterChar.clear('-');
		filterChar.clear(':');
	}
	
	static {
		transFilterChar.set(',');
		transFilterChar.set('\'');
	}
	
	private static TextHandler instance = null;
	
	private TextHandler() {
	}
	
	public static TextHandler getInstance() {
		if (instance == null) {
			instance = new TextHandler();
		}
		return instance;
	}
	
	/**
	 * 返回关键字数组
	 * @param query
	 * @return
	 */
	public static String[] getKeyWords(String query) {
		char[] chs = query.toCharArray();
		int len = chs.length;
		for (int i = 0; i < len; i++) {
			char ch = chs[i];
			if (transFilterChar.get(ch)) {
				chs[i] = space;
			}
		}
		return cutWord(chs);
	}
	
	public static String joinWith(String[] tokens, String connector) {
		StringBuffer sb = new StringBuffer();
		for (String token : tokens) {
			sb.append(token).append(connector);
		}
		if (sb.length() > connector.length()) {
			sb.delete(sb.length() - connector.length(), sb.length());
		}
		return sb.toString();
	}
	
	public static String joinWith(Collection<String> c, String connector) {
		StringBuffer sb = new StringBuffer();
		for (String token : c) {
			sb.append(token).append(connector);
		}
		if (sb.length() > connector.length()) {
			sb.delete(sb.length() - connector.length(), sb.length());
		}
		return sb.toString();
	}
	
	/**
	 * 根据表达式获取转换后的可查询的SOLR语句
	 * @param value 
	 * @param column 
	 * @param op 
	 * @return 
	 */
	public String getTransCondition(String value, String column, String op) {
		boolean isSql = "SQL语句".equalsIgnoreCase(column.trim())
				|| "\"$SQLTEXT\"".equalsIgnoreCase(column.trim());
		
		if (isSql) {
			if ("like".equals(op) || "not like".equals(op)) {
				return preproccess(value, true);
			}
			return preproccess(value, false);
		} else {
			value = escapeChar(value);
			return transValue(value, op);
		}
	}

	/**
	 * 对简单搜索的预处理
	 * @param text
	 * @return
	 */
	public String transSimpleQuery(String text) {
		return preproccess(text, true);
	}

	/**
	 * 对分词字段的查询预处理
	 * @param text
	 * @return
	 */
	public static String preproccess(String text, boolean isFilter) {
		text = text.toLowerCase();
		char[] chs = clearText(text, isFilter);
		return escapeChar(chs);
	}
	
	/**
	 * 过滤特殊字符，将特殊字符用空格替换
	 * @param chs
	 * @return
	 */
	private static String escapeChar(char[] chs) {
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
	 * 将特殊字符转义
	 * @param text
	 * @return
	 */
	private static String escapeChar(String text) {
		char[] chs = text.toCharArray();
		return escapeChar(chs);
	}

	/**
	 * 将普通值转化为SOLR可查询字符串
	 * @param value
	 * @param op
	 * @return
	 */
	private String transValue(String value, String op) {
		boolean isLike = "like".equalsIgnoreCase(op.trim()) || "not like".equalsIgnoreCase(op.trim());
		if (!isLike) {
			return quote + value + quote;
		} else {
			String[] token = cutWord(clearText(value));
			StringBuffer buffer = new StringBuffer(256);
			for (int i = 0; i < token.length; i++) {
				buffer.append(star).append(token[i]).append(star).append(space);
			}
			return buffer.length() > 0 ? buffer.delete(buffer.length() - 1,
					buffer.length()).toString() : "";
		}
	}
	/**
	 * 将普通值转化为SOLR可查询字符串
	 * @param value 
	 * @param opType 
	 * @return String
	 */
//	public static String processStringValue(String value, int opType) {
//		value = escapeChar(value);
//		if (opType == RuleParserTreeTreeConstants.JJTLIKE
//				|| opType == RuleParserTreeTreeConstants.JJTNOT_LIKE) {
//			String[] token = cutWord(clearText(value));
//			StringBuffer buffer = new StringBuffer(256);
//			for (int i = 0; i < token.length; i++) {
//				buffer.append(star).append(token[i]).append(star).append(space);
//			}
//			return buffer.length() > 0 ? buffer.delete(buffer.length() - 1,
//					buffer.length()).toString() : "";
//		} else {
//			return quote + value + quote;
//		}
//	}
	/**
	 * 对in , not in操作的值做处理
	 * @param value
	 * @param columnType
	 * @return
	 */
	public String transInValue(String value, String columnType) {
		String[] tokens = cutWord(clearText(value.substring(1, value.length()-1)), ',');
		StringBuffer buffer = new StringBuffer(value.length());
		buffer.append("(");
		String val = "";
		for (int i = 0; i < tokens.length; i++) {
			if ("".equals(tokens[i].trim())) {
				continue;
			}
			if ("date".equals(columnType)) {
				val = transDate(tokens[i].trim());
			} else {
				val = escapeChar(tokens[i].trim());
			}
			if ("".equals(val)) {
				continue;
			}
			buffer.append(quote).append(val).append(quote);
			buffer.append(space).append(OR).append(space);
		}
		if (buffer.length() > or.length()) {
			buffer.delete(buffer.length() - or.length(), buffer.length());
		}
		buffer.append(")");
		
		return buffer.toString();
	}
	
	/**
	 * 分割单词，默认用空格分割
	 * @param chs
	 * @return
	 */
	private static String[] cutWord(char[] chs) {
		return cutWord(chs, space);
	}

	/**
	 * 分割单词
	 * 
	 * @param chs
	 * @return
	 */
	private static String[] cutWord(char[] chs, char separator) {
		List<String> list = new ArrayList<String>(32);
		int len = chs.length;
		int soffset = 0;
		int eoffset = soffset;
		for (int i = 0; i < len; i++) {
			if (chs[i] == separator) {
				if (i - 1 >= 0 && chs[i - 1] != separator) {
					eoffset = i - 1;
					String word = String.copyValueOf(chs, soffset, eoffset
							- soffset + 1);
					list.add(word);
				}
				if (i + 1 < len && chs[i + 1] != separator) {
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
	 * 过滤特殊字符，默认不过滤
	 * 
	 * @param text
	 * @return
	 */
	private static char[] clearText(String text) {
		return clearText(text, false);
	}

	/**
	 * 过滤特殊字符
	 * 
	 * @param text
	 * @return
	 */
	private static char[] clearText(String text, boolean isFilter) {
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
	
	public static String transDate(String inputStr,DateFormat dateFormat) {
		 if (inputStr==null || "".equals(inputStr.trim())) { 
	        	return "";
	        }
	        
	        inputStr = inputStr.trim().toLowerCase();
	        if (inputStr.startsWith("sysdate")) {
	        	inputStr = inputStr.replaceAll(" ", "");
	        	Calendar calendar = Calendar.getInstance(locale);
	        	Date current = null;
	        	int len = inputStr.length();
	        	int sysdateLen = 7;
	        	char div = '/';
	            
	            if (len == sysdateLen) {
	                current = calendar.getTime();
	            } else if (len > sysdateLen) {
	                char op = inputStr.charAt(sysdateLen);
	                String boudary = inputStr.substring(sysdateLen+1, len);
	                if ("".equals(boudary)) {
	                	return "";
	                }
	                int posOfSlash = boudary.indexOf(div);
	                if (posOfSlash > 0) {
						try {
							String numeratorStr = boudary.substring(0, posOfSlash).trim();
							String denominatorStr = boudary.substring(posOfSlash+1).trim();
							int numerator = Integer.valueOf(numeratorStr);
							int denominator = Integer.valueOf(denominatorStr);
		                    int seconds = numerator * SECONDS_ONE_DAY / denominator;
		                    
		                    if (op == '-') {
		                        calendar.add(Calendar.SECOND, -seconds);
		                    } else if (op == '+') {
		                        calendar.add(Calendar.SECOND, seconds);
		                    }
						} catch (NumberFormatException e) {
							return "";
						}
	                } else if (posOfSlash < 0) {
	                    int amout;
						try {
							amout = Integer.valueOf(boudary);
						} catch (NumberFormatException e) {
							return "";
						}
	                    if (op == '-') {
	                        calendar.add(Calendar.DAY_OF_MONTH, -amout);
	                    } else if (op == '+') {
	                        calendar.add(Calendar.DAY_OF_MONTH, amout);
	                    }
	                } else {
	                	return "";
	                }
	                current = calendar.getTime();
	            }
	            
	            return dateFormat.format(current);
	        } else {
	        	Date date = null;
	            try {
	    			date = DATEFORMAT_DATABASE.parse(inputStr);
	    		} catch (ParseException e) {
	    			return "";
	    		}
	        	return dateFormat.format(date);
	        }
	}
	/**
     * 将普通时间字符串转换成solr可查询的时间字符串
     * 
     * 其中sysdate为默认参数，表示当前时间，可以用加减表示当前时间上一时刻或下一时刻
     * 单位为天
     * 如：
     *    sysdate - 1       昨天 
     *    sysdate + 1       明天
     *    sysdate + 1/24    加一小时
     *    sysdate - 1/24    减一小时
     *    sysdate + 1/1440  加一分钟
     *    sysdate - 1/1440  减一分钟
     *    sysdate + 1/86400 加一秒钟
     *    sysdate - 1/86400 减一秒钟
     *    
     * @param inputStr 时间字符串
     * @return solr可查询的时间字符串
     */
    public static String transDate(String inputStr) {
    	return transDate(inputStr, DATEFORMAT_SOLR);
    }

    /**
     * 将日期Date转换为SOLR日期字符串
     * @param date 日期
     * @return solr日期字符串
     */
    public static String transDate(Date date) {
    	if (date == null) {
    		return "";
    	}
    	
    	String solrDate = "";
    	solrDate = DATEFORMAT_SOLR.format(date);
    	
    	return solrDate;
    }
    
    private static DecimalFormat idformat = new DecimalFormat("00000.#");
	public static String getAccessAuditIdCopy(AccessAudit access) {
		String prefix = DATEFORMAT_NUMBERIC.format(access.getOptime());
		long id = Long.valueOf(access.getId());
		String suffix = idformat.format(id % 100000);
		return prefix+suffix;
	}
}
