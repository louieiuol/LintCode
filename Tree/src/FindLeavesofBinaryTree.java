//650. Find Leaves of Binary Tree
//中文English
//Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
//
//Example
//Example1
//Input: {1,2,3,4,5}
//Output: [[4, 5, 3], [2], [1]].
//Explanation:
//
//    1
//   / \
//  2   3
// / \     
//4   5    
//Example2
//Input: {1,2,3,4}
//Output: [[4, 3], [2], [1]].
//Explanation:
//
//    1
//   / \
//  2   3
// /
//4 

import java.util.*;
public class FindLeavesofBinaryTree {
	 public class TreeNode {
		     public int val;
		     public TreeNode left, right;
		     public TreeNode(int val) {
		        this.val = val;
		        this.left = this.right = null;
		     }
		 }
	//因为是由外层向内层说明是由按高度由低到高排序 所以我们可以用HashMap来记住层数 和对应每层的点
    public List<List<Integer>> findLeaves(TreeNode root) {
    	List<List<Integer>> res=new ArrayList<>();
    	if(root == null) return res;
    	HashMap<Integer, ArrayList<Integer>> map=new HashMap<>();
    	dfs(root, map);
    	//把root和map放入dfs中实现层级循环
    	for(int i=0; i<map.size(); i++) {
    		res.add(map.get(i));
    	}
    	//按0层（leaf)到 n层（root）的顺序加入结果
    	return res;
    }
    
    private int dfs(TreeNode root, HashMap<Integer, ArrayList<Integer>> map) {
    	if(root == null) {
    		return -1;
    	}
    	//base case 默认leaf node为第0层 那么空值时为-1层 （leaf node下面的一层）
    	int leftLevel=dfs(root.left, map);
    	int rightLevel=dfs(root.right, map);
    	//分治法左右两边去找对应的层数
    	int level=Math.max(leftLevel, rightLevel)+1;
    	//层数为左右两边最大值+1
    	map.putIfAbsent(level, new ArrayList<Integer>());
    	//把当前层数和root值放入map
    	map.get(level).add(root.val);
    	//向上传递当前层数
    	return level;
    }
}
