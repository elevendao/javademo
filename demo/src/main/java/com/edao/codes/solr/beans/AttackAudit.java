/**
 * 版权所有：美创科技
 * 项目名称:capaa-web2.3
 * 创建者: liushuai
 * 创建日期: 2012-7-23
 * 文件说明: 安全攻击事件审计
 * 最近修改者：liushuai
 * 最近修改日期：2012-7-23
 */
package com.edao.codes.solr.beans;

import java.util.Date;
import org.apache.solr.client.solrj.beans.Field;

/**
 * @author liushuai
 *
 */
public class AttackAudit extends Audit {
	@Field("id")
	private String id;
	@Field("euser_id")
	private String euserId;
	@Field("euser")
	private String euser;
	@Field("cert")
	private String cert;
	@Field("hwcode")
	private String hwcode;
	@Field("hwtype")
	private String hwtype;
	@Field("ip_address")
	private String ipAddress;
	@Field("host")
	private String host;
	@Field("mac_address")
	private String macAddress;
	@Field("os_user")
	private String osUser;
	@Field("termcode")
	private String termcode;
	@Field("appname")
	private String appname;
	@Field("md51")
	private String md51;
	@Field("md52")
	private String md52;
	@Field("appstart")
	private Date appstart;
	@Field("appbreak")
	private Date appbreak;
	@Field("certsession_id")
	private String certsessionId;
	@Field("attack_time")
	private Date attackTime;
	@Field("attack_type")
	private String attackType;
	@Field("action_level")
	private String actionLevel;
	@Field("audit_level")
	private String auditLevel;
	@Field("reason")
	private String reason;
	@Field("memo")
	private String memo;
	@Field("review")
	private String review;
	
	private String faketext;
	
	private String subRule;//订阅规则
	private String alertLevel;//订阅警告级别
	
	private Integer logId;
	
	
	public Integer getLogId() {
		return logId;
	}
	
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public String getSubRule() {
		return subRule;
	}
	public void setSubRule(String subRule) {
		this.subRule = subRule;
	}
	public String getAlertLevel() {
		return alertLevel;
	}
	public void setAlertLevel(String alertLevel) {
		this.alertLevel = alertLevel;
	}
	
	/**  
	 * 获取faketext  
	 * @return faketext faketext  
	 */
	public String getFaketext() {
		return faketext;
	}
	
	/**  
	 * 设置faketext  
	 * @param faketext faketext  
	 */
	public void setFaketext(String faketext) {
		this.faketext = faketext;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEuserId() {
		return euserId;
	}
	public void setEuserId(String euserId) {
		this.euserId = euserId;
	}
	public String getEuser() {
		return euser;
	}
	public void setEuser(String euser) {
		this.euser = euser;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	public String getHwcode() {
		return hwcode;
	}
	public void setHwcode(String hwcode) {
		this.hwcode = hwcode;
	}
	public String getHwtype() {
		return hwtype;
	}
	public void setHwtype(String hwtype) {
		this.hwtype = hwtype;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getOsUser() {
		return osUser;
	}
	public void setOsUser(String osUser) {
		this.osUser = osUser;
	}
	public String getTermcode() {
		return termcode;
	}
	public void setTermcode(String termcode) {
		this.termcode = termcode;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getMd51() {
		return md51;
	}
	public void setMd51(String md51) {
		this.md51 = md51;
	}
	public String getMd52() {
		return md52;
	}
	public void setMd52(String md52) {
		this.md52 = md52;
	}
	public Date getAppstart() {
		return subtract(appstart);
	}
	public void setAppstart(Date appstart) {
		this.appstart = appstart;
	}
	public Date getAppbreak() {
		return subtract(appbreak);
	}
	public void setAppbreak(Date appbreak) {
		this.appbreak = appbreak;
	}
	public String getCertsessionId() {
		return certsessionId;
	}
	public void setCertsessionId(String certsessionId) {
		this.certsessionId = certsessionId;
	}
	public Date getAttackTime() {
		return subtract(attackTime);
	}
	public void setAttackTime(Date attackTime) {
		this.attackTime = attackTime;
	}
	public String getAttackType() {
		return attackType;
	}
	public void setAttackType(String attackType) {
		this.attackType = attackType;
	}
	public String getActionLevel() {
		return actionLevel;
	}
	public void setActionLevel(String actionLevel) {
		this.actionLevel = actionLevel;
	}
	public String getAuditLevel() {
		return auditLevel;
	}
	public void setAuditLevel(String auditLevel) {
		this.auditLevel = auditLevel;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	public String getDataSource() {
		return "3";
	}
	
	@Override
	public String getUser() {
		String user = getEuser();
		return user == null || user.equals("") ? "匿名用户" : user;
	}
}
