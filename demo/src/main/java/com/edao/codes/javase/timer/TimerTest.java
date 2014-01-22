package com.edao.codes.javase.timer;

import java.util.Timer;


public class TimerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Timer timer = new Timer();
		
		timer.schedule(new MyTask(), 1000, 2000);
		
		
	}
	
	static class MyTask extends java.util.TimerTask{

		@Override
		public void run() {
			System.out.println("-----------");
		}
		
	}

}
