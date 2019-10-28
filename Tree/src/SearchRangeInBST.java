//11. Search Range in Binary Search Tree
//中文English
//Given a binary search tree and a range [k1, k2], return node values within a given range in ascending order.
//
//Example
//Example 1:
//
//Input：{5},6,10
//Output：[]
//        5
//it will be serialized {5}
//No number between 6 and 10
//Example 2:
//
//Input：{20,8,22,4,12},10,22
//Output：[12,20,22]
//Explanation：
//        20
//       /  \
//      8   22
//     / \
//    4   12
//it will be serialized {20,8,22,4,12}
//[12,20,22] between 10 and 22

import java.util.*;

public class SearchRangeInBST {
	  public class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		      }
		  }
    List<Integer> result=new ArrayList<>();
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        dfs(root, k1, k2);
        return result;
    }
    
    private void dfs(TreeNode root, int k1, int k2){
        if(root == null) return;
        //二叉查找树的中序遍历是有序的
        //这里必须按照中序遍历的顺序才能保证返回结果升序
        if(root.val > k2){
            dfs(root.left, k1, k2);
        }
        if(root.val >= k1 && root.val <= k2){
            result.add(root.val);
            dfs(root.left, k1, k2);
            dfs(root.right, k1, k2);
        }
        if(root.val < k1){
            dfs(root.right, k1, k2);
        }
    }
}
