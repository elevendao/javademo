package com.edao.codes.javase.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Testip {
	public static void main(String[] args) {
		//String regexstr = "^(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5])$";
		//String ipstr = "172.16.4.23";
//		String regexstr = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]|\\*)$";
//		String ipstr = "203.204.23.*";
//		String regex = "^\\s*(0\\d|1\\d|2[0-3])\\s*\\:\\s*(0\\d|[1-5]\\d)\\s*-\\s*(0\\d|1\\d|2[0-3])\\s*\\:\\s*(0\\d|[1-5]\\d)\\s*$";
//		String str = "   12  :  00 - 00  : 00  ";
//		Pattern parttern = Pattern.compile(regex);
//		Matcher matcher = parttern.matcher(str);
//		boolean ismatch = matcher.matches();
//		System.out.println(ismatch);
//		String str1 = "12";
//		String str2 = "2";
//		boolean b = Integer.valueOf(str1) < Integer.valueOf(str2);
//		System.out.println(b);
//		String str3 = "172.15.4.5,172.16.4.5��17234234,sfsf";
//		System.out.println(str3);
//		System.out.println(str3.replace("��", ","));
//		System.out.println(Integer.valueOf("06"));
//		System.out.println(compare_time("23:59", "23:59"));
//		String str = "  , ,,sdfsadf   , ,asdfas, ,";
		String str = "";
		List list = splitByComma(str);
		System.out.println(list.size());
	}
	private static List<String> splitByComma(String str) {
		if (str==null) return null;
		List<String> list = new ArrayList<String>();
		String[] token = str.split(",");
		for (int i=0; i<token.length; i++) {
			if ("".equals(token[i]) || "".equals(token[i].trim())) continue;
			list.add(token[i].trim());
		}
		
		return list;
	}
	
	private static int compare_time(String t1, String t2) {//t1<t2 -1 t1==t2 0 t1>t2 1
		String[] token1 = t1.split(":");
		String[] token2 = t2.split(":");
		int h1 = Integer.valueOf(token1[0].trim());
		int h2 = Integer.valueOf(token2[0].trim());
		int m1 = Integer.valueOf(token1[1].trim());
		int m2 = Integer.valueOf(token2[1].trim());
		if (h1 > h2) {
			return 1;
		} else if (h1 == h2) {
			if (m1>m2)
				return 1;
			else if (m1 == m2)
				return 0;
		}
		return -1;
	}
}
