/*
1561. BST Node Distance
Given a list of numbers, construct a BST from it(you need to insert nodes one-by-one with the given order to get the BST) and find the distance between two given nodes.

Example
input:
numbers = [2,1,3]
node1 = 1
node2 = 3
output:
2
Notice
If two nodes do not appear in the BST, return -1
We guarantee that there are no duplicate nodes in BST
The node distance means the number of edges between two nodes
*/
public class BSTNodeDistance {
	    /**
	     * @param numbers: the given list
	     * @param node1: the given node1
	     * @param node2: the given node2
	     * @return: the distance between two nodes
	     */
	    public class TreeNode{
	        int val;
	        TreeNode left;
	        TreeNode right;
	        public TreeNode(int val){
	            this.val=val;
	            this.left=null;
	            this.right=null;
	        }
	        TreeNode root=null;
	        
	        private TreeNode insert(int number, TreeNode root){
	            if(root == null){
	                root=new TreeNode(number);
	                return root;
	            }
	            if(root.val > number){
	                root.left=insert(number, root.left);
	            }else{
	                root.right=insert(number, root.right);
	            }
	            return root;
	        }
	        
	        private boolean check(TreeNode root, int target){
	            if(root == null) return false;
	            if(root.val > target){
	                return check(root.left, target);
	            }else if(root.val < target){
	                return check(root.right, target);
	            }else{
	                return true;
	            }
	        }
	        
	        public int bstDistance(int[] numbers, int node1, int node2) {
	            // Write your code here
	            if(numbers == null || numbers.length == 0) return -1;
	            root=new TreeNode(numbers[0]);
	            for(int i=1; i<numbers.length; i++){
	                insert(numbers[i], root);
	            }
	            if(!check(root, node1) || !check(root, node2)) return -1;
	            return findDistance(root, node1, node2);
	        }
	        
	        private int findDistance(TreeNode root, int target1, int target2){
	            if(root == null){
	                return 0;
	            }else if(root.val < target1 && root.val < target2){
	                return findDistance(root.right, target1, target2);
	            }else if(root.val > target1 && root.val > target2){
	                return findDistance(root.left, target1, target2);
	            }else{
	                return findDistanceHelper(root, target1, 0)+findDistanceHelper(root, target2, 0);
	            }
	        }
	        
	        private int findDistanceHelper(TreeNode root, int target, int counter){
	            if(root == null){
	                return 0;
	            }else if(root.val == target){
	                return counter;
	            }else if(root.val < target){
	                return findDistanceHelper(root.right, target, counter+1);
	            }else{
	                return findDistanceHelper(root.left, target, counter+1);
	            }
	        } 
	    }
}
