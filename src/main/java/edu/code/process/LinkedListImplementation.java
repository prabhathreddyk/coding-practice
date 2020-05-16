package edu.code.process;

public class LinkedListImplementation {

	public static void main(String[] args) {
		ListNode head = createLL();
		printLL(head);
		head = reverseList(head);
		printLL(head);
	}

	private static ListNode createLL() {
		ListNode prev = null;
		ListNode head = null;
		for (int i=1;i<6;i++){
			ListNode node = new ListNode(i);
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

}
