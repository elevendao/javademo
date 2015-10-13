package com.edao.codes.patterns.templatemethod.t3;

public class Tea extends CaffeineBeverage {
	
	private void addLemon() {
		System.out.println("Adding lemon");
	}

	private void steepTeaBag() {
		System.out.println("Steeping the tea");
	}

	@Override
	void brew() {
		System.out.println("Steeping the tea");
	}

	@Override
	void addCondiments() {
		System.out.println("Adding lemon");
	}

}
