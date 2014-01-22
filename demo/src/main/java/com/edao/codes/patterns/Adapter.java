package com.edao.codes.patterns;


/**
 * 对象的适配器模式
 * @author liushuai
 *
 */
public class Adapter implements Target{
	
	private Adaptee adaptee;
	
	public Adapter(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}

	public void method1() {
		adaptee.method1();
	}

	public void mehtod2() {
		System.out.println("special method ...");
	}
	
	public static void main(String[] args) {
		Adaptee adaptee = new Adaptee();
		Adapter adapter = new Adapter(adaptee);
		adapter.method1();
		adapter.mehtod2();
	}

}


class Adaptee {
	public void method1() {
		System.out.println("original method ...");
	}
}

interface Target {
	void method1();
	void mehtod2();
}