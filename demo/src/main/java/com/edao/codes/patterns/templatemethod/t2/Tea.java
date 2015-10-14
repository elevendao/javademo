package com.edao.codes.patterns.templatemethod.t2;

public class Tea extends CaffeineBeverage {
	
	void prepareRecipe() {
		boilWater();
		steepTeaBag();
		pourInCup();
		addLemon();
	}

	private void addLemon() {
		System.out.println("Adding lemon");
	}

	private void steepTeaBag() {
		System.out.println("Steeping the tea");
	}

}
