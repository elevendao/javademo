/**
 * 版权所有：美创科技
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-2-17
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-2-17
 */
package com.edao.codes.solr;

import java.util.List;

/**
 * @author liushuai
 *
 */
public class SearchResult {
	private List items;
	private int totalCount;
	
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
