import java.util.*;
public class PlusOneLinkedList {
	  public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		      }
		  }
	
	public ListNode plusOne(ListNode head) {
		if(head == null) return null;
		ListNode dummy=new ListNode(0);
		dummy.next=head;
		Stack<ListNode> stack=new Stack<>();
		ListNode node=head;
		while(node!=null) {
			stack.push(node);
			node=node.next;
		}
		while(!stack.isEmpty()) {
			ListNode curr=stack.pop();
			if(curr.val != 9) {
				curr.val+=1;
				return dummy.next;
			}else {
				curr.val=0;
			}
		}
		dummy.val=1;
		return dummy;
	}
}
