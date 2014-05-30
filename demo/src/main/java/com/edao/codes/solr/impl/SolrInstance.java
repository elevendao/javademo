/**
 * 版权所有：美创科技
 * 项目名称:capaa-web-b-2.5.1.0
 * 创建者: liushuai
 * 创建日期: 2013-9-16
 * 文件说明: solr服务实例，一个solr服务实例对应一个solr web 程序
 * 最近修改者：liushuai
 * 最近修改日期：2013-9-16
 */
package com.edao.codes.solr.impl;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

/**
 * @author liushuai
 *
 */
public class SolrInstance {
	String name;
	String host;
	String port;
	String[] dataSources;
	HttpSolrServer server;

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
	 * 返回solr实例名称
	 * @return solr实例名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置solr实例名称
	 * @param name solr实例名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回实例中的保存的数据源类型
	 * @return 数据源类型
	 */
	public String[] getDataSources() {
		return dataSources;
	}

	/**
	 * 设置实例中的保存的数据源类型
	 * @param dataSources 数据源类型
	 */
	public void setDataSources(String[] dataSources) {
		this.dataSources = dataSources;
	}

	/**
	 * 返回提供搜索服务的SolrServer
	 * @return SolrServer
	 */
	public HttpSolrServer getServer() {
		if (server == null) {
			server = new HttpSolrServer("http://" + host + ":" + port + "/solr/");
		}
		return server;
	}

	/**
	 * 设置提供搜索服务的SolrServer
	 * @param server SolrServer
	 */
	public void setServer(HttpSolrServer server) {
		this.server = server;
	}
}
