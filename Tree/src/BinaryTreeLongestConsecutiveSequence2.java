//614. Binary Tree Longest Consecutive Sequence II
//中文English
//Given a binary tree, find the length(number of nodes) of the longest consecutive sequence(Monotonic and adjacent node values differ by 1) path.
//The path could be start and end at any node in the tree
//
//Example
//Example 1:
//
//Input:
//{1,2,0,3}
//Output:
//4
//Explanation:
//    1
//   / \
//  2   0
// /
//3
//0-1-2-3
//Example 2:
//
//Input:
//{3,2,2}
//Output:
//2
//Explanation:
//    3
//   / \
//  2   2
//2-3

public class BinaryTreeLongestConsecutiveSequence2 {
	  public class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		      }
	  }
	  
	  //这题是可以从某个node出发到另一个node的连续序列就可以 所以我们需要记录对于每层的左右两边的最高值和最低值
	  //同时记录最大长度 因为有3个需要返回的记录项 我们建立ResultType
	  
	  class ResultType{
		  int len;
		  int max;
		  int min;
		  public ResultType(int len, int max, int min) {
			  this.len=len;
			  this.max=max;
			  this.min=min;
		  }
	  }
	  public int longestConsecutive2(TreeNode root) {
		  ResultType res=dfs(root); 
		  //把ResultType记录成3个返回值的集合 
		  return res.len; 
		  //因为每层都记录了其所在的3种属性 那么自然最顶层的root就包含了该树最大值
	  }
	private ResultType dfs(TreeNode root) {
		// TODO Auto-generated method stub
		if(root == null) return new ResultType(0,0,0); 
		//如果是空 返回每个是0的新ResultType
		
		ResultType leftResult=dfs(root.left);
		ResultType rightResult=dfs(root.right);
		//分治法 先让每个点去走左边右边 最后分层处理 （自底向上的解法）
		
		int up=0, down=0;
		//记录每层内能走到的最大的最远距离和最小的最远距离
		if(root.left!=null && root.left.val +1 == root.val) {
			//左边值不为空 并且 如果左边node的值比root正好小1
			down=Math.max(down, leftResult.min+1);
			//当前层的最小的最远距离就是 取最大的 当前的值 和左边结果的最小最远距离+1的值（加上当前node）
		}
		
		if(root.left!=null && root.left.val -1 == root.val) {
			//左边值不为空 并且 如果左边node的值比root正好大1
			up=Math.max(up, leftResult.max+1);
			//当前层的最大的最远距离就是 取最大的 当前的值 和左边结果的最大最远距离+1的值 （加上当前node）
		}
		
		if(root.right != null && root.right.val +1 == root.val) {
			//右边值不为空 并且 如果右边node的值比root正好小1
			down=Math.max(down, rightResult.min+1);
			//当前层的最小的最远距离就是 取最大的 当前的值 和右边结果的最小最远距离+1的值（加上当前node）
		}
		
		if(root.right !=null && root.right.val -1 == root.val) {
			//右边值不为空 并且 如果右边node的值比root正好大1
			up=Math.max(up, rightResult.min+1);
			//当前层的最大的最远距离就是 取最大的 当前的值 和右边结果的最大最远距离+1的值 （加上当前node）
		}
		//对每层的总长度进行更新
		int len=down+1+up; 
		//当前层的长度是最长的最大值加1加最长的最小值 
		len=Math.max(len, Math.max(leftResult.len, rightResult.len));
		//当前层的最大长度是 当前长度计算结果 左子树存在的最大长度 右子树存在的最长长度取最大值 
		return new ResultType(len, up, down);
		//把刚刚计算的 最大长度 最远的最大值 和最远的最小值 都放入新的ResultType 并返回给上一层
		}
}
