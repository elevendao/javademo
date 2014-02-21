package com.edao.codes.javase.exception;

/**
 * 版权所有：liushuai
 * 项目名称:test
 * 创建者: liushuai
 * 创建日期: 2012-9-13
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2012-9-13
 */
/**
 * @author liushuai
 *
 */
public class CatchTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		int a = new CatchTest().test();
		System.out.println(a);
	}
	
	public int test() throws Exception {
		int a = -1;
		String str = null;
		
		try {
			a = str.length();
			a = 1;
		} catch (Exception e) {
			throw e;
			/*try {
				str.length();
			} catch (Exception e1) {
				//a = 2;
			}*/
		} finally {
			a = 3;
		}
		return a;
	}
}
