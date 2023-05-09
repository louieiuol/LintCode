//Leetcode 109
class ConvertOrderedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        return buildTree(head, null);
    }

    private TreeNode buildTree(ListNode head, ListNode end){
        if(head == end){
            return null;
        }
        ListNode mid = findMid(head, end);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(head, mid);
        root.right = buildTree(mid.next, end);
        return root;
    }


    private ListNode findMid(ListNode head, ListNode end){
        ListNode slow = head;
        ListNode fast = head;
        while(fast != end && fast.next != end){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}