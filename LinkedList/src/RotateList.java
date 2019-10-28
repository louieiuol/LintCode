/*
170. Rotate List
中文English
Given a list, rotate the list to the right by k places, where k is non-negative.

Example
Example 1:

Input:1->2->3->4->5  k = 2
Output:4->5->1->2->3
Example 2:

Input:3->2->1  k = 1
Output:1->3->2 */

public class RotateList {
	  public class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	  }
	    private int getLength(ListNode head) {
	        int length = 0;
	        while (head != null) {
	            length ++;
	            head = head.next;
	        }
	        return length;
	    }
	    
	    public ListNode rotateRight(ListNode head, int n) {
	        if (head == null) {
	            return null;
	        }
	        
	        int length = getLength(head);
	        n = n % length;
	        
	        ListNode dummy = new ListNode(0);
	        dummy.next = head;
	        head = dummy;
	        
	        ListNode tail = dummy;
	        for (int i = 0; i < n; i++) {
	            head = head.next;
	        }
	        
	        while (head.next != null) {
	            tail = tail.next;
	            head = head.next;
	        }
	        
	        head.next = dummy.next;
	        dummy.next = tail.next;
	        tail.next = null;
	        return dummy.next;
	    }
}
