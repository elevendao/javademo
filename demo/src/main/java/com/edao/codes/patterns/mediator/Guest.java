package com.edao.codes.patterns.mediator;

public class Guest {

	private boolean isSpeaking;
	private Mediator mediator;
	
	public Guest(Mediator mediator) {
		this.mediator = mediator;
	}
	
	public void speak() {
		mediator.guestSpeak();
	}
	
	public void stop() {
		mediator.guestStop();
	}
	
	public boolean isSpeaking() {
		return isSpeaking;
	}
	
	public void setSpeaking(boolean isSpeaking) {
		this.isSpeaking = isSpeaking;
	}
	
}
