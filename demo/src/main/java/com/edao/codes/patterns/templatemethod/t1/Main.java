package com.edao.codes.patterns.templatemethod.t1;

public class Main {

	public static void main(String[] args) {
		Coffee coffee = new Coffee();
		Tea tea = new Tea();
		
		coffee.prepareRecipe();
		tea.prepareRecipe();
	}

}
