/*
 * 95. Validate Binary Search Tree
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
A single node tree is a BST
Example
Example 1:
	Input:  For the following binary tree（only one node）:
	-1
	Output：true
	
Example 2:
	Input:  For the following binary tree:
	  2
	 / \
	1   4
	   / \
	  3   5
		
	Output: true
*/

//O(n)
//check using pre-order traversal logic, store last node as previous one,
//recursively checking through smaller value to greater value
public class ValidateBinarySearchTree {
	public class TreeNode {
		   public int val;
		   public TreeNode left, right;
		   public TreeNode(int val) {
		      this.val = val;
			  this.left = this.right = null;
			}
		}
	
        TreeNode lastNode=null;
        //store previous node 
        boolean isFirstNode=true;
        //check if the first Node
        public boolean isValidBST1(TreeNode root) {
            // write your code here
            if(root == null) return true;
            if(!isValidBST1(root.left)) return false;
            //check left side
            if(!isFirstNode && lastNode.val>=root.val) return false;
            //check the root value is satisfy greater than previous value
            isFirstNode=false;
            //change the flag
            lastNode=root;
            //set previous node to current root
            if(!isValidBST1(root.right)) return false;
            //check right side
            return true;
        }
        
//O(logn)
//divide and conquer 
//use self-define method result type store min and max node and isBST attribute
//similar to LCA problem, left max value should less than current node value,
//right min value should greater than current node value.
        
        private class ResultType{
        	boolean isBST;
        	TreeNode minNode;
        	TreeNode maxNode;
        	ResultType(boolean isBST){
        		this.isBST=isBST;
        		minNode=null;
        		maxNode=null;
        	}
        }
        public boolean isValidBST2(TreeNode root) {
        	return check(root).isBST;
        }
		private ResultType check(TreeNode root) {
			// TODO Auto-generated method stub
			ResultType curr=new ResultType(true);
			if(root == null) return curr;
			ResultType leftResult=check(root.left);
			ResultType rightResult=check(root.right);
			if(!leftResult.isBST || !rightResult.isBST) {
				curr.isBST=false;
				return curr;
			}
			//if left result or right result has already fails
			//pass fails message to above
			if(leftResult.maxNode!=null && leftResult.maxNode.val>=root.val) {
				curr.isBST=false;
				return curr;
			}
			//if left max node not null and left max node value greater than root node value  ----> false case
			if(rightResult.minNode!=null && rightResult.minNode.val<=root.val) {
				curr.isBST=false;
				return curr;
			}
			//if right min node not null and right min node value less than root node value  ----> false case
			curr.isBST=true;
			//current node checking is pass
			if(leftResult.minNode!= null) {
				curr.minNode=leftResult.minNode;
			}else {
				curr.minNode=root;
			}
			//curr node's min value is left side min value
			if(rightResult.maxNode!=null) {
				curr.maxNode=rightResult.maxNode;
			}else {
				curr.maxNode=root;
			}
			//curr node's max value is right side max value
			return curr;
		}
        
}
