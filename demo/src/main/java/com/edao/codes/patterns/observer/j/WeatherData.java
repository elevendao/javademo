package com.edao.codes.patterns.observer.j;

import java.util.Observable;

public class WeatherData extends Observable {

	private float temperature, humidity, pressure;
	
	public WeatherData() {
		// TODO Auto-generated constructor stub
	}
	
	public float getTemperature() {
		return this.temperature;
	}
	
	public float getHumidity() {
		return this.humidity;
	}
	
	public float getPressure() {
		return this.pressure;
	}
	
	public void mesurementsChanged() {
		setChanged();
		notifyObservers();
	}
	
	public void setMesurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		mesurementsChanged();
	}
}
