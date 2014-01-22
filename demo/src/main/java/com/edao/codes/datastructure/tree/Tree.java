package com.edao.codes.datastructure.tree;

/**
 * @author liushuai
 *
 */
public class Tree {
	
	private Node root;
	
	public Tree() {
	}
	
	public Tree(int[] arr) {
		root = new Node();
		root.setValue(arr[0]);
		for (int i=1; i<arr.length; i++) {
			root.addChild(new Node(arr[i]));
		}
	}
	
	public Node getRoot() {
		return root;
	}
	
	public void traverse() {
		//preOrder(root);
		//inOrder(root);
		postOrder(root);
	}
	
	public void preOrder(Node node) {
		if (node == null) {
			return;
		}
		visit(node);
		preOrder(node.getLeftChild());
		preOrder(node.getRightChild());
	}
	
	public void inOrder(Node node) {
		if (node == null) {
			return;
		}
		inOrder(node.getLeftChild());
		visit(node);
		inOrder(node.getRightChild());
	}
	
	public void postOrder(Node node) {
		if (node == null) {
			return;
		}
		postOrder(node.getLeftChild());
		postOrder(node.getRightChild());
		visit(node);
	}
	
	
	private void visit(Node node) {
		System.out.println(node.getValue() + " ");
	}

}
