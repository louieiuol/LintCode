//661. Convert BST to Greater Tree
//中文English
//Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
//
//Example
//Example 1:
//
//Input : {5,2,13}
//              5
//            /   \
//           2     13
//Output : {18,20,13}
//             18
//            /   \
//          20     13
//Example 2:
//
//Input : {5,3,15}
//              5
//            /   \
//           3     15
//Output : {20,23,15}
//             20
//            /   \
//          23     15



public class ConvertBSTtoGreaterTree {
	  public class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		      }
	  }
	int sum=0;
	//定义GOLBAL常量的方法 而不是作为参数传入 因为作为参数传入 dfs时用的还是原来传入的值
	public TreeNode convertBST(TreeNode root) {
		if(root == null) return root;
		dfs(root);
		return root;
	}
	
	private void dfs(TreeNode root) {
		//按照右中左的顺序遍历
		if(root == null) return;
		dfs(root.right);
		//累加并且更新sum
		sum+=root.val;
		root.val=sum;
		dfs(root.left);
	}
}
