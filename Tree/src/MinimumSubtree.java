public class MinimumSubtree {
	public class TreeNode {
		   public int val;
		   public TreeNode left, right;
		   public TreeNode(int val) {
		      this.val = val;
			  this.left = this.right = null;
			}
		}
	
	
	public TreeNode subtree=null;
	public int mini=Integer.MAX_VALUE;
	
	public TreeNode findSubTree1(TreeNode root) {
		helper(root);
		return subtree;
	}

	private int helper(TreeNode root) {
		// TODO Auto-generated method stub
		if(root == null) {
			return 0;
		}
		
		int sum=helper(root.left)+ helper(root.right)+root.val;
		
		if( sum < mini) {
			sum= mini;
			subtree=root;
		}
		
		return sum;
	}
}
