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
public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = Util.genRandomIntArr(20);
		Util.print(arr);
		new QuickSort().sort(arr);
		Util.print(arr);
	}
	
	public void sort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}

}
