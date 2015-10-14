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
import java.util.Stack;


/**
 * @author Leo
 *
 */
public class CompsiteIterator implements Iterator {
	Stack stack = new Stack();
	
	/**
	 * 
	 */
	public CompsiteIterator(Iterator iterator) {
		stack.push(iterator);
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		/*
		 * check stack whether it is empty, if empty, there is no more element;
		 * if not empty, then peek iterator from stack and check whether it has next element.
		 * if the iterator is empty, then pop it from stack. call hasNext() recursively.
		 *  
		 */
		if (stack.empty()) {
			return false;
		} else {
			Iterator iterator = (Iterator) stack.peek();
			if (!iterator.hasNext()) {
				stack.pop();
				return hasNext();
			} else {
				return true;
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Object next() {
		/*
		 * if has next element, then we can get it from iterator which in the stack.
		 */
		if (hasNext()) {
			Iterator iterator = (Iterator) stack.peek();
			MenuComponent component = (MenuComponent) iterator.next();
			if (component instanceof Menu) {
				stack.push(component.createIterator());
			}
			return component;
		}
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
