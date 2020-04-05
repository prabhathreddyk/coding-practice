package edu.code.model;

public class BinaryTree {
	private BinaryTreeNode root;
	private int maxDepth;
	private int minDepth;
	
	public BinaryTree(int value){
		root = new BinaryTreeNode(value, null, null);
	}
	public BinaryTree(BinaryTreeNode root){
		this.root = root;
	}
	
	public BinaryTreeNode getRoot() {
		return root;
	}
	public void setRoot(BinaryTreeNode root) {
		this.root = root;
	}
	public int getMaxDepth() {
		return maxDepth;
	}
	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}
	public int getMinDepth() {
		return minDepth;
	}
	public void setMinDepth(int minDepth) {
		this.minDepth = minDepth;
	}
	
}
