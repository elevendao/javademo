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
public class SoldState implements State {

	GumballMachine gumballMachine;
	
	/**
	 * @param gumballMachine
	 */
	public SoldState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#insertQuarter()
	 */
	@Override
	public void insertQuarter() {
		System.out.println("Please wait, we're already giving you a gumball");
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#enjectQuarter()
	 */
	@Override
	public void enjectQuarter() {
		System.out.println("Sorry, you already turned crank");
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#turnCrank()
	 */
	@Override
	public void turnCrank() {
		System.out.println("Turning twice doesn't get you another gumball");
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#dispense()
	 */
	@Override
	public void dispense() {
		gumballMachine.releaseBall();
		if (gumballMachine.getCount() > 0) {
			gumballMachine.setState(gumballMachine.getNoQuarterState());
		} else {
			System.out.println("Oops, out of gumballs!");
			gumballMachine.setState(gumballMachine.getSoldOutState());
		}
	}
	
	public String toString() {
		return "delivering a gumball";
	}
}
