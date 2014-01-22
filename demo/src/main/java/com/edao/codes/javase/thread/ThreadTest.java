package com.edao.codes.javase.thread;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liushuai
 *
 */
public class ThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ShareSource source = new Source2();
		IncrementThread iThread1 = new IncrementThread("IncrementThread - 1", source);
		IncrementThread iThread2 = new IncrementThread("IncrementThread - 2", source);
		
		iThread1.start();
		iThread2.start();

		List<GetThread> list = new ArrayList<GetThread>(20);
		for (int i=0; i<1000; i++) {
			GetThread gThread = new GetThread("GetThread - " + i, source);
			list.add(gThread);
		}		
		for (GetThread gThread : list) {
			gThread.start();
		}
	}

}

interface ShareSource {
	int get();
	void increment();
}

class Source1 implements ShareSource {
	private Object lock = new Object();  // the sync object
	
	private int i = 0;
	
	public int get() {
		synchronized (lock) {
			return i;	
		}
	}
	
	public void increment() {
		//synchronized (lock) {
			i++;
		//}
	}
}

class Source2 implements ShareSource {
	private Lock lock = new ReentrantLock();
	
	private int i = 0;
	
	public int get() {
		lock.lock();
		try {
			return i;	
		} finally {
			lock.unlock();
		}
	}
	
	public void increment() {
		//synchronized (lock) {
			i++;
		//}
	}
}

class IncrementThread extends Thread {
	private ShareSource source;
	private String name;
	public IncrementThread(String name, ShareSource source) {
		this.source = source;
		this.name = name;
	}

	@Override
	public void run() {
		super.run();
		while (true) {
			int i = source.get();
			System.out.println(name + "--i--" + i);
			source.increment();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class GetThread extends Thread {
	private ShareSource source;
	private String name;
	public GetThread(String name, ShareSource source) {
		this.source = source;
		this.name = name;
	}

	@Override
	public void run() {
		super.run();
		while (true) {
			int i = source.get();
			System.out.println(name + "--i--" + i);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
