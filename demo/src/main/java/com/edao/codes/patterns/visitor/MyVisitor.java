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
public class MyVisitor implements Visitor {

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.visitor.Visitor#visit(com.edao.codes.patterns.visitor.Subject)
	 */
	@Override
	public void visit(Subject subject) {
		System.out.println("visit the subject : " + subject.getSubject());
	}

}
