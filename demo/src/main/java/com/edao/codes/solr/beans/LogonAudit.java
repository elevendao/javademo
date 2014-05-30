/**
 * 版权所有：美创科技
 * 项目名称:capaa-web2.3
 * 创建者: liushuai
 * 创建日期: 2012-7-23
 * 文件说明: 登陆审计
 * 最近修改者：liushuai
 * 最近修改日期：2012-7-23
 */
package com.edao.codes.solr.beans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import org.apache.solr.client.solrj.beans.Field;

/**
 * @author liushuai
 *
 */
public class LogonAudit extends Audit implements Cloneable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2963784383237830291L;
	@Field("id")
	private String id;
	@Field("certsession_id")
	private String certsessionId;
	@Field("rule_name")
	private String ruleName;
	@Field("euser_id")
	private String euserId;
	@Field("euser")
	private String euser;
	@Field("dbuser")
	private String dbuser;
	@Field("ip_address")
	private String ipAddress;
	@Field("host")
	private String host;
	@Field("mac_address")
	private String macAddress;
	@Field("end_ip")
	private String endIp;
	@Field("os_user")
	private String osUser;
	@Field("appname")
	private String appname;
	@Field("end_app")
	private String endApp;
	@Field("logon_time")
	private Date logonTime;
	@Field("logoff_time")
	private Date logoffTime;
	@Field("cmdtype")
	private String cmdtype; 
	@Field("what")
	private String what;
	@Field("serverhost")
	private String serverhost;
	@Field("dbname")
	private String dbname;
	@Field("instance_name")
	private String instanceName;
	@Field("action_level")
	private String actionLevel;
	@Field("audit_level")
	private String auditLevel;
	@Field("sid")
	private String sid;
	@Field("serial")
	private String serial;
	@Field("audsid")
	private String audsid;
	@Field("review")
	private String review;
	@Field("appuser")
	private String appuser;

	public String getAppuser() {
		return appuser;
	}
	public void setAppuser(String appuser) {
		this.appuser = appuser;
	}
	private String subRule;//订阅规则
	private String alertLevel;//订阅警告级别
	private String sessionid;
	private String times;
	private Date lastModified;
	
	private Integer logId;
	
	
	public Integer getLogId() {
		return logId;
	}
	
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	private boolean logonTimeFlag = false;
	private boolean logoffTimeFlag = false;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCertsessionId() {
		return certsessionId;
	}
	public void setCertsessionId(String certsessionId) {
		this.certsessionId = certsessionId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
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
	public String getDbuser() {
		return dbuser;
	}
	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
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
	public String getEndIp() {
		return endIp;
	}
	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
	public String getOsUser() {
		return osUser;
	}
	public void setOsUser(String osUser) {
		this.osUser = osUser;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getEndApp() {
		return endApp;
	}
	public void setEndApp(String endApp) {
		this.endApp = endApp;
	}
	public Date getLogonTime() {
		if (!logonTimeFlag) {
			this.logonTime = subtract(logonTime);
			logonTimeFlag = true;
		}
		return logonTime;
		//return subtract(logonTime);
	}
	public void setLogonTime(Date logonTime) {
		this.logonTime = logonTime;
	}
	public Date getLogoffTime() {
		if (!logoffTimeFlag) {
			this.logoffTime = subtract(logoffTime);
			logoffTimeFlag = true;
		}
		return logoffTime;
		//return subtract(logoffTime);
	}
	public void setLogoffTime(Date logoffTime) {
		this.logoffTime = logoffTime;
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
	public String getServerhost() {
		return serverhost;
	}
	public void setServerhost(String serverhost) {
		this.serverhost = serverhost;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
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
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getAudsid() {
		return audsid;
	}
	public void setAudsid(String audsid) {
		this.audsid = audsid;
	}

	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	public String getDataSource() {
		return "0";
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	
	@Override
	public Object clone() {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(this);
			
			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bis);
			return ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	@Override
	public String getUser() {
		String user = getEuser();
		if(user == null){
			user = getAppuser();
		}		
		if(user == null){
			user = getDbuser();
		}
		
		return user == null || user.equals("") ? "匿名用户" : user;
	}
	
	
}
