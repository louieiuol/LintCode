// Leetcode 206. 反转链表
// 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
class ReverseList {
    //递归
    public ListNode reverseList1(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode newNode=reverseList(head.next);
        head.next.next=head;
        head.next=null;
        return newNode;
    }
    //迭代
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}

