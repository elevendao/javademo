/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-3-14
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-3-14
 */
package com.edao.codes.patterns.strategy;


/**
 * @author liushuai
 *
 */
public class AddStrategy implements Strategy {

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.Strategy#calculate(int, int)
	 */
	@Override
	public int calculate(int a, int b) {
		// TODO Auto-generated method stub
		return a + b;
	}

}
