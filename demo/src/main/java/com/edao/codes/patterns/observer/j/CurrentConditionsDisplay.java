package com.edao.codes.patterns.observer.j;

import java.util.Observable;
import java.util.Observer;

import com.edao.codes.patterns.observer.DisplayElement;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
	
	private Observable observable;
	private float temperature;
	private float humidity;

	public CurrentConditionsDisplay(Observable obs) {
		this.observable = obs;
		this.observable.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof WeatherData) {
			WeatherData w = (WeatherData) o;
			this.temperature = w.getTemperature();
			this.humidity = w.getHumidity();
		}
		display();
	}
	
	public void display() {
		System.out.println("Current conditons : " + this.temperature + "F, " + this.humidity + "%");
	}

}
