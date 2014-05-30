/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-5-30
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-5-30
 */
package com.edao.codes.solr.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.edao.codes.solr.constant.CommonParams;
import com.edao.codes.solr.constant.DataSource;

/**
 * @author liushuai
 *
 */
public class PivotFields {
	private String pivots;
	private String[] fields;
	private List<String> mainFields = new ArrayList<String>();
	private List<String> restFields = new ArrayList<String>();
	private boolean isSingle = false;
	DataSource dataSource;
	public PivotFields(DataSource dataSource, String pivotFields) {
		this.dataSource = dataSource;
		this.pivots = pivotFields;
		fields = pivotFields.split(",");
		if (DataSource.ACCESS == dataSource) {
			for (String f : fields) {
				f = f.trim();
				if (CommonParams.accessFieldMap.containsKey(f)) {
					mainFields.add(CommonParams.accessFieldMap.get(f));
				} else {
					restFields.add(f);
				}
			}
			if (restFields.size() > 0){
				mainFields.add("hashid");
			}
		} else {
			for (String f : fields) {
				mainFields.add(f.trim());
			}
		}
		if (mainFields.size() == 1) {
			isSingle = true;
		}
	}
	
	public boolean isMainFieldsEmpty() {
		return mainFields.size() == 0;
	}
	
	public boolean isRestFieldsEmpty() {
		return restFields.size() == 0;
	}
	
	public String mainFields() {
		return StringUtils.join(mainFields, ',');
	}

	/**
	 * @return the pivots
	 */
	public String getPivots() {
		return pivots;
	}

	/**
	 * @param pivots the pivots to set
	 */
	public void setPivots(String pivots) {
		this.pivots = pivots;
	}

	/**
	 * @return the fields
	 */
	public String[] getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(String[] fields) {
		this.fields = fields;
	}

	/**
	 * @return the restFields
	 */
	public List<String> getRestFields() {
		return restFields;
	}

	/**
	 * @param restFields the restFields to set
	 */
	public void setRestFields(List<String> restFields) {
		this.restFields = restFields;
	}

	/**
	 * @return the isSingle
	 */
	public boolean isSingle() {
		return isSingle;
	}

	/**
	 * @param isSingle the isSingle to set
	 */
	public void setSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}

	/**
	 * @param mainFields the mainFields to set
	 */
	public void setMainFields(List<String> mainFields) {
		this.mainFields = mainFields;
	}

	/**
	 * @return the mainFields
	 */
	public List<String> getMainFields() {
		return mainFields;
	}
}
