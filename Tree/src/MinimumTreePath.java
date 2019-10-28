/*
 * Given a binary tree, return its minimum value of the path from root to leave.
 */
public class MinimumTreePath {
	public class TreeNode {
		   public int val;
		   public TreeNode left, right;
		   public TreeNode(int val) {
		      this.val = val;
			  this.left = this.right = null;
			}
		}
	    public int Solution(TreeNode root) {
	        if (root == null)   return 0;
	        if (root.left != null && root.right == null)
	            return Solution(root.left) + root.val;
	        if (root.left == null && root.right != null)
	            return Solution(root.right) + root.val;
	        return Math.min(Solution(root.left), Solution(root.right)) + root.val;
	    }
}
