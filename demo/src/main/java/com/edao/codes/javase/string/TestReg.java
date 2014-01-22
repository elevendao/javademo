package com.edao.codes.javase.string;

import java.util.regex.Pattern;

/**
 * @author liushuai
 *
 */
public class TestReg {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name = "data-config.xml";
		//boolean b = name.matches("*.xml");
		//System.out.println(b);
		
		boolean b = Pattern.matches("^.+\\.xml$", name);
		System.out.println(b);
	}

}
