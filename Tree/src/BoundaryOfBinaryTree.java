//878. Boundary of Binary Tree
//中文English
//Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
//
//Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.
//
//The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.
//
//The right-most node is also defined by the same way with left and right exchanged.
//
//Example
//Example 1:
//
//Input: {1,#,2,3,4}
//Output: [1,3,4,2]
//Explanation: 
//  1
//   \
//    2
//   / \
//  3   4
//  The root doesn't have left subtree, so the root itself is left boundary.
//  The leaves are node 3 and 4.
//  The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
//  So order them in anti-clockwise without duplicates and we have [1,3,4,2].
//Example 2:
//
//Input: {1,2,3,4,5,6,#,#,#,7,8,9,10}
//Output: [1,2,4,7,8,9,10,6,3]
//Explanation: 
//          1
//     /          \
//    2            3
//   / \          / 
//  4   5        6   
//     / \      / \
//    7   8    9  10  
//  The left boundary are node 1,2,4. (4 is the left-most node according to definition)
//  The leaves are node 4,7,8,9,10.
//  The right boundary are node 1,3,6,10. (10 is the right-most node).
//  So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].

import java.util.*;  
public class BoundaryOfBinaryTree {
	  public class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		      }
		  }
	List<Integer> res=new ArrayList<>();
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        // write your code here
    	if(root == null) {
    		return res;
    	}
    	//先把root加入结果 而不是在后面三个中加入 因为我们走某一边的时候不希望出现空的时候跑到另一边去
    	res.add(root.val);
    	if(root.left == null && root.right == null) {
    		return res;
    		//如果只有root 直接返回
    	}
    	//因为题目说是逆时针加入 正好是个三角形区域 我们就把加入的地方分位三步
    	addLeft(root.left);
    	addChildren(root);
    	addRight(root.right);
    	return res;
    }
    
    private void addLeft(TreeNode root) {
    	if(root == null || (root.left == null) && (root.right == null)) {
    		//为空返回 如果root左右两边都为空 证明是leaf 我们leaf单独加入所以遇到leaf 直接返回
    		return;
    	}
    	//一层层沿着路径加入 先加入在向下找
    	res.add(root.val);
    	if(root.left!=null) {
    		//左边有点就向左走 没有点在考虑右边走 这样能保证永远是最左边的边被访问到
    		addLeft(root.left);
    	}else {
    		addLeft(root.right);
    	}
    }
    
    private void addChildren(TreeNode root) {
    	if(root == null) {
    		return;
    	}
    	//如果root左右为空 加入结果 
    	if(root.left == null && root.right == null) {
    		res.add(root.val);
    	}
    	//按先左后右的顺序加入 
    	addChildren(root.left);
    	addChildren(root.right);
    }
    
    private void addRight(TreeNode root) {
    	if(root == null || (root.left == null && root.right == null)) {
    		return;
    	}
    	//因为是逆时针 我们要先走到底部再向上走 先递归后添加
    	if(root.right!=null) {
    		addRight(root.right);
    	}else {
    		addRight(root.left);
    	}
    	res.add(root.val);
    }
}
