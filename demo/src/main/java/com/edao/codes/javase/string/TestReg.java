package com.edao.codes.javase.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liushuai
 *
 */
public class TestReg {

	public boolean checkSuffix(String name, String suffix) {
		Pattern p = Pattern.compile("^.+\\."+suffix+"$");
		Matcher match = p.matcher(name);
		return match.find();
	}

}
