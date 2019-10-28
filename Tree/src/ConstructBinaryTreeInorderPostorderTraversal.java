//72. Construct Binary Tree from Inorder and Postorder Traversal
//中文English
//Given inorder and postorder traversal of a tree, construct the binary tree.
//
//Example
//Example 1:
//
//Input：[],[]
//Output：{}
//Explanation:
//The binary tree is null
//Example 2:
//
//Input：[1,2,3],[1,3,2]
//Output：{2,1,3}
//Explanation:
//The binary tree is as follows
//  2
// / \
//1   3
//
//Notice
//You may assume that duplicates do not exist in the tree.


public class ConstructBinaryTreeInorderPostorderTraversal {
	
	 public class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		      }
		  }
	    public TreeNode buildTree(int[] inorder, int[] postorder) {
	        // write your code here
	        if(inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) return null;
	        return dfs(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
	    }
	    
	    private TreeNode dfs(int[] inorder, int instart, int inend, int[] postorder, int poststart, int postend){
	        if(instart > inend || poststart > postend) return null;
	        TreeNode root=new TreeNode(postorder[postend]);
	        //postend的root是end点的第一个
	        int pos=findRootInInorder(inorder, postorder[postend]);
	        root.left=dfs(inorder, instart, pos-1, postorder, poststart, poststart+pos-1-instart);
	        root.right=dfs(inorder, pos+1, inend, postorder, postend-inend+pos, postend-1);
	        return root;
	    }
	    
	    private int findRootInInorder(int[] inorder, int target){
	        for(int i=0; i<inorder.length; i++){
	            if(inorder[i] == target){
	                return i;
	            }
	        }
	        return -1;
	    }
}
