package com.edao.codes.patterns.observer;

public class StatisticsDisplay implements Observer, DisplayElement {

	private Subject subject;
	private float maxTemp = 0.0f;
	private float minTemp = 200f;
	private float tempSum = 0.0f;
	private int numReadings;

	public StatisticsDisplay(Subject subject) {
		this.subject = subject;
		this.subject.addObserver(this);
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		tempSum += temperature;
		numReadings++;
		if (temperature > maxTemp) {
			maxTemp = temperature;
		}
		if (temperature < minTemp) {
			minTemp = temperature;
		}
		
		display();
	}

	public void display() {
		System.out.println("Avg/Max/Min temperature = "
				+ (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp);
	}

}
