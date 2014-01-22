package com.edao.codes.javase.collection;

import java.util.ArrayList;
import java.util.List;


/**
 * @author liushuai
 *
 */
public class MyList {
	
	public static void main(String[] args) {
		//new MyList().test();
		test1();
	}
	
	public static void test1() {
		List<String> aa = new ArrayList<String>();
		for (String s : aa) {
			System.out.println(s);
		}
	}
	
	public void test() {
		List list = new ArrayList();
		Car qq = new Car("qq", 4);
		Car bmw = new Car("bmw", 4);
		Car qq2 = new Car("qq", 4);
		list.add(qq);
		list.add(bmw);
		System.out.println(list.contains(qq));
	}
	
	
	class Car {
		String name;
		int wheels;
		
		public Car() {}
		
		public Car(String name, int wheels) {
			this.name = name;
			this.wheels = wheels;
		}
		
		String getName() {
			return this.name;
		}
		
		int getWheels() {
			return this.wheels;
		}
	}
}
