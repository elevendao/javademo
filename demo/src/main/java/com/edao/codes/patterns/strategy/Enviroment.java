/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-3-14
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-3-14
 */
package com.edao.codes.patterns.strategy;

/**
 * @author liushuai
 *
 */
public class Enviroment {
	Strategy strategy;
	
	public Enviroment(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public Strategy getStrategy() {
		return strategy;
	}
	
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public int calculate(int a, int b) {
		return strategy.calculate(a, b);
	}
	
	public static void main(String[] args) {
		AddStrategy addStrategy = new AddStrategy();
		Enviroment env = new Enviroment(addStrategy);
		System.out.println(env.calculate(3, 4));
		
		SubStrategy subStrategy = new SubStrategy();
		env.setStrategy(subStrategy);
		System.out.println(env.calculate(3, 4));
	}
}
