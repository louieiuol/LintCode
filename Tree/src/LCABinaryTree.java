

public class LCABinaryTree {
	
	public class TreeNode {
		   public int val;
		   public TreeNode left, right;
		   public TreeNode(int val) {
		      this.val = val;
			  this.left = this.right = null;
			}
		}
	
	public class ResultType {
		boolean hasA;
		boolean hasB;
		boolean isFound;
		TreeNode lca;
		public ResultType(boolean hasA, boolean hasB, boolean isFound, TreeNode lca) {
			this.hasA=hasA;
			this.hasB=hasB;
			this.isFound=isFound;
			this.lca=lca;
		}
	}
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
		if(root == null) {
			return null;
		}
		
		if(A == B) {
			return A;
		}
		
		ResultType result=helper(root, A, B);
		
		if(result.isFound) {
			return result.lca;
		}
		
		return null;
	}

	private ResultType helper(TreeNode root, TreeNode a, TreeNode b) {
		ResultType result=new ResultType(false, false, false, null);
		if(root == null) {
			return result;
		}
		
		ResultType resultLeft= helper(root.left, a, b);
		ResultType resultRight= helper(root.right, a, b);
		
		if(resultLeft.isFound) {
			result.isFound=true;
			result.lca=resultLeft.lca;
			return result;
		}
		
		if(resultRight.isFound) {
			result.isFound=true;
			result.lca=resultRight.lca;
			return result;
		}
		
		if(resultLeft.hasA || resultRight.hasA || root.val == a.val) {
			result.hasA=true;
		}
		
		if(resultLeft.hasB || resultRight.hasB || root.val == b.val) {
			result.hasB=true;
		}
		
		if(result.hasA && result.hasB) {
			result.lca=root;
			result.isFound=true;
		}
		
		return result;
	}
}
