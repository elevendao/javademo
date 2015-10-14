package com.edao.codes.patterns.templatemethod.t3;

public class Coffee extends CaffeineBeverage {

	private void addSugarAndMilk() {
		System.out.println("Adding sugar and milk.");
	}

	private void brewCoffeeGrinds() {
		System.out.println("Dripping coffee through filter.");
	}

	@Override
	void brew() {
		System.out.println("Dripping coffee through filter.");
	}

	@Override
	void addCondiments() {
		System.out.println("Adding sugar and milk.");
	}
	

}
