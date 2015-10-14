package com.edao.codes.patterns.templatemethod.t4;

public class Main {

	public static void main(String[] args) {
		CoffeeWithHook coffee = new CoffeeWithHook();
		TeaWithHook tea = new TeaWithHook();
		
		System.out.println("\nMaking coffee...");
		coffee.prepareRecipe();
		System.out.println("\nMaking tea...");
		tea.prepareRecipe();
	}

}
