/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-2-17
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-2-17
 */
package com.edao.codes.beans;

import java.util.HashMap;

/**
 * @author liushuai
 * @param <T>
 *
 */
public class GroupResult {
	int count;
	HashMap<String, Object> vals;

	public GroupResult() {
		super();
		vals = new HashMap<String, Object>();
	}
	
	public void put(String key, Object value) {
		vals.put(key, value);
	}
	
	public Object get(String key) {
		return vals.get(key);
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public String toString() {
		return "count=" + count + "," + vals.toString();
	}
	
	@SuppressWarnings("unchecked")
	public GroupResult clone() {
		GroupResult clone = new GroupResult();
		clone.setCount(count);
		clone.vals = (HashMap<String, Object>) this.vals.clone();
		return clone;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return vals.hashCode();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof GroupResult) {
			GroupResult o = (GroupResult) obj;
			return this.vals.equals(o.vals);
		}
		return false;
	}
	
	public static void main(String[] args) {
		GroupResult r = new GroupResult();
		r.put("sessid", 1224234);
		r.put("cmdtype", "sdfs");
		r.setCount(4);
		System.out.println(r);
		
		GroupResult clone = (GroupResult) r.clone();
		clone.setCount(6);
//		clone.put("cmdtype", "select");
		System.out.println(clone);
		
		System.out.println(clone.equals(r));
	}
	
}
