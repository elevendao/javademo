package com.edao.codes.rmi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class RMIRemoteServer {
//	private static final Logger logger = Logger.getLogger(RMIRemoteServer.class);
	private static Logger logger = Logger.getLogger("logAgent");
	
	public RMIRemoteServer() {
		RMIRemoteImplObject conn;
		String serviceName = getServiceName();
		System.out.println(serviceName);
		try {
			conn = new RMIRemoteImplObject();
			// 可以手动启动RMI Registry，也可以在程序中启动�?
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			// 绑定名字服务，地�?��本地计算机名或本机IP，默认端口是1099�?
//			java.rmi.Naming.bind("//localhost:1099/"+serviceName, conn);
			java.rmi.Naming.bind(serviceName, conn);
			// 如果没有异常抛出，则绑定成功�?
			// 如果名字已经被绑定，可以用Naming.rebind()替换掉已绑定的服�?
			logger.info(serviceName);
			logger.info("agent start");
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
            	logger.info("agent stop use close");
            }
        });
	}
	
	public RMIRemoteServer(String threadName) {
		RMIRemoteImplObject conn;
		String serviceName = getServiceName();
		System.out.println(serviceName);
		try {
			conn = new RMIRemoteImplObject(threadName);
			// 可以手动启动RMI Registry，也可以在程序中启动�?
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			// 绑定名字服务，地�?��本地计算机名或本机IP，默认端口是1099�?
//			java.rmi.Naming.bind("//localhost:1099/"+serviceName, conn);
			java.rmi.Naming.bind(serviceName, conn);
			// 如果没有异常抛出，则绑定成功�?
			// 如果名字已经被绑定，可以用Naming.rebind()替换掉已绑定的服�?
			logger.info(serviceName);
			logger.info("agent start");
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
            	logger.info("agent stop use close");
            }
        });
	}
	
	private String getServiceName() {
		String osName = System.getProperty("os.name");
		Process process = null;
		BufferedReader br = null;
		String processId = "";
		String pidStr = java.lang.management.ManagementFactory
				.getRuntimeMXBean().getName().trim();
		processId = pidStr.substring(0, pidStr.indexOf('@'));
		try {
			if (osName != null && !"".equals(osName)) {
				if (osName.equalsIgnoreCase("Windows XP")
						|| osName.equalsIgnoreCase("Windows Vista")
						|| osName.equalsIgnoreCase("Windows 7")
						|| osName.equals("Windows 2003")) {
					String command = "wmic process where processId=" + processId
							+ " get name";
					process = Runtime.getRuntime().exec(command);
					br = new BufferedReader(new InputStreamReader(process
							.getInputStream()));
					process.getOutputStream().flush();
					process.getOutputStream().close();
					String line = br.readLine();

					while ((line = br.readLine()) != null) {
						if (!line.equals("")) {
							return line.trim();
						}
					}
				} else if (osName.equalsIgnoreCase("Linux")) {
					String[] command = new String[] { "/bin/sh", "-c",
							"ps -p " + processId + " | awk '{print $4}'" };
					process = Runtime.getRuntime().exec(command);
					br = new BufferedReader(new InputStreamReader(process
							.getInputStream()));
					process.getOutputStream().flush();
					process.getOutputStream().close();
					String line = br.readLine();
					while ((line = br.readLine()) != null) {
						if (!line.equals(""))
							return line.trim();
					}
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("error msg: " +e.getMessage());
		} finally {
			try {
				process.destroy();
				br.close();
			} catch (Exception e) {
				//e.printStackTrace();
				logger.error("error msg: " + e.getMessage());
			}
		}

		return "";
	}

	public static void main(String[] args) {
		logger.info("agent start");
		new RMIRemoteServer();
		System.out.println("sss");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sss");
	}
}
