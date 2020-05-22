package edu.code.process;

class MyDoubleLinkedList {
    
    DoublyListNode head;

    /** Initialize your data structure here. */
    public MyDoubleLinkedList() {
        
    }
     public static void main(String[] args){
    	 MyDoubleLinkedList myLinkedList = new MyDoubleLinkedList();
    	 myLinkedList.addAtHead(1);
    	 myLinkedList.addAtTail(2);
    	 myLinkedList.addAtTail(3);
    	 myLinkedList.addAtTail(4);
    	 myLinkedList.addAtTail(5);
    	 DoublyListNode thirdNode = myLinkedList.head.next.next;
    	 thirdNode.child = thirdNode.next;
    	 thirdNode.next = null;
    	 thirdNode.child.prev = null;
    	 myLinkedList.addAtTail(6);
    	 myLinkedList.flatten(myLinkedList.head);
     }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        DoublyListNode node = head;
        for (int i=0; i<index;i++){
            if (node == null)
                return -1;
            node = node.next;
        }
        return node == null ? -1 : node.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        DoublyListNode node = new DoublyListNode(val);
        if (head != null)
            head.prev = node;
        node.next = head;
        head = node;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        DoublyListNode node = head;
        while (node.next != null){
            node = node.next;
        }
        DoublyListNode currentNode = new DoublyListNode(val);
        node.next = currentNode;
        currentNode.prev = node;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        
        DoublyListNode currentNode = new DoublyListNode(val);
        if (index == 0){
            currentNode.next = head;
            if (head != null)
                head.prev = currentNode;
            head = currentNode;
            return;
        }
        DoublyListNode node = head;
        for (int i=0;i<index-1;i++){
            if (node == null)
                return;
            node = node.next;
        }
        if (node == null)
            return;
        
        currentNode.prev = node;
        if (node.next != null){
            currentNode.next = node.next;
            node.next.prev = currentNode;            
        }
        node.next = currentNode;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (head == null)
            return;
        if (index == 0){
            head = head.next;
            if (head !=null)
                head.prev = null;
        }
            
        DoublyListNode node = head;
        for (int i=0;i<index-1;i++){
            if (node == null)
                return;
            node = node.next;
        }
        if (node == null || node.next == null)
            return;
        node.next = node.next.next;
        if (node.next != null)
            node.next.prev = node;
    }
    
    public DoublyListNode flatten(DoublyListNode head) {
        if (head == null)
            return null;
        multiLevelFlatten(head);
        
        return head;
    }
    
    private DoublyListNode multiLevelFlatten(DoublyListNode head){
        
    	DoublyListNode currentNode = head;
        
        while (currentNode.next != null || currentNode.child != null){
        	DoublyListNode lastNode = null;
            if (currentNode.child != null){
                lastNode = multiLevelFlatten(currentNode.child);
                DoublyListNode temp = currentNode.next;
                currentNode.next = currentNode.child;
                currentNode.child.prev = currentNode;
                lastNode.next = temp;
                if (temp != null)
                	temp.prev = lastNode;
                currentNode.child = null;
                currentNode= lastNode;
                if (currentNode.next == null)
                    break;
            }
            currentNode = currentNode.next;
            
        }
        return currentNode;
    }
    
    
}

class DoublyListNode {
    int val;
    DoublyListNode next, prev, child;
    DoublyListNode(int x) { val = x; }
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
