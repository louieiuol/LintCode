import java.util.ArrayList;
import java.util.List;

//376. Binary Tree Path Sum
//中文English
//Given a binary tree, find all paths that sum of the nodes in the path equals to a given number target.
//
//A valid path is from root node to any of the leaf nodes.
//
//Example
//Example 1:
//
//Input:
//{1,2,4,2,3}
//5
//Output: [[1, 2, 2],[1, 4]]
//Explanation:
//The tree is look like this:
//	     1
//	    / \
//	   2   4
//	  / \
//	 2   3
//For sum = 5 , it is obviously 1 + 2 + 2 = 1 + 4 = 5
//Example 2:
//
//Input:
//{1,2,4,2,3}
//3
//Output: []
//Explanation:
//The tree is look like this:
//	     1
//	    / \
//	   2   4
//	  / \
//	 2   3
//Notice we need to find all paths from root node to leaf nodes.
//1 + 2 + 2 = 5, 1 + 2 + 3 = 6, 1 + 4 = 5 
//There is no one satisfying it.
//{1,2,4,2,3}
//

public class BinaryTreePathSum {
	public class TreeNode {
		   public int val;
		   public TreeNode left, right;
		   public TreeNode(int val) {
		      this.val = val;
			  this.left = this.right = null;
			}
		}
	//因为结果是个2重列表 在外部定义结果列表比较好做
    List<List<Integer>> res=new ArrayList<>();
    //结果是返回一个2重list的题目一定要记得deep copy不要在原来的list上进行操作
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // write your code here
        if(root == null) return res;
        dfs(root, target, new ArrayList<Integer>());
        return res;
    }
    
    //因为是沿路径加入结果 所以得先分析后循环 不可以分治
    private void dfs(TreeNode root, int target, ArrayList<Integer> lst){
        if(root == null){
            return;
        }
        //base case 1: 为空返回
        if(root.left==null && root.right==null){
            if(root.val == target){
                ArrayList<Integer> next=new ArrayList<>(lst);
                next.add(root.val);
                //这里要先deep copy一遍list 而不能在传入的list上添加 因为其他的递归可能用到同一个list 影响其他的结果
                res.add(next);
            }    
            return;
        }
        //base case 2:对于leaf需要判断的问题都需要一个对leaf node处理的base case 
        ArrayList<Integer> nextlst=new ArrayList<>(lst);
        nextlst.add(root.val);
        //当每走到一个node时候 分成两种分支去找 所以一定要deep copy之前的list 否则会互相影响 
        dfs(root.left, target-root.val, nextlst);
        dfs(root.right, target-root.val, nextlst);
    }
}
