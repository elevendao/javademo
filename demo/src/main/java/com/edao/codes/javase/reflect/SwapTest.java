/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-3-24
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-3-24
 */
package com.edao.codes.javase.reflect;

import java.lang.reflect.Field;

/**
 * @author liushuai
 *
 */
public class SwapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Integer a = 3;
//		Integer b = 4;
		int a = 1;
		int b = 2;
		SwapTest t = new SwapTest();
		t.swap(a, b);
//		System.out.println("a="+a);
//		System.out.println("b="+b);
		Integer c = 1;
		Integer d = 2;
		t.swap(c, d);
		System.out.println("c=" + c);
		System.out.println("d="+d);
	}
	
	public void swap(Integer a, Integer b) {
		try {
			System.out.println("bsa="+a);
			System.out.println("bsb="+b);
			Field field =Integer.class.getDeclaredField("value");
			field.setAccessible(true);
			int tmp = a.intValue();
			field.setInt(a, b.intValue());
			field.setInt(b, tmp);
			field.setAccessible(false);
			System.out.println("sa="+a);
			System.out.println("sb="+b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
