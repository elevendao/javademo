/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-4-16
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-4-16
 */
package com.edao.codes.beans;

import java.util.Comparator;

import org.apache.solr.client.solrj.SolrQuery.ORDER;

/**
 * @author liushuai
 *
 */
public class NewGroupResultComparator implements Comparator<NewGroupResult>  {
	private ORDER order;
	
	public NewGroupResultComparator(ORDER order) {
		this.order = order;
	}
	
	public int compare(NewGroupResult o1, NewGroupResult o2) {
		if (ORDER.desc == order) {
			if (o1.count > o2.count) {
				return -1;
			} else if (o1.count < o2.count) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if (o1.count > o2.count) {
				return 1;
			} else if (o1.count < o2.count) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
