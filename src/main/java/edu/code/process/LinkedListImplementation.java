package edu.code.process;

public class LinkedListImplementation {

	public static void main(String[] args) {
		ListNode head = createLL();
		printLL(head);
		head = reverseList(head);
		printLL(head);
		
		// Palindrome check
		ListNode endOfFirstHalf = getEndOfFirstHalf(head);
		ListNode startOfSecondHalf = reverseList(endOfFirstHalf.next);
		boolean check = true;
		ListNode node1 = head;
		ListNode node2 = startOfSecondHalf;
		while (check && node1 != null && node2 != null){
			if (node1.val != node2.val){
				check = false;
			}
			node1 = node1.next;
			node2 = node2.next;
		}
		reverseList(startOfSecondHalf);
		System.out.println("Is palindrome: "+ check);
		addTwoNumbers(head, head);
		addTwoNumbersRecursive(head, head);
	}

	private static ListNode getEndOfFirstHalf(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null){
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	private static ListNode createLL() {
		ListNode prev = null;
		ListNode head = null;
		
		for (int i=1;i<4;i++){
			int val  = i;
			if (val ==4)
				val = 2;
			if (val == 5)
				val = 1;
			ListNode node = new ListNode(val);
			if (head == null){
				head = node;
				prev = node;
				continue;
			}
			prev.next = node;
			prev = node;
		}
		return head;
	}
	
	public static void printLL(ListNode head){
		ListNode node = head;
		while (node != null){
			System.out.print(" "+node.val);
			node = node.next;
		}
		System.out.println("");
	}
	
	public static ListNode reverseList(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode node = head;
        while (node.next != null){
            ListNode temp = node.next;
            node.next = temp.next;
            temp.next = head;
            head = temp;           
        }
        return head;
    }
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode currentNode = dummyNode;
        int carryOver = 0;
        while ( l1 !=null || l2 != null){
            int sum = 0;
            if (l1 != null && l2 != null){
                sum = l1.val + l2.val + carryOver;
                l1 = l1.next;
                l2 = l2.next;
            } else if ( l1 == null){
                sum = l2.val+ carryOver;
                l2 = l2.next;
            }else{
                sum = l1.val+ carryOver;
                l1 = l1.next;
            }
            
            carryOver = sum / 10;
            currentNode.next = new ListNode(sum - 10*carryOver);
            currentNode = currentNode.next;
        }
        
        return dummyNode.next;
    }
	
	public static ListNode addTwoNumbersRecursive(ListNode l1, ListNode l2) {
        return addNumbers(l1, l2, 0); 
    }
    
    private  static ListNode addNumbers(ListNode l1, ListNode l2, int carry){
        if (l1 == null && l2 == null){
            if (carry > 0)
                return new ListNode(carry);
            else
                return null;
        }
            
        ListNode node;
        int sum = 0;
        if (l1 == null){
            sum = l2.val + carry;
            l2 = l2.next;
        }else if (l2 == null){
            sum = l1.val + carry;
            l1 = l1.next;
        } else{
            sum = l1.val + l2.val + carry;
            l1 = l1.next;
            l2 = l2.next;
        }
        carry = sum /10;
        node = new ListNode(sum - 10*carry);
        node.next = addNumbers( l1,  l2, carry);
       return node;
                  
    }

}
