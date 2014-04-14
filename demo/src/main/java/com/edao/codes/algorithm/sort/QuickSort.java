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
		int len = 10;
		int[] arr = Util.genRandomIntArr(len);
		Util.print(arr);
		new QuickSort().sort(arr, 0, len-1);
		Util.print(arr);
	}
	
	public void sort(int[] arr, int start, int end) {
		int i=start;
		int j=end;
		while (i<j) {
			// 右侧扫描，找出第一个比key小的值，交换。
			while(i<j && arr[i]<=arr[j]) {
				j--;
			}
			if (i<j) {
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
			// 左侧扫描，找出第一个比key大的值，交换。此时key为arr[j]
			while (i<j && arr[i]<arr[j]) {
				i++;
			}
			if (i<j) {
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		
		// 递归调用， 把Key前面的完成排序
		if (i-start > 1) {
			sort(arr, start, i-1);
		}
		// 递归调用，把Key后面的完成排序
		if (end-i > 1) {
			sort(arr, i+1, end);
		}
	}

}
