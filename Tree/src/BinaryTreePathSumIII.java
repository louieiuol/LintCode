//472. Binary Tree Path Sum III
//中文English
//Give a binary tree, and a target number, find all path that the sum of nodes equal to target, the path could be start and end at any node in the tree.
//
//Example
//Example1
//
//Input: {1,2,3,4},6
//Output: [[2, 4],[2, 1, 3],[3, 1, 2],[4, 2]]
//Explanation:
//The tree is look like this:
//    1
//   / \
//  2   3
// /
//4
//Example2
//
//Input: {1,2,3,4},3
//Output: [[1,2],[2,1],[3]]
//Explanation:
//The tree is look like this:
//    1
//   / \
//  2   3
// /
//4

import java.util.*;

public class BinaryTreePathSumIII {
	  class ParentTreeNode {
		      public int val;
		      public ParentTreeNode parent, left, right;
		  }
	
	List<List<Integer>> result=new ArrayList<>();
    public List<List<Integer>> binaryTreePathSum3(ParentTreeNode root, int target) {
        // write your code here
        if(root == null) return result;
        dfs(root, target);
        return result;
    }
    
    private void dfs(ParentTreeNode root, int target){
        if(root == null) return;
        findSum(root, null, target, new ArrayList<Integer>());
        dfs(root.left, target);
        dfs(root.right, target);
    }
    
    private void findSum(ParentTreeNode root, ParentTreeNode father, int target, List<Integer> path){
        if(root == null) return;
        path.add(root.val);
        target-=root.val;
        if(target == 0){
            result.add(new ArrayList<Integer>(path));
        }
        if(root.parent != null && root.parent != father){
            findSum(root.parent, root, target, path);
        }
        if(root.left != null && root.left != father){
            findSum(root.left, root, target, path);
        }
        if(root.right != null && root.right != father){
            findSum(root.right, root, target, path);
        }
        path.remove(path.size()-1);
    }
}
