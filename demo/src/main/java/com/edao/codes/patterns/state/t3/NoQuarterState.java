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
public class NoQuarterState implements State {
	
	GumballMachine gumballMachine;
	
	/**
	 * 
	 */
	public NoQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#insertQuarter()
	 */
	@Override
	public void insertQuarter() {
		System.out.println("You insert a quarter");
		this.gumballMachine.setState(gumballMachine.getHasQuarterState());
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#enjectQuarter()
	 */
	@Override
	public void enjectQuarter() {
		System.out.println("You haven't inserted a quarter");
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#turnCrank()
	 */
	@Override
	public void turnCrank() {
		System.out.println("You turned but there's no quarter");
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#dispense()
	 */
	@Override
	public void dispense() {
		System.out.println("You need insert a quarter first");
	}
	
	public String toString() {
		return "waiting for a quarter";
	}
}
