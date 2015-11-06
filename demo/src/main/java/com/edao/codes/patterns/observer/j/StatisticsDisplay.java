package com.edao.codes.patterns.observer.j;

import java.util.Observable;
import java.util.Observer;

import com.edao.codes.patterns.observer.DisplayElement;

public class StatisticsDisplay implements Observer, DisplayElement {
	
	private Observable observable;
	private float maxTemp = 0.0f;
	private float minTemp = 200f;
	private float tempSum = 0.0f;
	private int numReadings;
	
	public StatisticsDisplay(Observable obs) {
		this.observable = obs;
		this.observable.addObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Avg/Max/Min temperature = "
				+ (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof WeatherData) {
			WeatherData w = (WeatherData) o;
			float temperature = w.getHumidity();
			tempSum += temperature;
			numReadings++;
			if (temperature > maxTemp) {
				maxTemp = temperature;
			}
			if (temperature < minTemp) {
				minTemp = temperature;
			}
		}
		
		display();
	}

}
