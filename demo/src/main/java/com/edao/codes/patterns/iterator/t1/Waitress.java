package com.edao.codes.patterns.iterator.t1;

import java.util.ArrayList;
import com.edao.codes.patterns.iterator.MenuItem;

public class Waitress {
	String name;
	
	public Waitress(String name) {
		this.name = name;
	}
	
	void printMenu() {
		PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
		ArrayList breakfastMenuItems = pancakeHouseMenu.getMenuItems();
		
		DinerMenu dinerMenu = new DinerMenu();
		MenuItem[] dinerMenuItems = dinerMenu.getMenuItems();
		
		for (int i=0; i<breakfastMenuItems.size(); i++) {
			MenuItem menuItem = (MenuItem) breakfastMenuItems.get(i);
			System.out.println(menuItem.getName() + "	" + menuItem.getPrice() + "	");
			System.out.println(menuItem.getDesciption());
		}
		
		for (int i=0; i<dinerMenuItems.length; i++) {
			MenuItem menuItem = dinerMenuItems[i];
			System.out.println(menuItem.getName() + "	" + menuItem.getPrice() + "	");
			System.out.println(menuItem.getDesciption());
		}
	}
	void printBreakfastMenu(){
		PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
		ArrayList breakfastMenuItems = pancakeHouseMenu.getMenuItems();
		
		for (int i=0; i<breakfastMenuItems.size(); i++) {
			MenuItem menuItem = (MenuItem) breakfastMenuItems.get(i);
			System.out.println(menuItem.getName() + "	" + menuItem.getPrice() + "	");
			System.out.println(menuItem.getDesciption());
		}
	}
	void printLunchMenu(){
		DinerMenu dinerMenu = new DinerMenu();
		MenuItem[] dinerMenuItems = dinerMenu.getMenuItems();
		
		for (int i=0; i<dinerMenuItems.length; i++) {
			MenuItem menuItem = dinerMenuItems[i];
			System.out.println(menuItem.getName() + "	" + menuItem.getPrice() + "	");
			System.out.println(menuItem.getDesciption());
		}
	}
	void printVegetarianMenu() {
		PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
		ArrayList breakfastMenuItems = pancakeHouseMenu.getMenuItems();
		
		DinerMenu dinerMenu = new DinerMenu();
		MenuItem[] dinerMenuItems = dinerMenu.getMenuItems();
		
		for (int i=0; i<breakfastMenuItems.size(); i++) {
			MenuItem menuItem = (MenuItem) breakfastMenuItems.get(i);
			if (menuItem.isVegetarian()) {
				System.out.println(menuItem.getName() + "	" + menuItem.getPrice() + "	");
				System.out.println(menuItem.getDesciption());
			}
		}
		
		for (int i=0; i<dinerMenuItems.length; i++) {
			MenuItem menuItem = dinerMenuItems[i];
			if (menuItem.isVegetarian()) {
				System.out.println(menuItem.getName() + "	" + menuItem.getPrice() + "	");
				System.out.println(menuItem.getDesciption());
			}
		}
	}
	boolean isItemVegetarian(String name){
		PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
		ArrayList breakfastMenuItems = pancakeHouseMenu.getMenuItems();
		
		DinerMenu dinerMenu = new DinerMenu();
		MenuItem[] dinerMenuItems = dinerMenu.getMenuItems();
		
		for (int i=0; i<breakfastMenuItems.size(); i++) {
			MenuItem menuItem = (MenuItem) breakfastMenuItems.get(i);
			if (menuItem.getName().equals(name) && menuItem.isVegetarian()) {
				return true;
			}
		}
		
		for (int i=0; i<dinerMenuItems.length; i++) {
			MenuItem menuItem = dinerMenuItems[i];
			if (menuItem.getName().equals(name) && menuItem.isVegetarian()) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Waitress jean = new Waitress("jean");
		jean.printMenu();
	}
}
