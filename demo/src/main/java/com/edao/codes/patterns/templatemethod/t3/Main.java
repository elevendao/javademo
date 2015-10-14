package com.edao.codes.patterns.templatemethod.t3;

public class Main {

	public static void main(String[] args) {
		Coffee coffee = new Coffee();
		Tea tea = new Tea();
		
		coffee.prepareRecipe();
		tea.prepareRecipe();
	}

}
