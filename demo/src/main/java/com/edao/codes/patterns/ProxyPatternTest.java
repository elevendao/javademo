package com.edao.codes.patterns;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liushuai
 *
 */
public class ProxyPatternTest {
	public static void main(String[] args) {
//		Sourceable source = new ProxyObject();
//		source.method();
		
		Sourceable s = new Source();
		//Source s = new Source();
		Sourceable proxy = (Sourceable) DynamicProxyHandlerFactory.factory(s);
		proxy.method();
		System.out.println(proxy); // 为何打印出来是null, 而且会执行before(),after()方法
		//当DynamicProxyHandler的invoke方法返回的是null时，打印的值就是null
		//由于System.out.println(proxy)语句会调用proxy的toString()方法，只要调用方法就会触发invoke()方法，从而调用了before(),after()方法
	}
}

interface Sourceable {
	void method();
}

class Source implements Sourceable {
	public void method() {
		System.out.println("original method ... ");
	}
}

class ProxyObject implements Sourceable {
	private Source source;
	public ProxyObject() {
		super();
		this.source = new Source();
	}
	public void method() {
		before();
		this.source.method();
		after();
	}
	
	private void before() {
		System.out.println("before proxy...");
	}
	
	private void after() {
		System.out.println("after proxy...");
	}
}

class DynamicProxyHandler implements InvocationHandler {
	
	private Object obj;
	public void bind(Object obj) {
		this.obj = obj;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		before();
		Object invokeObj = method.invoke(obj, args); // 执行被代理对象的方法
		after();
		return invokeObj;
	}
	
	void before() {
		System.out.println("before()");
	}
	
	void after() {
		System.out.println("after()");
	}
}

class DynamicProxyHandlerFactory {
	
	public static Object factory(Sourceable source) {
		DynamicProxyHandler handler = new DynamicProxyHandler();
		handler.bind(source);
		return Proxy.newProxyInstance(source.getClass().getClassLoader(), source.getClass().getInterfaces(), handler);
	}
	
}
