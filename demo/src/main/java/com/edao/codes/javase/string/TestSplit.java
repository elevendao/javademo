package com.edao.codes.javase.string;

public class TestSplit {
	public static void main(String[] args) {
		String str = "select  from ";
		String[] token = str.split(" ");
		System.out.println(token.length);
	}
}
