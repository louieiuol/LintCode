
public class PlusOneLinkedList {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	//解法1 iterative 
	public ListNode plusOne1(ListNode head) {
		if(head == null) return null;
		ListNode dummy=new ListNode(0);
		//额外添加一位在head前 给进位的情况预留空间
		dummy.next=head;
		ListNode left=dummy; //左指针从dummy开始 指向 从左往右最后一个digit上非9的数的node的位置
		ListNode right=dummy; //右指针从dummy开始 循环遍历整个list 从开头指向结尾
		while(right.next!=null) {
			right=right.next;
			//因为第一个是空 从第二个元素开始 找到倒数第二个数
			if(right.val!=9) {
				left=right;
				//左指针记录最后一个digit非9的数
			}
		}
		//对最后一个数进行判断
		if(right.val != 9) {
			//如果不是9 那么+1
			right.val+=1;
		}else {
			//如果是9 那么left指针指向的位置+1 之后digit都清0
			left.val+=1;
			left=left.next;
			//指针移动到下一位
			while(left!=null) {
				left.val=0;
				left=left.next;
			}
		}
		if(dummy.val == 0) return dummy.next; //如果没有进位 则返回原来的头
		else return dummy; //如果进位则返回+1的新头
	}
	
	//解法2 recursive 
	int carry=1;
	public ListNode plusOne2(ListNode head) {
		if(head == null )return null;
		ListNode dummy=new ListNode(0);
		dummy.next=head;
		calculate(head);
		return dummy.val == 1 ? dummy:dummy.next;
	}
	
	private void calculate(ListNode head) {
		if(head == null) return;
		calculate(head.next);
		//先往后找 最先对最后一个进行处理
		ListNode before=head; //记录下当前原来的值
		head.val = (head.val + carry) % 10; //位置上的值 = (原来值+之前的进位值) %10 如果是 0-8 +1 % 10 就是本身 如果是9 +1 % 10 是0 
		carry=(before.val+carry)/10; //更新进位值 进位是由原来值进行计算的+最初的进位 /10 如果是 0-8 +1 /10 为0 没有进位 9 +1 /10就进位成1 
	}
}
