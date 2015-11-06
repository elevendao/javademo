package com.edao.codes.patterns.decorator;

public class Milk extends CondimentDecorator {
	
	Beverage beverage;
	
	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Milk";
	}

	@Override
	public double cost() {
		return beverage.cost() + .10;
	}

	@Override
	public int getSize() {
		return beverage.getSize();
	}

}
