package com.edao.codes.patterns;
/**
 * @author liushuai
 *
 */
public class DecoratorPatternTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Car car = new BenzCar();
		car = new GpsDecorator(car);
		car = new FlyDecorator(car);
		car.move();
	}

}

interface Car {
	void move();
}

class BenzCar implements Car {
	public void move() {
		System.out.println("drive so fast ... ");
	}
}

class GpsDecorator implements Car {
	
	private Car car;
	
	public GpsDecorator(Car car) {
		this.car = car;
	}

	public void move() {
		car.move();
		automatic();
	}
	
	private void automatic() {
		System.out.println("automatic ...");
	}
}

class FlyDecorator implements Car {
	
	private Car car;
	
	public FlyDecorator(Car car) {
		this.car = car;
	}

	public void move() {
		car.move();
		addSwing();
	}
	
	private void addSwing() {
		System.out.println("i can fly ... ");
	}
	
}
