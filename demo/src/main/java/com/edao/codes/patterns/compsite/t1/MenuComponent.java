/**
 * 版权所有：elevendao
 * 项目名称:demo
 * 创建者: Leo
 * 创建日期: 2015年10月13日
 * 文件说明:
 * 最近修改者：Leo
 * 最近修改日期：2015年10月13日
 */
package com.edao.codes.patterns.compsite.t1;

import java.nio.channels.UnsupportedAddressTypeException;


/**
 * @author Leo
 *
 */
public abstract class MenuComponent {
	
	public void add(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}
	public void remove(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}
	public MenuComponent getChild(int i) {
		throw new UnsupportedAddressTypeException();
	}
	public String getName() {
		throw new UnsupportedAddressTypeException();
	}
	public String getDescription() {
		throw new UnsupportedAddressTypeException();
	}
	public double getPrice() {
		throw new UnsupportedAddressTypeException();
	}
	public boolean isVegetarian() {
		throw new UnsupportedAddressTypeException();
	}
	public void print() {
		throw new UnsupportedAddressTypeException();
	}
}
