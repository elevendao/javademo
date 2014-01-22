package com.edao.codes.util;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;


/**
 * @author Administrator
 *
 */
public class ClassPathScanHandler {
	String packageName = "test.annotation";
	
	public void scan() {
		String package2Path = packageName.replace(".", "/");
		System.out.println(package2Path);
		try {
			Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(package2Path);
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					System.out.println(filePath);
					
					File dir = new File(filePath);
					if (!dir.exists() || !dir.isDirectory()) {
						return;
					}
					
					File[] files = dir.listFiles();
					for (File file : files) {
						String fileName = file.getName();
						if (fileName.indexOf("$") != -1) {
							continue;
						}
						System.out.println(fileName);
						String clazzName = packageName + "." + fileName.substring(0, fileName.lastIndexOf("."));
						try {
							Class clazz = Thread.currentThread().getContextClassLoader().loadClass(clazzName);
							Annotation[] annotations = clazz.getAnnotations();
							for (Annotation annotation : annotations) {
								System.out.println(annotation.annotationType());
							}
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				} else if ("jar".equals(protocol)) {
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ClassPathScanHandler scanner = new ClassPathScanHandler();
		scanner.scan();
	}
}
