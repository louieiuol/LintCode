/*597. Subtree with Maximum Average
Given a binary tree, find the subtree with maximum average. Return the root of the subtree.

Example
Example 1

Input：
     1
   /   \
 -5     11
 / \   /  \
1   2 4    -2 
Output：11(it's a TreeNode)
Example 2

Input：
     1
   /   \
 -5     11
Output：11(it's a TreeNode)
Notice
LintCode will print the subtree which root is your return node.
It's guaranteed that there is only one subtree with maximum average.
*/
public class SubtreeWithMax {
	  public class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		     }
		  }
    private TreeNode res=null;
    private double max=-1000;
    
    public TreeNode findSubtree2(TreeNode root) {
        // write your code here
        if(root==null) return res;
        findHelper(root);
        return res;
    }
    private int findHelper(TreeNode root){
        if(root==null) return 0;
        int sum=findHelper(root.left)+findHelper(root.right)+root.val;
        double size=findSize(root);
        double average=(sum/size);
        if(average>max){
            max=average;
            res=root;
        }
        return sum;
    }
    private int findSize(TreeNode root){
        if(root==null) return 0;
        else return 1+findSize(root.left)+findSize(root.right);
    }
	public class ResultType{
		int sum;
		int size;
		public ResultType(int sum, int size){
			this.sum=sum;
			this.size=size;
		}
	}
	TreeNode result=null;
	ResultType maxresult=null;

	public TreeNode findSubtree(TreeNode root) {
        // write your code here
		helper(root);
		return result;
    }
    private ResultType helper(TreeNode root){
    	if(root == null) return new ResultType(0,0);
    	ResultType resultLeft=helper(root.left);
    	ResultType resultRight=helper(root.right);
    	ResultType curr=new ResultType(
    		resultLeft.sum+resultRight.sum+root.val,
    		resultLeft.size+resultRight.size+1
    	);
    	if(result== null || curr.sum * maxresult.size > maxresult.sum * curr.size){
    		result=root;
    		maxresult=curr;
    	}
    	return curr;
    }
}

