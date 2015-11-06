/**
 * 版权所有：elevendao
 * 项目名称:demo
 * 创建者: Leo
 * 创建日期: 2015年10月17日
 * 文件说明:
 * 最近修改者：Leo
 * 最近修改日期：2015年10月17日
 */
package com.edao.codes.patterns.state.t2;


/**
 * @author Leo
 *
 */
public class HasQuarterState implements State {

	GumballMachine gumballMachine;
	
	/**
	 * @param gumballMachine
	 */
	public HasQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#insertQuarter()
	 */
	@Override
	public void insertQuarter() {
		System.out.println("You can't insert another quarter");
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#enjectQuarter()
	 */
	@Override
	public void enjectQuarter() {
		System.out.println("Quarter returnd");
		gumballMachine.setState(gumballMachine.getNoQuarterState());
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#turnCrank()
	 */
	@Override
	public void turnCrank() {
		System.out.println("You turned...");
		gumballMachine.setState(gumballMachine.getSoldState());
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#dispense()
	 */
	@Override
	public void dispense() {
		System.out.println("No gumball dispensed");
	}
	
	public String toString() {
		return "waiting for turn of crank";
	}
}
