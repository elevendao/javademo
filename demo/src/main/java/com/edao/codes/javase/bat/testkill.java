package com.edao.codes.javase.bat;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class testkill {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		killProcess(null);
	}
	
	
	public static void killProcess(String name) {
//		String processName = service.getProcessName();
//		int osType = service.getOsType();
		String processName = "java-pipe1.exe";
		int osType = 1;
		Process process = null;
		BufferedReader br = null;
		
		try {
			if (osType <= 3 && osType > 0) {
				//xp,vista,win7
				String[] command= {"cmd.exe", "/c", "wmic process where name='"+processName+"' call terminate"};
				process = Runtime.getRuntime().exec(command);
			}else if (osType == 4) {
				//linux
			}
			br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			process.getOutputStream().flush();
			process.getOutputStream().close();
			
			String line=null;
			while ((line=br.readLine())!=null) {
				//System.out.println(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
//			log.error("isProcessExists():" + e.getMessage());
		} finally {
			try {
				process.destroy();
				br.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
