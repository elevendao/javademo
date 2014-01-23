package com.edao.codes.codec.jasypt;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncryptUtil {
	private static final Logger LOG = Logger.getLogger(EncryptUtil.class);
	private static final EncryptUtil instance = new EncryptUtil();
	private StandardPBEStringEncryptor encryptor = null;
	
	private EncryptUtil() {
		init();
	}
	
	private void init() {
		encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("capaa");
	}
	
	public static String decrypt(String encrypt) {
		if ((encrypt.startsWith("ENC(") || encrypt.startsWith("enc(")) && encrypt.endsWith(")")) {
			String innerStr = encrypt.substring(4, encrypt.length()-1);
			String decryption = instance.encryptor.decrypt(innerStr);
			return decryption;
		}
		return encrypt;
	}
	
	public static String decrypt(StandardPBEStringEncryptor encryptor, String encrypt) {
		if ((encrypt.startsWith("ENC(") || encrypt.startsWith("enc(")) && encrypt.endsWith(")")) {
			String innerStr = encrypt.substring(4, encrypt.length()-1);
			String decryption = encryptor.decrypt(innerStr);
			return decryption;
		}
		return encrypt;
	}
	
	public static EncryptUtil getInstance() {
		if (instance == null) 
			return new EncryptUtil();
		return instance;
	}
	
	public void setEncryptor(StandardPBEStringEncryptor encryptor) {
		this.encryptor = encryptor;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		Properties prop = new Properties();
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("capaa");
//		String en = encryptor.encrypt("man");
//		System.out.println(en);
		//String enc = "ENC(qEC3U5mQ8X9f5byaiagw4tYCxCvTl033dNweFxVUgHQ\=)";
		String enc = args[0];
		//System.out.println(enc);
//		String decryption = decrypt(encryptor, enc);
		String decryption = decrypt(enc);
		System.out.println(decryption);
	}
}
