//246. Binary Tree Path Sum II
//中文English
//Your are given a binary tree in which each node contains a value. Design an algorithm to get all paths which sum to a given value. The path does not need to start or end at the root or a leaf, but it must go in a straight line down.
//
//Example
//Example 1:
//
//Input:
//{1,2,3,4,#,2}
//6
//Output:
//[[2, 4],[1, 3, 2]]
//Explanation:
//The binary tree is like this:
//    1
//   / \
//  2   3
// /   /
//4   2
//for target 6, it is obvious 2 + 4 = 6 and 1 + 3 + 2 = 6.
//Example 2:
//
//Input:
//{1,2,3,4}
//10
//Output:
//[]
//Explanation:
//The binary tree is like this:
//    1
//   / \
//  2   3
// /   
//4   
//for target 10, there is no way to reach it.

import java.util.*;

//这道题最容易犯的错误就是，从每个节点新开始一个搜索，这样会在树的末端大量重复，而且这种重复没法用Set消除，因为可能存在多条路径整数序列一样。
//DFS pre-order traverse + 回溯操作

public class BinaryTreePathSumII {
	  public class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		     }
	 }
	
    List<List<Integer>> result=new ArrayList<>();
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // write your code here
        if(root == null);
        dfs(root, target, new ArrayList<Integer>());
        return result;
    }
    
    private void dfs(TreeNode root, int target, List<Integer> path){
        if(root == null) return;
        int sum=0;
        path.add(root.val);
        for(int i=path.size()-1; i>=0 ; i--){
            sum+=path.get(i);
            if(sum == target){
                result.add(new ArrayList<Integer>(path.subList(i, path.size())));
            }
        }
        dfs(root.left, target, path);
        dfs(root.right, target, path);
        path.remove(path.size()-1);
    }
}
