/**
 * 版权所有：美创科技
 * 项目名称:b-capaa2.3.0.16.1
 * 创建者: liushuai
 * 创建日期: 2012-10-24
 * 文件说明: search.properties文件映射对象
 * 最近修改者：liushuai
 * 最近修改日期：2012-10-24
 */
package com.edao.codes.solr.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.edao.codes.solr.constant.DataSource;

/**
 * @author liushuai
 *
 */
public class SolrConfig {
	
	// 提取search.properties配置文件的instance中在各个参数
	private Pattern p = Pattern.compile("\\([^\\(\\)]+=[^\\(\\)]+\\)");

	private Map<String, SolrInstance> instanceMap;
	private Map<String, AuditDataSource> dataSourceMap;
	private String cores;
	private String host;
	private String port;
	
	//报表字体
	private String font;
	//月报表发送时间
	private String month;
	//周报表发送时间
	private String week;
	//日报表发送时间
	private String day;
	
	private String socketport;
	
	private String instance1;
	private String instance2;
//	private JdbcDao jdbcDao;
	
	private String fomatChs(String encoding, String str){
		if(encoding!=null&&!encoding.equals("")){
			try {
				str=new String(str.getBytes("ISO-8859-1"), encoding);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	
	/**  
	 * 获取socketport  
	 * @return socketport socketport  
	 */
	public String getSocketport() {
		return socketport;
	}
	
	/**  
	 * 设置socketport  
	 * @param socketport socketport  
	 */
	public void setSocketport(String socketport) {
		this.socketport = socketport;
	}

	/**  
	 * 获取month  
	 * @return month month  
	 */
	public String getMonth() {
		return fomatChs("utf-8", month);
	}
	
	/**  
	 * 设置month  
	 * @param month month  
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**  
	 * 获取week  
	 * @return week week  
	 */
	public String getWeek() {
		return fomatChs("utf-8", week);
	}
	
	/**  
	 * 设置week  
	 * @param week week  
	 */
	public void setWeek(String week) {
		this.week = week;
	}
	
	/**  
	 * 获取day  
	 * @return day day  
	 */
	public String getDay() {
		return fomatChs("utf-8", day);
	}
	
	/**  
	 * 设置day  
	 * @param day day  
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**  
	 * 获取font  
	 * @param encoding 编码（ex：utf-8）
	 * @return font font  
	 */
	public String getFont(String encoding) {
		return fomatChs(encoding, font);
	}
	
	/**  
	 * 设置font  
	 * @param font font  
	 */
	public void setFont(String font) {
		this.font = font;
	}

	/**
	 * @return 审计core
	 */
	@Deprecated
	public String getCores() {
		return cores;
	}
	
	/**
	 * 审计core
	 * @param cores 
	 */
	@Deprecated
	public void setCores(String cores) {
		this.cores = cores;
	}
	
	/**
	 * 返回主机地址
	 * @return host
	 */
	public String getHost() {
		return host;
	}
	
	/**
	 * 设置主机地址
	 * @param host 主机地址
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
	/**
	 * 返回主机端口
	 * @return port
	 */
	public String getPort() {
		return port;
	}
	
	/**
	 * 设置主机端口
	 * @param port 主机端口
	 */
	public void setPort(String port) {
		this.port = port;
	}
	
	private KeepPolicyList getIndexKeepPolicy() {
//		Map map = jdbcDao.queryForMap("select * from mc$keep_policy where record_type='INDEX'");
//		String keepUnit = (String) map.get("KEEP_UNIT");
//		Integer keepTime = ((java.math.BigDecimal) map.get("KEEP_TIME")).intValue();
//		String recordType = (String) map.get("RECORD_TYPE");
		String keepUnit = "MONTH";
		Integer keepTime = 3;
		String recordType = "INDEX";
		KeepPolicyList indexPolicy = new KeepPolicyList();
		indexPolicy.setKeepTime(keepTime);
		indexPolicy.setKeepUnit(keepUnit);
		indexPolicy.setRecordType(recordType);
		
		return indexPolicy;
	}
	
	/**
	 * 初始化solr配置
	 */
	public void init() {
		KeepPolicyList indexPolicy = getIndexKeepPolicy();
		
		load(indexPolicy);
	}
	
	/**
	 * 根据索引保留策略加载solr core
	 * @param indexPolicy 索引保留策略
	 */
	public void load(KeepPolicyList indexPolicy) {
		dataSourceMap = new HashMap<String, AuditDataSource>();
		instanceMap = new HashMap<String, SolrInstance>();
		SolrInstance inst1 = initSoleInstance("instance1", instance1);
		SolrInstance inst2 = initSoleInstance("instance2", instance2);
		instanceMap.put("instance1", inst1);
		instanceMap.put("instance2", inst2);
		
		for (String key : instanceMap.keySet()){
			SolrInstance ins = instanceMap.get(key);
			String[] dataSources = ins.getDataSources();
			
			for (int i=0; i<dataSources.length; i++) {
				String dsName = dataSources[i];
				AuditDataSource ds = new AuditDataSource(dsName, ins);
				ds.setKeepPolicy(indexPolicy);
				dataSourceMap.put(dsName, ds);
			}
		}
		
		for (Map.Entry<String, AuditDataSource> entry : dataSourceMap.entrySet()) {
			AuditDataSource dataSource = entry.getValue();
			dataSource.init();
		}
	}
	
	/**
	 * 根据数据源类型返回审计数据源
	 * @param dataSource 数据源类型
	 * @return 审计数据源
	 */
	public AuditDataSource getAuditDataSource(String dataSource) {
		return dataSourceMap.get(dataSource);
	}
	
	/**
	 * 根据数据源类型返回审计数据源
	 * @param dataSource 数据源类型
	 * @return 审计数据源
	 */
	public AuditDataSource getAuditDataSource(DataSource dataSource) {
		return dataSourceMap.get(dataSource.toString());
	}
	
	/**
	 * 返回所有的数据源名称
	 * @return 所有的数据源名称
	 */
	public String[] getDataSources() {
		Set<String> set = dataSourceMap.keySet();
		String[] dataSources = new String[set.size()];
		set.toArray(dataSources);
		
		return dataSources;
	}
	
	/**
	 * 根据solr实例名称返回solr实例
	 * @param name 实例名称
	 * @return solr实例
	 */
	public SolrInstance getSolrInstance(String name) {
		return instanceMap.get(name);
	}
	
	/*
	 * 解析出instance配置中的各个参数
	 */
	private SolrInstance initSoleInstance(String name, String config) {
		SolrInstance instance = new SolrInstance();
		instance.setName(name);
		Matcher m = p.matcher(config);
		Properties props = new Properties();
		while (m.find()) {
			String kvStr = m.group();
			kvStr = kvStr.substring(1, kvStr.length() - 1);
			int midPos = kvStr.indexOf("=");
			String key  = kvStr.substring(0, midPos);
			String value = kvStr.substring(midPos + 1);
			props.put(key, value);
		}
		String host = props.getProperty("host");
		String port = props.getProperty("port");
		String dataSource = props.getProperty("dataSource");
		String[] dataSources = dataSource.split(",");

		instance.setName(name);
		instance.setHost(host);
		instance.setPort(port);
		instance.setDataSources(dataSources);
		
		return instance;
	}

	/**
	 * @return instance
	 */
	public String getInstance1() {
		return instance1;
	}

	/**
	 * 注入第1个solr instance
	 * @param instance1 instance
	 */
	public void setInstance1(String instance1) {
		this.instance1 = instance1;
	}

	/**
	 * @return instance
	 */
	public String getInstance2() {
		return instance2;
	}

	/**
	 * 注入第2个solr instance
	 * @param instance2 instance
	 */
	public void setInstance2(String instance2) {
		this.instance2 = instance2;
	}

	/**
	 * @return JdbcDao
	 */
//	public JdbcDao getJdbcDao() {
//		return jdbcDao;
//	}

	/**
	 * 注入JdbcDao
	 * @param jdbcDao JdbcDao
	 */
//	public void setJdbcDao(JdbcDao jdbcDao) {
//		this.jdbcDao = jdbcDao;
//	}

	/**
	 * 字体
	 * @return 字体
	 */
	public String getFont() {
		return font;
	}
	
}
