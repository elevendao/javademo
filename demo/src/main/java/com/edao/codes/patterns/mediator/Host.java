package com.edao.codes.patterns.mediator;

public class Host {

	private boolean isSpeaking;
	private Mediator mediator;
	
	public Host(Mediator mediator) {
		this.mediator = mediator;
	}
	
	public void speak() {
		mediator.hostSpeak();
	}
	
	public void stop() {
		mediator.hostStop();
	}
	
	public boolean isSpeaking() {
		return isSpeaking;
	}
	
	public void setSpeaking(boolean isSpeaking) {
		this.isSpeaking = isSpeaking;
	}
	
}
