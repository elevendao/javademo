/**
 * 版权所有：美创科技
 * 项目名称:capaa-web-b-2.6.0.0
 * 创建者: liushuai
 * 创建日期: 2014-1-21
 * 文件说明: 查询类型
 * 最近修改者：liushuai
 * 最近修改日期：2014-1-21
 */
package com.edao.codes.solr.constant;

/**
 * @author liushuai
 *
 */
public enum QueryType {
	/** simple */
	SIMPLE(0),
	/** extended */
	EXTENDED(1),
	/** advanced */
	ADVANCED(2);
	
	private int val;
	
	private QueryType(int val) {
		this.val = val;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.val);
	}
	
	/**
	 * valueOf
	 * @param val dataSource value
	 * @return DataSource
	 */
	public static QueryType getEnum(int val) {
		for (QueryType a : values()) {
			if (val == a.val) {
				return a;
			}
		}
		throw new IllegalArgumentException("error value");
	}
	
	/**
	 * valueOf
	 * @param value dataSource value
	 * @return DataSource
	 */
	public static QueryType getEnum(String value) {
		int v;
		try {
			v = Integer.valueOf(value);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("error value : " + value);
		}
		return getEnum(v);
	}
}
