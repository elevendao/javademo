/**
 * 版权所有：elevendao
 * 项目名称:demo
 * 创建者: Leo
 * 创建日期: 2015年10月13日
 * 文件说明:
 * 最近修改者：Leo
 * 最近修改日期：2015年10月13日
 */
package com.edao.codes.patterns.iterator.t4;


import java.util.Hashtable;
import java.util.Iterator;
import com.edao.codes.patterns.iterator.MenuItem;


/**
 * @author Leo
 */
public class CafeMenu implements Menu {

	private Hashtable	menuItems	= new Hashtable();

	/**
	 * 
	 */
	public CafeMenu() {
		addItem("Soup of the day", "A cup of the soup of the day, with a side salad", true, 3.69);
		addItem("Burrito", "A large burrito, with whole pinto beans, salsa, guacamole", true, 4.29);
		addItem("Veggie Burger and Air Fries", "Veggie burger on a whole wheat bun, lettuce, tomato, and fries", false, 3.99);
	}

	public void addItem(String name, String description, boolean vegetarian, double price) {
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		menuItems.put(menuItem.getName(), menuItem);
	}

	/*
	 * (non-Javadoc)
	 * @see com.edao.codes.patterns.iterator.t4.Menu#createIterator()
	 */
	@Override
	public Iterator createIterator() {
		return menuItems.values().iterator();
	}
}
