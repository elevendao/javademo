package com.edao.codes.patterns.templatemethod.t2;

public abstract class CaffeineBeverage {
	
	abstract void prepareRecipe();
	
	public void pourInCup() {
		System.out.println("Pouring into cup.");
	}
	
	public void boilWater() {
		System.out.println("Boiling Water.");
	}

}
