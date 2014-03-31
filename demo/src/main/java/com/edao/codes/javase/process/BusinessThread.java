package com.edao.codes.javase.process;

public class BusinessThread extends Thread {
	Service service;
	
	public BusinessThread(Service service) {
		this.service = service;
	}
	
	public void run() {
		while(true) {
			String name = service.getName();
			boolean exists = service.isProcessExist();
			System.out.println("Business :" + name + ",process exists : " + exists);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
