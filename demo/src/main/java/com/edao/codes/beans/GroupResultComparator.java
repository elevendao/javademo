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

import java.util.Comparator;

import org.apache.solr.client.solrj.SolrQuery.ORDER;

/**
 * @author liushuai
 *
 */
public class GroupResultComparator implements Comparator<GroupResult> {
	private ORDER order;
	
	public GroupResultComparator(ORDER order) {
		this.order = order;
	}
	
	public int compare(GroupResult o1, GroupResult o2) {
		if (ORDER.desc == order) {
			if (o1.count > o2.count) {
				return -1;
			} else if (o1.count < o2.count) {
				return 1;
			} else {
				return o1.group.compareTo(o2.group);
			}
		} else {
			if (o1.count > o2.count) {
				return 1;
			} else if (o1.count < o2.count) {
				return -1;
			} else {
				return o1.group.compareTo(o2.group);
			}
		}
	}
	
}
