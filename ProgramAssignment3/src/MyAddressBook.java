//INFS 519
//Name Guanhua Liu
//G number G01161931
//Programming Asssignment 3
//BST part

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MyAddressBook {
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
	private Node root;
	private int total;
	//store total number of keys in tree
	
	public MyAddressBook() {
		this.root=null;
		this.total=0;
	}
	//Initialize the root and total
	
	public boolean insert(String key, String value) {
		if(key!=null) {
			if(this.root==null) {
				//if root is empty create the new node and set as root
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
				addHelper(this.root,key,value);
				//increase the total
				this.total++;
				//update all node's height and size
				updateHeight(this.root);
				updateSize(this.root);
				return true;
			}
		}
		return false;
	}

	private void addHelper(Node root, String key, String value) {
		// compare to find where insert the node
		if(root.key.compareTo(key)>0) {
			//insert at the left of the root
			if(root.left==null) {
				//insert at the null node of the current root
				Node newNode=new Node(key,value);
				root.left=newNode;
			}else {
				addHelper(root.left, key, value);
				//insert at the left children of the current root
			}
		}else {
			//insert at the right of the root
			if(root.right==null) {
				//insert at the null node of the current root
				Node newNode=new Node(key, value);
				root.right=newNode;
			}else {
				//insert at the right children of the current root
				addHelper(root.right,key,value);
			}
		}
	}

	private void updateHeight(Node root) {
		// TODO Auto-generated method stub
		if(root!=null) {
			//call calculate height function on current node 
			//initialize call is -1 before root
			root.height=calculateHeight(root,-1);
			//update all children's height
			if(root.left!=null) {
				updateHeight(root.left);
			}
			if(root.right!=null) {
				updateHeight(root.right);
			}
		}
	}

	private void updateSize(Node root) {
		// TODO Auto-generated method stub
		if(root!=null) {
			//update root size 
			root.size=calculateSize(root);
			//update all children size
			if(root.left!=null) {
				updateSize(root.left);
			}
			
			if(root.right!=null) {
				updateSize(root.right);
			}
		}
	}

	public String lookUp(String key) {
		//check if tree contains the key
		if(isContain(this.root,key)) {
			return lookUpHelper(this.root,key);
		}
		return null;
	}
	
	private String lookUpHelper(Node root, String key) {
		//find the same key in tree
		//if root is not the key, goes to its left and right
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

	public boolean deleteContact(String key) {
		//check if key is in the tree
		if(isContain(this.root,key)) {
			this.root=deleteHelper(this.root, key);
			//decrease the key total size
			this.total--;
			//update all height and size
			updateHeight(this.root);
			updateSize(this.root);
			return true;
		}
		return false;
	}
	
	private Node deleteHelper(Node root, String key) {
		if(root!=null) {
			//if key is on the left, goes to its left
			if(key.compareTo(root.key)<0) {
				root.left=deleteHelper(root.left,key);
			}else if(key.compareTo(root.key)>0){
			//if key is on the right, goes to its right
				root.right=deleteHelper(root.right, key);
			}else {
				//if it is child, set to null
				if(root.right == null && root.left == null) {
					root =null;
				}else if(root.left!=null && root.right==null) {
					//let root refers to its left
					root= root.left;
				}else if(root.right!=null && root.left==null) {
					//let root refers to its right
					root=root.right;
				}else {
					Node rightMinimumNode=findNode(root.right);
					//find minimum node on the right 
					root.key=rightMinimumNode.key;
					root.value=rightMinimumNode.value;
					//replace current key and value with the node
					root.right=deleteHelper(root.right, rightMinimumNode.key);
					//delete the node from the right
				}
			}
		}
		return root;
	}

	private Node findNode(Node node)  
    {  
		//find the minimum node
        Node current = node;
        //set minimum node as current root
        while (current.left != null)  {
        	//if contains left node, goes to its left
        	current = current.left;  
        }
        return current;  
        	
    }

	public boolean update(String key, String newValue) {
		if(isContain(this.root, key)) {
			//if contains the key, call the update helper
			updateHelper(this.root, key, newValue);
			return true;
		}
		return false;
	}
	
	private void updateHelper(Node root, String key, String newValue) {
		// if current key is same as key, change its value
		if(root!=null) {
			if(root.key.compareTo(key)==0) {
				 root.value=newValue;
				 return;
			}else if(root.key.compareTo(key)>0) {
				//find the key on the left
				updateHelper(root.left, key, newValue);
			}else {
				//find the key on the right
				updateHelper(root.right, key, newValue);
			}
		}
	}

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

	public void save() throws IOException {
		String file=preorderTraversal(this.root);
		//store info as a string using pre-order traversal
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
	
	private boolean isContain(Node root, String key) {
		//check if the file contains specific key
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
	
	
	//calculate number of children the root contains
	private int calculateSize(Node root) {
		// TODO Auto-generated method stub
		if(root == null) {
			return 0;
		}
		//increase 1 as traversal through each level
		return 1+calculateSize(root.left)+calculateSize(root.right);
	}
	
	private int calculateHeight(Node root, int height) {
		// TODO Auto-generated method stub
		if(root == null) {
			return height;
		}
		//calculate both left side and right side heights
		int leftHeight=calculateHeight(root.left, height+1);
		int rightHeight=calculateHeight(root.right, height+1);
		//return the greater side as its height
		if(leftHeight>rightHeight) {
			return leftHeight;
		}else {
			return rightHeight;
		}
	}
	
	public int height2(Node root) {
		if(root!=null) {
			int left=height2(root.left);
			int right=height2(root.right);
			if(left >right) {
				return left;
			}else {
				return right;
			}
		}else {
			return -1;
		}
	}
	
}
