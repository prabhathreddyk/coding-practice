package edu.code.process.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import edu.code.model.BinaryTree;
import edu.code.model.BinaryTreeNode;

public class BinaryTreeUtility {
	
	private static int pindex=0;
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
    
    /**
     * Given post Order and In order traversal. Construct Binary tree.
     * @param inorder
     * @param postorder
     * @return
     */
    public static BinaryTreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0)
            return null;
        pindex = postorder.length-1;
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>(inorder.length);
        for (int i=0;i<inorder.length;i++)
            inMap.put(inorder[i], i);
        return buildTree(inorder, postorder, 0, inorder.length-1, inMap);
        
    }
    
    private static BinaryTreeNode buildTree(int[] inorder, int[] postorder, int start, int last, Map<Integer, Integer> inMap){
        if (start > last)
            return null;
        
        BinaryTreeNode root = new BinaryTreeNode(postorder[pindex--]);
        int position = inMap.get(root.getValue());
        root.right = buildTree(inorder, postorder, position+1, last, inMap);
        root.left = buildTree(inorder, postorder, start, position-1, inMap);


        return root;
    }
    
    /**
     * Given pre Order and In order traversal. Construct Binary tree.
     * @param inorder
     * @param postorder
     * @return
     */
    public static BinaryTreeNode preorderbuildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0)
            return null;
        pindex = 0;
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>(inorder.length);
        for (int i=0;i<inorder.length;i++)
            inMap.put(inorder[i], i);
        return preorderbuildTree(inorder, postorder, 0, inorder.length-1, inMap);
        
    }
    
    private static BinaryTreeNode preorderbuildTree(int[] inorder, int[] postorder,
    		int start, int last, Map<Integer, Integer> inMap){
        if (start > last)
            return null;
        
        BinaryTreeNode root = new BinaryTreeNode(postorder[pindex++]);
        int position = inMap.get(root.getValue());
        root.left = buildTree(inorder, postorder, start, position-1, inMap);
        root.right = buildTree(inorder, postorder, position+1, last, inMap);
        return root;
    }
    
    /**
     * 
     * @param root
     * @return
     */
    public static String serialize(BinaryTreeNode root) {
        if (root == null)
            return "";
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        BinaryTreeNode empty = new BinaryTreeNode(-1);
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
        	BinaryTreeNode node = queue.remove();
        	if (node != empty){
                sb.append(node.getValue()).append(",");
                queue.add(node.left == null ? empty : node.left);
                queue.add(node.right == null ? empty : node.right);
            }else{
                sb.append("null").append(",");
            }
        }
        return sb.toString().substring(0, sb.length()-1);
        
    }
    
    // Decodes your encoded data to tree.
    public static BinaryTreeNode deserialize(String data) {
        if (data == null || data.equals(""))
            return null;
        String[] values = data.split(",");
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        BinaryTreeNode root = new BinaryTreeNode(Integer.valueOf(values[0]));
        queue.add(root);
        for (int i=1; i<values.length;i++){
            BinaryTreeNode node = queue.remove();
            
            node.left = addNodeToQueue(values[i++], queue);
            node.right = addNodeToQueue(values[i], queue);            

        }
        return root;
    }
    
    private static BinaryTreeNode addNodeToQueue(String val, Queue<BinaryTreeNode> queue){
        if (!val.equals("null")){
        	BinaryTreeNode node = new BinaryTreeNode(Integer.valueOf(val));
            queue.add(node);
            return node;
        }
        return null;
    }
    
    // Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
    public List<BinaryTreeNode> generateTrees(int n) {
        if (n==0)
            return new ArrayList<BinaryTreeNode>();
        return generateTrees( 1, n); 
    }
    
    private List<BinaryTreeNode> generateTrees(int start, int end){
        if (start > end)
            return null;
        List<BinaryTreeNode> treeList = new ArrayList<BinaryTreeNode>();
        for (int i=start; i<=end;i++){
            
            List<BinaryTreeNode> leftList = generateTrees(start, i-1);
            List<BinaryTreeNode> rightList = generateTrees(i+1, end);
            if (leftList == null && rightList == null)
                treeList.add(new BinaryTreeNode(i));
            else if (leftList == null) {
                for (BinaryTreeNode rightNode : rightList){
                    BinaryTreeNode node = new BinaryTreeNode(i);
                    node.right = rightNode;
                    treeList.add(node);
                }
            } else if (rightList == null) {
                for (BinaryTreeNode leftNode : leftList){
                    BinaryTreeNode node = new BinaryTreeNode(i);
                    node.left = leftNode;
                    treeList.add(node);
                }
            } else{
                for (BinaryTreeNode leftNode : leftList){
                    for (BinaryTreeNode rightNode : rightList){
                        BinaryTreeNode node = new BinaryTreeNode(i);
                        node.left = leftNode;
                        node.right = rightNode;
                        treeList.add(node);
                    }
                }
            }
        }
        return treeList;
    }
    
    public boolean isValidBST(BinaryTreeNode root) {
        if (root == null)
            return true;
        long min = Integer.MIN_VALUE;
        long max = Integer.MAX_VALUE;
        return isValidBST(root.left, min-1, root.value) && isValidBST(root.right, root.value, max+1);
    }
    
    private boolean isValidBST(BinaryTreeNode root, long min, long max){
        if (root == null)
            return true;
        if (root.value <= min || root.value >= max)
            return false;
        return isValidBST(root.left, min, root.value) && isValidBST(root.right, root.value, max);
    }
    
    public static int widthOfBinaryTree(BinaryTreeNode root) {
        if (root == null)
            return 0;
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);
        BinaryTreeNode nul = new BinaryTreeNode(-1);
        BinaryTreeNode end = new BinaryTreeNode(-1);
        queue.add(end);
        boolean hasChild = false;
        int count = 0;
        int tempCount = 0;
        int maxWidth = 0;
        boolean isLastRow = false;
        while(!queue.isEmpty() && !isLastRow){
        	BinaryTreeNode node = queue.remove();
            if(node == end){
            	maxWidth = Math.max(maxWidth, count);
                if (!hasChild){
                	isLastRow = true;
                	break;
                }
                hasChild=false;	
                while(true){
                    if (queue.isEmpty()){
                        break;
                    }                        
                    if (queue.peek() == nul)
                        queue.remove();
                    else{
                        queue.add(end);
                        count=0;
                        tempCount=0;
                        break;
                    }
                }
            }else{
                tempCount++;
                if (node != nul)
                    count = tempCount;
                if (node.left != null || node.right != null)
                    hasChild=true;
                queue.add(node.left == null ? nul : node.left);
                queue.add(node.right == null ? nul : node.right);
            }
        }
        return maxWidth;
    }
    
    /*private int max = 1;
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        List<Integer> startOfLevel = new LinkedList<>();
        helper(root, 0, 1, startOfLevel);
        return max;
    }
    public void helper(TreeNode root, int level, int index, List<Integer> list) {
        if (root == null) return;
        if (level == list.size()) list.add(index);
        max = Math.max(max, index + 1 - list.get(level));
        helper(root.left, level + 1, index * 2, list);
        helper(root.right, level + 1, index * 2 + 1, list);
    }*/
    
}
