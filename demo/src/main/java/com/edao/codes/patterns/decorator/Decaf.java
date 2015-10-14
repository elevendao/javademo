package com.edao.codes.patterns.decorator;

public class Decaf extends Beverage {
	
	public Decaf() {
		description = "Decaf";
		size = TALL;
	}

	@Override
	public double cost() {
		return 1.05;
	}

}
