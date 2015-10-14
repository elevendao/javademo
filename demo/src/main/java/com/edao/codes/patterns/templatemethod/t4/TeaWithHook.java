package com.edao.codes.patterns.templatemethod.t4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TeaWithHook extends CaffeineBeverageWithHook {

	private void addLemon() {
		System.out.println("Adding lemon");
	}

	private void steepTeaBag() {
		System.out.println("Steeping the tea");
	}

	@Override
	void brew() {
		System.out.println("Steeping the tea");
	}

	@Override
	void addCondiments() {
		System.out.println("Adding lemon");
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
		
		System.out.print("Would you like lemon with your tea (y/n)? ");
		
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
