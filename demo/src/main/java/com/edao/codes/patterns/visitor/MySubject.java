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
public class MySubject implements Subject {
	
	private String name;
	
	public MySubject(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.visitor.Subject#accept(com.edao.codes.patterns.visitor.Visitor)
	 */
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.visitor.Subject#getSubject()
	 */
	@Override
	public String getSubject() {
		return this.name;
	}
}
