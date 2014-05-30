/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-5-23
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-5-23
 */
package com.edao.codes.solr.sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * @author liushuai
 *
 */
public class FileMap {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		File f = new File("buffer.dat");
		if (!f.exists()){
			f.createNewFile();
		}
		RandomAccessFile raf = new RandomAccessFile(f, "rw");
		FileChannel rafc = raf.getChannel();
		long bufferStart = 0L;
		int bufSize = 32 * 1024 * 1024;
		rafc.map(MapMode.READ_ONLY, bufferStart,
				bufSize);
		byte[] array = new byte[16];
		for (int i=0; i<array.length; i++) {
			array[i] = 1;
		}
		for (int i=0; i<1000000; i++) {
			ByteBuffer src = ByteBuffer.wrap(array);
			rafc.write(src);
		}
	}

}
