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
public class SoldOutState implements State {

	GumballMachine gumballMachine;
	
	/**
	 * @param gumballMachine
	 */
	public SoldOutState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#insertQuarter()
	 */
	@Override
	public void insertQuarter() {
		System.out.println("You can't insert a quarter, the machine is sold out");
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#enjectQuarter()
	 */
	@Override
	public void enjectQuarter() {
		System.out.println("You can't enject, you haven't insert a quarter yet");
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#turnCrank()
	 */
	@Override
	public void turnCrank() {
		System.out.println("You turned but there are no gumballs");
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.state.t2.State#dispense()
	 */
	@Override
	public void dispense() {
		System.out.println("No gumball dispensed");
	}
	
	public String toString() {
		return "sold out";
	}
}
