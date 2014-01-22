package com.edao.codes.codec.md5;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author Administrator
 *
 */
public class MD5Test {
	public static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7',
		'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	public void test() throws NoSuchAlgorithmException {
		String str = "hzmcAdmin";
		
		MessageDigest digest =  MessageDigest.getInstance("MD5");
		
		byte[] bs = digest.digest(str.getBytes());
//		for (int i=0; i<bs.length; i++) {
//			System.out.println("1:" + bs[i]);
//			System.out.println("2:" + Integer.toBinaryString(bs[i]));
//			System.out.println("3:" + hexChar[(bs[i] & 0xf0) >>> 4]);
//			System.out.println("4:" + hexChar[(bs[i] & 0x0f)]);
//		}
		
		System.out.println(toHexString(bs));
	}
	
	public String toHexString(byte[] b) {
		StringBuffer buffer = new StringBuffer(b.length * 2);
		
		for (int i=0; i<b.length; i++) {
			buffer.append(hexChar[(b[i] & 0xf0) >>> 4]);
			buffer.append(hexChar[b[i] & 0x0f]);
		}
		
		return buffer.toString();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		new MD5Test().test();
	}
}
