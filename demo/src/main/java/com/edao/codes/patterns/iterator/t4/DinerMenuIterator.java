/**
 * 版权所有：elevendao
 * 项目名称:demo
 * 创建者: Leo
 * 创建日期: 2015年10月5日
 * 文件说明:
 * 最近修改者：Leo
 * 最近修改日期：2015年10月5日
 */
package com.edao.codes.patterns.iterator.t4;


import java.util.Iterator;
import com.edao.codes.patterns.iterator.MenuItem;


/**
 * @author Leo
 */
public class DinerMenuIterator implements Iterator {

	MenuItem[]	items;
	int			position	= 0;

	/**
	 * 
	 */
	public DinerMenuIterator(MenuItem[] items) {
		this.items = items;
	}

	/*
	 * (non-Javadoc)
	 * @see com.edao.codes.patterns.iterator.t2.Iterator#hasNext()
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
	 * @see com.edao.codes.patterns.iterator.t2.Iterator#next()
	 */
	@Override
	public Object next() {
		MenuItem menuItem = items[position];
		position = position + 1;
		return menuItem;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		if (position <= 0) {
			throw new IllegalStateException("You can't remove an item until you've done at least one next()");
		}
		if (items[position - 1] != null) {
			for (int i = position - 1; i < items.length - 1; i++) {
				items[i] = items[i + 1];
			}
			items[items.length - 1] = null;
		}
	}
}
