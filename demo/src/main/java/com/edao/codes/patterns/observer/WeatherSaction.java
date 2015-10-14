package com.edao.codes.patterns.observer;

public class WeatherSaction {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		
		CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		
		weatherData.setMesurements(80, 65, 34.4f);
		weatherData.setMesurements(82, 67, 29.8f);
		weatherData.setMesurements(78, 63, 32.7f);
	}

}
