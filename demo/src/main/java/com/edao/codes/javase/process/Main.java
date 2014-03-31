package com.edao.codes.javase.process;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Service s1 = new Service();
		s1.setName("java-pipe1.exe");
		Service s2 = new Service();
		s2.setName("java-pipe2.exe");
		Service s3 = new Service();
		s3.setName("QQ.exe");
		
		List services = new ArrayList();
		services.add(s1);
		services.add(s2);
		services.add(s3);
		synchronized (services) {
		CheckThread checkThread = new CheckThread();
		checkThread.start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (services){
		for (int i=0; i<services.size(); i++) {
			Service service = (Service) services.get(i);
			BusinessThread thread = new BusinessThread(service);
			thread.start();
		}
		}
	}
}
