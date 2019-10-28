import java.util.ArrayList;
import java.util.List;

//
//480. Binary Tree Paths
//中文English
//Given a binary tree, return all root-to-leaf paths.
//
//Example
//Example 1:
//
//Input：{1,2,3,#,5}
//Output：["1->2->5","1->3"]
//Explanation：
//   1
// /   \
//2     3
// \
//  5
//Example 2:
//
//Input：{1,2}
//Output：["1->2"]
//Explanation：
//   1
// /   
//2     


public class BinaryTreePath {
	public class TreeNode {
		   public int val;
		   public TreeNode left, right;
		   public TreeNode(int val) {
		      this.val = val;
			  this.left = this.right = null;
			}
		}
	
    public List<String> lst=new ArrayList<String>();
    
    public List<String> binaryTreePaths(TreeNode root) {
        // write your code here
        if(root == null) {
			return lst;
		}
        dfsSearch(root, "");
        return lst;
    }
    
    private void dfsSearch(TreeNode root, String str) {
		if(root == null) {
			return;
		}
		
	    str=str.concat(Integer.toString(root.val));
		
		if(root.left == null && root.right ==null){
		    lst.add(str);
		}else{
		    str=str.concat("->");
		    if(root.left !=null) {
			    dfsSearch(root.left, str);
		    }
		
		    if(root.right != null) {
			    dfsSearch(root.right, str);
		    }
		}
		
	}
}
