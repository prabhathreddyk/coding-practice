package edu.code.process.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import edu.code.model.BinaryTree;
import edu.code.model.BinaryTreeNode;

public class BinaryTreeUtility {
	
	public static List<Integer> inOrderTraverse(BinaryTree binaryTree){
		List<Integer> traversedArray = new ArrayList<Integer>();
		inOrderTraverse( binaryTree.getRoot(), 1, binaryTree,  traversedArray);
		System.out.println("InOrder Traverse : "+ traversedArray.toString());
		return traversedArray;
	}

	/**
	 * InOrder Traverse Left -> Root -> Right
	 * @param node
	 * @param currentDepth
	 * @param binaryTree
	 */
	public static void inOrderTraverse(BinaryTreeNode node, int currentDepth, BinaryTree binaryTree, List<Integer> traversedArray) {
		if (traversedArray == null){
			traversedArray = new ArrayList<Integer>();
		}
		if (node == null){
			return;
		}
		if ( node.getLeft() == null && node.getRight() == null){
			traversedArray.add(node.getValue());
			binaryTree.setMaxDepth(binaryTree.getMaxDepth() > currentDepth 
					? binaryTree.getMaxDepth() : currentDepth);
			binaryTree.setMinDepth(binaryTree.getMinDepth() != 0
					&& binaryTree.getMinDepth() < currentDepth ? 
							binaryTree.getMinDepth() : currentDepth);
			return;
		}
		// Left
		inOrderTraverse(node.getLeft(), currentDepth+1, binaryTree, traversedArray);
		// Root
		traversedArray.add(node.getValue());
		// Right
		inOrderTraverse(node.getRight(), currentDepth+1, binaryTree, traversedArray);
	}
	
	/**
	 * InOrder Traversal using Recursive
	 * @param binaryTree
	 * @return
	 */
	public static List<Integer> inOrderTraverseIterative(BinaryTree binaryTree) {
		BinaryTreeNode node = binaryTree.getRoot();
		int currentDepth = 0;
		int maxDepth = 0;
		int minDepth = 0;
		List<Integer> traversedArray = new ArrayList<Integer>();
		Stack<BinaryTreeNode> treeStack = new Stack<BinaryTreeNode>();
		while(true){
			if (node == null){
				if (treeStack.isEmpty()){
					break;
				}
				node = treeStack.pop();
				currentDepth--;
			} else{
				currentDepth++;
				if (node.getLeft() == null && node.getRight() == null ){
					if (currentDepth > maxDepth){
						maxDepth = currentDepth;
					}
					if (minDepth == 0 || currentDepth < minDepth){
						minDepth = currentDepth;
					}
				}
				// Left
				if (node.getLeft() != null){
					treeStack.push(node);
					node = node.getLeft();
					continue;
				}
			}
			
			// Root or leaf Node
			traversedArray.add(node.getValue());
			
			// Right
			node = node.getRight();
		}
		binaryTree.setMaxDepth(maxDepth);
		binaryTree.setMinDepth(minDepth);
		System.out.println("Traversed tree : " + traversedArray.toString() + ", MaxDepth : " + maxDepth + ", MinDepth : "+ minDepth);
		return traversedArray;
	}
	
	/**
	 * Returns Max Depth in a Binary Tree
	 * @param node
	 * @return
	 */
	public static int maxDepth(BinaryTreeNode node){
		if (node == null){
			return 0;
		}
		return 1+ Math.max(maxDepth(node.getLeft()), maxDepth(node.getRight()));
	}
	
	/**
	 * Returns Minimum depth in a Binary Tree
	 * @param node
	 * @return
	 */
	public static int minDepth(BinaryTreeNode node){
		if (node == null){
			return 0;
		}
		return 1+ Math.min(maxDepth(node.getLeft()), maxDepth(node.getRight()));
	}
	
	/**
	 * Returns kthSmallest value in BST 
	 * returns -1 if BT is not BST
	 * @param binaryTree
	 * @return
	 */
	public static int kthSmallestInBST(BinaryTree binaryTree, int k){
		List<Integer> traversedArray = inOrderTraverse(binaryTree);
		if (traversedArray == null || traversedArray.size() == 0 || traversedArray.size() < k ||
				!CollectionUtils.isSorted(traversedArray)){
			return -1;
		}
		return traversedArray.get(k-1);
	}
}
