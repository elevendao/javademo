/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-3-31
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-3-31
 */
package com.edao.codes.javase.annotation;

/**
 * @author liushuai
 *
 */
public class AuditBean {

	private String id;
	
	@FactorField("name")
	private String name;
	
	@FactorField(value="cmdtype", isRuleElement=false)
	private String cmdtype;
	
	@FactorField(value="ipAddr", isRuleElement=true)
	private String ipAddr;
	
	@FactorField(value="macAddr", isRuleElement=true)
	private String macAddr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCmdtype() {
		return cmdtype;
	}
	
	public void setCmdtype(String cmdtype) {
		this.cmdtype = cmdtype;
	}
	
	public String getIpAddr() {
		return ipAddr;
	}
	
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
	public String getMacAddr() {
		return macAddr;
	}
	
	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

}
