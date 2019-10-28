

public class ChildFirstThenParent {
	public class TreeNode {
		public int val;
		public TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
	public class Tree{
		TreeNode root;
		public Tree() {
			this.root=null;
		}
		public void insert(int[] arr) {
			this.root=insertHelper(arr, 0);
		}
		private TreeNode insertHelper(int[] arr, int i) {
	        if(i < arr.length){
	            TreeNode root = new TreeNode(arr[i]);
	            root.left = insertHelper(arr,2*i + 1);
	            root.right = insertHelper(arr,2*i + 2);
	            return root;
	        }
	        return null;
			
		}
		public void printTree() {
			printTreeHelper(this.root);
		}
		private void printTreeHelper(TreeNode root) {
			if(root == null) return;
			System.out.println(root.val);
			printTreeHelper(root.left);
			printTreeHelper(root.right);
		}
	}
	public void printChildThenParent(TreeNode root) {
		printAllChild(root);
		printAllParent(root);
	}
	private void printAllParent(TreeNode root) {
		if(root == null) return;
		else if(root.left==null && root.right==null) {
			return;
		}
		printAllParent(root.left);
		printAllParent(root.right);
		System.out.println("Parent is "+root.val);
		
		
	}
	private void printAllChild(TreeNode root) {
		if(root==null) return;
		else if(root.left!=null && root.right==null) printAllChild(root.left);
		else if(root.right!=null && root.left==null) printAllChild(root.right);
		else if(root.left!=null && root.right!=null) {
			printAllChild(root.left);
			printAllChild(root.right);
		}else {
			System.out.println("Child is "+root.val);
		}
	}
}
