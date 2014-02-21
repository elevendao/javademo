/**
 * 版权所有：liushuai
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2013-1-28
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2013-1-28
 */
package com.edao.codes.javase.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author liushuai
 *
 */
public class ListFiles {
	
	private File dicPath = null;
	
	public ListFiles(String dir) {
		dicPath = new File(dir);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String dir = "D:\\log";
		ListFiles test = new ListFiles(dir);
		File[] files = test.listWordsFiles();
		for (int i=0; i<files.length; i++) {
			File file = files[i];
			System.out.println(file.getName());
		}
	}
	
	File[] listWordsFiles() {
		return dicPath.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				//return name.startsWith("words") && name.endsWith(".dic");
				return name.endsWith(".log");
			}
			
		});
	}

}
