package com.edao.codes.patterns;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 版权所有：美创科技
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2013-12-3
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2013-12-3
 */

/**
 * @author liushuai
 *
 */
public class ProtoType implements Cloneable, Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String string;
	private SerializableObject obj;
	
	public Object shallowClone() throws CloneNotSupportedException {
		ProtoType p = (ProtoType) super.clone();
		return p;
	}
	
	public Object deepClone() throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);
		
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}
	
	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public SerializableObject getObj() {
		return obj;
	}

	public void setObj(SerializableObject obj) {
		this.obj = obj;
	}


	/**
	 * @param args
	 * @throws CloneNotSupportedException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
		ProtoType p = new ProtoType();
		p.setString("aaa");
		SerializableObject so = new SerializableObject();
		so.setString("so");
		p.setObj(so);
		
		ProtoType shallowCopy = (ProtoType) p.shallowClone();
		ProtoType deepCopy = (ProtoType) p.deepClone();
		
		System.out.println(p.getString() + ", " + p.getObj() + ", " + p.getObj().getString());
		System.out.println(shallowCopy.getString() + ", " + shallowCopy.getObj() + ", " + shallowCopy.getObj().getString());
		System.out.println(deepCopy.getString() + ", " + deepCopy.getObj() + ", " + deepCopy.getObj().getString());
		System.out.println("=======================================================");
		p.getObj().setString("other");
		System.out.println(p.getString() + ", " + p.getObj() + ", " + p.getObj().getString());
		System.out.println(shallowCopy.getString() + ", " + shallowCopy.getObj() + ", " + shallowCopy.getObj().getString());
		System.out.println(deepCopy.getString() + ", " + deepCopy.getObj() + ", " + deepCopy.getObj().getString());
		System.out.println("=======================================================");
		shallowCopy.getObj().setString("so");
		System.out.println(p.getString() + ", " + p.getObj() + ", " + p.getObj().getString());
		System.out.println(shallowCopy.getString() + ", " + shallowCopy.getObj() + ", " + shallowCopy.getObj().getString());
		System.out.println(deepCopy.getString() + ", " + deepCopy.getObj() + ", " + deepCopy.getObj().getString());
		System.out.println("=======================================================");
		deepCopy.getObj().setString("other");
		System.out.println(p.getString() + ", " + p.getObj() + ", " + p.getObj().getString());
		System.out.println(shallowCopy.getString() + ", " + shallowCopy.getObj() + ", " + shallowCopy.getObj().getString());
		System.out.println(deepCopy.getString() + ", " + deepCopy.getObj() + ", " + deepCopy.getObj().getString());
		
	}


}

class SerializableObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 832797120030864269L;
	
	private String string;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
	
}

