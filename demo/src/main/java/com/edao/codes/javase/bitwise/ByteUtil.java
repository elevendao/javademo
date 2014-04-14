package com.edao.codes.javase.bitwise;

/**
 * byte字节转化辅助类
 * 
 */
public class ByteUtil {

	/**
	 * 转化成字节序列
	 * 
	 * @param l
	 * @param longBytes 需要设置的字节数组
	 * @param index  需要定位的字节数组开始索引
	 * @param length  需要设置的字节数组大小
	 */
	public static void longToBytes(long l, byte[] longBytes, int index,
			int length) {
		for (int i = 0; i < length; i++) {
			longBytes[index + i] = (byte) (l >> 8 * i);
		}
	}

   /**
    * 用00 填充字节空位
    * @param longBytes 需要设置的字节数组
    * @param index 需要定位的字节数组开始索引
    * @param length 需要设置的字节数组大小
    */
	public static void addEmpty(byte[] longBytes, int index, int length) {
		for (int i = 0; i < length; i++) {
			longBytes[index+i] = (byte)0;
		}
	}
	
	/**
	 * 将字节转化为long类型数值
	 * @param longBytes
	 * @param index
	 * @param length
	 * @return
	 */
	public static long bytesToLong(byte[] bytes, int index, int length) {
		long result = 0;
		for (int i = 0; i < length; i++) {
			result = result | ((long) (bytes[index + i] & 0xff) << 8 * i);
		}
		return result;
	}
	
	/**
	 * long转化为字节的大端写法
	 * @param l
	 * @param longBytes
	 * @param index
	 * @param length
	 */
	public static void longToBytesBigEndian(long l, byte[] longBytes, int index,
			int length) {
		for (int i = 0; i < length; i++) {
			longBytes[index + i] = (byte) (l >> 8 * (length - i - 1));
		}
	}
	
	/**
	 * 字节转化为long的大端写法
	 * @param bytes
	 * @param index
	 * @param length
	 * @return
	 */
	public static long bytesToLongBigEndian(byte[] bytes, int index, int length) {
		long result = 0;
		for (int i = 0; i < length; i++) {
			result = result
					| ((long) (bytes[index + i] & 0xff) << 8 * (length - i - 1));
		}
		return result;
	}
}
