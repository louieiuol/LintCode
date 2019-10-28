//902. Kth Smallest Element in a BST
//中文English
//Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
//
//Example
//Example 1:
//
//Input：{1,#,2},2
//Output：2
//Explanation：
//	1
//	 \
//	  2
//The second smallest element is 2.
//Example 2:
//
//Input：{2,1,3},1
//Output：1
//Explanation：
//  2
// / \
//1   3
//The first smallest element is 1.
//Challenge
//What if the BST is modified (insert/delete operations) often 
//and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
import java.util.*;
public class KthSmallestElementinBST {
	
	  public class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		      }
		  }
	public int kthSmallest1(TreeNode root, int k) {
        Map<TreeNode, Integer> numOfChildren = new HashMap<>();
        countNodes(root, numOfChildren);
        return quickSelectOnTree(root, k, numOfChildren);
	}
	
	private int countNodes(TreeNode root, Map<TreeNode, Integer> numOfChildren) {
        if (root == null) {
            return 0;
        }
        
        int left = countNodes(root.left, numOfChildren);
        int right = countNodes(root.right, numOfChildren);
        numOfChildren.put(root, left + right + 1);
        return left + right + 1;
    }
    
    private int quickSelectOnTree(TreeNode root, int k, Map<TreeNode, Integer> numOfChildren) {
        if (root == null) {
            return -1;
            //没有找到
        }
        
        int left = root.left == null ? 0 : numOfChildren.get(root.left);
        if (left >= k) {
            return quickSelectOnTree(root.left, k, numOfChildren);
        }
        if (left + 1 == k) {
            return root.val;
        }
        
        return quickSelectOnTree(root.right, k - left - 1, numOfChildren);
    }
	
	public int kthSmallest2(TreeNode root, int k) {
		if(root == null || k== 0) return -1;
		Stack<TreeNode> stack=new Stack<>();
		TreeNode node=root;
		while(node!=null) {
			stack.push(node);
			node=node.left;
		}
		for(int i=0; i<k-1; i++) {
			TreeNode curr=stack.pop();
			TreeNode right=curr.right;
			if(right!=null) {
				stack.push(right);
				right=right.left;
			}
		}
		return stack.peek().val;
	}
	
	
}
