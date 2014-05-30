/**
 * 版权所有：美创科技
 * 项目名称:capaa-web2.6
 * 创建者: liushuai
 * 创建日期: 2013-5-22
 * 文件说明: 搜索结果包装类
 * 最近修改者：liushuai
 * 最近修改日期：2013-5-22
 */
package com.edao.codes.solr.impl;

import java.util.List;


/**
 * @author liushuai
 *
 */
public class SearchResult<T> {
	private List<T> items;
	private SearchParam searchParam;
	private int totalCount;
	
	/**
	 * 返回搜索结果
	 * @return List
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * 设置搜索结果
	 * @param items 搜索结果
	 */
	public void setItems(List<T> items) {
		this.items = items;
	}
	
	/**
	 * 返回搜索参数
	 * @return SearchParam
	 */
	public SearchParam getSearchParam() {
		return searchParam;
	}

	/**
	 * 设置搜索参数
	 * @param searchParam 搜索参数
	 */
	public void setSearchParam(SearchParam searchParam) {
		this.searchParam = searchParam;
	}
	
	/**
	 * 返回搜索结果总数
	 * @return int
	 */
	public int getTotalCount() {
		return totalCount;
	}
	
	/**
	 * 设置搜索结果总数
	 * @param totalCount 搜索结果总数
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
