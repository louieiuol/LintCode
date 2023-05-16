import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//375. Clone Binary Tree
//中文English
//For the given binary tree, return a deep copy of it.
//
//Example
//Example 1:
//
//Input: {1,2,3,4,5}
//Output: {1,2,3,4,5}
//Explanation:
//The binary tree is look like this:
//     1
//   /  \
//  2    3
// / \
//4   5
//Example 2:
//
//Input: {1,2,3}
//Output: {1,2,3}
//Explanation:
//The binary tree is look like this:
//   1
// /  \
//2    3
//Notice
//You'd better not just return root to solve the problem.

public class CloneTree {

    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public TreeNode cloneTree(TreeNode root) {
        TreeNode newroot= null;
        if(root!=null) {
            HashMap<TreeNode, TreeNode> map=new HashMap<TreeNode, TreeNode>();
            //create a hash map for storing relations in old tree and new tree
            cloneNodes(root,map);
            cloneRelations(map);
            newroot=map.get(root);
        }
        return newroot;
    }

    private void cloneRelations(HashMap<TreeNode, TreeNode> map) {
        for(TreeNode key: map.keySet()) {
            map.get(key).left=key.left;
            map.get(key).right=key.right;
        }
    }

    private void cloneNodes(TreeNode root, HashMap<TreeNode, TreeNode> map) {
        // TODO Auto-generated method stub
        Queue<TreeNode> queue=new LinkedList<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size=queue.size();
            for(int i=0; i<size; i++) {
                TreeNode node=queue.poll();
                if(node!=null) {
                    TreeNode newnode=new TreeNode(node.val);
                    map.put(node, newnode);
                    if(node.left!=null ) {
                        queue.add(node.left);
                    }
                    if(node.right!=null) {
                        queue.add(node.right);
                    }
                }
            }
        }
    }


}
