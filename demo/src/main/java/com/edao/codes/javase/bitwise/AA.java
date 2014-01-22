package com.edao.codes.javase.bitwise;
import java.util.ArrayList;
import java.util.List;

/**
 * 版权所有：美创科技
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2013-7-15
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2013-7-15
 */

/**
 * @author liushuai
 *
 */
public class AA {

	/**
	 * 64以内的组合情况
	 */
	public static void main(String[] args) {
		char[] chs = new char[13];
		char start = 'a';
		for (int i=0; i<13; i++) {
			chs[i] = (char) (i + start);
		}
		
		int n = 5;
		int m = 13;
		System.out.println("total condition count : " + getTotalCombinationCount(n, m));
		for (int i=1; i<=n; i++) {
			Integer[] res = getCombination(i, m);
			for (Integer posNum : res) {
				String str = getString(posNum, chs);
				System.out.println(str);
			}
		}
	}
	
	static String getString(Integer posNum, char[] chs) {
		int length = Integer.bitCount(posNum);
		int[] comb = new int[length];
		
		char[] binary = Integer.toBinaryString(posNum).toCharArray();
		int posIdx = 0;
		int pos = 0;
		int startIdx = binary.length - 1;
		int endIdx = 0;
		for (int i=startIdx; i>=endIdx; i--) {
			if (binary[i] == '1') {
				comb[posIdx++] = pos;
			}
			pos++;
		}
		
		char[] str = new char[length];
		for (int i=0; i<length; i++) {
			str[i] = chs[comb[i]];
		}
		
		return String.valueOf(str);
	}
	
	static Integer[] getCombination(int n, int m) {
		char[] binary = new char[m];
		for (int i=0; i<binary.length; i++) {
			binary[i] = '1';
		}
		
		int max = Integer.valueOf(new String(binary), 2);
		List<Integer> list = new ArrayList<Integer>();
		for (int i=0; i<max; i++) {
			int posNum = Integer.bitCount(i & max);
			if (n == posNum) {
				list.add(i);
			}
		}
		Integer[] res = new Integer[list.size()];
		list.toArray(res);
		return res;
	}
	
	static int getCombinationCount(int n, int m) {
		char[] binary = new char[m];
		for (int i=0; i<binary.length; i++) {
			binary[i] = '1';
		}
		
		int max = Integer.valueOf(new String(binary), 2);
		
		int count = 0;
		for (int i=0; i<max; i++) {
			int posNum = Integer.bitCount(i & max);
			if (n == posNum) {
				count++;
			}
		}
		return count;
	}
	
	static int getTotalCombinationCount(int n, int m) {
		int count = 0;
		for (int i=1; i<=n; i++) {
			int c = getCombinationCount(i, m);
			count += c;
			System.out.println(c);
		}
		
		System.out.println(count);
		return count;
	}

}
