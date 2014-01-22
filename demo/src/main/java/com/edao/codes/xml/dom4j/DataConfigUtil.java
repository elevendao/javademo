package com.edao.codes.xml.dom4j;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author liushuai
 * 
 */
public class DataConfigUtil {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		DataConfigUtil dom4jParser = new DataConfigUtil();
		String currentdir = System.getProperty("user.dir");
		String properties = "system.properties";
		File propertiesFile = new File(currentdir + File.separator + properties);
		Properties props = getProperties(propertiesFile);
		String url = "";
		String user = "";
		String password = "";
		String driver = "";
		try {
			url = readValue(props, "jdbc.jdbcUrl");
			user = readValue(props, "jdbc.user");
			password = readValue(props, "jdbc.password");
			driver = readValue(props, "jdbc.driverClass");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		DataSource dataSource = new DataSource();
		dataSource.setUrl(url);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setDriver(driver);
		
		File dir = new File(currentdir + File.separator + "multicore");
		List<File> dataConfigs = dom4jParser.getDataConfigFiles(dir);
		for (File dataConfig : dataConfigs) {
			System.out.println("file === " + dataConfig);
			dom4jParser.copyFile(dataConfig);
			dom4jParser.setDataSource(dataConfig, dataSource);
		}
	}
	
	/**
	 * get properties
	 * @param file
	 * @return
	 */
	public static Properties getProperties(File file) {
		Properties props = new Properties();
		
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					file));
			props.load(new FileInputStream(file));
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("system.properties文件不存在！请配置该文件！");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return props;
	}
	
	/**
	 * get value from properties by key
	 * @param props
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String readValue(Properties props, String key) throws Exception {
		String value = props.getProperty(key);
		
		if (value == null || "".equals(value)) {
			throw new Exception(key + "未配置！");
		}
			
		return value;
	}
	
	/**
	 * backup files of data-config.xml
	 * @param srcFile
	 */
	public void copyFile(File srcFile) {
		String name = srcFile.getName();
		File destFile = new File(srcFile.getParentFile(), name + ".bak");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * get all data-config.xml of solr core
	 * @param dir
	 * @return
	 */
	public List<File> getDataConfigFiles(File dir) {
		List<File> list = new ArrayList<File>();
		if (!dir.exists()) {
			return list;
		}
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				File[] dfiles = file.listFiles(new FileFilter() {
					public boolean accept(File file) {
						if (file.isDirectory() && file.getName().equals("conf")) {
							return true;
						}
						return false;
					}
				});
				File confDir = dfiles!=null && dfiles.length > 0 ? dfiles[0] : null;
				if (confDir != null) {
					File dataConfig = new File(confDir.getAbsolutePath() + File.separator + "data-config.xml");
					if (dataConfig.exists()) {
						list.add(dataConfig);
					}
				}
			}
		}
		
		return list;
	}
	
	//update the dataSource tag in data-config.xml
	public void setDataSource(File inputXml, DataSource dataSource) {
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputXml);
			List<?> list = document.selectNodes("//dataSource");
			Iterator<?> iter = list.iterator();
			while (iter.hasNext()) {
				Element element = (Element) iter.next();
				Attribute driverAttr = element.attribute("driver");
				Attribute urlAttr = element.attribute("url");
				Attribute userAttr = element.attribute("user");
				Attribute passwordAttr = element.attribute("password");
				driverAttr.setValue(dataSource.getDriver());
				urlAttr.setValue(dataSource.getUrl());
				userAttr.setValue(dataSource.getUser());
				passwordAttr.setValue(dataSource.getPassword());
			}

			XMLWriter output = new XMLWriter(new FileWriter(inputXml));
			output.write(document);
			output.close();
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

// DataSource bean
class DataSource {
	String driver;
	String url;
	String user;
	String password;
	String batchSize;
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBatchSize() {
		return batchSize;
	}
	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
	}
}
