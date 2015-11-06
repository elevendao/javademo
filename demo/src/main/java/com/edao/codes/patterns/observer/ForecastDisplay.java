package com.edao.codes.patterns.observer;

public class ForecastDisplay implements Observer, DisplayElement {
	
	private Subject subject;
	private float currentPressure = 29.2f;
	private float lastPressure;
	
	public ForecastDisplay(Subject subject) {
		this.subject = subject;
		this.subject.addObserver(this);
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		this.lastPressure = this.currentPressure;
		this.currentPressure = pressure;
		display();
	}
	
	public void display() {
		System.out.print("Forecast : ");
		if (currentPressure > lastPressure) {
			System.out.println("Improving weather on the way!");
		} else if (currentPressure == lastPressure) {
			System.out.println("More of the same.");
		} else {
			System.out.println("Watch out for cooler, rainy weather.");
		}
	}

}
