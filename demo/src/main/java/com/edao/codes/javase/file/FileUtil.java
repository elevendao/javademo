/**
 * 版权所有：美创科技
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2013-7-9
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2013-7-9
 */
package com.edao.codes.javase.file;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author liushuai
 *
 */
public class FileUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File multicore = new File("G:/solr-release/b-solr-engine2.7.0.0-l/capaa-search4.0.2/multicore");
		String fileName = "data-config.xml";
		FileUtil util = new FileUtil();
		
		List<File> list = new ArrayList<File>();
		File[] dirs = util.listDirectory(multicore);
		for (File dir : dirs) {
			File conf = util.findDir(dir, "conf");
			File file = util.findFile(conf, fileName);
			if (file != null) {
				list.add(file);
			}
		}
		
		for (File file : list) {
			System.out.println(file);
		}
	}
	
	/**
	 * find file in a specified directory . 
	 * if exists, return the file ;
	 *  if doesn't exist, return null .
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public File findFile(File dir, final String fileName) {
		if (dir == null || !dir.exists() || !dir.isDirectory()
				|| fileName == null || "".equals(fileName)) {
			return null;
		}
		
		File[] files = dir.listFiles(new FileFilter() {
			
			public boolean accept(File file) {
				return file.isFile() && Pattern.matches(fileName, file.getName());
			}
		});
		
		return files != null && files.length > 0 ? files[0] : null;
	}
	
	/**
	 * find directory in a specified directory .
	 * if exists, return the directory ;
	 * if doesn't exist, return null .
	 * @param dir
	 * @param dirName
	 * @return
	 */
	public File findDir(File dir, final String dirName) {
		if (dir == null || !dir.exists() || !dir.isDirectory()
				|| dirName == null || "".equals(dirName)) {
			return null;
		}
		
		File[] dirs = dir.listFiles(new FileFilter() {
			
			public boolean accept(File file) {
				return file.isDirectory() && file.getName().equals(dirName);
			}
		});
		
		return dirs != null && dirs.length > 0 ? dirs[0] : null;
	}
	
	/**
	 * list sub directories of the specified directory
	 * @param dir
	 * @return
	 */
	public File[] listDirectory(File dir) {
		if (dir == null || !dir.exists() || !dir.isDirectory()) {
			return null;
		}
		
		File[] dirs = dir.listFiles(new FileFilter() {
			
			public boolean accept(File file) {
				return file.isDirectory();
			}
		});
		
		return dirs;
	}

}
