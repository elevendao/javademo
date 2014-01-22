package com.edao.codes.datastructure.tree;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author liushuai
 *
 */
public class Main {
	public static void main(String[] args) {
		int count = 10;
		int[] arr = new int[count];
		for (int i=0; i<count; i++) {
			arr[i] = (int) (Math.random() * 1000);
		}
		
		Tree tree = new Tree(arr);
		tree.traverse();
		
//		Hashtable table = new Hashtable();
//		table.put("s", "sss");
//		System.out.println(table.get(null));
		
		HashMap map = new HashMap();
		map.put("ss", "sss");
		map.put(null, "bbbbbbb");
		System.out.println(map.get(null));
	}
}
