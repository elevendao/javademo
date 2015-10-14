package com.edao.codes.patterns.templatemethod.t1;

public abstract class CaffeineBeverage {
	
	abstract void prepareRecipe();
	
	private void pourInCup() {
		System.out.println("Pouring into cup.");
	}
	
	private void boilWater() {
		System.out.println("Boiling Water.");
	}

}
