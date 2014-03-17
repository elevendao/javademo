package com.edao.codes.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;

/**
 * 版权所有：美创科技
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2014-3-7
 * 文件说明: 
 * 最近修改者：liushuai
 * 最近修改日期：2014-3-7
 */

/**
 * @author liushuai
 * 打补丁步骤：
 * 1. 从SVN下载project
 * 2. build compile project
 * 3. svn diff tag1 tag2 使用svn工具将新版本中更改过的文件列表输出到一个文件中
 * 4. 依旧更改文件列表打补丁包
 */
public class PatchTool {
	private File patchRoot; // 补丁包根目录
	private File patchWarRoot;
	private File patchWarLibRoot;
	private File patchWarClassesRoot;
	private File projectRoot; // 工程项目根目录
	private File projectBuildRoot;
	private File projectBuildClassesRoot;
	
	public static void main(String[] args) throws IOException {
		PatchTool tool = new PatchTool();
		File projectRoot = new File("D:\\capaa_workspace\\capaa-web-2.6.0.3-tag");
		File patchRoot = new File("D:\\patch\\2601-2604");
		File patchFile = new File(patchRoot, "patch2601-2603.txt");
		tool.setPatchRoot(patchRoot);
		tool.setProjectRoot(projectRoot);
		tool.patch(patchFile);
	}
	
	public void setPatchRoot(File dir) {
		patchRoot = dir;
		patchWarRoot = new File(patchRoot, "war");
		patchWarLibRoot = new File(patchWarRoot, "lib");
		patchWarClassesRoot = new File(patchWarRoot, "classes");
	}
	
	public void setProjectRoot(File dir) {
		projectRoot = dir;
		projectBuildRoot = new File(projectRoot, "build");
		projectBuildClassesRoot = new File(projectBuildRoot, "classes");
	}
	
	public void patch(File patch) throws IOException {
		FileInputStream fis = new FileInputStream(patch);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = "";
		int skips = 1; // 跳过行数
		while (skips-->0) {
			br.readLine();
		}
		
		while ((line = br.readLine()) != null) {
			File srcFile = new File(projectRoot, line);
			File destFile = new File(patchRoot, line);
			
			if (line.startsWith("src")) {
				if (line.endsWith(".java")) {
					line = line.substring(3, line.lastIndexOf('.'));
					srcFile = new File(projectBuildClassesRoot, line + ".class");
					File parentFile = srcFile.getParentFile();
					String filename = srcFile.getName();
					final String javaName = filename.substring(0, filename.indexOf('.'));
					// 可能在一个类中存在匿名类、内部类
					File[] subFiles = parentFile.listFiles(new FileFilter() {
						
						@Override
						public boolean accept(File file) {
							String name = file.getName();
							if (name.endsWith(".class") && name.startsWith(javaName)) {
								return true;
							}
							return false;
						}
					});
					if (subFiles!=null) {
						for (File f : subFiles) {
							int start = projectBuildRoot.getAbsolutePath().length();
							destFile = new File(patchWarRoot, f.getAbsolutePath().substring(start));
							FileUtils.copyFile(f, destFile);
						}
					}
				} else {
					line = line.substring(3);
					destFile = new File(patchWarClassesRoot, line);
					if (srcFile.exists() && srcFile.isFile()) {
						FileUtils.copyFile(srcFile, destFile);
					}
				}
			} else if (line.startsWith("lib") && line.endsWith(".jar")) {
				if (srcFile.exists()) {
					FileUtils.copyFileToDirectory(srcFile, patchWarLibRoot);
				}
			} else {
				if (srcFile.exists() && srcFile.isFile()) {
					FileUtils.copyFile(srcFile, destFile);
				}
			}
		}
	}
}
