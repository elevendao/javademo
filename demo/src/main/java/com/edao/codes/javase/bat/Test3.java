package com.edao.codes.javase.bat;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;


public class Test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test3 test = new Test3();
		test.run();
	}

	public void run() {
		String fileDir = "D:\\workbench\\example";
		String startPath = "run.bat";
		Process p;
		try {
			p = Runtime.getRuntime().exec(
					"cmd.exe /c " + "\"" + startPath + "\"", null,
					new File(fileDir));
			BufferedReader br = new BufferedReader(new InputStreamReader(p
					.getInputStream()));
			p.getOutputStream().flush();
			p.getOutputStream().close();
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
