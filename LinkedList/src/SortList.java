//98. Sort List
//中文English
//Sort a linked list in O(n log n) time using constant space complexity.
//
//Example
//Example 1:
//
//Input:  1->3->2->null
//Output:  1->2->3->null
//Example 2:
//
//Input: 1->7->2->6->null
//Output: 1->2->6->7->null	
//Challenge
//Solve it by merge sort & quick sort separately.


public class SortList {
	
	  public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		      }
		  }
    public ListNode mergeSortList(ListNode head) {
        // write your code here
    	if(head == null || head.next == null) return head;
    	//递归的出口 空list或者 只有一个值的时候 直接返回 
    	ListNode mid=findMid(head); //找到中间值 
    	ListNode pointer=mid.next; //记录右边的开始
    	mid.next=null; // 把list拆成两半 
    	ListNode part1=mergeSortList(head); //对前半部分mergeSort 并记录返回指针
    	ListNode part2=mergeSortList(pointer); //对后半部分mergeSort 并记录返回指针
    	return merge(part1, part2); //两部分再合并
    }
    private ListNode merge(ListNode part1, ListNode part2) {
		ListNode dummy=new ListNode(0);
		ListNode head=dummy;
		while(part1 != null && part2 != null) {
			if(part1.val < part2.val) {
				head.next=part1; //不需要额外空间的写法 
				part1=part1.next;
			}else {
				head.next=part2;
				part2=part2.next;
			}
			head=head.next;
		}
		if(part1 != null) {
			head.next=part1;
		}
		if(part2 != null) {
			head.next=part2;
		}
		return dummy.next;
	}
	//快慢双针搜索mid 
	private ListNode findMid(ListNode head) {
		ListNode slow=head;
		ListNode fast=head.next;
		//按fast指针所指的对应值进行判断
		while(fast != null && fast.next != null) {
			slow=slow.next;
			fast=fast.next.next;
		}
		return slow;
	}
	
    public ListNode quickSortKList(ListNode head) {
    	//need implement
    	return null;
    }
    
}
