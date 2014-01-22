package com.edao.codes.javase.collection;
import java.util.TreeSet;

public class TreeSetTest {
	public static void main(String[] args) throws InterruptedException {
//		TreeSet<Integer> ts = new TreeSet<Integer>();
//		ts.add(102);
//		ts.add(20);
//		ts.add(209);
//		ts.add(209);
//		ts.add(0);
//		ts.add(293081);
//		ts.add(278392);
//		ts.add(12890376);
//		ts.add(12890376);
//		System.out.println(ts);
		long readStartTime1 = System.currentTimeMillis();
		long readEndTime2 = System.currentTimeMillis();
		long se = 0;
		while (true) {
			readEndTime2 = System.currentTimeMillis();
			se = readEndTime2 - readStartTime1;
//			System.out.println(se);
//			Thread.sleep(3000);
			if (se >= 3000) {
				 System.out.println(readStartTime1+"  "+readEndTime2);
				System.out.println(se + " is need wait...");
				readStartTime1 = readEndTime2;
			}
		}
	}
}
