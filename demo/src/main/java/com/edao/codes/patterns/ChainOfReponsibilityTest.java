package com.edao.codes.patterns;


/**
 * @author liushuai
 *
 */
public class ChainOfReponsibilityTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbStractHandler handler1 = new MyHandler();
		AbStractHandler handler2 = new MyHandler();
		
		handler1.setHander(handler2);
		
		handler1.handleRequest();
	}

}

abstract class AbStractHandler {
	private AbStractHandler handler;
	
	public AbStractHandler getHandler() {
		return this.handler;
	}
	
	public void setHander(AbStractHandler handler) {
		this.handler = handler;
	}
	
	abstract void handleRequest();
}

class MyHandler extends AbStractHandler {

	/* (non-Javadoc)
	 * @see patterns.AbStractHandler#handleRequest()
	 */
	@Override
	void handleRequest() {
		if (getHandler() != null) {
			System.out.println("pass request ...");
			getHandler().handleRequest();
		} else {
			System.out.println("hanle request ...");
		}
	}
	
}