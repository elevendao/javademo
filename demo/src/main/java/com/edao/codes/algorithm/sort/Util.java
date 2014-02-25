/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-2-25
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-2-25
 */
package com.edao.codes.algorithm.sort;

/**
 * @author liushuai
 *
 */
public class Util {

	public static void print(int[] arr) {
		for (int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static int[] genRandomIntArr(int length) {
		int[] arr = new int[length];
		for (int i=0; i<arr.length; i++) {
			arr[i] = (int)(Math.random() * 100);
		}
		return arr;
	}
}
