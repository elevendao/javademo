/**
 * 版权所有：elevendao
 * 项目名称:demo
 * 创建者: Leo
 * 创建日期: 2015年10月13日
 * 文件说明:
 * 最近修改者：Leo
 * 最近修改日期：2015年10月13日
 */
package com.edao.codes.patterns.compsite.t2;

import java.util.Iterator;


/**
 * @author Leo
 */
public class MenuItem extends MenuComponent {

	String	name;
	String	description;
	boolean	vegetarian;
	double	price;

	public MenuItem(String name, String description, boolean vegetarian, double price) {
		this.name = name;
		this.description = description;
		this.vegetarian = vegetarian;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public double getPrice() {
		return price;
	}
	
	public void print() {
		System.out.print("	" + getName());
		if (isVegetarian()) {
			System.out.print("(v)");
		}
		System.out.println(", " + getPrice());
		System.out.println("		-- " + getDescription());
	}
	
	public Iterator createIterator() {
		return new NullIterator();
	}
}
