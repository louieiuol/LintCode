//651. Binary Tree Vertical Order Traversal
//中文English
//Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
//
//If two nodes are in the same row and column, the order should be from left to right.
//
//Example
//Example1
//
//Inpurt:  {3,9,20,#,#,15,7}
//Output: [[9],[3,15],[20],[7]]
//Explanation:
//   3
//  /\
// /  \
// 9  20
//    /\
//   /  \
//  15   7
//Example2
//
//Input: {3,9,8,4,0,1,7}
//Output: [[4],[9],[3,0,1],[8],[7]]
//Explanation:
//     3
//    /\
//   /  \
//   9   8
//  /\  /\
// /  \/  \
// 4  01   7



import java.util.*;
public class BinaryTreeVerticalOrderTraversal {
	public class TreeNode {
		public int val;
		public TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
	
	class LevelNode{
		TreeNode node;
		int level;
		public LevelNode(TreeNode node, int level) {
			this.node=node;
			this.level=level;
		}
	}
	List<List<Integer>> res=new ArrayList<>();
	public List<List<Integer>> verticalOrder(TreeNode root) {
		if(root == null) return res;
		HashMap<Integer, ArrayList<Integer>> map=new HashMap<>();
		//建立坐标 node list的图 来储存最终结果 
		bfs(map, root);
		ArrayList<Integer> levelList=new ArrayList<>();
		levelList.addAll(map.keySet());
		//题目因为要按照从左到右的同层顺序加入 所以是需要用到BFS
		Collections.sort(levelList);
		//对key进行排序然后全部加入结果
		for(int ele: levelList) {
			res.add(map.get(ele));
		}
		return res;
	}
	

	private void bfs(HashMap<Integer, ArrayList<Integer>> map, TreeNode root) {
		Queue<LevelNode> queue=new LinkedList<>();
		queue.offer(new LevelNode(root, 0));
		while(queue.isEmpty()) {
			LevelNode curr=queue.poll();
			//如果第一次出现该坐标 创建新的list 
			map.putIfAbsent(curr.level, new ArrayList<Integer>());
			map.get(curr.level).add(curr.node.val);
			if(curr.node.left!=null) {
				//左边点是当前点的坐标-1 右边点的坐标是当前点+1 把新的 LevelNode 放入queue
				queue.offer(new LevelNode(curr.node.left, curr.level-1));
				queue.offer(new LevelNode(curr.node.right, curr.level+1));
			}
		}
	}
}
