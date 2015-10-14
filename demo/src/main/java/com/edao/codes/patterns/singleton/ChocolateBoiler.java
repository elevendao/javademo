package com.edao.codes.patterns.singleton;

public class ChocolateBoiler {
	private boolean empty;
	private boolean boiled;
	
	/*
	public ChocolateBoiler() {
		empty = true;
		boiled = false;
	}
	*/
	
	private volatile static ChocolateBoiler instance;
	
	private ChocolateBoiler() {
		empty = true;
		boiled = false;
	}
	
	/*
	public static ChocolateBoiler getInstance() {
		if (instance == null) {
			instance = new ChocolateBoiler();
		}
		return instance;
	}
	*/
	
	/*
	public static synchronized ChocolateBoiler getInstance() {
		if (instance == null) {
			instance = new ChocolateBoiler();
		}
		return instance;
	}
	*/
	
	public static ChocolateBoiler getInstance() { // double-checked locking
		if (instance == null) {
			synchronized(ChocolateBoiler.class) {
				if (instance == null) {
					instance = new ChocolateBoiler();
				}
			}
		}
		return instance;
	}
	
	
	public void fill() {
		if (isEmpty()) {
			empty = false;
			boiled = false;
			// fill boiler with the mixture of chocolate and milk
		}
	}

	public void drain() {
		if (!isEmpty() && isBoiled()) {
			// drain boiled mixture of chocolate and milk
			empty = true;
		}
	}

	private boolean isEmpty() {
		return empty;
	}

	private boolean isBoiled() {
		return boiled;
	}
}
