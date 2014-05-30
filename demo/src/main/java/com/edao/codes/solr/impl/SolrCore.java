/**
 * 版权所有：美创科技
 * 项目名称:capaa-web-b-2.5.1.0
 * 创建者: liushuai
 * 创建日期: 2013-9-16
 * 文件说明: SolrCore信息
 * 最近修改者：liushuai
 * 最近修改日期：2013-9-16
 */
package com.edao.codes.solr.impl;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

/**
 * @author liushuai
 *
 */
public class SolrCore {
	private String dataSource; // 所属的数据源
	private String coreName;   // core的名称
	private String host;       // 主机地址
	private String port;       // 端口
	private String time;       // 当solr core 是访问审计时，core的时间
	private String unit;       // 时间单位 月，日；月对应的时间格式yyyymm，日对应的时间格式 yyyyMMdd
	private boolean update;    // 是否开启更新
	private HttpSolrServer server;
	
	/**
	 * SolrCore Constructor
	 */
	public SolrCore() {
		super();
	}
	
	/**
	 * SolrCore Constructor
	 * @param dataSource 数据源名称
	 * @param coreName SolrCore名称
	 * @param host 主机
	 * @param port 端口
	 * @param update 是否更新索引
	 */
	public SolrCore(String dataSource, String coreName, String host, String port, boolean update) {
		this.dataSource = dataSource;
		this.coreName = coreName;
		this.host = host;
		this.port = port;
		this.update = update;
		server = new HttpSolrServer("http://" + host + ":" + port + "/solr/" + coreName);
	}
	
	/**
	 * 返回SolrCore可访问的url地址
	 * @return url
	 */
	public String getUrl() {
		return host + ":" + port + "/solr/" + coreName;
	}
	
	/**
	 * 返回SolrServer服务对象
	 * @return HttpSolrServer 
	 */
	public HttpSolrServer getHttpSolrServer() {
		if (server == null) {
			server = new HttpSolrServer("http://" + host + ":" + port + "/solr/" + coreName);
		}
		return server;
	}
	
	/**
	 * 返回数据源名称
	 * @return String
	 */
	public String getDataSource() {
		return dataSource;
	}
	
	/**
	 * 设置数据源名称
	 * @param dataSource 数据源名称
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
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
	
	/**
	 * @return 是否更新索引
	 */
	public boolean getUpdate() {
		return update;
	}
	
	/**
	 * 设置是否更新索引
	 * @param update 是否更新索引
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}

	/**
	 * 返回SolrCore名称
	 * @return SolrCore名称
	 */
	public String getCoreName() {
		return coreName;
	}

	/**
	 * 设置SolrCore名称
	 * @param coreName SolrCore名称
	 */
	public void setCoreName(String coreName) {
		this.coreName = coreName;
	}
	
	@Override
	public String toString() {
		return "coreName = " + this.coreName + ", host = " + this.host
				+ ", this.port = " + port + ", this.update = " + update;
	}

	/**
	 * @return 时间
	 */
	public String getTime() {
		return time;
	}

	/**
	 * 时间
	 * @param time 时间
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return 时间单位
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 设置时间单位
	 * @param unit 时间单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
