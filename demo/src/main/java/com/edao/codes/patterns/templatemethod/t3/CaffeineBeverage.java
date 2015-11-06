package com.edao.codes.patterns.templatemethod.t3;

public abstract class CaffeineBeverage {
	
	final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		addCondiments();
	}
	
	abstract void brew();
	
	abstract void addCondiments();
	
	public void pourInCup() {
		System.out.println("Pouring into cup.");
	}
	
	public void boilWater() {
		System.out.println("Boiling Water.");
	}

}
