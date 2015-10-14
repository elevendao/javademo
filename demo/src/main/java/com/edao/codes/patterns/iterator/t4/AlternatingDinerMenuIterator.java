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


import java.util.Calendar;
import java.util.Iterator;
import com.edao.codes.patterns.iterator.MenuItem;


/**
 * @author Leo
 */
public class AlternatingDinerMenuIterator implements Iterator {

	MenuItem[]	items;
	int			position	= 0;

	/**
	 * 
	 */
	public AlternatingDinerMenuIterator(MenuItem[] items) {
		this.items = items;
		Calendar rightNow = Calendar.getInstance();
		position = rightNow.get(Calendar.DAY_OF_WEEK) % 2;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		if (position >= items.length || items[position] == null) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Object next() {
		MenuItem menuItem = items[position];
		position = position + 2;
		return menuItem;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		new UnsupportedOperationException("Alternating Diner Menu Iterator does not support remove()");
	}
}
