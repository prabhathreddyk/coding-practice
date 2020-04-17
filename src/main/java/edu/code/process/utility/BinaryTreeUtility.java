package edu.code.process.utility;

import edu.code.model.BinaryTree;
import edu.code.model.BinaryTreeNode;

public class BinaryTreeUtility {

	public static void inOrderTraverse(BinaryTreeNode node, int currentDepth, BinaryTree binaryTree) {
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
	
	public static int maxDepth(BinaryTreeNode node){
		if (node == null){
			return 0;
		}
		return 1+ Math.max(maxDepth(node.getLeft()), maxDepth(node.getRight()));
	}
	
	public static int minDepth(BinaryTreeNode node){
		if (node == null){
			return 0;
		}
		return 1+ Math.min(maxDepth(node.getLeft()), maxDepth(node.getRight()));
	}
}
