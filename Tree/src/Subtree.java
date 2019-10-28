//245. Subtree
//中文English
//You have two very large binary trees: T1, with millions of nodes, and T2, with hundreds of nodes. Create an algorithm to decide if T2 is a subtree of T1.
//
//Example
//Example 1:
//
//Input：{1,2,3,#,#,4},{3,4}
//Output：true
//Explanation：
//T2 is a subtree of T1 in the following case:
//           1                3
//          / \              / 
//    T1 = 2   3      T2 =  4
//            /
//           4
//Example 2:
//
//Input：{1,2,3,#,#,4},{3,#,4}
//Output：false
//Explanation：
//T2 isn't a subtree of T1 in the following case:
//
//           1               3
//          / \               \
//    T1 = 2   3       T2 =    4
//            /
//           4
//Notice
//A tree T2 is a subtree of T1 if there exists a node n in T1 such that the subtree of n is identical to T2. That is, if you cut off the tree at node n, the two trees would be identical.

public class Subtree {
	  public class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		      }
		  }
	
	    public boolean isSubtree(TreeNode T1, TreeNode T2) {
	        // write your code here
	        if(T2 == null) return true;
	        if(T1 == null) return false;
	        return check(T1,T2) || isSubtree(T1.left, T2) || isSubtree(T1.right, T2);
	    }
	    
	    private boolean check(TreeNode T1, TreeNode T2){
	        if(T1 == null && T2 == null) return true;
	        if((T1 == null && T2 != null) || (T2 == null && T1 != null) )return false;
	        if(T1.val == T2.val){
	            return check(T1.left, T2.left) && check(T1.right, T2.right);
	        }else{
	            return false;
	        }
	    }
}
