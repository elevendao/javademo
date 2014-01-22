package com.edao.codes.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIRemoteImplObject extends UnicastRemoteObject implements RMIRemote {
	private static final long serialVersionUID = 1L;

	private String name;
	
	public RMIRemoteImplObject() throws RemoteException {
		super();
	}
	
	protected RMIRemoteImplObject(String name) throws RemoteException {
		this();
		this.name = name;
	}

	public int connect() throws RemoteException {	

		Thread[] threadlist = findAllThreads();
		System.out.println("*****************thread length::"+threadlist.length);
		int count = 0;
		for (int i=0; i<threadlist.length; i++) {
			Thread th = threadlist[i];
			System.out.println("***********************"+"id::"+th.getId()+"==name::"+th.getName()+"==alive::"+th.isAlive()+"==state::"+th.getState()+"==Priority::"+th.getPriority()+"==daemon::"+th.isDaemon()+",groupname::"+th.getThreadGroup().getName());
			String threadName = th.getName();
			if (threadName != null && threadName.equals(name)) {
				if (th.isAlive()) count++;
			}
		}
		
		if (count > 0) return 1;
		return 0;
	}
	

	public static Thread[] findAllThreads() {
		ThreadGroup group = 
			Thread.currentThread().getThreadGroup();
		ThreadGroup topGroup = group;
		// traverse the ThreadGroup tree to the top
		while ( group != null ) {
			topGroup = group;
			group = group.getParent();
		}
		// Create a destination array that is about
		// twice as big as needed to be very confident
		// that none are clipped.
		int estimatedSize = topGroup.activeCount() * 2;
		Thread[] slackList = new Thread[estimatedSize];
		// Load the thread references into the oversized
		// array. The actual number of threads loaded 
		// is returned.
		int actualSize = topGroup.enumerate(slackList);
		// copy into a list that is the exact size
		Thread[] list = new Thread[actualSize];
		System.arraycopy(slackList, 0, list, 0, actualSize);
		return list;
	}
}
