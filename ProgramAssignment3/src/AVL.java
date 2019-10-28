import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class AVL {
	private class Node{
		private String key;
		private String value;
		//store key value pairs
		private Node left;
		private Node right;
		//left and right references
		private int height;
		//height stores node's height
		private int size;
		//size stores node children number
		private Node(String key, String value) {
			this.key= key;
			this.value=value;
			this.left= null;
			this.right=null;
			this.size=1;
			this.height=0;
		}
		//node constructor initialize key to the parameter 
		//initialize value to the parameter
		//initialize size as 1
		//initialize height as 0
	}

	Node root;
	int total;
	//store total number of keys in tree
	
	public AVL() {
		this.root=null;
		this.total=0;
	}
	
	//Initialize the root and total
	
	//same as insert in BST tree
	public boolean insert(String key, String value) {
		if(key!=null) {
			//if root is empty create the new node and set as root
			if(this.root==null) {
				Node newNode=new Node(key,value);
				this.root=newNode;
				this.total=1;
				return true;
			}else {
				//check if the tree contains the key
				if(isContain(this.root,key)) {
					return false;
				}
				//invoker helper method
				this.root=addHelper(this.root,key,value);
				//increase number and update all node's height and size
				this.total++;
				updateHeight(this.root);
				updateSize(this.root);
				return true;
			}
		}
		return false;
	}

	private Node addHelper(Node root, String key, String value) {
		if(root!=null) {
			//perform normal add process
			if(key.compareTo(root.key) <0 ) {
				root.left=addHelper(root.left, key, value);
			}else {
				root.right=addHelper(root.right, key ,value);
			}
			root.height = Maximum(getHeight(root.left), getHeight(root.right)) + 1;
			//update its height each add step
			root=rebalanceForInsert(root,key);
			//call re-balance function
		}else {
			root=new Node(key,value);
			//create a new node as root
		}
		return root;
	}

	private Node rebalanceForInsert(Node root, String key) {
		// TODO Auto-generated method stub
		if((getHeight(root.left)-getHeight(root.right)>1)) {
			if(key.compareTo(root.left.key)<0) {
				root=rotateRight(root);
			}else {
				root.left=rotateLeft(root.left);
				root=rotateRight(root);
			}
		}else if((getHeight(root.left)-getHeight(root.right))<-1){
			if(key.compareTo(root.right.key)>0) {
				root=rotateLeft(root);
			}else {
				root.right=rotateRight(root.right);
				root=rotateLeft(root);
			}
		}else {
			return root;
		}
		return root;
	}
	
	//return greater number in two numbers
	private int Maximum(int num1, int num2) {
		// TODO Auto-generated method stub
		return (num1 > num2) ? num1 : num2;
	}

	//get height of the node
	private int getHeight(Node root) {
		// TODO Auto-generated method stub
		if(root==null) {
			return -1;
		}
		//if tree is empty return -1
		return calculateHeight(root,-1);
		//call calculate height take -1 as initial value
	}

	private Node rotateLeft(Node root) {
		Node rightChild=root.right;
		root.right=rightChild.left;
		rightChild.left=root;
		root.height = Maximum(getHeight(root.left), getHeight(root.right)) + 1;
		rightChild.height = Maximum(getHeight(rightChild.left), getHeight(rightChild.right)) + 1;
		return rightChild;
	}

	private Node rotateRight(Node root) {
		// TODO Auto-generated method stub
		Node leftChild=root.left;
		root.left=leftChild.right;
		leftChild.right=root;
		root.height = Maximum(getHeight(root.left), getHeight(root.right)) + 1;
		leftChild.height = Maximum(getHeight(leftChild.left), getHeight(leftChild.right)) + 1;
		return leftChild;
	}

	
	public boolean isAVL() {
		 return maxDepth(this.root) != -1;
	}

	private int maxDepth(Node root) {
		// TODO Auto-generated method stub
	    if (root == null) {
           return 0;
       }

       int left = maxDepth(root.left);
       int right = maxDepth(root.right);
       if (left == -1 || right == -1 || Math.abs(left-right) > 1) {
           return -1;
       }
       return Math.max(left, right) + 1;
   }

	//same as update height in BST tree
	private void updateHeight(Node root) {
		//same as updateHeight in BST tree
		if(root!=null) {
			root.height=calculateHeight(root,-1);
			if(root.left!=null) {
				updateHeight(root.left);
			}
			if(root.right!=null) {
				updateHeight(root.right);
			}
		}
	}

	//same as update size in BST tree
	private void updateSize(Node root) {
		//same as updateSize in BST tree
		if(root!=null) {
			root.size=calculateSize(root);
			if(root.left!=null) {
				updateSize(root.left);
			}
			
			if(root.right!=null) {
				updateSize(root.right);
			}
		}
	}

	//same as lookUp in BST
	public String lookUp(String key) {
		if(isContain(this.root,key)) {
			return lookUpHelper(this.root,key);
		}
		return null;
	}
	
	//same as lookUp helper in BST tree
	private String lookUpHelper(Node root, String key) {
		if(root!=null) {
			if(root.key.compareTo(key)==0) {
				return root.value;
			}else if(root.key.compareTo(key)>0) {
				return lookUpHelper(root.left, key);
			}else {
				return lookUpHelper(root.right, key);
			}
		}
		return null;
	}


	//same as delete in BST tree
	public boolean delete(String key) {
		if(isContain(this.root,key)) {
			this.root=deleteHelper(this.root,key);
			this.total--;
			updateHeight(this.root);
			updateSize(this.root);
			return true;
		}
		return false;
	}
	
	private Node deleteHelper(Node root, String key) {
		if(root!=null) {
			if(key.compareTo(root.key)<0) {
				root.left=deleteHelper(root.left,key);
			}else if(key.compareTo(root.key)>0){
				root.right=deleteHelper(root.right, key);
			}else {
				if(root.right == null && root.left == null) {
					root =null;
				}else if(root.left!=null && root.right==null) {
					root= root.left;
				}else if(root.right!=null && root.left==null) {
					root=root.right;
				}else {
					Node rightMinimumNode=findNode(root.right);
					root.key=rightMinimumNode.key;
					root.value=rightMinimumNode.value;
					root.right=deleteHelper(root.right, rightMinimumNode.key);
				}
			}
			if(root != null) {
				root.height = Maximum(getHeight(root.left), getHeight(root.right)) + 1;
				root=rebalanceForDelete(root);
			}
		}
		return root;
	}

	  private Node rebalanceForDelete(Node root) {
		if(getHeight(root.left)-getHeight(root.right)>1) {
			if(getHeight(root.left.left)-getHeight(root.left.right)>=0) {
				root=rotateRight(root);
			}else {
				root.left=rotateLeft(root.left);
				root=rotateRight(root);
			}
		}else if(getHeight(root.left)-getHeight(root.right)<-1) {
			if(getHeight(root.right.left)-getHeight(root.right.right)>0) {
				root.right=rotateRight(root.right);
				root=rotateLeft(root);
			}else {
				root=rotateLeft(root);
			}
		}else {
			
		}
		return root;
	}

	//same as find minimum node in BST tree
	private Node findNode(Node node)  
	    {  
	        Node current = node;  	  
	        while (current.left != null) {
	        	current = current.left;  
	        }
	        return current;  
	    }

	//same as update in BST tree
	public boolean update(String key, String newValue) {
		if(isContain(this.root, key)) {
			updateHelper(this.root, key, newValue);
			return true;
		}
		return false;
	}
	
	
	//same as update helper in BST tree
	private void updateHelper(Node root, String key, String newValue) {
		// TODO Auto-generated method stub
		if(root!=null) {
			if(root.key.compareTo(key)==0) {
				 root.value=newValue;
				 return;
			}else if(root.key.compareTo(key)>0) {
				updateHelper(root.left, key, newValue);
			}else {
				updateHelper(root.right, key, newValue);
			}
		}
	}
	
	//same as displayAll in BST tree
	public int displayAll(){
		//print all tree node using in-order traversal 
		if(this.root!=null) {
			printHelper(this.root);
		}
		//print the size information
		System.out.println("");
		printSize(this.root);
		return 0;
	}
	
	//same as printSize in BST tree
	private void printSize(Node root) {
		if(root!=null) {
			//if tree is not empty print its root size 
			System.out.println("Tree size = "+ this.root.size);
			System.out.println("Number of contacts in addressbook = "+ this.root.size);
		}else {
			//if tree is empty print 0
			System.out.println("Tree size = 0");
			System.out.println("Number of contacts in addressbook = 0");
		}
	}
	
	//same as print helper in BST tree
	private void printHelper(Node root) {
		//in-order traversal to print all node info
		if(root!=null) {
			printHelper(root.left);
			System.out.println(root.key);
			System.out.println(root.value);
			System.out.println("	---"+ " Node height = "+ root.height);
			printHelper(root.right);
		}
	}

	//same as save method in BST tree
	public void save() throws IOException {
		String file=preorderTraversal(this.root);
		//store info as a string using pre-order traversal
		System.out.println(file);
		Scanner scan=new Scanner(System.in);
		boolean flag=true;
		//set a boolean flag to store input file status
		while(flag) {
			System.out.println("Enter the output file name: ");
			String filename=scan.nextLine();
			//store output file name in string
			if(!filename.equals("")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
				//create a new write buffer
				flag=false;
				//change flag to false
				writer.write(file);
				//write the file and close
				writer.close();
			}else {
				//error message if the file name is empty
				System.out.println("file name cannot be empty, please re-enter");
			}
		}
	}
	
	//same as pre-order traversal in BST tree
	private String preorderTraversal(Node root) {
		//perform a pre-order traversal and store them into a string
		String info="";
		if(root!=null) {
			info=root.key+"\n"+root.value+"\n";
			info +=preorderTraversal(root.left);
			info +=preorderTraversal(root.right);
		}
		return info;
	}
	
	//same as isContain method in BST tree
	private boolean isContain(Node root, String key) {
		if(root==null) {
			return false;
		}else {
			if(root.key.equals(key)) {
				return true;
			}else {
				return isContain(root.left,key) || isContain(root.right,key);
			}
		}
	}
	
	//same as calculate size in BST tree
	private int calculateSize(Node root) {
		// TODO Auto-generated method stub
		if(root == null) {
			return 0;
		}
		return 1+calculateSize(root.left)+calculateSize(root.right);
	}
	
	
	//same as calculate height in BST tree
	private int calculateHeight(Node root, int height) {
		// TODO Auto-generated method stub
		if(root == null) {
			return height;
		}
		int leftHeight=calculateHeight(root.left, height+1);
		int rightHeight=calculateHeight(root.right, height+1);
		if(leftHeight>rightHeight) {
			return leftHeight;
		}else {
			return rightHeight;
		}
	}
	
}
