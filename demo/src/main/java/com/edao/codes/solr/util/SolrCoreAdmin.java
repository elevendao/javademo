/**
 * 版权所有：美创科技
 * 项目名称:capaa-web-b-2.5.1.0
 * 创建者: liushuai
 * 创建日期: 2013-9-16
 * 文件说明: solr core管理类
 * 最近修改者：liushuai
 * 最近修改日期：2013-9-16
 */
package com.edao.codes.solr.util;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.request.CoreAdminRequest.Create;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.common.params.CoreAdminParams.CoreAdminAction;
import org.apache.solr.common.util.NamedList;

import com.edao.codes.solr.impl.SolrCore;
import com.edao.codes.util.DateUtil;

/**
 * @author liushuai
 * 对solr core进行管理以及对solr应用程序信息的获取
 */
public final class SolrCoreAdmin {
	
	private static SolrCoreAdmin INSTANCE;
	
	private SolrCoreAdmin() {}
	
	/**
	 * 返回单例对象
	 * @return SolrCoreAdmin
	 */
	public static SolrCoreAdmin getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SolrCoreAdmin();
		}
		return INSTANCE;
	}
	
	/**
	 * 根据coreName检查server中是否存在SolrCore
	 * @param coreName SolrCore名称
	 * @param server SolrServer
	 * @return 是否存在
	 */
	public boolean existCore(String coreName, SolrServer server) {
		CoreAdminRequest request = new CoreAdminRequest();
		request.setAction(CoreAdminAction.STATUS);
		CoreAdminResponse response;
		
		boolean exist = false;
		try {
			response = request.process(server);
			NamedList coreStatus = response.getCoreStatus();
			Object core = coreStatus.get(coreName);
			return core != null ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exist;
	}
	
	/**
	 * 获取solr应用中当前加载的所有core的名称
	 * @param server 
	 * @return list
	 */
	public List<String> getSolrCores(SolrServer server) {
		List<String> cores = new ArrayList<String>();
		
		CoreAdminRequest request = new CoreAdminRequest();
		request.setAction(CoreAdminAction.STATUS);
		CoreAdminResponse response;
		
		try {
			response = request.process(server);
			NamedList coreStatus = response.getCoreStatus();
			int size = coreStatus.size();
			for (int i=0; i<size; i++) {
				String name = coreStatus.getName(i);
				cores.add(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cores;
	}
		
	/**
	 * 向server中发命令，根据日期生成指定月的core目录
	 * @param date 月
	 * @param server SolrServer
	 */
	public void createSolrCoreDirectory(Date date, SolrServer server){
		HttpSolrServer httpServer = (HttpSolrServer) server;
		HttpClient httpClient = httpServer.getHttpClient();
		HttpGet get = new HttpGet(httpServer.getBaseURL() + "/mchzadmin");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "create"));
		//params.add(new BasicNameValuePair("datasource", "access"));
		params.add(new BasicNameValuePair("time", DateUtil.DF_YYYYMM.format(date)));
		params.add(new BasicNameValuePair("srcDir", "access"));
		
		try {
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
			get.setURI(new URI(get.getURI().toString() + "?" + str));
			httpClient.execute(get);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 向server中发命令，生成指定core目录
	 * @param solrCore solrCore
	 * @param server SolrServer
	 */
	public void createSolrCoreDirectory(SolrCore solrCore, SolrServer server){
		HttpSolrServer httpServer = (HttpSolrServer) server;
		HttpClient httpClient = httpServer.getHttpClient();
		HttpGet get = new HttpGet(httpServer.getBaseURL() + "/mchzadmin");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "create"));
		params.add(new BasicNameValuePair("time", solrCore.getTime()));
		params.add(new BasicNameValuePair("unit", solrCore.getUnit()));
		params.add(new BasicNameValuePair("srcDir", "access"));
		
		try {
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
			get.setURI(new URI(get.getURI().toString() + "?" + str));
			httpClient.execute(get);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 加载指定名称和目录的SolrCore
	 * @param coreName SolrCore名称
	 * @param instanceDir core目录
	 * @param server SolrServer
	 */
	public void load(String coreName, String instanceDir, SolrServer server){
		// 在主机名host，端口为port的solr应用上，
		// 加载core名称为coreName，目录为dir的core
		Create create = new CoreAdminRequest.Create();
        create.setCoreName(coreName);
        create.setInstanceDir(instanceDir + "/");
        create.setSchemaName("schema.xml");
        create.setConfigName("solrconfig.xml");
        create.setDataDir("../../../data/solr/"+coreName);
        
        try {
			create.process(server);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 卸载指定名称的SolrCore，可选择是否保留索引文件
	 * @param coreName SolrCore名称
	 * @param deleteIndex true 保留索引 false 不保留索引
	 * @param server SolrServer
	 */
	public void unload(String coreName, boolean deleteIndex, SolrServer server) {
		// 在主机名host，端口为port的solr应用上，
		// 卸载core名称为coreName，目录为dir的core
		try {
	        CoreAdminRequest.unloadCore(coreName, deleteIndex, server);
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }
	}
	
	/**
	 * 重载指定名称SolrCore
	 * @param coreName SolrCore名称
	 * @param server SolrServer
	 */
	public void reload(String coreName, SolrServer server) {
		// 在主机名host，端口为port的solr应用上，
		// 重新加载core名称为coreName，目录为dir的core
		try {
	        CoreAdminRequest.reloadCore(coreName, server);
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }
	}
	
//	public static void main(String[] args) {
//		SolrCoreAdmin test = SolrCoreAdmin.getInstance();
//		HttpSolrServer server=new HttpSolrServer("http://localhost:8983/solr");
//		JSONObject json = test.getMemoryOccupation(server);
//		System.out.println(json);
//	}
	
	/**
	 * 返回主机内存信息
	 * @param server SolrServer
	 * @return 内存信息
	 */
	public JSONObject getMemoryOccupation(HttpSolrServer server) {
		// 查找在主机名host，端口为port的solr应用的内存占用信息
		// ...
		//HttpSolrServer server=new HttpSolrServer("http://"+host+":"+port+"/solr");
		CoreAdminRequest request = new CoreAdminRequest();
		request.setAction(CoreAdminAction.STATUS);
		CoreAdminResponse response;
		try {
			response = request.process(server);
			NamedList coreStatus = response.getCoreStatus();

			if (coreStatus.size() > 0) {
				String name = coreStatus.getName(0);
				HttpClient client = server.getHttpClient();
				HttpGet method = new HttpGet(server.getBaseURL() + "/" + name
						+ "/admin/system?wt=json");
				HttpResponse resp = client.execute(method);
				HttpEntity entity = resp.getEntity();
				String body = EntityUtils.toString(entity);
				
				JSONObject json = JSONObject.fromObject(body);
				JSONObject systemInfo = json.getJSONObject("system");
//				System.out.println(systemInfo);
				return systemInfo;
			} else {
				HttpClient httpClient = server.getHttpClient();
				HttpGet get = new HttpGet(server.getBaseURL() + "/mchzadmin");

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("action", "system"));
				
				try {
					String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
					get.setURI(new URI(get.getURI().toString() + "?" + str));
					HttpResponse resp = httpClient.execute(get);
					HttpEntity entity = resp.getEntity();
					String body = EntityUtils.toString(entity);
					JSONObject json = JSONObject.fromObject(body);
					JSONObject systemInfo = json.getJSONObject("system");
					return systemInfo;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
