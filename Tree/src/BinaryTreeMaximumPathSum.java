
//94. Binary Tree Maximum Path Sum
//中文English
//Given a binary tree, find the maximum path sum.
//
//The path may start and end at any node in the tree.
//
//Example
//Example 1:
//	Input:  For the following binary tree（only one node）:
//	2
//	Output：2
//	
//Example 2:
//	Input:  For the following binary tree:
//
//      1
//     / \
//    2   3
//		
//	Output: 6

	

public class BinaryTreeMaximumPathSum {
	  public class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		      }
	  }
	
	public class ResultType{
		int rootMax; //store path from root
		int pathMax; //store path from any node
		public ResultType(int rootMax, int pathMax) {
			this.rootMax=rootMax;
			this.pathMax=pathMax;
		}
	}
	  
    public int maxPathSum(TreeNode root) {
    	if(root == null) {
    		return 0;
    	}
    	ResultType result=dfs(root);
    	return result.pathMax;	
    }
    
    private ResultType dfs(TreeNode root) {
    	ResultType result=new ResultType(0,Integer.MIN_VALUE);
    	if(root == null) {
    		return result;
    	}
    	
    	ResultType leftResult=dfs(root.left);
    	ResultType rightResult=dfs(root.right);
    	
    	int rootMax=Math.max(leftResult.rootMax, rightResult.rootMax)+root.val; 
    	//calculate max path from root to node and select the max path as rootMax
    	result.rootMax=Math.max(0, rootMax);
    	//store to result if it greater than 0
    	
    	int pathMax=Math.max(leftResult.pathMax, rightResult.pathMax);
    	//select from the max of left side max path and right side max path 
    	result.pathMax=Math.max(pathMax, leftResult.rootMax+rightResult.rootMax+root.val);
    	//choose max from the previous total path max or from  current root to node path
    	return result;
    }
}
