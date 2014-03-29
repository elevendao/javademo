package com.edao.codes.patterns.mediator;

public class MediatorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Mediator mediator = new Mediator();
		Host host = new Host(mediator);
		Guest guest = new Guest(mediator);
		
		mediator.registHost(host);
		mediator.registGuest(guest);
		
		host.speak();
		guest.speak();
		host.stop();
		guest.speak();
		host.speak();
	}

}
