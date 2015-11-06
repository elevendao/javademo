package com.edao.codes.patterns.decorator;

public class Soy extends CondimentDecorator {
	
	Beverage beverage;
	
	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Soy";
	}

	@Override
	public double cost() {
		double cost = beverage.cost();
		if (getSize() == Beverage.TALL) {
			cost += 0.10;
		} else if (getSize() == Beverage.GRANDE) {
			cost += 0.15;
		} else if (getSize() == Beverage.VENTI) {
			cost += 0.20;
		}
		return cost;
	}

	@Override
	public int getSize() {
		return beverage.getSize();
	}

}
