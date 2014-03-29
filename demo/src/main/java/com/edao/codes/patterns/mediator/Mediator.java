/**
 * 版权所有：liushuai
 * 项目名称:demo
 * 创建者: liushuai
 * 创建日期: 2014-3-26
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2014-3-26
 */
package com.edao.codes.patterns.mediator;

/**
 * 中介者是处于众多对象中，并恰当的处理众多对象之间关系的角色
 * 在解耦参与者之间的联系的同时，中介者自身也不免任务过重，因为
 * 几乎所有的业务逻辑都转交到中介者身上。这也是中介者模式的不足之处
 * 
 * @author liushuai
 *
 */
public class Mediator {
	
	Host host;
	Guest guest;
	
	void registHost(Host host) {
		this.host = host;
	}
	
	void registGuest(Guest guest) {
		this.guest = guest;
	}

	public void hostSpeak() {
		if (guest.isSpeaking()) {
			guest.stop();
		}
			
		System.out.println("host is speaking ...");
		host.setSpeaking(true);
	}

	public void guestSpeak() {
		if (host.isSpeaking()) {
			System.out.println("host speaking now, can not be interrupted ...");
		} else {
			System.out.println("guest is speaking ...");
			guest.setSpeaking(true);
		}
	}
	
	public void guestStop() {
		System.out.println("guest stop speaking now...");
		guest.setSpeaking(false);
	}
	
	public void hostStop() {
		System.out.println("host stop speaking now...");
		host.setSpeaking(false);
	}
}
