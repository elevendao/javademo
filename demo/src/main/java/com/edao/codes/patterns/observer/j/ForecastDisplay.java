package com.edao.codes.patterns.observer.j;

import java.util.Observable;
import java.util.Observer;

import com.edao.codes.patterns.observer.DisplayElement;

public class ForecastDisplay implements Observer, DisplayElement {
	
	private Observable observable;
	private float currentPressure = 29.2f;
	private float lastPressure;

	public ForecastDisplay(Observable obs) {
		this.observable = obs;
		this.observable.addObserver(this);
	}
	
	@Override
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

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof WeatherData) {
			WeatherData w = (WeatherData) o;
			this.lastPressure = this.currentPressure;
			this.currentPressure = w.getPressure();
		}
		
		display();
	}

}
