/**
 * 版权所有：美创科技
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-1-24
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-1-24
 */
package com.edao.codes.javase.string;

import junit.framework.TestCase;

/**
 * @author liushuai
 *
 */
public class StringTest extends TestCase {
	
	public void testSplit() {
		String str = "select  from ";
		String[] token = str.split(" ");
		assertEquals("select", token[0]);
		assertEquals("", token[1]);
		assertEquals("from", token[2]);
		assertEquals(3, token.length);
	}
}
