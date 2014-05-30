/**
 * 版权所有：美创科技
 * 项目名称:capaa-web2.3
 * 创建者: liushuai
 * 创建日期: 2012-7-23
 * 文件说明: 活动证书审计
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
public class CertAudit extends Audit {
	@Field("id")
	private String id;
	
	@Field("euser_id")
	private String euserId;
	@Field("euser")
	private String euser;
	@Field("audit_euserid")
	private String auditEuserId;
	@Field("audit_euser")
	private String auditEuser;
	@Field("egroup_id")
	private String egroupId;
	@Field("egroup")
	private String egroup;
	@Field("earea_id")
	private String eareaId;
	@Field("earea")
	private String earea;
	@Field("cert")
	private String cert;
	@Field("hwcode")
	private String hwcode;
	@Field("hwtype")
	private String hwtype;
	@Field("login_name")
	private String loginName;
	@Field("login_class")
	private String loginClass;
	@Field("ip_address")
	private String ipAddress;
	@Field("primary_ip")
	private String primaryIp;
	@Field("mac_address")
	private String macAddress;
	@Field("termcode")
	private String termcode;
	@Field("appname")
	private String appname;
	@Field("client_process")
	private String clientProcess;
	@Field("logon_time")
	private Date logonTime;
	@Field("logoff_time")
	private Date logoffTime;
	@Field("validate_time")
	private Date validateTime;
	@Field("validate_times")
	private String validateTimes;
	@Field("cmdtype")
	private String cmdtype;
	@Field("what")
	private String what;
	@Field("action_level")
	private String actionLevel;
	@Field("audit_level")
	private String auditLevel;
	//@Field("last_modified")
	//private String lastModified;
	//显示用
	private String certext;
	private Integer logId;
	
	
	public Integer getLogId() {
		return logId;
	}
	
	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	private String subRule;//订阅规则
	private String alertLevel;//订阅警告级别
	
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
	 * 获取certext  
	 * @return certext certext  
	 */
	public String getCertext() {
		return certext;
	}

	
	/**  
	 * 设置certext  
	 * @param certext certext  
	 */
	public void setCertext(String certext) {
		this.certext = certext;
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

	public String getAuditEuserId() {
		return auditEuserId;
	}

	public void setAuditEuserId(String auditEuserId) {
		this.auditEuserId = auditEuserId;
	}

	public String getAuditEuser() {
		return auditEuser;
	}

	public void setAuditEuser(String auditEuser) {
		this.auditEuser = auditEuser;
	}

	public String getEgroupId() {
		return egroupId;
	}

	public void setEgroupId(String egroupId) {
		this.egroupId = egroupId;
	}

	public String getEgroup() {
		return egroup;
	}

	public void setEgroup(String egroup) {
		this.egroup = egroup;
	}

	public String getEareaId() {
		return eareaId;
	}

	public void setEareaId(String eareaId) {
		this.eareaId = eareaId;
	}

	public String getEarea() {
		return earea;
	}

	public void setEarea(String earea) {
		this.earea = earea;
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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginClass() {
		return loginClass;
	}

	public void setLoginClass(String loginClass) {
		this.loginClass = loginClass;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPrimaryIp() {
		return primaryIp;
	}

	public void setPrimaryIp(String primaryIp) {
		this.primaryIp = primaryIp;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
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

	public String getClientProcess() {
		return clientProcess;
	}

	public void setClientProcess(String clientProcess) {
		this.clientProcess = clientProcess;
	}

	public Date getLogonTime() {
		return subtract(logonTime);
	}

	public void setLogonTime(Date logonTime) {
		this.logonTime = logonTime;
	}

	public Date getLogoffTime() {
		return subtract(logoffTime);
	}

	public void setLogoffTime(Date logoffTime) {
		this.logoffTime = logoffTime;
	}

	public Date getValidateTime() {
		return subtract(validateTime);
	}

	public void setValidateTime(Date validateTime) {
		this.validateTime = validateTime;
	}

	public String getValidateTimes() {
		return validateTimes;
	}

	public void setValidateTimes(String validateTimes) {
		this.validateTimes = validateTimes;
	}

	public String getCmdtype() {
		return cmdtype;
	}

	public void setCmdtype(String cmdtype) {
		this.cmdtype = cmdtype;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
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

	public void setAuditLevel(String audtiLevel) {
		this.auditLevel = audtiLevel;
	}

	/*public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}*/
	
	public String getDataSource() {
		return "2";
	}
	
	@Override
	public String getUser() {
		String user = getEuser();
		return user == null || user.equals("") ? "匿名用户" : user;
	}
}
