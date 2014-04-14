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

/**
 * @author liushuai
 *
 */
public class HDTV implements Comparable<HDTV> {

	private int size;
	private String brand;
	
	public HDTV(int size, String brand) {
		this.size = size;
		this.brand = brand;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(HDTV o) {
		if (this.size > o.size) {
			return 1;
		} else if (this.size < o.size) {
			return -1;
		}
		return 0;
	}
}
