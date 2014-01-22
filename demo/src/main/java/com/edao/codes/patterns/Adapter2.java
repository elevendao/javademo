package com.edao.codes.patterns;


/**
 * 接口的适配器模式
 * @author liushuai
 *
 */
public class Adapter2 {
	
	public static void main(String[] args) {
		Adapteeable adaptee1 = new AdapteeWrapper1();
		Adapteeable adaptee2 = new AdapteeWrapper2();
		adaptee1.method1();
		adaptee1.method2();
		adaptee2.method1();
		adaptee2.method2();
		
	}

}

interface Adapteeable {
	void method1();
	void method2();
}

class Adaptee1 implements Adapteeable {
	public void method1() {
		System.out.println("adaptee1 method1 ...");
	}
	public void method2() {
		System.out.println("adaptee1 method2 ...");
	}
}

class Adaptee2 implements Adapteeable {
	public void method1() {
		System.out.println("adaptee2 method1 ...");
	}
	public void method2() {
		System.out.println("adaptee2 method2 ...");
	}
}
/**
 * 像上面的情况，每个实现接口的类都需要实现接口的所有方法，这样就不是很方便
 */

/**
 * 借助抽象类，该抽象类实现了该接口，实现了接口的所有方法，而我们不和原始接口
 * 打交道，只和该抽象类联系。只需写一个类，继承该抽象类，重写需要的方法就可以了
 */
abstract class Wrapper implements Adapteeable {
	public void method1(){}
	public void method2(){}
}

class AdapteeWrapper1 extends Wrapper {
	public void method1() {
		System.out.println("AdapteeWrapper1 method1 ...");
	}
}

class AdapteeWrapper2 extends Wrapper {
	public void method2() {
		System.out.println("AdapteeWrapper2 method2 ...");
	}
}