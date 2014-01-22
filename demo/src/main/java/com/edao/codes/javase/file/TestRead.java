package com.edao.codes.javase.file;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class TestRead {
	static String inNum;
	public static void main(String[] args) throws IOException{
		File source_file=new File(args[0]);
		RandomAccessFile source = new RandomAccessFile(source_file, "r");
		String block_size=get_block_size(source);
		System.out.println(block_size);
		System.out.println("block_size:"+Str2Num(block_size));
		boolean flag=true;
		while(flag){
			mess();
			if (inNum.matches("\\d+")){
				show_block(get_block(Str2Num(block_size),Integer.parseInt(inNum),source),16);
			}else if(inNum.matches("q")){
				System.out.println("exit!");
				break;
			}
		}
		source.close();
	}
	
	public static void mess() throws IOException{
		System.out.print("Input a block number:");
		inNum=(new BufferedReader(new InputStreamReader(System.in))).readLine();
	}
	
	public static String get_block_size(RandomAccessFile source) throws IOException{
		StringBuilder sb=new StringBuilder();
		source.seek(4);
		int temp;
		for(int i=0;i<4;i++){
			temp=source.read();
			if(temp<16) sb.append("0");
			sb.append(Integer.toHexString(temp)+" ");
		}
		return sb.toString();
	}
	
	public static String[] get_block(long block_size,int k,RandomAccessFile source) throws IOException{
		String[] block=new String[(int) block_size];
		source.seek(block_size*k);
		int temp;
		for(int i=0;i<block_size;i++){
			temp=source.read();
			if (temp<16) block[i]="0"+Integer.toHexString(temp).toUpperCase();
			else block[i]=Integer.toHexString(temp).toUpperCase();
		}
		return block;
	}
	
	public static void show_block(String[] block,int k){
		System.out.println("block number:"+inNum);
		System.out.print("----------------------------------------------------");
		for (int i = 0; i < block.length; i++) {
			if (i % k == 0) 
				System.out.print("\n"+String.format("%1$3s", Integer.toHexString(i).toUpperCase())+":");
			System.out.print(" "+block[i]);
		}
		System.out.println("\n----------------------------------------------------");
	}
	
	public static long Str2Num(String a) {
		String[] b = a.split(" ");
		String c = "";
		for (int i = b.length - 1; i >= 0; i--) {
			c += b[i];
		}
		return Long.parseLong(c, 16);
	}
	
}
