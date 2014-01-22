package com.edao.codes.javase.thread;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BarrierTest {
	List<Integer> list;
	private CyclicBarrier barrier;
	private int threadCount = 0;
	private long sum;
	
	public BarrierTest(List<Integer> list, int threadCount) {
		this.list = list;
		this.threadCount = threadCount;
	}
	
	public long getSum() {
		barrier = new CyclicBarrier(threadCount + 1);
		List threadList = new ArrayList();
		int len = list.size()/threadCount;
		if (len == 0) {
			threadCount=list.size(); 
            len=list.size()/threadCount;
		}
		long d1 = System.currentTimeMillis();
		for (int i=0; i<threadCount; i++) {
			List subList;
			if (i == threadCount-1) {
				subList = list.subList(i*len, list.size());
			} else {
				subList = list.subList(i*len, len*(i+1)>list.size()?list.size():len*(i+1));
			}
			SubIntegerSumTask subTask = new SubIntegerSumTask(subList, barrier);
			threadList.add(subTask);
			new Thread(subTask).start();
		}
		
		try {
			barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long d2 = System.currentTimeMillis();
		System.out.println(">>>"+(d2-d1));
		for (int i=0; i<threadList.size(); i++) {
			SubIntegerSumTask subTask = (SubIntegerSumTask)threadList.get(i);
			sum += subTask.getSubSum();
		}
		return sum;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		int threadCount = 10;
		for (int i = 1; i <= 1000000; i++) {  
            list.add(i);  
        }
		long d1 = System.currentTimeMillis();
		BarrierTest test = new BarrierTest(list, threadCount);
		long sum = test.getSum();
		long d2 = System.currentTimeMillis();
		System.out.println("sum: "+sum+", time:"+(d2-d1));
	}

}


class SubIntegerSumTask implements Runnable {
	private List<Integer> list;
	private long subSum=0L;
	private CyclicBarrier barrier;
	
	public SubIntegerSumTask(List<Integer> list, CyclicBarrier barrier) {
		this.list = list;
		this.barrier = barrier;
	}
	
	public long getSubSum() {
		return subSum;
	}
	
	private void doWork() {
		long d1 = System.currentTimeMillis();
		if (list != null) {
			for (int i : list) {
				subSum += i;
			}
		}
		long d2 = System.currentTimeMillis();
		System.out.println(d2-d1);
	}
	
	public void run() {
		doWork();
		try {
			barrier.await();//�ؼ�ʹ���߳�����դ���ȴ�ֱ�����е��̶߳�������դ��
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}