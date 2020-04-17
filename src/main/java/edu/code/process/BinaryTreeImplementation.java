package edu.code.process;

import edu.code.model.BinaryTree;
import edu.code.model.BinaryTreeNode;
import edu.code.process.utility.BinaryTreeUtility;

public class BinaryTreeImplementation {

	public static void main(String[] args) {
		BinaryTreeImplementation bti = new BinaryTreeImplementation();
		BinaryTree binaryTree = getBinaryTree();
		
		System.out.println("isBinaryTreeBalanced with Traverse:" + bti.isBinaryTreeBalanced(binaryTree));
		System.out.println("isBinaryTreeBalanced :" + (BinaryTreeUtility.maxDepth(binaryTree.getRoot()) - BinaryTreeUtility.minDepth(binaryTree.getRoot()) < 2));

	}

	private static BinaryTree getBinaryTree() {
		BinaryTree binaryTree = new BinaryTree(15);
		binaryTree.getRoot().setLeft(new BinaryTreeNode(4, null, null));
		binaryTree.getRoot().setRight(new BinaryTreeNode(12, new BinaryTreeNode(3, null, null), null));
		return binaryTree;
	}
	
	private boolean isBinaryTreeBalanced(BinaryTree binaryTree){
		BinaryTreeUtility.inOrderTraverse(binaryTree.getRoot(), 1, binaryTree);
		return binaryTree.getMaxDepth() - binaryTree.getMinDepth() < 2;
	}

}
