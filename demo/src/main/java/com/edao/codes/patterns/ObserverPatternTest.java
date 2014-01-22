package com.edao.codes.patterns;


import java.util.ArrayList;
import java.util.List;

/**
 * @author liushuai
 *
 */
public class ObserverPatternTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Subject emailSub = new EmailSubject();
		Observer observer1 = new RealObserver1();
		Observer observer2 = new RealObserver2();
		observer1.subscribe(emailSub);
		observer2.subscribe(emailSub);
		emailSub.operate("hello");
		emailSub.operate("world");
	}

}

interface Subject {
	void addObserver(Observer o);
	void removeObserver(Observer o);
	void notifyAllObserver(String msg);
	void operate(String msg);
}

interface Observer {
	void update(String msg);
	void subscribe(Subject subject);
}

abstract class AbstractSubject implements Subject {
	
	private List<Observer> observers;
	
	AbstractSubject() {
		observers = new ArrayList<Observer>();
	}

	public void addObserver(Observer o) {
		this.observers.add(o);
	}

	public void removeObserver(Observer o) {
		this.observers.remove(o);
	}
	
	public List<Observer> getObservers() {
		return observers;
	}

	public abstract void notifyAllObserver(String msg);

	public abstract void operate(String msg);
	
}

class EmailSubject extends AbstractSubject {
	
	public EmailSubject() {
		super();
	}
	
	public void notifyAllObserver(String msg) {
		for (Observer o : getObservers()) {
			o.update(msg);
		}
	}

	@Override
	public void operate(String msg) {
		System.out.println("send email ....");
		notifyAllObserver(msg);
	}
	
}

class RealObserver1 implements Observer {
	String email;
	List<Subject> subjects;
	
	public RealObserver1() {
		email = "observer1@bbb.com";
		subjects = new ArrayList<Subject>();
	}
	
	public void update(String msg) {
		System.out.println("RealObserver1 receive email, email message : " + msg);
	}

	public void subscribe(Subject subject) {
		subject.addObserver(this);
		this.subjects.add(subject);
	}
	
}

class RealObserver2 implements Observer {
	String email;
	List<Subject> subjects;
	
	public RealObserver2() {
		email = "observer2@aaa.com";
		subjects = new ArrayList<Subject>();
	}
	
	public void update(String msg) {
		System.out.println("RealObserver2 receive email, email message : " + msg);
	}

	public void subscribe(Subject subject) {
		subject.addObserver(this);
		this.subjects.add(subject);
	}
	
}
