

public class Tree {
	public class BinaryTree{
		Node root; 
		int  size;
		
		public BinaryTree() {
			this.root=null;
			this.size=0;
		}
		
		public void add(int value) {
			Node newNode=new Node(value);
			if(this.root !=null) {
				addHelper(this.root, value);
			}else {
				this.root=newNode;
				this.size=0;
			}
			this.size++;
			return;
		}
		
		public class Node{
			int value;
			Node left;
		    Node right;
			public Node() {
				this.value=0;
				this.left=null;
				this.right=null;
			}
			public Node(int value) {
				this.value=value;
				this.left= null;
				this.right=null;
			}
		}
		public void addHelper(Node root, int value) {
			// TODO Auto-generated method stub
			Node newNode=new Node(value);
			if(root==null) {
				root=newNode;
				return;
			}
			
			if(root.left== null) {
				root.left=newNode;
				return;
			}
			
			if(root.right == null) {
				root.right=newNode;
				return;
			}
			addHelper(root.left, value);
			return;
		}
		
		
	}
	
	public int calculateSize(BinaryTree tree) {
		if(tree.root == null) {
			return 0;
		}
		return calculateHelper(tree.root);
	}
	
	public int calculateHeight(BinaryTree tree) {
		if(tree.root !=null) {
			return heightHelper(tree.root, 0);
		}else {
			return 0;
		}
	}
	
	public void preorderTraversal(BinaryTree tree) {
		if(tree.root!=null) {
			preOrder(tree.root);
		}
	}
	
	private void preOrder(BinaryTree.Node root) {
		// TODO Auto-generated method stub
		if(root!=null) {
			System.out.println(root.value);
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	
	public void postorderTraversal(BinaryTree tree) {
		if(tree.root!=null) {
			postOrder(tree.root);
		}
	}

	private void postOrder(BinaryTree.Node root) {
		// TODO Auto-generated method stub
		if(root!=null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.println(root.value);
		}
	}
	
	public void inorderTraversal(BinaryTree tree) {
		if(tree.root!=null) {
			inOrder(tree.root);
		}
	}

	private void inOrder(BinaryTree.Node root) {
		// TODO Auto-generated method stub
		if(root!=null) {
			inOrder(root.left);
			System.out.println(root.value);
			inOrder(root.right);
		}
	}

	private int heightHelper(BinaryTree.Node root, int height) {
		// TODO Auto-generated method stub
		if(root == null) {
			return height;
		}
		
		int leftHeight=heightHelper(root.left, height+1);
		int rightHeight=heightHelper(root.right, height+1);
		
		if(leftHeight>rightHeight) {
			return leftHeight;
		}else {
			return rightHeight;
		}
		
	}



	private int calculateHelper(BinaryTree.Node root) {
		// TODO Auto-generated method stub
		if(root == null) {
			return 0;
		}
		return 1+calculateHelper(root.left)+calculateHelper(root.right);
	}
	
}
