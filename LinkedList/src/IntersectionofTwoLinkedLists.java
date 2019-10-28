
public class IntersectionofTwoLinkedLists {
	  public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		      }
	  }
	  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	        // write your code here
	        ListNode result=null;
	        if(headA == null || headB == null) return result;
	        ListNode end=headA;
	        while(end.next!= null){
	            end=end.next;
	        }
	        //找到第一个list的尾巴
	        end.next=headB;
	        //把尾巴连到第二个list的头上 形成环
	        result=findCycle(headA);
	        //找到两个list的交点
	        end.next=null;
	        //还原list
	        return result;
	    }
	    
	    private ListNode findCycle(ListNode head){
	        ListNode slow=head;
	        ListNode fast=head.next;
	        //设置快慢指针 都从第一个list的头出发
	        while(slow != fast){
	            if(slow == null || fast == null){
	                return null;
	            }//如果没有环 返回空
	            slow=slow.next;
	            fast=fast.next.next;
	            //慢指针移动一步 快指针移动两步
	        }
	        //两指针停在环内某点
	        slow=head;
	        //把慢针移动到第一个list的头
	        fast=fast.next;
	        //快针移动到下一个
	        //此时两指针距离重合点的距离相同
	        //因为此时从A list走到B list所需距离就是快针比慢针提前的距离
	        while(slow!=fast){
	            slow=slow.next;
	            fast=fast.next;
	        }
	        //再次相遇即是交点
	        return slow;
	    }
}
