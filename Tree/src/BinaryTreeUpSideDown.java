//649. Binary Tree Upside Down
//中文English
//Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
//
//Example
//Example1
//
//Input: {1,2,3,4,5}
//Output: {4,5,2,#,#,3,1}
//Explanation:
//The input is
//    1
//   / \
//  2   3
// / \
//4   5
//and the output is
//   4
//  / \
// 5   2
//    / \
//   3   1
//Example2
//
//Input: {1,2,3,4}
//Output: {4,#,2,3,1}
//Explanation:
//The input is
//    1
//   / \
//  2   3
// /
//4
//and the output is
//   4
//    \
//     2
//    / \
//   3   1



public class BinaryTreeUpSideDown {
	  public class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		      }
		  }
	
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		if(root == null) {
			return null;
		}
		//需要事先判断是否为空，因为内循环直接判断左值是否为空
		return dfs(root);
	}
	
	//dfs直接返回最左边的node作为新root
	private TreeNode dfs(TreeNode root) {
		if(root == null) {
			return root;
			//碰到底部没有left的root就返回
		}
		if(root.left == null && root.right == null) {
			return root;
		}
		//关于有leaf节点的讨论时一定要增加对leaf的判断
		TreeNode leftResult=dfs(root.left);
		//分治法先走到底部 在一层层向上处理
		root.left.left=root.right;
		root.left.right=root;
		//这里不要用leftResult 去修改 leftResult作为pointer 指向该点 还要向上返回pointer层层处理
		//我们这里直接改root root不再使用了
		//root的左边就是当前点 target点左边指向root自己的右边
		//root的左边就是当前点 target点右边指向root自己
		root.left=null;
		root.right=null;
		//把root本身的 reference改为空 root被弃用 因为要保证最后一个node不指向别的 （会形成环）
		return leftResult;
		//把当前层的左结果向上逐层返回
	}
}
