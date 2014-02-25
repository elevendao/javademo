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
public class InsertSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = Util.genRandomIntArr(20);
		Util.print(arr);
		new InsertSort().sort(arr);
		Util.print(arr);
	}

	public void sort(int[] arr) {
		for (int i=1; i<arr.length; i++) {
	        int j = i-1;
	        int tmp = arr[i];
	        while (j>=0 && arr[j]>tmp) {
	          arr[j+1] = arr[j];
	          j--;
	        }
	        arr[j+1] = tmp;
	      }
	}
}
