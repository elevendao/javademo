/**
 * 版权所有：elevendao
 * 项目名称:demo
 * 创建者: Leo
 * 创建日期: 2015年10月19日
 * 文件说明:
 * 最近修改者：Leo
 * 最近修改日期：2015年10月19日
 */
package com.edao.codes.patterns.state.t3;


/**
 * @author Leo
 *
 */
public class WinnerState implements State {
	
	GumballMachine gumballMachine;
	
	/**
	 * 
	 */
	public WinnerState(GumballMachine gumballMachine) {
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
	 * @see com.edao.codes.patterns.state.t3.State#dispense()
	 */
	@Override
	public void dispense() {
		System.out.println("You're a Winner! you get two gumballs for your quarter.");
		this.gumballMachine.releaseBall();
		if (this.gumballMachine.getCount() == 0) {
			this.gumballMachine.setState(this.gumballMachine.getSoldOutState());
		} else {
			this.gumballMachine.releaseBall();
			if (this.gumballMachine.getCount() > 0) {
				this.gumballMachine.setState(this.gumballMachine.getNoQuarterState());
			} else {
				System.out.println("Oops! out of gumballs");
				this.gumballMachine.setState(gumballMachine.getSoldOutState());
			}
		}
	}
}
