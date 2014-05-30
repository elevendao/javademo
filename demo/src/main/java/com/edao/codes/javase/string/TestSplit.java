package com.edao.codes.javase.string;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestSplit {
	public static void main(String[] args) {
		String input = "select  from ";
		String reg = " ";
//		String[] token = str.split(" ");
//		System.out.println(token.length);
//		Pattern p = Pattern.compile(reg);
//		Matcher m = p.matcher(input);
//		ArrayList<String> matchList = new ArrayList<String>(); 
//		int index = 0;
//		while (m.find()) {
//			System.out.println("start : " + index);
//			System.out.println("end : " + m.start());
//			String match = input.substring(index, m.start()).toString();
//			matchList.add(match);
//			index = m.end(); 
//		}
//		
//		int resultSize = matchList.size();
//		String[] result = new String[resultSize];
//		matchList.subList(0, resultSize).toArray(result);
//		
//		print(result);
//		cut("sss");
		testss();
	}
	
	public static void print(String[] obj) {
		if (obj == null) {
			System.out.println("null");
		} else if (obj.length == 0) {
			System.out.println("[]");
		} else {
			
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			sb.append(obj[0]);
			
			for (int i=1; i<obj.length; i++) {
				sb.append(",").append(obj[i]);
			}
			sb.append("]");
			
			System.out.println(sb.toString());
		}
	}
	
	public static String cut(String original) {
		String combinedInfo = "MMDRV.EXE | TPCC || 172.16.4.112 | LIGUOFEN |  | LIGUOFEN | 2014-05-21 10:23:21 |  |  |  |  |  | noregister |  | SELECT_SENSITIVE_TABLE | 2014-05-21 10:23:21 | sdfs";
		int index = 17;
		int p = 0;
		int nextp = 0;
		for (int i=0; i<index; i++) {
			p = combinedInfo.indexOf('|', nextp);
			nextp = p+1;
		}
		String bindvalue = combinedInfo.substring(nextp+1);
		System.out.println(bindvalue);
		return bindvalue;
	}
	
	public static void testss() {
		String aa = "simulreject | 模拟拒绝 | high | 高";
//		String[] arr = aa.split(" \\| ");
//		for (String s : arr){
//			System.out.println(s);
//		}
	}
}
