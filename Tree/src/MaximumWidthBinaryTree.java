//1101. Maximum Width of Binary Tree
//中文English
//Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.
//
//The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.
//
//Example
//Example 1:
//
//Input: 
//
//           1
//         /   \
//        3     2
//       / \     \  
//      5   3     9 
//
//Output: 4
//Explanation: The maximum width existing in the third level with the length 4 (5,3,#,9).
//Example 2:
//
//Input: 
//
//          1
//         /  
//        3    
//       / \       
//      5   3     
//
//Output: 2
//Explanation: The maximum width existing in the third level with the length 2 (5,3).
//Example 3:
//
//Input: 
//
//          1
//         / \
//        3   2 
//       /        
//      5      
//
//Output: 2
//Explanation: The maximum width existing in the second level with the length 2 (3,2).
//Example 4:
//
//Input: 
//
//          1
//         / \
//        3   2
//       /     \  
//      5       9 
//     /         \
//    6           7
//		
//Output: 8
//Explanation:The maximum width existing in the fourth level with the length 8 (6,#,#,#,#,#,#,7).
//Notice
//The answer will be in the range of 32-bit signed integer.

import java.util.*;
public class MaximumWidthBinaryTree {
	  public class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		      }
		  }
	
    public int widthOfBinaryTree(TreeNode root) {
        // Write your code here
        if(root == null) return 0;
        LinkedList<TreeNode> queue=new LinkedList<>();
        //linkedlist 才有peekLast peekFirst 方法
        root.val=0;
        int max=1;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            max=Math.max(max, queue.peekLast().val - queue.peekFirst().val + 1);
            //把每层的第一个和最后一个数找到 然后相减+1
            for(int i=0; i<size; i++){
                TreeNode curr=queue.poll();
                if(curr.left != null){
                    curr.left.val = curr.val * 2;
                    //用*2来标记左node的坐标
                    queue.offer(curr.left);
                }
                if(curr.right != null){
                    curr.right.val = curr.val * 2 + 1;
                    //用*2+1来标记右node的坐标
                    queue.offer(curr.right);
                }
            }
        }
        return max;
    }
}
