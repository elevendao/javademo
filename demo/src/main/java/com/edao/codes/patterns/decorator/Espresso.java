package com.edao.codes.patterns.decorator;

public class Espresso extends Beverage {

	public Espresso() {
		description = "Espresso";
		size = TALL;
	}

	@Override
	public double cost() {
		return 1.99;
	}
	
}
