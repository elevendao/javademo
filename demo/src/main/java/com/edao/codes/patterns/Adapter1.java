package com.edao.codes.patterns;


/**
 * 类的适配器模式
 * @author liushuai
 *
 */
public class Adapter1 extends Adaptee implements Target{

	public void mehtod2() {
		System.out.println("special method ...");
	}
	
	public static void main(String[] args) {
		Adapter1 adapter = new Adapter1();
		adapter.method1();
		adapter.mehtod2();
	}

}