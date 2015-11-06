package com.edao.codes.patterns.decorator;

public class DarkRoast extends Beverage {
	
	public DarkRoast() {
		description = "Dark Roast Coffee";
		size = TALL;
	}

	@Override
	public double cost() {
		return .99;
	}

}
