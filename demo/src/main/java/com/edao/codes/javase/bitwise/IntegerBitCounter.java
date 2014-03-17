/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-3-13
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-3-13
 */
package com.edao.codes.javase.bitwise;

/**
 * @author liushuai
 *
 */
public class IntegerBitCounter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = countNumOfOneInBinary(Integer.MAX_VALUE);
		System.out.println(count);
	}
	
	public static int countNumOfOneInBinary(Integer val) {
		int count = 0;
		for (int i=0; i<Integer.SIZE; i++) {
			if (( ( 1 << i ) & val ) >> i == 1) {
				count++;
			}
		}
		return count;
	}

}
