//595. Binary Tree Longest Consecutive Sequence
//中文English
//Given a binary tree, find the length of the longest consecutive sequence path.
//
//The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
//The longest consecutive path need to be from parent to child (cannot be the reverse).
//
//Example
//Example 1:
//
//Input:
//   1
//    \
//     3
//    / \
//   2   4
//        \
//         5
//Output:3
//Explanation:
//Longest consecutive sequence path is 3-4-5, so return 3.
//Example 2:
//
//Input:
//   2
//    \
//     3
//    / 
//   2    
//  / 
// 1
//Output:2
//Explanation:
//Longest consecutive sequence path is 2-3,not 3-2-1, so return 2.


//这道题的特点是计算最长连续数列必须是从root开始 而不是任意node 而且必须从root 到leaf是递增的
public class BinaryTreeLongestConsecutiveSequence {
	public class TreeNode {
		public int val;
		public TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
	//思路是divide and conquer 
	//因为每次只有一条path 我们不需要额外建立 ResultType的多返回值类 我们用return的单一值即可
	int len=0;
	//创建常量记录最大值
	public int longestConsecutive(TreeNode root) {
		dfs(root);
		return len;
	}
	private int dfs(TreeNode root) {
		// TODO Auto-generated method stub
		if(root == null) return 0; //如果root为空 返回0
		int left=dfs(root.left);
		int right=dfs(root.right);
		//两边分开处理 让他们分别traverse到底部 （自底向上的解法）
		//逐层处理 
		int max=1;
		//记录当前层和下方的左右路径中的最长路径，因为当前层已经包含了root 那么至少为1
		if(root.left!=null && root.left.val-1 == root.val) {
			//对于左边不为空 左边值比root值正好大1的情况
			max=Math.max(max, left+1);
			//当前值则为左边的最大值+1 (加上当前值) 和当前值比较
		}
		if(root.right!=null && root.right.val-1 == root.val) {
			//对于右边不为空 右边值比root值正好大1的情况
			max=Math.max(max, right+1);
			//当前值则为右边的最大值+1 和当前值比较
		}
		len=Math.max(len, max);
		//当每层算完max后和len比较 更新最长的len值
		return max;
		//每次返回的是当前层和层下最长路径长度
	}
}
