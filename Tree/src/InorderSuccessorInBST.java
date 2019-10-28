//448. Inorder Successor in BST
//中文English
//Given a binary search tree (See Definition) and a node in it, find the in-order successor of that node in the BST.
//
//If the given node has no in-order successor in the tree, return null.
//
//Example
//Example 1:
//
//Input: {1,#,2}, node with value 1
//Output: 2
//Explanation:
//  1
//   \
//    2
//Example 2:
//
//Input: {2,1,3}, node with value 1
//Output: 2
//Explanation: 
//    2
//   / \
//  1   3
//Binary Tree Representation
//
//Challenge
//O(h), where h is the height of the BST.
//
//Notice
//It's guaranteed p is one node in the given tree. (You can directly compare the memory address to find p)
//



public class InorderSuccessorInBST {
	 public class TreeNode {
		     int val;
		     TreeNode left;
		     TreeNode right;
		     TreeNode(int x) { val = x; }
	}
	//分治法 可以由大问题分成小问题 
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    	if(root == null || p == null) {
    		return null;
    	}
    	//base case root为空返回空 一般情况 额外加上p也不能为空 否则返回空
    	if(root.val <= p.val) {
    		//如果 root值小于或等于目标值 那么说明目标值的继承者比root肯定要大的
    		TreeNode rightResult=inorderSuccessor(root.right, p);
    		//分治法在右边寻找 因为找不到返回空所以不需要进行分类讨论了
    		return rightResult;
    	}else {
    		TreeNode leftResult=inorderSuccessor(root.left, p);
    		//如果root值大于目标值 有两种可能 左节点能找到 肯定继承者是左边节点找到的
    		//为什么呢？因为root大于目标值 左节点也有大于目标值的 而左节点找到的比root小 所以左节点找到的肯定是最近的
    		//分治法向左去找目标值 对找到的结果进行讨论
    		if(leftResult != null) {
    			return leftResult;
    		}else {
    			//找不到一定是root
    			return root;
    		}
    	}
    }
}
