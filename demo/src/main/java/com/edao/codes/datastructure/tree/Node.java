package com.edao.codes.datastructure.tree;

/**
 * @author liushuai
 *
 */
public class Node {
	
	private int priority;
	private int value;
	private Node leftChild;
	private Node rightChild;
	private Node parent;
	
	public Node() {
		
	}
	
	public Node(int value) {
		this.value = value;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void addChild(Node child) {
		if (child.getValue() < this.value) {
			if (this.leftChild != null) {
				this.leftChild.addChild(child);
			} else {
				this.leftChild = child;
				child.parent = this;
			}
		} else {
			if (this.rightChild != null) {
				this.rightChild.addChild(child);
			} else {
				this.rightChild = child;
				child.parent = this;
			}
		}
	}
	
	public Node getLeftChild() {
		return leftChild;
	}
	
	public Node getRightChild() {
		return rightChild;
	}
	
	public Node getParent() {
		return this.parent;
	}

}
