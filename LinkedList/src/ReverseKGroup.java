class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        int count = 0;
        while(fast != null){
            for(int i = 0; i < k && fast != null; i++){
                fast = fast.next;
            }
            if(fast == null){
                break;
            }
            ListNode start = slow.next;
            ListNode end = fast.next;
            fast.next = null;
            slow.next = reverse(start);
            start.next = end;
            slow = start;
            fast = slow;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}