/* *
 * Name: Guanhua Liu
 * G number: G01161931
 * Class: INFS 519
 * Project Assignment 1
 * */
import java.util.Scanner;

public class MyAddressBook implements Table{
	private class Node{
		private String key;
		private String value;
		private DynamicArray<String> msgs;
		private Node next;
		private Node(String key, String value) {
			this.key= key;
			this.value=value;
			msgs=new DynamicArray<String>();
			this.next= null;
		}
		//node constructor initialize key to the parameter 
		//initialize value to the parameter
		//create a new ArrayList
		//set node next to null
	}
	
	//create a Node class inside the Table class
	private Node head;
	private Node mark;
	private Scanner s;
	private int size;
	//every table contains a head node as the head
	//every table has the number of nodes as size
	public MyAddressBook() {
		this.head=null;
		this.size=0;
		s=new Scanner(System.in);
	}
	
	/*table constructor initialize field head to null and size to 0
	*@param key is a string
	*@param value is a string
	*@return the status of insertion
	*/
	public boolean insert(String key, String value) {
		//Inserts a new entry to the table. If an entry already exists 
		//with the given key value make no insertion but return false.
		if(key != null && value!=null) {
			//check if key and value both not null
			if (this.head == null) {
				Node node=new Node(key,value);
				this.head=node;
				this.head.next=null;
				this.size++;
				return true;
				//if the table is empty, set key and value to current key and value
			}else {
				Node newNode=new Node(key,value);
				//create a new node
				Node curr=this.head;
				//set pointer as curr and points to head
				Node prev=null;
				//System.out.println(head.next.key);
				//set previous pointer as prev to null
				while(curr!=null) {
					if(curr.key.equals(key)) {
						return false;
						//if the entry already exists with the given key value return false
					}
					prev=curr;
					curr=curr.next;
				}
				//Traversal through entire table
				prev.next=newNode;
				//add the newNode to the last and return true; 
				this.size++;
				//increase size of the table 
				return true;
			}
		}
		return false;
	}
	
	/*@param is a string
	 *@return a value in string
	 */
	public String lookUp(String key){
		//Looks up the entry with the given key and returns the associated value. 
		//If no entry is found null is returned.
		if(key != null && this.size != 0) {
			//check parameter is not null and table size not 0
			Node curr=this.head;
			//set pointer to head
			while(curr!= null) {
				if(curr.key.equals(key)) {
					return curr.value;
					//if key has been found return curr value
				}
				curr=curr.next;
				//move to next element
			}
		}
		return null;
	}
	
	/*@param key is a string 
	 *@return is a status of deletion
	 */
	public boolean deleteContact(String key) {
		//Deletes the entry with the given key. 
		//If no entry is found returns false.
		if(key != null && this.size != 0) {
			Node curr=this.head;
			Node prev=null;
			while(curr!=null) {
				if(curr.key.equals(key)) {
					if(prev!=null) {
						prev.next=curr.next;
						//move previous pointer's next to current pointer's next
					}else {
						this.head.next=null;
						this.head=null;
						//remove the head and set head next to null
					}
					this.size--;
					return true;
					//decrease the size and return true
				}
				prev=curr;
				curr=curr.next;
				//move pointer to the next element
			}
		}
		return false;
	}
	
	
	/* @param key is a string
	 * @param newValue is a string
	 * @return the status of updating
	 */
	public boolean update(String key, String newValue){
		//Replaces the old value associated with with the given key with the newValue string.
		if(key != null && this.size!=0 ) {
			Node curr=this.head;
			while(curr!=null) {
				if(curr.key.equals(key)) {
					curr.value=newValue;
					return true;
				}
				curr=curr.next;
			}
		}
		return false;
	}
	
	
	//@return the status of operation
	public boolean markToStart() {
		//Sets the mark (see below) to the first item in the table. Returns false if the table is empty.
		if(size!=0) {
			this.mark=this.head;
			return true;
		}else {
			return false;
		}
	}
	
	//@return the status of setting mark
	public boolean advanceMark() {
		//Sets the mark (see below) to the first item in the table. Returns false if the table is empty.
		if(this.mark!=null) {
			if(this.mark.next!=null) {
				this.mark=this.mark.next;
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	//@return the string at the mark
	public String keyAtMark() {
		//Returns the key stored in the item at the current mark.
		if(this.mark!=null) {
			return this.mark.key;
		}
		return null;
	}
	
	//@return the string at the mark
	public String valueAtMark() {
		//Returns the value stored in the item at the current mark.
		if(this.mark!=null) {
			return this.mark.value;
		}
		return null;
	}
	
	
	//@return the number of all the elements
	public int displayAll() {
		//Displays Name/Address for each table entry. Returns total entry count.
		if(this.size > 0) {
			Node curr=this.head;
			int counter=0;
			while(curr!=null) {
				System.out.println("Name: "+ curr.key);
				System.out.println("Address: "+ curr.value);
				System.out.println("");
				counter++;
				curr=curr.next;
			}
			return counter;
		}
		System.out.println("Contact book is empty!");
		return 0;
	}
	
	/* @param key is a string
	 * @param msg is a string
	 * @return the status of adding operation
	 */
	public boolean sendMessage(String key, String msg) {
		if(key != null && this.size!=0 ) {
			Node curr=this.head;
			while(curr!=null) {
				if(curr.key.equals(key)) {
					return(curr.msgs.add(msg));
				}
				curr=curr.next;
			}
			
		}
		return false;
	}
	
	/* @param key is a string
	 * @param msg is a string
	 * @return the status of deleting operation
	 */
	
	public boolean deleteMessage(String key) {
		if(key != null) {
			Node curr= this.head;
			while(curr!=null) {
				if(curr.key.equals(key)){
					for(int i=0; i<curr.msgs.getLength(); i++) {
						System.out.println((i+1)+". "+curr.msgs.getElement(i));
					}
					boolean flag=true;
					while(flag) {
						System.out.println("Choose message to delete:");
						String msgNumber=s.next();
						System.out.println("Message no: "+ msgNumber);
						int number=Integer.parseInt(msgNumber);
						if(number >0 && number <=curr.msgs.getLength()) {
							flag=false;
							curr.msgs.remove(msgNumber);
						}else {
							System.out.println("Error input, please re-enter");
						}
					}
					return true;
				}
				curr=curr.next;
			}
		}
		return false;
	}
	
	//clear the table

	public boolean DisplayAllMessage(String key) {
		if(key != null) {
			Node curr=this.head;
			while(curr!=null) {
				if(curr.key.equals(key)){
					return(curr.msgs.display(key));
				}
				curr=curr.next;
			}
		}
		return false;
	}
	
}
