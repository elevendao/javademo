/**
 * 版权所有：elevendao
 * 项目名称:demo
 * 创建者: Leo
 * 创建日期: 2015年10月5日
 * 文件说明:
 * 最近修改者：Leo
 * 最近修改日期：2015年10月5日
 */
package com.edao.codes.patterns.iterator.t3;


import java.util.Iterator;
import com.edao.codes.patterns.iterator.MenuItem;


/**
 * @author Leo
 */
public class Waitress {

	Menu	pancakeHouseMenu;
	Menu			dinerMenu;

	/**
	 * 
	 */
	public Waitress(Menu pancakeMenuHouse, Menu dinerMenu) {
		this.pancakeHouseMenu = pancakeMenuHouse;
		this.dinerMenu = dinerMenu;
	}

	public void printMenu() {
		Iterator pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator dinerIterator = dinerMenu.createIterator();
		System.out.println("MENU\n----\nBREAKFAST");
		printMenu(pancakeIterator);
		System.out.println("\nLUNCH");
		printMenu(dinerIterator);
	}

	private void printMenu(Iterator iterator) {
		while (iterator.hasNext()) {
			MenuItem menuItem = (MenuItem) iterator.next();
			System.out.println(menuItem.getName() + ", " + menuItem.getPrice() + " -- " + menuItem.getDesciption());
		}
	}

	public static void main(String[] args) {
		Waitress alice = new Waitress(new PancakeHouseMenu(), new DinerMenu());
		alice.printMenu();
	}
}
