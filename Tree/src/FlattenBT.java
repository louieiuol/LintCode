//453. Flatten Binary Tree to Linked List
//中文English
//Flatten a binary tree to a fake "linked list" in pre-order traversal.
//
//Here we use the right pointer in TreeNode as the next pointer in ListNode.
//
//Example
//Example 1:
//
//Input:{1,2,5,3,4,#,6}
//Output：{1,#,2,#,3,#,4,#,5,#,6}
//Explanation：
//     1
//    / \
//   2   5
//  / \   \
// 3   4   6
//
//1
//\
// 2
//  \
//   3
//    \
//     4
//      \
//       5
//        \
//         6
//Example 2:
//
//Input:{1}
//Output:{1}
//Explanation：
//         1
//         1
//Challenge
//Do it in-place without any extra memory.
//
//Notice
//Don't forget to mark the left child of each node to null. Or you will get Time Limit Exceeded or Memory Limit Exceeded.
//



public class FlattenBT {
	public class TreeNode {
		   public int val;
		   public TreeNode left, right;
		   public TreeNode(int val) {
		      this.val = val;
			  this.left = this.right = null;
			}
		}
	
	public void flatten(TreeNode root) {
		helper(root);
	}

	private TreeNode helper(TreeNode root) {
		if(root == null) {
			return null;
		}
		
		TreeNode leftLast=helper(root.left);
		TreeNode rightLast=helper(root.right);
		
		if(leftLast != null) {
			leftLast.right=root.right;
			//make left most node's right as parent's right node
			root.right=root.left;
			//move left most node from parent left side to parent's right side
			root.left=null;
			//make parent left side points empty
		}
		//返回顺序 右边 -> 左边 -> 中间
		if(rightLast!=null) {
			return rightLast;
			//   2          2     root last return 
			//  / \    -->   \ 
			// 3   4          3   left second return 
    //      left   right       \ 
			//                  4 right first return 
			//pass right most node first
		}
		
		if(leftLast!= null ) {
			return leftLast;
			//pass left most node
		}
		
		return root;
	}
	
	
	
	
}
