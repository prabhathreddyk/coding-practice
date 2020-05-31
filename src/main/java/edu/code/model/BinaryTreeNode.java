package edu.code.model;

public class BinaryTreeNode {

	public int value;
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	public int getValue() {
		return value;
	}
	
	public BinaryTreeNode(int value, BinaryTreeNode left, BinaryTreeNode right) {
		super();
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public BinaryTreeNode(int value) {
		this.value = value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	public BinaryTreeNode getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	public BinaryTreeNode getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}

}
