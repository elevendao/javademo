package com.edao.codes.javase.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CheckThread extends Thread {
	
	public void run() {
		while (true) {
			System.out.println("main : "+isProcessExists("QQ1.exe"));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isProcessExists(String name) {
		Process process = null;
		BufferedReader br = null;
		
		try {
			String processName = name;
			//xp,vista,win7
			//String[] command = new String[] {"cmd.exe","/c","wmic process where name='"+processName +"' get processId"};
			String[] command = new String[] {"cmd.exe","/c","tasklist /FI \"IMAGENAME eq "+processName+"\""};
			process = Runtime.getRuntime().exec(command);
			br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			process.getOutputStream().flush();
			process.getOutputStream().close();
			
			String line = null;
			br.readLine();
			br.readLine();
			br.readLine();
			while ((line=br.readLine())!=null) {
//				System.out.println(processName+"===javaMonitor==="+line);
				if (line!=null && !line.equals("")) {
					System.out.println(processName+"===javaMonitor==="+line);
					if (line.trim().startsWith(processName));
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				process.destroy();
				br.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		CheckThread th = new CheckThread();
		th.start();
	}
}
