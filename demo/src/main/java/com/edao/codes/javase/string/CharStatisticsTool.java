/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-2-21
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-2-21
 */
package com.edao.codes.javase.string;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liushuai
 *
 */
public class CharStatisticsTool {

	private static final int CHAR_ONE_BYTE_MASK = 0xFFFFFF80;

	private static final int CHAR_TWO_BYTES_MASK = 0xFFFFF800;

	private static final int CHAR_THREE_BYTES_MASK = 0xFFFF0000;

	private static final int CHAR_FOUR_BYTES_MASK = 0xFFE00000;

	private static final int CHAR_FIVE_BYTES_MASK = 0xFC000000;

	private static final int CHAR_SIX_BYTES_MASK = 0x80000000;
	  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input = "我是aabbcc";
		//count(input);
		//count2(input);
		try {
			substring(input, 5);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static void count(String input) {
		System.out.println(input.getBytes().length);
		System.out.println(input.length());
		int nCount = 0;
		int eCount = 0;
		int cCount = 0;
		char[] chs = new char[input.length()];
		input.getChars(0, chs.length, chs, 0);
		for (int i=0; i<chs.length; i++) {
			char ch = chs[i];
			int byteCount = String.valueOf(ch).getBytes().length;
			if (byteCount > 1) {
				cCount++;
			} else if (ch >= 'a' && ch <= 'z') {
				eCount++;
			} else if (ch >= 'A' && ch <= 'Z') {
				eCount++;
			} else if (ch >= '0' && ch <= '9') {
				nCount++;
			}
		}
		
		System.out.println("number count="+nCount);
		System.out.println("english char count="+eCount);
		System.out.println("chinese char count="+cCount);
	}
	
	public static void count2(String input) {
		HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
		for (int i=0; i<input.length(); i++) {
			char ch = input.charAt(i);
			if (dict.containsKey(ch)) {
				dict.put(ch, dict.get(ch)+1);
			} else {
				dict.put(ch, 1);
			}
		}
		for (Map.Entry<Character, Integer> en : dict.entrySet()) {
			System.out.println(en.getKey() + ", " + en.getValue());
		}
	}
	
	public static void substring(String input, int byteCount) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (byteCount > 0) {
			char ch = input.charAt(i);
			//int charBytes = String.valueOf(ch).getBytes().length;
			int numbPerChar = countNumberPerChar(ch);
			byteCount = byteCount - numbPerChar;
			i++;
			if (i > input.length()) {
				break;
			}
			//System.out.println(i + "," + charBytes + ", " + byteCount);
			sb.append(ch);
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * return the numbers of bytes that hold an Unicode char
	 * @param ch
	 * @return
	 */
	public static int countNumberPerChar(char ch) {
		if ((ch & CHAR_ONE_BYTE_MASK) == 0) {
			return 1;
		} else if ((ch & CHAR_TWO_BYTES_MASK) == 0) {
			return 2;
		} else if ((ch & CHAR_THREE_BYTES_MASK) == 0) {
			return 3;
		} else if ((ch & CHAR_FOUR_BYTES_MASK) == 0) {
			return 4;
		} else if ((ch & CHAR_FIVE_BYTES_MASK) == 0) {
			return 5;
		} else if ((ch & CHAR_SIX_BYTES_MASK) == 0) {
			return 6;
		} else {
			return -1;
		}
	}

}
