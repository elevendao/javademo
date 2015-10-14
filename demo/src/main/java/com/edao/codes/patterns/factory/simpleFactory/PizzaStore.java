package com.edao.codes.patterns.factory.simpleFactory;

public class PizzaStore {
	
	SimplePizzaFactory simplePizzaFactory;
	
	public PizzaStore(SimplePizzaFactory simplePizzaFactory) {
		this.simplePizzaFactory = simplePizzaFactory;
	}
	
	public Pizza orderPizza(String type) {
		Pizza pizza;
		pizza = createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.bake();
		
		return pizza;
	}
	
	public Pizza createPizza(String type) {
		return this.simplePizzaFactory.createPizza(type);
	}
}
