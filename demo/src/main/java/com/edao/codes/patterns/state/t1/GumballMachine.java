/**
 * 版权所有：elevendao
 * 项目名称:demo
 * 创建者: Leo
 * 创建日期: 2015年10月14日
 * 文件说明:
 * 最近修改者：Leo
 * 最近修改日期：2015年10月14日
 */
package com.edao.codes.patterns.state.t1;


/**
 * @author Leo
 *
 */
public class GumballMachine {
	final int SOLD_OUT = 0;
	final int NO_QUARTER = 1;
	final int HAS_QUARTER = 2;
	final int SOLD = 3;
	
	int state = SOLD_OUT;
	int count = 0;
	
	/**
	 * 
	 */
	public GumballMachine(int count) {
		// TODO Auto-generated constructor stub
		this.count = count;
		if (count > 0) {
			state = NO_QUARTER;
		}
	}
	
	public void insertQuarter() {
		if (state == HAS_QUARTER) {
			System.out.println("You can't insert another quarter");
		} else if (state == NO_QUARTER) {
			state = HAS_QUARTER;
			System.out.println("You insert a quarter");
		} else if (state == SOLD_OUT) {
			System.out.println("You can't insert a quarter, the machine is sold out");
		} else if (state == SOLD) {
			System.out.println("Please wait, we're already giving you a gumball");
		}
	}
	
	public void enjectQuarter() {
		if (state == HAS_QUARTER) {
			System.out.println("Quarter returnd");
			state = NO_QUARTER;
		} else if (state == NO_QUARTER) {
			System.out.println("You haven't inserted a quarter");
		} else if (state == SOLD_OUT) {
			System.out.println("You can't enject, you haven't insert a quarter yet");
		} else if (state == SOLD) {
			System.out.println("Sorry, you already turned crank");
		}
	}
	
	public void turnCrank() {
		if (state == HAS_QUARTER) {
			System.out.println("You turned...");
			state = SOLD;
			dispense();
		} else if (state == NO_QUARTER) {
			System.out.println("You turned but there's no quarter");
		} else if (state == SOLD_OUT) {
			System.out.println("You turned but there are no gumballs");
		} else if (state == SOLD) {
			System.out.println("Turning twice doesn't get you another gumball");
		}
	}
	
	public void dispense() {
		if (state == SOLD) {
			System.out.println("A gumball comes rolling out the slot");
			count = count - 1;
			if (count == 0) {
				System.out.println("Oops, out of gumballs");
				state = SOLD_OUT;
			} else {
				state = NO_QUARTER;
			}
		} else if (state == NO_QUARTER) {
			System.out.println("You need insert a quarter first");
		} else if (state == HAS_QUARTER) {
			System.out.println("No gumball dispensed");
		} else if (state == SOLD_OUT) {
			System.out.println("No gumball dispensed");
		}
	}
	
	public String toString() {
		StringBuffer status = new StringBuffer();
		status.append("\nMighty Gumball, Inc.\n");
		status.append("Java-enabled Standing Gumball Model #2004\n");
		status.append("Inventory : " + count + " gumball");
		if (count != 1) {
			status.append("s");
		}
		status.append("\nMachine is");
		
		if (state == SOLD_OUT) {
			status.append("sold out");
		} else if (state == NO_QUARTER) {
			status.append("waiting for quarter");
		} else if (state == HAS_QUARTER) {
			status.append("waiting for turn of crank");
		} else if (state == SOLD) {
			status.append("delivering a gumball");
		}
		return status.toString();
	}
	
	public void refill(int numberOfGumballs) {
		this.count = numberOfGumballs;
		this.state = NO_QUARTER;
	}
}
