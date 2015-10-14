/**
 * 版权所有：elevendao
 * 项目名称:demo
 * 创建者: Leo
 * 创建日期: 2015年10月5日
 * 文件说明:
 * 最近修改者：Leo
 * 最近修改日期：2015年10月5日
 */
package com.edao.codes.patterns.iterator.t2;

import java.util.ArrayList;


/**
 * @author Leo
 *
 */
public class PancakeHouseMenuIterator implements Iterator {
	
	ArrayList items;
	int position = 0;
	
	/**
	 * 
	 */
	public PancakeHouseMenuIterator(ArrayList items) {
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.iterator.t2.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		if (position<items.size()) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.edao.codes.patterns.iterator.t2.Iterator#next()
	 */
	@Override
	public Object next() {
		Object obj = items.get(position);
		position = position + 1;
		return obj;
	}
}
