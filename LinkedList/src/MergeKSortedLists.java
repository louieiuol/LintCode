//104. Merge K Sorted Lists
//中文English
//Merge k sorted linked lists and return it as one sorted list.
//
//Analyze and describe its complexity.
//
//Example
//Example 1:
//	Input:   [2->4->null,null,-1->null]
//	Output:  -1->2->4->null
//
//Example 2:
//	Input: [2->6->null,5->null,7->null]
//	Output:  2->5->6->7->null
import java.util.*;	

public class MergeKSortedLists {
	  public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int val) {
		          this.val = val;
		          this.next = null;
		      }
		  }
	
    public ListNode mergeKLists(List<ListNode> lists) {  
        // write your code here
        ListNode dummy=new ListNode(0);
        ListNode head=dummy;
        PriorityQueue<ListNode> pq=new PriorityQueue<>((a, b) -> (a.val-b.val));
        for(ListNode ele: lists){
            if(ele != null){
                pq.offer(ele);
            }
        }
        while(!pq.isEmpty()){
            ListNode curr=pq.poll();
            head.next=curr;
            if(curr.next != null){
                pq.offer(curr.next);
            }
            head=head.next;
        }
        return dummy.next;
    }
}
