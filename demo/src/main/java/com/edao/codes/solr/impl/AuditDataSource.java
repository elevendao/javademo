/**
 * 版权所有：美创科技
 * 项目名称:capaa-web-b-2.5.1.0
 * 创建者: liushuai
 * 创建日期: 2013-9-16
 * 文件说明: 审计数据源对象
 * 最近修改者：liushuai
 * 最近修改日期：2013-9-16
 */
package com.edao.codes.solr.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

import com.edao.codes.solr.constant.DataSource;
import com.edao.codes.solr.util.SolrCoreAdmin;
import com.edao.codes.util.DateUtil;

/**
 * @author liushuai
 *
 */
public class AuditDataSource {
	private Map<String, SolrCore> coreMap; // 保存coreName与solrCore的映射
	private String dsName; // 数据源名称
	int initialCapacity = 1;
	private SolrInstance solrInstance;
	SolrCore solrCore;
	KeepPolicyList keepPolicy;
	
	/**
	 * AuditDataSource Constructor
	 * @param dsName 数据源名称
	 * @param solrInstance solr web 实例
	 */
	public AuditDataSource(String dsName, SolrInstance solrInstance) {
		this.dsName = dsName;
		this.solrInstance = solrInstance;
		coreMap = new HashMap<String, SolrCore>(initialCapacity);
	}
	
	/**
	 * 初始化， 加载core
	 */
	public void init() {
		if (DataSource.ACCESS.toString().equals(dsName)) {
			Date date = new Date();
			DateFormat format = DateUtil.DF_YYYYMM;
			int field = Calendar.MONTH;
			if ("DAY".equals(keepPolicy.getKeepUnit())) {
				format = DateUtil.DF_YYYYMMDD;
				field = Calendar.DAY_OF_MONTH;
			}
			for (int j = 0; j < keepPolicy.getKeepTime() + 1; j++) {
				String time = DateUtil.date2String(date, format, field, -j);
				String coreName = "access_" + time;
				SolrCore solrCore = new SolrCore(dsName, coreName,
						solrInstance.getHost(), solrInstance.getPort(), true);
				solrCore.setTime(time);
				solrCore.setUnit(keepPolicy.getKeepUnit());
				this.put(coreName, solrCore);
			}
			Set<String> coreNameSet = coreSet();
			SolrCoreAdmin coreAdmin = SolrCoreAdmin.getInstance();
			List<String> coreNameList = coreAdmin.getSolrCores(solrInstance
					.getServer());
			for (String coreName : coreNameList) {
				if (!coreNameSet.contains(coreName)
						&& coreName.startsWith("access_")) {
					coreAdmin.unload(coreName, false, solrInstance.getServer());
				}
			}
		} else {
			SolrCore solrCore = new SolrCore(dsName, dsName,
					solrInstance.getHost(), solrInstance.getPort(), true);
			this.put(dsName, solrCore);
		}
	}
	
	/**
	 * 添加SolrCore
	 * @param coreName SolrCore名称
	 * @param core SolrCore
	 */
	public void put(String coreName, SolrCore core) {
		coreMap.put(coreName, core);
		
		SolrCoreAdmin coreAdmin = SolrCoreAdmin.getInstance();
		boolean existCore = coreAdmin.existCore(coreName, solrInstance.getServer());
		if (!existCore) {
			String instanceDir = coreName;
			if (coreName.startsWith("access_")) {
				coreAdmin.createSolrCoreDirectory(core, solrInstance.getServer());
			} else {
				if ("0".equals(coreName)) {
					instanceDir = "logon";
				} else if ("2".equals(coreName)) {
					instanceDir = "certa";
				} else if ("3".equals(coreName)) {
					instanceDir = "attack";
				} else if ("4".equals(coreName)) {
					instanceDir = "certh";
				} else if ("5".equals(coreName)) {
					instanceDir = "compliance";
				} else if ("1000".equals(coreName)) {
					instanceDir = "homepage";
				}
			}
			coreAdmin.load(coreName, instanceDir, solrInstance.getServer());
		}
	}
	
	/**
	 * 删除SolrCore
	 * @param coreName core名称
	 * @param deleteIndex 是否删除索引
	 */
	public void remove(String coreName, boolean deleteIndex) {
		// 将core从coreMap中移除，同时把solr中对应的core卸载
		SolrCore solrCore = coreMap.remove(coreName);
		
		SolrCoreAdmin coreAdmin = SolrCoreAdmin.getInstance();
		coreAdmin.unload(solrCore.getCoreName(), deleteIndex, solrInstance.getServer());
	}
	
	/**
	 * 该数据源是否只有一个SolrCore
	 * @return 是否只有一个SolrCore
	 */
	public boolean isSingleCore() {
		return coreMap.size() == 1;
	}
	
	/**
	 * 返回数据源名称
	 * @return 数据源名称
	 */
	public String getDataSourceName() {
		return this.dsName;
	}

	/**
	 * 返回该数据源所在的solr实例
	 * @return SolrInstance
	 */
	public SolrInstance getSolrInstance() {
		return solrInstance;
	}
	
	/**
	 * 根据core名称返回SolrCore
	 * @param name core名称
	 * @return SolrCore
	 */
	public SolrCore getSolrCore(String name) {
		return coreMap.get(name);
	}
	
	/**
	 * 是否存在指定名称的SolrCore
	 * @param name core名称
	 * @return SolrCore
	 */
	public boolean containsSolrCore(String name) {
		return coreMap.containsKey(name);
	}
	
	private int random(int range) {
		return (int) (Math.random() * range);
	}
	
	private void serviceCore() {
		if (coreMap.size() == initialCapacity) {
			solrCore = (SolrCore) (coreMap.get(coreMap.keySet().toArray()[0]));
		} else if (coreMap.size() > initialCapacity) {
			Set<String> coreNames = coreMap.keySet();
			List<String> list = new ArrayList<String>(coreNames);
			int index = random(coreMap.size());
			solrCore = (SolrCore) coreMap.get(list.get(index));
		}
	}
	
	/**
	 * 返回数据源对应的url
	 * @return url
	 */
	public String getUrl() {
		serviceCore();
		return solrCore.getUrl();
	}
	
	/**
	 * 返回提供搜索服务的SolrServer
	 * @return SolrServer
	 */
	public HttpSolrServer getHttpSolrServer() {
		serviceCore();
		return solrCore.getHttpSolrServer();
	}
	
	/**
	 * 若由多个core提供服务，返回shards参数
	 * @return 分片参数
	 */
	public String getShards() {
		String str = "";
		for (String key : coreMap.keySet()) {
			str += coreMap.get(key).getUrl() + ",";
		}
		
		return coreMap.size() > 1 ? str : "";
	}
	
	/**
	 * 返回该数据源所拥有的所有SolrCore
	 * @return SolrCore集合
	 */
	public List<SolrCore> getCores() {
		return new ArrayList<SolrCore>(coreMap.values());
	}
	
	/**
	 * @return core name set
	 */
	public Set<String> coreSet() {
		return coreMap.keySet();
	}
	
	@Override
	public String toString() {
		String str="";
		
		Set<String> keySet = coreMap.keySet();
		for (String key : keySet) {
			str += "\tcore " + key + " : \n";
			str += "\t\t" + coreMap.get(key) + "\n";
		}
		
		return str;
	}

	public KeepPolicyList getKeepPolicy() {
		return keepPolicy;
	}

	public void setKeepPolicy(KeepPolicyList keepPolicy) {
		this.keepPolicy = keepPolicy;
	}
}
