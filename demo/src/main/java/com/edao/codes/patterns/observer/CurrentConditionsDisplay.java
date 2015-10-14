package com.edao.codes.patterns.observer;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
	
	private Subject subject;
	private float temperature;
	private float humidity;
	private float pressure;
	
	public CurrentConditionsDisplay(Subject subject) {
		this.subject = subject;
		this.subject.addObserver(this);
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		display();
	}
	
	public void display() {
		System.out.println("Current conditons : " + this.temperature + "F, " + this.humidity + "%");
	}

}
