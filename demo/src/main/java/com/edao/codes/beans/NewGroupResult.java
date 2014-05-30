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
import java.util.Map;

/**
 * @author liushuai
 * @param <T>
 *
 */
public class NewGroupResult<K,V> extends HashMap<K,V> {
	String group;
	int count;
	
	public NewGroupResult() {
		super();
	}
	
	public NewGroupResult(Map<? extends K, ? extends V> m) {
		super(m);
	}
	
	public Object clone() {
		NewGroupResult<K, V> clone = new NewGroupResult<K, V>(this);
		clone.setCount(count);
		return clone;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public String toString() {
		return "count=" + count + "," + super.toString();
	}
	
	public static void main(String[] args) {
		NewGroupResult<String, Object> r = new NewGroupResult<String, Object>();
		r.put("sessid", 1224234);
		r.put("cmdtype", "sdfs");
		r.setCount(4);
		System.out.println(r);
		
		NewGroupResult<String, Object> clone = (NewGroupResult<String, Object>) r.clone();
		clone.setCount(6);
		clone.put("cmdtype", "select");
		System.out.println(clone);
		
		System.out.println(clone.equals(r));
	}
}
