package com.edao.codes.codec.md5;


import java.math.BigInteger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MD5CoderTest {
	String inputStr = "hello";
	byte[] inputData = inputStr.getBytes();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@TestGetIntersection
//	public void testEqaul() throws Exception {
//		Assert.assertArrayEquals(MD5Coder.encryptMD5(inputData),MD5Coder.encryptMD5(inputData));
//		
//		BigInteger md5 = new BigInteger(MD5Coder.encryptMD5(inputData));      
//        System.err.println("MD5:\n" + md5.toString(16));
//	}

}
