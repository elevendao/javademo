/**
 * 版权所有：美创科技
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-2-17
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-2-17
 */
package com.edao.codes.beans;

/**
 * @author liushuai
 *
 */
public class GroupResult {
	String group;
	int count;
	
	public GroupResult(String group, int count) {
		this.group = group;
		this.count = count;
	}
	
	public String toString() {
		return "count: " + count + "\t group: " + group;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GroupResult) {
			GroupResult other = (GroupResult) obj;
			return this.group.equals(other.group) && this.count == other.count;
		}
		return false;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
