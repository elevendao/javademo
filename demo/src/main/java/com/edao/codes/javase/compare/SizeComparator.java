/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-4-14
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-4-14
 */
package com.edao.codes.javase.compare;

import java.util.Comparator;

/**
 * @author liushuai
 *
 */
public class SizeComparator implements Comparator<HDTV>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(HDTV o1, HDTV o2) {
		if (o1.getSize() > o2.getSize()) {
			return 1;
		} else if(o1.getSize() < o2.getSize()) {
			return -1;
		}
		
		return 0;
	}

}
