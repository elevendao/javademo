/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-3-27
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-3-27
 */
package com.edao.codes.patterns.visitor;

/**
 * @author liushuai
 *
 */
public class VisitorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Visitor visitor = new MyVisitor();
		Subject subject=  new MySubject("love");
		
		visitor.visit(subject);
	}

}
