/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-4-14
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-4-14
 */
package com.edao.codes.javase.compare;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author liushuai
 *
 */
public class Main {

	/**
	 * when to use which?
	 * @param args
	 */
	public static void main(String[] args) {
		compare1();
		compare2();
	}
	
	public static void compare1() {
		HDTV tv1 = new HDTV(60, "sony");
		HDTV tv2 = new HDTV(55, "sumsung");
		
		if (tv1.compareTo(tv2) > 0) {
			System.out.println(tv1.getBrand() + " is better.");
		} else {
			System.out.println(tv2.getBrand() + " is better.");
		}
	}
	
	public static void compare2() {
		HDTV tv1 = new HDTV(60, "sony");
		HDTV tv2 = new HDTV(55, "sumsung");
		HDTV tv3 = new HDTV(59, "panasonic");
		
		ArrayList<HDTV> list = new ArrayList<HDTV>();
		list.add(tv1);
		list.add(tv2);
		list.add(tv3);
		
		Collections.sort(list);
		
		for (HDTV tv : list) {
			System.out.println(tv.getBrand() + ", " + tv.getSize());
		}
	}

}
