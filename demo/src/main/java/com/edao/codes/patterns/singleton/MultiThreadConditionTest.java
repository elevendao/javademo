package com.edao.codes.patterns.singleton;

public class MultiThreadConditionTest {

	public static void main(String[] args) {
		BoilerThread b1 = new BoilerThread(1);
		BoilerThread b2 = new BoilerThread(2);
		Thread t1 = new Thread(b1);
		Thread t2 = new Thread(b2);
		
		t1.start();
		t2.start();
		
	}
	
}

class BoilerThread implements Runnable {
	private int number;
	private ChocolateBoiler instance;
	
	public BoilerThread(int number) {
		this.number = number;
	}
	
	public void run() {
		instance = ChocolateBoiler.getInstance();
		System.out.println("BoilerThread - " + number + ", instance : " + instance);
	}

}
