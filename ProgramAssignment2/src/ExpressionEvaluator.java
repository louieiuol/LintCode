import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class ExpressionEvaluator {
	//define regular expression to match the string
	private static String pat1="([a-zA-Z0-9]+)";
	private static String pat2="([0-9]+)";
	//create class to run evaluation
	class Queue{
		Node head;
		int size;
		//create head node and size for the queue
		class Node{
			String val;
			Node next;
			//node contains a value and a reference
			Node(String val){
				this.val=val;
				this.next= null;
			}
			//constructor of node 
			//set val to value
			//set next reference to null
		}

		Queue(){
			this.head=null;
			this.size=0;
		}
		//constructor of queue
		public void clear() {
			this.head=null;
			this.size=0;
		}
		
		//clear head value 
		//reset size to 0
		public void enqueue(String val){
			if(val != null) {
				//check if key and value both not null
				Node newNode=new Node(val);
				//create a new node
				if (this.head == null) {
					this.head=newNode;
					this.head.next=null;
					//if the table is empty, set key and value to current node
				}else {
					newNode.next=this.head;
					this.head=newNode;
					//add node to the begin of queue
					//set new node as head
				}
				this.size++;
				//increase the size
			}
		}

		public String dequeue() {
			if(this.head != null || this.size > 0) {
				//check queue contains element
				Node target=null;
				if(this.size > 1) {
					//remove the end node of the queue
					Node curr=this.head;
					Node prev=null;
					while(curr.next!=null) {
						prev=curr;
						curr=curr.next;
					}
					//traversal through the queue to find last node
					target=curr;
					prev.next=null;
					//remove the reference
				}else {
					//only 1 element in queue
					target=this.head;
					//return the head 
					this.head.next=null;
					this.head=null;	
					//remove head reference and set to null
				}
				this.size--;
				//decrease the size and return value
				return target.val;
			}
			return null;
		}


		public boolean isEmpty() {
			//check if queue is empty
			if(size ==0) {
				return true;
			}else {
				return false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		File file=new File("expressions.txt");
		//create a file object from input parameter
		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		//create a bufferReader from the file	
		String line=null;
		//create a string line for reading buffer
		ProgramStack<String> stack=new ProgramStack<String>();
		//create a stack instance
		SymbolTable<Integer> table=new SymbolTable<Integer>();
		//create a table instance
		ExpressionEvaluator eval=new ExpressionEvaluator();
		//create a evaluator instance
		ExpressionEvaluator.Queue queue=eval.new Queue();
		//create a queue instance which is a inner class of evaluator
		while((line = bufferedReader.readLine()) != null) {
			//read buffer line by line
			boolean flag=false;
			//create a flag for determine output
			String[] array=line.split(" ");
			//split each line with space 
			String str1="Input expression:";
			for(String ele: array) {
				queue.enqueue(ele);
				str1+=" ";
				str1+=ele;
			}
			System.out.println(str1);
			//output each read line
			while(!queue.isEmpty()) {
				String ele=queue.dequeue();
				//get one element from queue
				//check if pattern matches words or numbers
				if(ele.matches(pat1)) {
					if(ele.matches(pat2)) {
						//if string is a number
						stack.push(ele);
					}else if(ele.matches(pat1)) {
						//if string is a variable 
						flag=true;
						//set flag to true when there exists variable
						if(table.get(ele)==null) {
							//if element cannot find in table create a new one
							table.put(ele, -1234);
						}
						//push element to stack
						stack.push(ele);
					}else {
						//invalid format
						stack.clear();
						stack.push("INVALID format");
						break;
					}
				}else if(checkUnarySymbol(ele)){
					//check if string is a unary symbol 
					String rhs=stack.pop();
					String lhs=stack.pop();
					if(rhs == null || lhs == null) {
						//if right hand side or left hand side is null
						stack.clear();
						stack.push("INVALID format");
						//invalid format
						break;
					}
					String result=calculate(ele, rhs, lhs, table);
					if(result == null) {
						stack.clear();
						stack.push("INVALID format");
						break;
					}
					//process unary symbol evaluation
					stack.push(result);
					//unary evaluation will push result back to stack
				}else if(checkBinarySymbol(ele)) {
					//check if string is a binary symbol
					String rhs=stack.pop();
					String lhs=stack.pop();
					if(table.get(lhs)==null) {
						//if left side is a number or right side is a variable
						stack.clear();
						stack.push("INVALID format");
						break;
					}
					if(table.get(rhs)!=null) {
						rhs=Integer.toString(table.get(rhs));
					}
					calculate2(ele, rhs, lhs, table);
					//process binary symbol evaluation
				}else {
					//invalid format
					stack.clear();
					stack.push("INVALID format");
					break;
				}
			}
			String value=stack.pop();
			//pop the top element in the stack
			if(stack.pop()!= null || value == null) {
				System.out.println("Value: INVALID format");
			}else {
			if(table.get(value)!=null) {
				//if it is a variable
				value=Integer.toString(table.get(value));
				//invalid output
			}
			/*else {
				if(table.get(value)!=null) {
				//if table contains value search it on table
				value=Integer.toString(table.get(value));
			} */
				System.out.println("Value: "+value);
				//output value
				if(flag && value!=null && !value.equals("INVALID format")) {
					//if it contains variable then output input expression
					String str2="Symbol table entries:"; 
					String str3="";
					for(String ele: array) {
						//filter for only variables get output 
						if(ele.matches(pat1)) {
							if(!ele.matches(pat2)) {
								if(!str3.contains(ele)) {
									str3+=" ";
									str3+=ele;
									str3+="=";
									str3+=table.get(ele);
									str3+=",";
								}
							}
						}
					}
					System.out.println(str2+str3.substring(0, str3.length()-1));
					//output the input expression
				}
				stack.clear();
				table.clear();
				queue.clear();
				//clear stack, queue, and table
			}
			}
		}
//}


	private static String calculate2(String ele, String rhs, String lhs, SymbolTable<Integer> table) {
		//calculate the binary operation
		String result=null;
		switch(ele) {
		case "=": 	table.put(lhs, Integer.parseInt(rhs));
					result=Integer.toString(table.get(lhs));
					break;
					
		case "+=":	int value2=table.get(lhs);
					table.put(lhs, value2+Integer.parseInt(rhs));
					//read from the table and increase then put back to table
					result=Integer.toString(table.get(lhs));
					//set as return result 
					break;

		case "-=":  int value3=table.get(lhs);
					table.put(lhs, value3-Integer.parseInt(rhs));
					result=Integer.toString(table.get(lhs));
					break;

		case "*=": 	int value4=table.get(lhs);
					table.put(lhs, value4*Integer.parseInt(rhs));
					result=Integer.toString(table.get(lhs));
					break;

		case "/=":	int value5=table.get(lhs);
					table.put(lhs, value5/Integer.parseInt(rhs));
					result=Integer.toString(table.get(lhs));
					break;
		}
		return result;
	}

	private static String calculate(String symbol, String rhs, String lhs, SymbolTable<Integer>table) {
		//calculate the unary operation
		String result=null;
		int right;
		int left;
		if(table.get(rhs)!=null) {
			right=table.get(rhs);
			//if right hand side is a symbol
		}else {
			right=Integer.parseInt(rhs);
			//if right hand side is a number;
		}


		if(table.get(lhs)!=null) {
			left=table.get(lhs);
			//if left hand side is a symbol
		}else {
			left=Integer.parseInt(lhs);
			//if left hand side is a number
		}
		
		if(right == -1234 || left == -1234) {
			return null;
		}

		switch(symbol) {
		//process unary symbol evaluation
		case "+": 	result=Integer.toString(left+right); 
		break;

		case "-": 	result=Integer.toString(left-right);
		break;

		case "*":	result=Integer.toString(left*right);
		break;

		case "/":   if(right!=0) {
			result=Integer.toString(left/right);
			if(left/right == 0) {
				//if left side less than right side
				return null;
			}
		}else {
			System.out.println("Division by Zero!");
		}
		break;
		case "^":  	int res = 1;
		for (int i = 1; i <= right; i++) {
			res *= left;
		}
		result=Integer.toString(res);
		break;
		}
		return result;
	}

	private static boolean checkUnarySymbol(String top) {
		//check if belongs to + - * /
		if(top.equals("+") || top.equals("-") || top.equals("*") || top.equals("/") || top.equals("^")) {
			return true;
		}
		return false;
	}

	private static boolean checkBinarySymbol(String top) {
		//check if belongs to += -= *= /=
		if(top.equals("=") || top.equals("+=") ||  top.equals("-=") ||  top.equals("*=") ||  top.equals("/=")) {
			return true;
		}
		return false;
	}
}
