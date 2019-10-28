import java.util.*;
public class ZigZag {
	

	/*
	 * 185. Matrix Zigzag Traversal 中文English Given a matrix of m x n elements (m
	 * rows, n columns), return all elements of the matrix in ZigZag-order.
	 * 
	 * Example Example 1: Input: [[1]] Output: [1]
	 * 
	 * Example 2: Input: [ [1, 2, 3, 4], [5, 6, 7, 8], [9,10, 11, 12] ]
	 * 
	 * Output: [1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12]
	 */
	public class Solution {
	    /**
	     * @param matrix: An array of integers
	     * @return: An array of integers
	     */
	    HashMap<Integer, ArrayList<Integer>> map=new HashMap<>();
	    public int[] printZMatrix(int[][] matrix) {
	        // write your code here
	        if(matrix == null || matrix.length ==0 || matrix[0].length == 0){
	            int[] res=new int[]{};
	            return res;
	        }
	        int level=0;
	        int len=matrix.length+matrix[0].length;
	        for(int i=0;i<len;i++){
	            map.put(i,new ArrayList<Integer>());
	        }
	        for(level=0; level<=len;level++){
	            for(int i=0; i<matrix.length;i++){
	                for(int j=0; j<matrix[0].length;j++){
	                    if((i+j)== level){
	                        map.get(level).add(matrix[i][j]);
	                    }
	                }
	            }
	        }
	        int size=matrix.length*matrix[0].length;
	        int[] res=new int[size];
	        int index=0;
	        for(int i=0; i<len; i++){
	            if(i % 2 == 1){
	                for(int ele: map.get(i)){
	                    res[index]=ele;
	                    index++;
	                }
	            }else{
	                for(int j=map.get(i).size()-1; j>=0;j--){
	                    res[index]=map.get(i).get(j);
	                    index++;
	                }
	            }
	        }
	        return res;
	    }
	}
	
	  public class TreeNode {
		     int val;
		     TreeNode left;
		     TreeNode right;
		     TreeNode(int x) { val = x; }
	}
	  
	/*
	 * 103. Binary Tree Zigzag Level Order Traversal Medium
	 * 
	 * 844
	 * 
	 * 55
	 * 
	 * Favorite
	 * 
	 * Share Given a binary tree, return the zigzag level order traversal of its
	 * nodes' values. (ie, from left to right, then right to left for the next level
	 * and alternate between).
	 * 
	 * For example: Given binary tree [3,9,20,null,null,15,7], 3 / \ 9 20 / \ 15 7
	 * return its zigzag level order traversal as: [ [3], [20,9], [15,7] ]
	 */
	
	public class Solution2 {
	    List<List<Integer>> res=new ArrayList<List<Integer>>();
	    ArrayList<ArrayList<Integer>> map=new ArrayList<ArrayList<Integer>>();
	    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	        if(root == null) return res;
	        Queue<TreeNode> queue=new LinkedList<TreeNode>();
	        queue.add(root);
	        while(!queue.isEmpty()){
	            int size=queue.size();
	            ArrayList<Integer> lst=new ArrayList<Integer>();
	            for(int i=0; i<size; i++){
	                TreeNode curr=queue.poll();
	                if(curr != null){
	                    lst.add(curr.val);
	                     queue.add(curr.left);
	                     queue.add(curr.right);
	                }
	            }
	            if(!lst.isEmpty()){
	                map.add(lst);
	            }
	        }
	        for(int i=0; i<map.size(); i++){
	            ArrayList<Integer> lst=map.get(i);
	            if(i % 2 ==0){
	                res.add(lst);
	            }else{
	                ArrayList<Integer> reverse=new ArrayList<Integer>();
	                for(int j=lst.size()-1; j>=0; j--){
	                    reverse.add(lst.get(j));
	                }
	                res.add(reverse);
	            }
	        }
	        return res;
	    }
	}
}
