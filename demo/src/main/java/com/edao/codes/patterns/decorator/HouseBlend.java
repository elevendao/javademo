package com.edao.codes.patterns.decorator;

public class HouseBlend extends Beverage {
	
	public HouseBlend() {
		description = "House Blend Coffee";
		size = TALL;
	}

	@Override
	public double cost() {
		return .89;
	}

}
