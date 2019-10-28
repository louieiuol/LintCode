
public class InsertionSort {
	public class LinkList{
		Node head;
		int length;

		LinkList(){
			this.head=null;
			this.length=0;
		}

		LinkList(Node node){
			this.head=node;
			this.length=0;
		}

		public void add(int value) {
			// TODO Auto-generated method stub
			Node newNode=new Node(value);
			if(this.length>0) {
				Node curr=this.head;
				Node prev=null;
				while(curr!=null) {
					prev=curr;
					curr=curr.next;
				}
				prev.next=newNode;
			}else {
				this.head=newNode;
			}
			this.length++;
		}
		
		public void printAll() {
			if(this.length>0) {
				Node curr=this.head;
				while(curr!=null) {
					System.out.println(curr.value);
					curr=curr.next;
				}
			}
		}
	}
	
	public class Node{
		public int value;
		public Node next;
		public Node() {
			this.value=0;
			this.next=null;
		}
		public Node(int value) {
			this.value=value;
			this.next= null;
		}
	}

	public LinkList insertionSort(LinkList lst){
		if(lst!=null) {
			LinkList result=new LinkList();
			if(lst.length >1) {
				result.head=new Node(lst.head.value);
				result.head.next=null;
				Node pointer=lst.head.next;
				while(pointer!=null) {
					Node curr=result.head;
					Node prev=null;
					Node newNode=new Node(pointer.value); 
					while(curr!=null) {
						if(pointer.value< curr.value) {
							if(prev==null) {
								newNode.next=curr;
								result.head=newNode;
								break;
							}else {
								prev.next=newNode;
								newNode.next=curr;
								break;
							}
						}
						prev=curr;
						curr=curr.next;
					}
					if(curr == null) {
						prev.next=newNode;
					}
					result.length++;
					pointer=pointer.next;
				}
				return result;
			}
			result.add(lst.head.value);
			return result;
		}
		return null;
	}

	public LinkList convertArray(int[] array) {
		int i=1;
		if(array!=null) {
			if(array.length >0) {
				LinkList lst=new LinkList();
				Node head=new Node(array[0]);
				Node prev=head;
				while(i<array.length) {
					Node node=new Node(array[i]);
					prev.next=node;
					prev=node;
					i++;
				}
				lst.head=head;
				lst.length=array.length;
				return lst;
			}
		}
		return null;
	}

}
