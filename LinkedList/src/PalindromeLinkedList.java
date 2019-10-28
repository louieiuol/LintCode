//
//223. Palindrome Linked List
//中文English
//Implement a function to check if a linked list is a palindrome.
//
//Example
//Example 1:
//
//Input: 1->2->1
//output: true
//Example 2:
//
//Input: 2->2->1
//output: false
//Challenge
//Could you do it in O(n) time and O(1) space?

public class PalindromeLinkedList {
	  public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		      }
	  }
	  
	  public boolean isPalindrome(ListNode head) {
		  if(head == null) return true;
		  ListNode middle= findMiddle(head);
		  //find middle of the list
		  middle.next=reverse(middle.next);
		  //reverse the last half of the array
		  ListNode p1=head;
		  ListNode p2=middle.next;
		  //set up two pointers to traversal, one points to begin, another points to end
		  //compare first half with second half
		  while(p1 != null && p2!=null && p1.val == p2.val) {
			  p1=p1.next;
			  p2=p2.next;
		  }
		  //if none of them reaches the end, keep checking them are same value
		  return p2 == null;
		  //when they finish, p2 should points to end, if in middle they are not match, 
		  //it will break inside, so that p2 won't be empty.
	  }
	  
	  //use slow and fast two pointers to find middle
	  private ListNode findMiddle(ListNode head) {
		  ListNode slow=head; //slow pointer 从第一个开始
		  ListNode fast=head.next; //fast pointer 从第二个开始
		  while(fast != null && fast.next!=null) {
			  //对快指针的当前 和 下一个都要进行判断
			  fast=fast.next.next;
			  slow=slow.next;
			  //快指针走两步 慢指针走一步
		  }
		  //当快指针走完的时候 慢指针正好走完一半
		  return slow;
	  }
	  
	  private ListNode reverse(ListNode head) {
		  ListNode prev=null; //定于前点
		  //因为是直接对当前List进行修改 不需要新建pointer来遍历了
		  while(head != null) {
			  ListNode temp =head.next; //建立暂时点保存当前点的下一个 
			  head.next=prev; //把当前点的下一个指向prev
			  prev=head; //把prev点替换成当前点
			  head=temp; //把当前点替换成之前保存过的下一个点
		  }
		  return prev;
		  //当head走到空时 prev保存了最后一个点 也是倒过来的list的开头
	  }
}
