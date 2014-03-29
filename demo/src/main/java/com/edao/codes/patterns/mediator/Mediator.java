package com.edao.codes.patterns.mediator;

public class Mediator {

	private Host host;
	private Guest guest;
	
	public void registHost(Host host) {
		this.host = host;
	}
	
	public void registGuest(Guest guest) {
		this.guest = guest;
	}
	
	public void hostSpeak() {
		if (guest.isSpeaking()) {
			guest.stop();
		}
		System.out.println("host speaking ...");
		host.setSpeaking(true);
	}
	
	public void hostStop() {
		System.out.println("host stop speaking ...");
		host.setSpeaking(false);
	}
	
	public void guestSpeak() {
		if (host.isSpeaking()) {
			System.out.println("host is speaking, can not be interrupted...");
		} else {
			System.out.println("guest speaking");
			guest.setSpeaking(true);
		}
	}
	
	public void guestStop() {
		System.out.println("guest stop speaking ...");
		guest.setSpeaking(false);
	}
	
}
