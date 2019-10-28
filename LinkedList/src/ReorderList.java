/*99. Reorder List
中文English
Given a singly linked list L: L0 → L1 → … → Ln-1 → Ln

reorder it to: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

Example
Example 1:
	Input:  1->2->3->4->null
	Output: 1->4->2->3->null

Example 2:
	Input: 1->2->3->4->5->null
	Output: 1->5->2->4->3->null
	
Challenge
Can you do this in-place without altering the nodes' values?
*/
public class ReorderList {
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	 public void reorderList(ListNode head) {
	        // write your code here
	        if(head == null || head.next == null) return;
	        //if list are less than two elements do nothing
	        ListNode mid=findMid(head);
	        //find mid value of list
	        ListNode secondpart=reverse(mid.next);
	        //reverse the second part
	        mid.next=null;
	        //set last node of first part point to null
	        //reduce list in half
	        merge(head,secondpart);
	        //merge two lists
	    }
	    
	    private ListNode findMid(ListNode head){
	        ListNode fast=head.next;
	        ListNode slow=head;
	        //use two slow and fast pointers
	        //slow starts from head 
	        //fast starts from head's next element
	        while(fast !=null && fast.next!=null){
	        	//while fast and fast.next not reach the end 
	            fast=fast.next.next;
	            //fast runs two steps a time
	            slow=slow.next;
	            //slow runs one step a time
	        }
	        return slow;
	        //when fast reaches the end, slow reaches the mid
	    }
	    
	    private ListNode reverse(ListNode head){
	    	//we use three pointers method: prev, current(head), next
	        ListNode prev=null;
	        //create a pointer store previous node
	        while(head !=null){
	            ListNode next=head.next;
	            //create a pointer store next node
	            head.next=prev;
	            //reverse the pointing, from current node points to previous node
	            prev=head;
	            //update the previous node as current node
	            head=next;
	            //update current node as next node
	        }
	        return prev;
	        //after it is done, current will point to null
	        //and previous node point to new head
	    }
	    
	    private void merge(ListNode head1,ListNode head2){
	        int counter=0;
	        //use a counter to determine which list come next
	        ListNode dummy=new ListNode(0);
	        //use a dummy node as a fake head to link them all
	        while(head1 != null && head2 != null){
	        	//make sure neither of them are null
	        	//list1 be linked first 
	        	if(counter % 2 ==0 ){
	            	//if it even counter, list1 will be link
	                dummy.next=head1;
	                //move list1 pointer to next
	                head1=head1.next;
	            }else{
	            	//if it odd counter, list2 will be link 
	                dummy.next=head2;
	                //move list2 pointer to next
	                head2=head2.next;
	            }
	        	//move current pointer to next and increase counter
	            dummy=dummy.next;
	            counter++;
	        }
	        //if there is one element left, append to last
	        if(head1 != null){
	            dummy.next=head1;
	        }else{
	            dummy.next=head2;
	        }
	    }
}
