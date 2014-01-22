package com.edao.codes.javase.bitwise;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;

/**
 * 版权所有：美创科技
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2013-8-8
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2013-8-8
 */

/**
 * @author liushuai
 *
 */
public class SS {
	public static void main(String[] args) {
//		bitCal();\
	}
	
	public static void demicalFormat() {
		String str = "000000";
		String id = "12345";
		DecimalFormat format = new DecimalFormat("00000.#");
		String s = format.format(123456 % 100000);
		System.out.println(s);
	}
	
	public static void swap(int a, int b) {
		a = a ^ b;
		b = b ^ a;
		a = a ^ b;
		
		System.out.println(a);
		System.out.println(b);
	}
	
	public static void bitCal() {
		String key = "john";
		int hash = key.hashCode();
		System.out.println(hash);
		System.out.println(Integer.toBinaryString(hash));
		int h = hash;
		System.out.println("step 1:");
		int h1 = h >>> 20;
		System.out.println(Integer.toBinaryString(h1));
		int h2 = h >>> 12;
		System.out.println(Integer.toBinaryString(h2));
		int h3 = h1 ^ h2;
		System.out.println(Integer.toBinaryString(h3));
		
		h ^= (h >>> 20) ^ (h >>> 12);
        h = h ^ (h >>> 7) ^ (h >>> 4);
        System.out.println(h);
	}
}
