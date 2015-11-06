package com.edao.codes.patterns.factory.simpleFactory;

public class PizzaTestDrive {

	public static void main(String[] args) {
		SimplePizzaFactory factory = new SimplePizzaFactory();
		PizzaStore pizzaStore = new PizzaStore(factory);
		
		Pizza pizza = pizzaStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza.getName() + "\n");
		System.out.println(pizza);
		
		pizza = pizzaStore.orderPizza("veggie");
		System.out.println("Ethan ordered a " + pizza.getName() + "\n");
		System.out.println(pizza);
		
		pizza = pizzaStore.orderPizza("clam");
		System.out.println("Ethan ordered a " + pizza.getName() + "\n");
		System.out.println(pizza);
		
		pizza = pizzaStore.orderPizza("pepperoni");
		System.out.println("Ethan ordered a " + pizza.getName() + "\n");
		System.out.println(pizza);
	}

}
