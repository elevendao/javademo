/**
 * 版权所有：美创科技
 * 项目名称:capaa-web2.3
 * 创建者: liushuai
 * 创建日期: 2012-7-20
 * 文件说明: 访问审计
 * 最近修改者：liushuai
 * 最近修改日期：2012-7-20
 */
package com.edao.codes.solr.beans;

import java.util.Date;
import org.apache.solr.client.solrj.beans.Field;

/**
 * @author liushuai
 *
 */
public class AccessAudit extends Audit {
	@Field("id")
	private String id;
	//@Field("id_copy")
	private String idcopy;
	@Field("sessid")
	private String sessid;
	@Field("rule_name")
	@FactorField("rule_name")
	private String ruleName;
	//@Field("appuser")
	private String appuser;
	//@Field("euser_id")
	private String euserId;
	//@Field("euser")
	@FactorField("euser")
	private String euser;
	//@Field("sessioninfo")
	private String sessioninfo;
	//@Field("end_ip")
	private String endIp;
	@Field("optime")
	@FactorField("optime")
	private Date optime;
	@Field("cmdtype")
	@FactorField("cmdtype")
	private String cmdtype;
	//@Field("object_owner")
	@FactorField("object_owner")
	private String objectOwner;
	//@Field("object_name")
	@FactorField("object_name")
	private String objectName;
	//@Field("object_type")
	private String objectType;
	//@Field("sqlid")
	private String sqlId;
	//@Field("sqltext")
	@FactorField("sqltext")
	private String sqltext;
	//@Field("bindvalue")
	private String bindvalue;
	//@Field("action_level")
	@FactorField("action_level")
	private String actionLevel;
	//@Field("audit_level")
	@FactorField("audit_level")
	private String auditLevel;
	//@Field("tx_id")
	private String txId;
	//@Field("scn")
	private long scn;
	//@Field("cscn")
	private long cscn;
	@Field("review")
	private String review;
	
	//@Field("lg_id")
	private String lgId;
	//@Field("certsession_id")
	private String certsessionId;
	//@Field("dbuser")
	@FactorField("dbuser")
	private String dbuser;
	//@Field("ip_address")
	@FactorField("ip_address")
	private String ipAddress;
	//@Field("host")
	@FactorField("host")
	private String host;
	//@Field("mac_address")
	@FactorField("mac_address")
	private String macAddress;
	//@Field("os_user")
	@FactorField("os_user")
	private String osUser;
	//@Field("appname")
	@FactorField("appname")
	private String appname;
	//@Field("end_app")
	private String endApp;
	//@Field("logon_time")
	private Date logonTime;
	//@Field("logoff_time")
	private Date logoffTime;
	//@Field("what")
	private String what;
	//@Field("serverhost")
	private String serverhost;
	//@Field("dbname")
	@FactorField("dbname")
	private String dbname;
	//@Field("instance_name")
	private String instanceName;
	//@Field("sid")
	private String sid;
	//@Field("serial")
	private String serial;
	//@Field("audsid")
	private String audsid;

	//@Field("group_id")
	private String groupId;
	//@Field("group_name")
	@FactorField("group_name")
	private String groupName;
	//@Field("area_id")
	private String areaId;
	//@Field("area_name")
	@FactorField("area_name")
	private String areaName;
	
	private LogonAudit logonAudit;
	
	private String subRule; //订阅规则
	private String alertLevel; //订阅警告级别
	
	private String sessionid;
	private String times;
	private String assetsetname;
	private String assetsetid;
	
	private Integer logId;
	

	
	public Integer getLogId() {
		return logId;
	}

	
	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	//访问对象自身的属性
	private String selfAppname;
	
	public String getSelfAppname() {
		if (logonAudit != null) {
			return logonAudit.getAppname();
		}
		return selfAppname;
	}

	public void setSelfAppname(String selfAppname) {
		this.selfAppname = selfAppname;
	}

	private boolean optimeFlag = false;
	private boolean logonTimeFlag = false;
	private boolean logoffTimeFlag = false;
	
