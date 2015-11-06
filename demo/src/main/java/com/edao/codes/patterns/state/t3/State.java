/**
 * 版权所有：elevendao
 * 项目名称:demo
 * 创建者: Leo
 * 创建日期: 2015年10月17日
 * 文件说明:
 * 最近修改者：Leo
 * 最近修改日期：2015年10月17日
 */
package com.edao.codes.patterns.state.t3;


/**
 * @author Leo
 *
 */
public interface State {
	
	void insertQuarter();
	
	void enjectQuarter();
	
	void turnCrank();
	
	void dispense();
}
