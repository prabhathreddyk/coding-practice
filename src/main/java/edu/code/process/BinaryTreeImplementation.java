package edu.code.process;

import edu.code.model.BinaryTree;
import edu.code.model.BinaryTreeNode;

public class BinaryTreeImplementation {

	public static void main(String[] args) {
		BinaryTreeImplementation bti = new BinaryTreeImplementation();
		BinaryTree binaryTree = new BinaryTree(15);
		binaryTree.getRoot().setLeft(new BinaryTreeNode(4, null, null));
		binaryTree.getRoot().setRight(new BinaryTreeNode(12, new BinaryTreeNode(3, null, null), null));
		
		System.out.println("isBinaryTreeBalanced with Traverse:" + bti.isBinaryTreeBalanced(binaryTree));
		System.out.println("isBinaryTreeBalanced :" + (bti.maxDepth(binaryTree.getRoot()) - bti.minDepth(binaryTree.getRoot()) < 2));

	}
	
	private boolean isBinaryTreeBalanced(BinaryTree binaryTree){
		inOrderTraverse(binaryTree.getRoot(), 1, binaryTree);
		return binaryTree.getMaxDepth() - binaryTree.getMinDepth() < 2;
	}

	private void inOrderTraverse(BinaryTreeNode node, int currentDepth, BinaryTree binaryTree) {
		if (node == null){
			return;
		}
		if ( node.getLeft() == null && node.getRight() == null){
			binaryTree.setMaxDepth(binaryTree.getMaxDepth() > currentDepth 
					? binaryTree.getMaxDepth() : currentDepth);
			binaryTree.setMinDepth(binaryTree.getMinDepth() != 0
					&& binaryTree.getMinDepth() < currentDepth ? 
							binaryTree.getMinDepth() : currentDepth);
			return;
		}
		inOrderTraverse(node.getLeft(), currentDepth+1, binaryTree);
		inOrderTraverse(node.getRight(), currentDepth+1, binaryTree);
	}
	
	private int maxDepth(BinaryTreeNode node){
		if (node == null){
			return 0;
		}
		return 1+ Math.max(maxDepth(node.getLeft()), maxDepth(node.getRight()));
	}
	
	private int minDepth(BinaryTreeNode node){
		if (node == null){
			return 0;
		}
		return 1+ Math.min(maxDepth(node.getLeft()), maxDepth(node.getRight()));
	}

}
