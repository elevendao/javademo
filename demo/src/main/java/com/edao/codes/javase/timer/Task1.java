/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-4-9
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-4-9
 */
package com.edao.codes.javase.timer;

/**
 * @author liushuai
 *
 */
public class Task1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable runnable = new Runnable() {
			final int interval = 1000;
			public void run() {
				while (true) {
					System.out.println("bbb");
					
					try {
						Thread.sleep(interval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
	}

}
