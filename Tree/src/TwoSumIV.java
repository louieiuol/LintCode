//689. Two Sum IV - Input is a BST
//中文English
//Given a binary search tree and a number n, find two numbers in the tree that sums up to n.
//
//Example
//Example1
//
//Input: 
//{4,2,5,1,3}
//3
//Output: [1,2] (or [2,1])
//Explanation:
//binary search tree:
//    4
//   / \
//  2   5
// / \
//1   3
//Example2
//
//Input: 
//{4,2,5,1,3}
//5
//Output: [2,3] (or [3,2])
//Notice
//Without any extra space.


public class TwoSumIV {
	  public class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		      }
	}
	  
	int[] res=new int[2];
	TreeNode mark;
    public int[] twoSum(TreeNode root, int n) {
        // write your code here
        if(root == null) return null;
        dfs(root, n);
        return res;
    }
    
    private void dfs(TreeNode root, int n){
       if(root == null){
           return;
       }
       find(root, n-root.val, root, n);
       dfs(root.left, n);
       dfs(root.right, n);
    }
    
    private void find(TreeNode root, int target,TreeNode origin,  int n){
        if(root == null){
            return;
        }
        if(root.val == target){
            res[0]=target;
            res[1]=n-target;
            return;
        }else if(root.val < target){
            find(root.right, target, origin, n);
        }else{
            find(root.left, target, origin, n);
        }
    }
}
