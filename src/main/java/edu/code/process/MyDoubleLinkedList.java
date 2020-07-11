package edu.code.process;

import java.util.Stack;

class MyDoubleLinkedList {
    
    DoublyListNode head;

    /** Initialize your data structure here. */
    public MyDoubleLinkedList() {
        
    }
     public static void main(String[] args){
    	 MyDoubleLinkedList myLinkedList = new MyDoubleLinkedList();
    	 /*myLinkedList.addAtHead(1);
    	 myLinkedList.addAtTail(2);
    	 myLinkedList.addAtTail(3);
    	 myLinkedList.addAtTail(4);
    	 myLinkedList.addAtTail(5);
    	 DoublyListNode thirdNode = myLinkedList.head.right.right;
    	 thirdNode.child = thirdNode.right;
    	 thirdNode.right = null;
    	 thirdNode.child.left = null;
    	 myLinkedList.addAtTail(6);
    	 myLinkedList.flatten(myLinkedList.head);
    	 */
    	 DoublyListNode root = new DoublyListNode(4, new DoublyListNode(2, new DoublyListNode(1), new DoublyListNode(3)), new DoublyListNode(5) );
    	 
    	 DoublyListNode result = myLinkedList.treeToDoublyList(root);
    	 System.out.println(result.val);
    	 DoublyListNode node = result.right;
    	 while(node != result){
    		 System.out.println(node.val);
    		 node = node.right;
    	 }
     }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        DoublyListNode node = head;
        for (int i=0; i<index;i++){
            if (node == null)
                return -1;
            node = node.right;
        }
        return node == null ? -1 : node.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        DoublyListNode node = new DoublyListNode(val);
        if (head != null)
            head.left = node;
        node.right = head;
        head = node;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        DoublyListNode node = head;
        while (node.right != null){
            node = node.right;
        }
        DoublyListNode currentNode = new DoublyListNode(val);
        node.right = currentNode;
        currentNode.left = node;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        
        DoublyListNode currentNode = new DoublyListNode(val);
        if (index == 0){
            currentNode.right = head;
            if (head != null)
                head.left = currentNode;
            head = currentNode;
            return;
        }
        DoublyListNode node = head;
        for (int i=0;i<index-1;i++){
            if (node == null)
                return;
            node = node.right;
        }
        if (node == null)
            return;
        
        currentNode.left = node;
        if (node.right != null){
            currentNode.right = node.right;
            node.right.left = currentNode;            
        }
        node.right = currentNode;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (head == null)
            return;
        if (index == 0){
            head = head.right;
            if (head !=null)
                head.left = null;
        }
            
        DoublyListNode node = head;
        for (int i=0;i<index-1;i++){
            if (node == null)
                return;
            node = node.right;
        }
        if (node == null || node.right == null)
            return;
        node.right = node.right.right;
        if (node.right != null)
            node.right.left = node;
    }
    
    public DoublyListNode flatten(DoublyListNode head) {
        if (head == null)
            return null;
        multiLevelFlatten(head);
        
        return head;
    }
    
    private DoublyListNode multiLevelFlatten(DoublyListNode head){
        
    	DoublyListNode currentNode = head;
        
        while (currentNode.right != null || currentNode.child != null){
        	DoublyListNode lastNode = null;
            if (currentNode.child != null){
                lastNode = multiLevelFlatten(currentNode.child);
                DoublyListNode temp = currentNode.right;
                currentNode.right = currentNode.child;
                currentNode.child.left = currentNode;
                lastNode.right = temp;
                if (temp != null)
                	temp.left = lastNode;
                currentNode.child = null;
                currentNode= lastNode;
                if (currentNode.right == null)
                    break;
            }
            currentNode = currentNode.right;
            
        }
        return currentNode;
    }
    
    
    public DoublyListNode treeToDoublyList(DoublyListNode root) {
        if (root == null)
            return null;
        Stack<DoublyListNode> stack = new Stack<DoublyListNode>();
        DoublyListNode node = root;
        DoublyListNode head = new DoublyListNode(-1);
        DoublyListNode prev = head;
        while(true){
            if (node == null){
                if (stack.isEmpty())
                    break;
                node = stack.pop();
                DoublyListNode temp = new DoublyListNode(node.val, prev, null);
                prev.right = temp;
                prev = temp;   
                node = node.right;
            } else{
                stack.add(node);
                node = node.left;
            }          
        }
        head = head.right;
        head.left = prev;
        prev.right = head;
        return head;
    }
    
}

class DoublyListNode {
    int val;
    DoublyListNode right, left, child;
    DoublyListNode(int x) { val = x; }
    
    DoublyListNode(int val, DoublyListNode left, DoublyListNode right){
    	this.val = val;
    	this.left= left;
    	this.right= right;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
