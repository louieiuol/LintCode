
public class Depth {
	private class Node{
		private int key;
		//store key value pairs
		private Node left;
		private Node right;
		//left and right reference
		private Node(int key) {
			this.key= key;
			this.left= null;
			this.right=null;
		}
	}
	private Node root;
	//store total number of keys in tree
	
	public Depth() {
		this.root=null;
	}
	
	public boolean insert(int key) {
			if(this.root==null) {
				//if root is empty create the new node and set as root
				Node newNode=new Node(key);
				this.root=newNode;
				return true;
			}else {
				//check if the tree contains the key
				if(isContain(this.root,key)) {
					return false;
				}
				
				//invoker helper method
				addHelper(this.root,key);
				//increase the total
				return true;
			}
	}
	
	private void addHelper(Node root, int key) {
		// compare to find where insert the node
		if(root.key > key ) {
			//insert at the left of the root
			if(root.left==null) {
				//insert at the null node of the current root
				Node newNode=new Node(key);
				root.left=newNode;
			}else {
				addHelper(root.left, key);
				//insert at the left children of the current root
			}
		}else {
			//insert at the right of the root
			if(root.right==null) {
				//insert at the null node of the current root
				Node newNode=new Node(key);
				root.right=newNode;
			}else {
				//insert at the right children of the current root
				addHelper(root.right,key);
			}
		}
	}
	private boolean isContain(Node root, int key) {
		//check if the file contains specific key
		if(root==null) {
			return false;
		}else {
			if(root.key == key) {
				return true;
			}else {
				return isContain(root.left,key) || isContain(root.right,key);
			}
		}
	}
	
	public int getDepth(int key) {
		if(root != null) {
			return getDepthHelper(root, key, 0);
		}else {
			return -1;
		}
	}

	private int getDepthHelper(Node root, int node, int depth) {
		if(root !=null) {
			if(root.key == node) {
				return depth;
			}else {
				int left=getDepthHelper(root.left, node, depth+1);
				int right=getDepthHelper(root.right, node ,depth+1);
				if(left == -1 && right == -1) {
					return -1;
				}else if (left != -1 && right == -1){
					return left;
				}else if( left == -1 && right != -1) {
					return right;
				}else {
					return -1;
				}
			}
		}else {
			return -1;
		}
	}
	
	
	public int getHeight(int key) {
		if(root !=null) {
			 return findKey(root ,key);
		}
		return -1;
	}

	private int findKey(Node root, int key) {
		// TODO Auto-generated method stub
		if(root != null) {
			if(root.key == key) {
				return calculateHeight(root);
			}else {
				findKey(root.left, key);
				findKey(root.right, key);
			}
		}
		return 0;
	}

	private int calculateHeight(Node node) {
		// TODO Auto-generated method stub
		if(node != null) {
			int left=calculateHeight(node.left);
			int right=calculateHeight(node.right);
			if(left > right) return left+1;
			else return right+1;
		}else {
			return -1;
		}
	}
}
