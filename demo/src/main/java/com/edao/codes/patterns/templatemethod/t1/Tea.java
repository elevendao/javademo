package com.edao.codes.patterns.templatemethod.t1;

public class Tea {
	
	void prepareRecipe() {
		boilWater();
		steepTeaBag();
		pourInCup();
		addLemon();
	}

	private void addLemon() {
		System.out.println("Adding lemon");
	}

	private void pourInCup() {
		System.out.println("Pouring into cup");
	}

	private void steepTeaBag() {
		System.out.println("Steeping the tea");
	}

	private void boilWater() {
		System.out.println("Boiling Water");
	}

}
