package com.edao.codes.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class JavaMonitor extends Thread {
	String name;
	String str;
	public JavaMonitor(String str, String name) {
		this.name = name;
		this.str = str;
	}
	
	public void run() {
		try {
			//String startPath = service.getStartPath();
			while (true) {
//				boolean processExists = isProcessExists(name);
				isProcessExists("ss"+1+"",name+"QQ.exe");
				System.out.println("===============================");
				isProcessExists("ss"+2+"",name+"eclipse.exe");
				System.out.println("===============================");
				isProcessExists("ss"+3+"",name+"firefox.exe");
				System.out.println("===============================");
				isProcessExists("ss"+4+"",name+"java.exe");
				System.out.println("===============================");
				isProcessExists("ss"+5+"",name+"YoudaoWSH.exe");
				System.out.println("===============================");
				isProcessExists("ss"+6+"",name+"conime.exe");
				System.out.println("===============================");
				isProcessExists("ss"+7+"",name+"java-pipe1.exe");
				System.out.println("===============================");
				isProcessExists("ss"+8+"",name+"java-pipe2.exe");
				System.out.println("===============================");
				isProcessExists("ss"+9+"",name+"java-pipe3.exe");
				System.out.println("===============================");
				isProcessExists("ss"+10+"",name+"java-pipe4.exe");
				System.out.println("===============================");
//				for (int i=0; i<15; i++) {
//					isProcessExists("ss"+i+"",name);
//					System.out.println("===============================");
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean isProcessExists(String st,String name) {
		Process process = null;
		BufferedReader br = null;
		
		try {
			String processName = name;
			//xp,vista,win7
			String[] command = new String[] {"cmd.exe","/c","wmic process where name='"+processName +"' get processId"};
			process = Runtime.getRuntime().exec(command);
			br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			process.getOutputStream().flush();
			process.getOutputStream().close();
			
			process.waitFor();
			String line = br.readLine();
			System.out.println(processName+"===javaMonitor==="+st+"===="+line);
			while ((line=br.readLine())!=null) {
				System.out.println(processName+"===javaMonitor==="+line);
//				if (line!=null && !line.equals("")) {
//					System.out.println(processName+"===javaMonitor==="+line);
//					return true;
//				}
			}
			//Thread.sleep(10);
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
		JavaMonitor[] monitors = new JavaMonitor[1];
		for (int i=0; i<monitors.length; i++) {
			monitors[i] = new JavaMonitor(i+"","");
			monitors[i].start();
		}
	}
}
