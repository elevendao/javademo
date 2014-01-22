package com.edao.codes.codec.md5;


import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Coder {
	 public static final String KEY_MD5 = "MD5";
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		String inputStr = "�򵥼���";      
//		System.err.println("ԭ��:\n" + inputStr);      
//	     
//        byte[] inputData = inputStr.getBytes();      
//        byte[] code = MD5Coder.encryptMD5(inputData);
//        BigInteger md5 = new BigInteger(MD5Coder.encryptMD5(inputData));  
//        System.out.println(md5);
		StringBuffer valueHex = new StringBuffer();
		byte re = -128;
		int tmp = re & 0xff;
		valueHex.append(tmp);
        System.out.println(valueHex.toString());
	}

	public static byte[] encryptMD5(byte[] data) throws Exception {         
        
	    MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);         
	    md5.update(data);         
	        
	    return md5.digest();         
	        
	}  
}
