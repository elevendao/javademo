package com.edao.codes.patterns.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class WeatherData implements Subject {
	
	private float temperature, humidity, pressure;
	private List<Observer> observers;
	
	public WeatherData() {
		observers = new ArrayList<Observer>();
	}

	@Override
	public void addObserver(Observer o) {
		if (o != null && !observers.contains(o)) {
			observers.add(o);
		}
	}

	@Override
	public void removeObserver(Observer o) {
		if (o != null) {
			observers.remove(o);
		}
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update(temperature, humidity, pressure);
		}
	}
	
	public void mesurementsChanged() {
		notifyObservers();
	}
	
	public void setMesurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		mesurementsChanged();
	}

}
