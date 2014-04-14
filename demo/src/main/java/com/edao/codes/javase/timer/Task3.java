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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author liushuai
 *
 */
public class Task3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.out.println(System.currentTimeMillis() + " aaa");
			}
			
		};
		
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 15, TimeUnit.SECONDS);
		//service.shutdown();
	}

}
