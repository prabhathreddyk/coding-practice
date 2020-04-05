package edu.code.model;

public class BinaryTreeNode {

	private int value;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	public int getValue() {
		return value;
	}
	
	public BinaryTreeNode(int value, BinaryTreeNode left, BinaryTreeNode right) {
		super();
		this.value = value;
		this.left = left;
		this.right = right;
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