	private String sqltextchs; //sql 翻译
	/**  
	 * 获取sqltextchs  
	 * @return sqltextchs sqltextchs  
	 */
	public String getSqltextchs() {
		return sqltextchs;
	}
	

	
	/**  
	 * 设置sqltextchs  
	 * @param sqltextchs sqltextchs  
	 */
	public void setSqltextchs(String sqltextchs) {
		this.sqltextchs = sqltextchs;
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
	 * @return 访问事件的ID
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id 访问事件的ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @param id 访问事件的ID
	 */
	public void setId(Integer id) {
		this.id = "" + id;
	}
	
	/**
	 * @return 数据库登录的ID号
	 */
	public String getSessid() {
		return sessid;
	}
	
	/**
	 * @param sessid 数据库登录的ID号
	 */
	public void setSessid(String sessid) {
		this.sessid = sessid;
	}
	
	/**
	 * @return 规则名称
	 */
	@FactorField("rule_name")
	public String getRuleName() {
		return ruleName;
	}
	
	/**
	 * @param ruleName 规则名称
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	/**
	 * @return 应用用户名称
	 */
	public String getAppuser() {
		return appuser;
	}
	
	/**
	 * @param appuser 应用用户名称
	 */
	public void setAppuser(String appuser) {
		this.appuser = appuser;
	}
	
	/**
	 * @return 企业用户ID
	 */
	public String getEuserId() {
		if (logonAudit == null) {
			return euserId;
		}
		return logonAudit.getEuserId();
	}
	
	/**
	 * @param euserId 企业用户ID
	 */
	public void setEuserId(String euserId) {
		this.euserId = euserId;
		if (logonAudit != null) {
			logonAudit.setEuserId(euserId);
		}
	}
	
	/**
	 * @return 企业用户名称
	 */
	public String getEuser() {
		if (logonAudit == null) {
			return euser;
		}
		return logonAudit.getEuser();
	}
	
	/**
	 * @param euser 企业用户名称
	 */
	public void setEuser(String euser) {
		this.euser = euser;
		if (logonAudit != null) {
			logonAudit.setEuser(euser);
		}
	}
	
	/**
	 * @return 中间件的session信息
	 */
	public String getSessioninfo() {
		return sessioninfo;
	}
	
	/**
	 * @param sessioninfo 中间件的session信息
	 */
	public void setSessioninfo(String sessioninfo) {
		this.sessioninfo = sessioninfo;
	}
	
	/**
	 * @return 终端IP
	 */
	public String getEndIp() {
		return endIp;
	}
	
	/**
	 * @param endIp 终端IP
	 */
	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
	
	/**
	 * @return 操作时间
	 */
	public Date getOptime() {
//		if (!optimeFlag) {
//			this.optime = subtract(optime);
//			optimeFlag = true;
//		}
		return optime;
		//return subtract(optime);
	}
	
	/**
	 * @param optime 操作时间
	 */
	public void setOptime(Date optime) {
		this.optime = optime;
	}
	
	/**
	 * @return 操作类型
	 */
	public String getCmdtype() {
		return cmdtype;
	}
	
	/**
	 * @param cmdtype 操作类型
	 */
	public void setCmdtype(String cmdtype) {
		this.cmdtype = cmdtype;
	}
	
	/**
	 * @return 资产拥有者
	 */
	public String getObjectOwner() {
		return objectOwner;
	}
	
	/**
	 * @param objectOwner 资产拥有者
	 */
	public void setObjectOwner(String objectOwner) {
		this.objectOwner = objectOwner;
	}
	
	/**
	 * @return 资产名称
	 */
	public String getObjectName() {
		return objectName;
	}
	
	/**
	 * @param objectName 资产名称
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	/**
	 * @return 资产类型
	 */
	public String getObjectType() {
		return objectType;
	}
	
	/**
	 * @param objectType 资产类型
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	/**
	 * @return SQL语句
	 */
	public String getSqltext() {
		return sqltext;
	}
	
	/**
	 * @param sqltext SQL语句
	 */
	public void setSqltext(String sqltext) {
		this.sqltext = sqltext;
	}
	
	/**
	 * @return 绑定变量
	 */
	public String getBindvalue() {
		return bindvalue;
	}
	
	/**
	 * @param bindvalue 绑定变量
	 */
	public void setBindvalue(String bindvalue) {
		this.bindvalue = bindvalue;
	}
	
	/**
	 * @return 响应行为
	 */
	public String getActionLevel() {
		return actionLevel;
	}
	
	/**
	 * @param actionLevel 响应行为
	 */
	public void setActionLevel(String actionLevel) {
		this.actionLevel = actionLevel;
	}
	
	/**
	 * @return 审计级别
	 */
	public String getAuditLevel() {
		return auditLevel;
	}
	
	/**
	 * @param auditLevel 审计级别
	 */
	public void setAuditLevel(String auditLevel) {
		this.auditLevel = auditLevel;
	}
	
	/**
	 * @return 事务id
	 */
	public String getTxId() {
		return txId;
	}
	
	/**
	 * @param txId 事务id
	 */
	public void setTxId(String txId) {
		this.txId = txId;
	}
	
	/**
	 * @return system change number
	 */
	public long getScn() {
		return scn;
	}
	
	/**
	 * @param scn system change number
	 */
	public void setScn(long scn) {
		this.scn = scn;
	}
	
	/**
	 * @return commit system change number
	 */
	public long getCscn() {
		return cscn;
	}
	
	/**
	 * @param cscn commit system change number
	 */
	public void setCscn(long cscn) {
		this.cscn = cscn;
	}
	
	/**
	 * @return 审阅
	 */
	public String getReview() {
		return this.review;
	}
	
	/**
	 * @param review 审阅
	 */
	public void setReview(String review) {
		this.review = review;
	}
	
	/**
	 * @return 数据库登陆事件ID
	 */
	public String getLgId() {
		return lgId;
	}
	
	/**
	 * @param lgId 数据库登陆事件ID
	 */
	public void setLgId(String lgId) {
		this.lgId = lgId;
	}
	
	/**
	 * @return 证书登录Session的id号
	 */
	public String getCertsessionId() {
		return certsessionId;
	}
	
	/**
	 * @param certsessionId 证书登录Session的id号
	 */
	public void setCertsessionId(String certsessionId) {
		this.certsessionId = certsessionId;
	}
	
	/**
	 * @return 数据库用户名
	 */
	public String getDbuser() {
//		return dbuser;
		if (logonAudit == null) {
			return dbuser;
		}
		return logonAudit.getDbuser();
	}
	
	/**
	 * @param dbuser 数据库用户名
	 */
	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}
	
