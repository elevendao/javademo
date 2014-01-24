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
public class TestRegTest extends TestCase {
	
	public void testCheckSuffix() {
		TestReg reg = new TestReg();
		String suffix = "xml";
		String name = "schema.xml";
		assertEquals(true, reg.checkSuffix(name, suffix));
	}
}
