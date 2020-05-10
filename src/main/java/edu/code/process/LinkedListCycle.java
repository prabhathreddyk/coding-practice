package edu.code.process;

public class LinkedListCycle {

	ListNode head;
	
	public static void main(String[] args) {
		LinkedListCycle obj = new LinkedListCycle();
		obj.add(new ListNode(3));
		obj.add(new ListNode(2));
		obj.add(new ListNode(0));
		obj.add(new ListNode(-4));
		// assigning cycle
		obj.head.next.next.next.next = obj.head.next;
		
		System.out.println(getCycleStarting(obj.head));
		
	}
	
	private static ListNode getCycleStarting(ListNode head) {
		
		if (head == null || head.next == null){
			return null;
		}
		ListNode node1 = head;
		ListNode node2 = head;
		while (node2 != null && node2.next != null){
			node2 = node2.next.next;
			node1 = node1.next;
			if (node1 == node2)
				break;
		}
		if (node2 == null || node2.next == null)
			return null; // No cycle exists
		
		// reassign node2 to head
		/**
		 * Distance from head to starting point of cycle = X
		 * Distance of circle = C
		 * Distance of meeting point from starting point of circle = P
		 * Now distance traveled by node1 = X+C*i+P  ( i- number of iterations in cycle which is an integer)
		 * node2 = X+C*j+P (j - number of iterations in cycle which is an integer)
		 * we know distance traveled by node2 = 2* node1
		 * 2(X+C*i+P) = X+C*j+P
		 * P = C (j-2i) - X
		 * Note: In the above equation j - 2i is an integer. C multiplied by some integer will give complete cycle. 
		 * P is falling short of X from starting point of cycle. Also distance from head to starting point of cycle.
		 * so reset node2 to head and increment by 1 node and same with node1. Both will meet after X iterations which is starting point of cycle. 
		 */
		node2 = head;
		while(node2 != node1){
			node2 = node2.next;
			node1 = node1.next;
		}
		
		return node2;
	}

	private void add(ListNode node){
		if (head == null){
			head = node;
			return;
		}
		ListNode temp = head;
		while (temp.next != null){
			temp = temp.next;
		}
		temp.next = node;
	}

}

class ListNode {
	int val;
	ListNode next;
	
	public ListNode(int val){
		this.val = val;
	}
	
	public String toString(){
		return ""+this.val;
	}
}
