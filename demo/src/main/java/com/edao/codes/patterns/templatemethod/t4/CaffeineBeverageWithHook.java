package com.edao.codes.patterns.templatemethod.t4;

public abstract class CaffeineBeverageWithHook {
	
	final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		if (customerWantsCondiments()) {
			addCondiments();
		}
	}
	
	/**
	 * 我们 在这里定义了一个方法，（通常）是空的缺省实现。这个方法只会返回true,不做别的事。
	 * 这就是一个钩子，子类可以覆盖这个方法，但不见得一定要这么做。
	 * @return
	 */
	public boolean customerWantsCondiments() {
		return true;
	}

	abstract void brew();
	
	abstract void addCondiments();
	
	public void pourInCup() {
		System.out.println("Pouring into cup.");
	}
	
	public void boilWater() {
		System.out.println("Boiling Water.");
	}

}
