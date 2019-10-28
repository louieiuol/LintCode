//900. Closest Binary Search Tree Value
//中文English
//Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
//
//Example
//Example1
//
//Input: root = {5,4,9,2,#,8,10} and target = 6.124780
//Output: 5
//Explanation：
//Binary tree {5,4,9,2,#,8,10},  denote the following structure:
//        5
//       / \
//     4    9
//    /    / \
//   2    8  10
//Example2
//
//Input: root = {3,2,4,1} and target = 4.142857
//Output: 4
//Explanation：
//Binary tree {3,2,4,1},  denote the following structure:
//     3
//    / \
//  2    4
// /
//1
//Notice
//Given target value is a floating point.
//You are guaranteed to have only one unique value in the BST that is closest to the target.




public class closestValue {
	
	public class TreeNode {
	       public int val;
	       public TreeNode left, right;
	       public TreeNode(int val) {
	           this.val = val;
	           this.left = this.right = null;
	       }
	 }
	
	 public int findclosestValue(TreeNode root, double target) {
	        // write your code here
	        if(root!=null) {
	        	TreeNode lowerbound=lowerBound(root,target);
	        	TreeNode upperbound=upperBound(root,target);
	        	
	        	if(lowerbound!=null && upperbound==null) {
	        		return lowerbound.val;
	        	}
	        	
	        	if(lowerbound==null && upperbound!=null) {
	        		return upperbound.val;
	        	}
	        	
	        	if(lowerbound!=null && upperbound!=null) {
	        		if((target-lowerbound.val) > (upperbound.val-target)) {
	        			return upperbound.val;
	        		}else {
	        			return lowerbound.val;
	        		}
	        	}
	        }
	        return 0;
	    }
	 
	 
	 private TreeNode lowerBound(TreeNode root, double target) {
		 if(root!=null) {
			 if(target<=root.val) {
				 return lowerBound(root.left,target);
			 }
			 
			 TreeNode node=lowerBound(root.right,target);
			 if(node != null) {
				 return node;
			 }else {
				 return root;
			 }
		 }
		 return null;
	 }
	 
	 
	 private TreeNode upperBound(TreeNode root, double target) {
		 if(root!=null) {
			 if(target>=root.val) {
				 return upperBound(root.right,target);
			 }
			 
			 TreeNode node=upperBound(root.left, target);
			 if(node!=null) {
				 return node;
			 }else {
				 return null;
			 }
		 }
		 return null;
	 }
}
