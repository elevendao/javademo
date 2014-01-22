package com.edao.codes.javase.bat;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;


public class Test {
	
	private static final String URL = getUrl1();
	
	private static String getUrl1() {
		return "hello";
	}
	
	public Test() {
	}

	/**
	 * @param args
	 * @throws Exception 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		Test test = new Test();
//		System.out.println(Test.URL);
//		String osName = System.getProperty("os.name");
//		System.out.println(osName);
//		for (int i=0; i<1000; i++) {
//			
//		}
//		throw new Error("hello");
		isExists("java-pipe1.exe");
	}
	public static String getPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();
        try {
            return name.substring(0, name.indexOf('@'));
        } catch (Exception e) {
            return "0";
        }

    }
	
	public static boolean isExists(String processName) throws Exception {
		Process process = null;
		BufferedReader br = null;
//		String[] command = new String[] {"cmd.exe","/c","tasklist /FI \"IMAGENAME eq "+processName+"\""};
		String[] command = new String[] {"cmd.exe","/c","wmic process where name='"+processName.trim()+"' get processId"};
		process = Runtime.getRuntime().exec(command);
		br = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		process.getOutputStream().flush();
		process.getOutputStream().close();
		String line = null;
		int ss = process.waitFor();
		
		while ((line=br.readLine())!=null) {
			if (line!=null && !"ProcessId".equals(line.trim()) && !"".equals(line.trim())) {
				System.out.println(line.trim());
			}
		}
//		
		return false;
	}
}
