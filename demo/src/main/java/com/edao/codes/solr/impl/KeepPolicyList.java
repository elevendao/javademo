package com.edao.codes.solr.impl;


public class KeepPolicyList {
	private Integer id;
	private Integer keepTime;
    private String keepUnit;
    private String recordType;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getKeepTime() {
		return keepTime;
	}

	
	public void setKeepTime(Integer keepTime) {
		this.keepTime = keepTime;
	}

	
	public String getKeepUnit() {
		return keepUnit;
	}

	
	public void setKeepUnit(String keepUnit) {
		this.keepUnit = keepUnit;
	}

	
	public String getRecordType() {
		return recordType;
	}

	
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
    
}