	/**
	 * @return IP地址
	 */
	public String getIpAddress() {
		//return ipAddress;
		if (logonAudit == null) {
			return ipAddress;
		}
		return logonAudit.getIpAddress();
	}
	
	/**
	 * @param ipAddress IP地址
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
		if (logonAudit != null) {
			logonAudit.setIpAddress(ipAddress);
		}
	}
	
	/**
	 * @return 主机地址
	 */
	public String getHost() {
//		return host;
		if (logonAudit == null) {
			return host;
		}
		return logonAudit.getHost();
	}
	
	/**
	 * @param host 主机地址
	 */
	public void setHost(String host) {
		this.host = host;
		if (logonAudit != null) {
			logonAudit.setHost(host);
		}
	}
	
	/**
	 * @return 物理地址
	 */
	public String getMacAddress() {
//		return macAddress;
		if (logonAudit == null) {
			return macAddress;
		}
		return logonAudit.getMacAddress();
	}
	
	/**
	 * @param macAddress 物理地址
	 */
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
		if (logonAudit != null) {
			logonAudit.setMacAddress(macAddress);
		}
	}
	
	/**
	 * @return 操作系统用户
	 */
	public String getOsUser() {
//		return osUser;
		if (logonAudit == null) {
			return osUser;
		}
		return logonAudit.getOsUser();
	}
	
	/**
	 * @param osUser 操作系统用户
	 */
	public void setOsUser(String osUser) {
		this.osUser = osUser;
		if (logonAudit != null) {
			logonAudit.setOsUser(osUser);
		}
	}
	
	/**
	 * @return 应用名称
	 */
	public String getAppname() {
//		return appname;
		if (logonAudit == null) {
			return appname;
		}
		return logonAudit.getAppname();
	}
	
	/**
	 * @param appname 应用名称
	 */
	public void setAppname(String appname) {
		this.appname = appname;
		if (logonAudit != null) {
			logonAudit.setAppname(appname);
		}
	}
	
	/**
	 * @return 终端应用
	 */
	public String getEndApp() {
		return endApp;
	}
	
	/**
	 * @param endApp 终端应用
	 */
	public void setEndApp(String endApp) {
		this.endApp = endApp;
	}
	
	/**
	 * @return 登录时间
	 */
	public Date getLogonTime() {
		if (!logonTimeFlag) {
			logonTimeFlag = true;
			if (logonAudit != null) {
				logonTime = add(logonAudit.getLogonTime());
			}
			
		}
		return logonTime;
	}
	
	/**
	 * @param logonTime 登录时间
	 */
	public void setLogonTime(Date logonTime) {
		this.logonTime = logonTime;
		if (logonAudit != null) {
			logonAudit.setLogonTime(logonTime);
		}
	}
	
	/**
	 * @return 退出时间
	 */
	public Date getLogoffTime() {
		if (!logoffTimeFlag) {
			logoffTimeFlag = true;
			if (logonAudit != null) {
				logoffTime = add(logonAudit.getLogoffTime());
			}
		}
		return logoffTime;
	}
	
	/**
	 * @param logoffTime 退出时间
	 */
	public void setLogoffTime(Date logoffTime) {
		this.logoffTime = logoffTime;
		if (logonAudit != null) {
			logonAudit.setLogoffTime(logoffTime);
		}
	}
	
	/**
	 * @return
	 */
	public String getWhat() {
		return what;
	}
	/**
	 * @param what
	 */
	public void setWhat(String what) {
		this.what = what;
	}
	
	/**
	 * @return 数据库主机名
	 */
	public String getServerhost() {
		return serverhost;
	}
	
	/**
	 * @param serverhost 数据库主机名
	 */
	public void setServerhost(String serverhost) {
		this.serverhost = serverhost;
	}
	
	/**
	 * @return 数据库名
	 */
	public String getDbname() {
//		return dbname;
		if (logonAudit == null) {
			return dbname;
		}
		return logonAudit.getDbname();
	}
	
	/**
	 * @param dbname 数据库名
	 */
	public void setDbname(String dbname) {
		this.dbname = dbname;
		if (logonAudit != null) {
			logonAudit.setDbname(dbname);
		}
	}
	
	/**
	 * @return 数据库实例名
	 */
	public String getInstanceName() {
//		return instanceName;
		if (logonAudit == null) {
			return instanceName;
		}
		return logonAudit.getInstanceName();
	}
	
	/**
	 * @param instanceName 数据库实例名
	 */
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
		if (logonAudit != null) {
			logonAudit.setInstanceName(instanceName);
		}
	}
	
	/**
	 * @return SID
	 */
	public String getSid() {
		return sid;
	}
	
	/**
	 * @param sid SID
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	/**
	 * @return serial
	 */
	public String getSerial() {
		return serial;
	}
	
	/**
	 * @param serial serial
	 */
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	/**
	 * @return audsid
	 */
	public String getAudsid() {
		return audsid;
	}
	
	/**
	 * @param audsid audsid
	 */
	public void setAudsid(String audsid) {
		this.audsid = audsid;
	}
	
	/**
	 * @return 部门ID
	 */
	public String getGroupId() {
		return groupId;
	}
	
	/**
	 * @param groupId 部门ID
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * @return 部门名称
	 */
	public String getGroupName() {
		return groupName;
	}
	
	/**
	 * @param groupName 部门名称
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	/**
	 * @return 区域ID
	 */
	public String getAreaId() {
		return areaId;
	}
	
	/**
	 * @param areaId 区域ID
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	/**
	 * @return 区域名称
	 */
	public String getAreaName() {
		return areaName;
	}
	
	/**
	 * @param areaName 区域名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * sqlid
	 * @return sqlid
	 */
	public String getSqlId() {
		return sqlId;
	}

	/**
	 * sqlid
	 * @param sqlId sqlid
	 */
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
	
	public String getDataSource() {
		return "1";
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
	public String getAssetsetname() {
		return assetsetname;
	}
	public void setAssetsetname(String assetsetname) {
		this.assetsetname = assetsetname;
	}
	public String getAssetsetid() {
		return assetsetid;
	}
	public void setAssetsetid(String assetsetid) {
		this.assetsetid = assetsetid;
	}
	public String getIdcopy() {
		return idcopy;
	}
	public void setIdcopy(String idcopy) {
		this.idcopy = idcopy;
	}

	public LogonAudit getLogonAudit() {
		return logonAudit;
	}

	public void setLogonAudit(LogonAudit logonAudit) {
		this.logonAudit = logonAudit;
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
