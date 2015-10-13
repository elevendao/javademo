package com.edao.codes.patterns.templatemethod.t4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoffeeWithHook extends CaffeineBeverageWithHook {
	
	private void addSugarAndMilk() {
		System.out.println("Adding sugar and milk.");
	}

	private void brewCoffeeGrinds() {
		System.out.println("Dripping coffee through filter.");
	}

	@Override
	void brew() {
		System.out.println("Dripping coffee through filter.");
	}

	@Override
	void addCondiments() {
		System.out.println("Adding sugar and milk.");
	}

	@Override
	public boolean customerWantsCondiments() {
		String answer = getUserInput();
		
		if (answer.toLowerCase().startsWith("y")) {
			return true;
		} else {
			return false;
		}
	}

	private String getUserInput() {
		String answer = null;
		
		System.out.print("Would you like milk and sugar with your coffee (y/n)? ");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			answer = br.readLine();
		} catch (IOException e) {
			System.out.println("IO error trying to read your answer!");
		}
		if (answer == null) {
			return "no";
		}
		
		return answer;
	}
	
	
}
