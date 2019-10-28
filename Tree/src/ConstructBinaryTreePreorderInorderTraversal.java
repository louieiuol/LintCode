//73. Construct Binary Tree from Preorder and Inorder Traversal
//中文English
//Given preorder and inorder traversal of a tree, construct the binary tree.
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
//Input：[2,1,3],[1,2,3]
//Output：{2,1,3}
//Explanation:
//The binary tree is as follows
//  2
// / \
//1   3
//
//Notice
//You may assume that duplicates do not exist in the tree.


public class ConstructBinaryTreePreorderInorderTraversal {
	 public class TreeNode {
		     public int val;
		     public TreeNode left, right;
		     public TreeNode(int val) {
		         this.val = val;
		         this.left = this.right = null;
		     }
		 }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // write your code here
    	if(preorder == null || preorder.length == 0 || inorder == null 
    			|| inorder.length == 0 || inorder.length != preorder.length) return null;
    	return dfs(inorder, 0, inorder.length-1, preorder, 0, preorder.length-1);
    }
    
    private TreeNode dfs(int[] inorder, int instart, int inend, int[] preorder, int prestart, int preend) {
    	if(instart > inend || prestart > preend) return null;
    	TreeNode root=new TreeNode(preorder[prestart]);
    	int pos=findRootPosInInorder(inorder, preorder[prestart]);
    	root.left=dfs(inorder, instart, pos-1, preorder, prestart+1, prestart+pos-instart);
    	//左边inorder 的取值是 instant 到 找到位置-1 确保比root小 prestart的取值是从prestart +1 开始（root被取走了）
    	//到走完这段inorder时候的长度结束 长度是（pos-1-instart）所以是prestart+1+pos-1-instart => prestart+pos-instart
    	root.right=dfs(inorder, pos+1, inend, preorder, preend-inend+pos+1, preend);
    	//右边inorder的取值是 pos+1 到 inend 确保比root大 preend取值是 原来的结尾不变，prestart是 preend-（inorder的取值长度）
    	//=> preend- (inend-pos+1) => preend-inend+pos+1
    	return root;
    }

	private int findRootPosInInorder(int[] inorder, int target) {
		for(int i=0; i<inorder.length; i++) {
			if(inorder[i] == target) {
				return i;
			}
		}
		return -1;
	}
}
