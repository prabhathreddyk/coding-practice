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
	 * InOrder Traversal using Iterative
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
	
	public static List<Integer> preOrderTraversal(BinaryTree binaryTree){
		List<Integer> traveredArray = new ArrayList<Integer>();
		preOrderTraversal(binaryTree.getRoot(), traveredArray);
		System.out.println("Pre Order travered string: "+traveredArray);
		return traveredArray;
	}
	
	/**
	 * PreOrder Recursive Root -> Left -> Right
	 * @param node
	 * @param traveredArray
	 */
	private static void preOrderTraversal(BinaryTreeNode node, List<Integer> traveredArray) {
		if (node == null)
			return;
		//Root or Lead Node
		traveredArray.add(node.getValue());
		//Left
		preOrderTraversal(node.getLeft(), traveredArray);
		//Right
		preOrderTraversal(node.getRight(), traveredArray);
	}
	
	public static List<Integer> preOrderIterative(BinaryTree binaryTree){
		List<Integer> traveredArray = new ArrayList<Integer>();
		preOrderIterative(binaryTree.getRoot(), traveredArray, new Stack<BinaryTreeNode>());
		return traveredArray;
	}
	
	/**
	 * PreOrder Iterative Root -> Left -> Right
	 * @param node
	 * @param traversedArray
	 * @param stack
	 */
	private static void preOrderIterative(BinaryTreeNode node, List<Integer> traversedArray, Stack<BinaryTreeNode> stack){
		
		while(true){
			if (node == null){
				if (stack.isEmpty()){
					break;
				}
				// Fetch Right After pop i.e Node is already visited and its Left if exists already visited
				node = stack.pop().getRight();
				if (node == null){
					continue;
				}
			} 
			
			//Root
			traversedArray.add(node.getValue());
			stack.push(node);
			
			//Left
			node = node.getLeft();
			
		}
		
		
	}
	
	public static List<Integer> postOrder(BinaryTree binaryTree){
		List<Integer> traveredArray = new ArrayList<Integer>();
		postOrder(binaryTree.getRoot(), traveredArray);
		return traveredArray;
	}

	/**
	 * PostOrder Recursive Left -> Right -> Root
	 * @param root
	 * @param traveredArray
	 */
	private static void postOrder(BinaryTreeNode node,
			List<Integer> traveredArray) {
		if (node == null){
			return;
		}
		//Left
		postOrder(node.getLeft(), traveredArray);
		//Right
		postOrder(node.getRight(), traveredArray);

		//Root or Leaf Node
		traveredArray.add(node.getValue());
	}
	
	public static List<Integer> postOrderIterative(BinaryTree binaryTree){
		List<Integer> traveredArray = new ArrayList<Integer>();
		postOrderIterative(binaryTree.getRoot(), traveredArray, new Stack<BinaryTreeNode>());
		return traveredArray;
	}

	private static void postOrderIterative(BinaryTreeNode node,
			List<Integer> traveredArray, Stack<BinaryTreeNode> stack) {
		while(true){
			if (node == null){
				if (stack.isEmpty()){
					break;
				}
				node = stack.peek();
				if (node.getRight() != null && !traveredArray.contains(node.getRight().getValue())){
					node = node.getRight();
				} else {
					node = stack.pop();
					traveredArray.add(node.getValue());
					// Left, Right, Root all are visited. So continue popping
					node = null;
				}
				
			} else{
				stack.push(node);
				//Left
				if (node.getLeft() != null){
					node = node.getLeft();
					continue;
				}
				node = node.getRight();
			}
		}
		
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
		if (node == null || (node.getLeft()==null && node.getRight()==null)){
			return 0;
		}
		int leftDepth=0;
		if (node.getLeft() != null){
			leftDepth = 1+minDepth(node.getLeft());
		}
		int rightDepth=0;
		if (node.getRight()!=null){
			rightDepth=1+minDepth(node.getRight());
		}
		if (leftDepth > 0 && rightDepth >0){
			return Math.min(leftDepth, rightDepth);
		} else if (leftDepth > 0){
			return leftDepth;
		} else {
			return rightDepth;
		}
		
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
