import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//
//69. Binary Tree Level Order Traversal
//中文English
//Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
//
//Example
//Example 1:
//
//Input：{1,2,3}
//Output：[[1],[2,3]]
//Explanation：
//  1
// / \
//2   3
//it will be serialized {1,2,3}
//level order traversal
//Example 2:
//
//Input：{1,#,2,3}
//Output：[[1],[2],[3]]
//Explanation：
//1
// \
//  2
// /
//3
//it will be serialized {1,#,2,3}
//level order traversal

public class BFSordertravel {
    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> result=new ArrayList<List<Integer>>();
        if(root!=null){
            Queue<TreeNode> queue=new LinkedList<TreeNode>();
            queue.add(root);
            while(!queue.isEmpty()){
                ArrayList<Integer> lst=new ArrayList<Integer>();
                int size=queue.size();
                for(int i=0; i<size; i++){
                    TreeNode node=queue.poll();
                    lst.add(node.val);
                    if(node.left!=null){
                        queue.add(node.left);
                    }
                    if(node.right!=null){
                        queue.add(node.right);
                    }
                }
                result.add(lst);
            }
        }
        return result;
    }
}


