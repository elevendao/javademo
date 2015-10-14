package com.edao.codes.patterns.iterator;

public class MenuItem {

	String name;
	String desciption;
	boolean vegetarian;
	double price;
	
	public MenuItem(String name,
			String description,
			boolean vegetarian,
			double price) {
		this.name = name;
		this.desciption = description;
		this.vegetarian = vegetarian;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getDesciption() {
		return desciption;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public double getPrice() {
		return price;
	}
}
