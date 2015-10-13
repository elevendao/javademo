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
 *
 */
public class NullIterator implements Iterator {

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Object next() {
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
