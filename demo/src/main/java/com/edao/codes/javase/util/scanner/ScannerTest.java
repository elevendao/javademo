/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-4-14
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-4-14
 */
package com.edao.codes.javase.util.scanner;

import java.util.Scanner;

/**
 * @author liushuai
 *
 */
public class ScannerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input = "1 2 3 a b c 234";
		Scanner scanner = new Scanner(input);
		
		int x = 0;
		while (scanner.hasNextInt()) {
			x = scanner.nextInt();
			System.out.println("x = " + x);
		}
		
//		do {
//			x = scanner.nextInt(); // will throw InputMismatchException
//			System.out.println("x = " + x);
//		} while (x != 0);
	}

}
