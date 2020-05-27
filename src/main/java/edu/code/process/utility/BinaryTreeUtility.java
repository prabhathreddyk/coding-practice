package edu.code.process.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import edu.code.model.BinaryTree;
import edu.code.model.BinaryTreeNode;

public class BinaryTreeUtility {
	
	private int output = 0;
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
	 * InOrder Traversal using Iterative. See also below alternate solution
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
	 * InOrder traversal iterative
	 * @param BinaryTreeNode root
	 * @return
	 */
	public List<Integer> inorderIterativeTraversal(BinaryTreeNode root) {
        List<Integer> path = new ArrayList<Integer>();
        if (root == null){
            return path;
        }
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        BinaryTreeNode node = root.getLeft();
        stack.push(root);
        
        while(true){
            if (node == null){
                if (stack.isEmpty())
                    break;
                node = stack.pop();
                path.add(node.getValue());
                node = node.getRight();
                continue;
            }
            
            stack.push(node);
            node = node.getLeft();            
        }
        return path;
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
	
	/**
	 * PreOrder Iterative Root -> Left -> Right
	 * @param node
	 * @param traversedArray
	 * @param stack
	 */
	public static List<Integer> preOrderIterative(BinaryTree binaryTree){
		List<Integer> traversedArray = new ArrayList<Integer>();
		BinaryTreeNode root = binaryTree.getRoot();
		if (root == null){
			return traversedArray;
		}
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		stack.push(root);
		while( !stack.isEmpty()){
			BinaryTreeNode node = stack.pop();
			
			traversedArray.add(node.getValue());
			// First push right 
			if (node.getRight() != null)
				stack.push(node.getRight());
			// push left to stack
			if (node.getLeft() != null)
				stack.push(node.getLeft());
		}
		
		return traversedArray;
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
		return postOrderIterative(binaryTree.getRoot());
	}

	private static List<Integer> postOrderIterative(BinaryTreeNode root) {
        List<Integer> path = new ArrayList<Integer>();
        if (root == null)
            return path;
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        stack.push(root);
        BinaryTreeNode node = root.getLeft();
        BinaryTreeNode lastPoped = null;
        while(true){
            if(node == null){
                if (stack.isEmpty()){
                    break;
                }
                BinaryTreeNode temp = stack.peek();
                if (temp.getRight() == null || temp.getRight() == lastPoped){
                    lastPoped = stack.pop();
                    path.add(temp.getValue());
                    continue;
                }else{
                    node = temp.getRight();
                }
            }
            stack.push(node);
            node = node.getLeft();
        }
        return path;
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
	
	/**
	 * Check if tree is Symmetric
	 * @param root
	 * @return
	 */
	public boolean isSymmetric(BinaryTreeNode root) {
        if (root == null || (root.getLeft() == null && root.getRight() == null))
            return true;
        return checkIsSymmetric(root.getLeft(), root.getRight());
        
    }
    
    private boolean checkIsSymmetric(BinaryTreeNode left, BinaryTreeNode right){
        if (left == null && right == null)
            return true;
        
        if (left != null && right != null && left.getValue() == right.getValue()){
            if (!checkIsSymmetric(left.getRight(), right.getLeft()) || !checkIsSymmetric(left.getLeft(), right.getRight()))
                return false;
                
        }else
            return false;
        return true;
    }
    
    /**
     * Given a binary tree, count the number of uni-value subtrees.
     * A Uni-value subtree means all nodes of the subtree have the same value.
     * @param root
     * @return
     */
    public int countUnivalSubtrees(BinaryTreeNode root) {
        checkUnivalSubtree(root);
        return output;
    }
    
    private boolean checkUnivalSubtree(BinaryTreeNode node){
        if (node == null)
            return false;
        if (node.getLeft() == null && node.right == null){
            output++;
            return true;
        }
        boolean leftChildStatus = false;
        boolean rightChildStatus = false;
        if (node.left == null || (checkUnivalSubtree(node.left) && node.left.value == node.value)){
            leftChildStatus = true; 
        }
        
        if (node.right == null || (checkUnivalSubtree(node.right) && node.right.value == node.value)){
            rightChildStatus = true; 
        }
        
        if (leftChildStatus && rightChildStatus){
            output++;
            return true;
        }
        return false;
    }
}
