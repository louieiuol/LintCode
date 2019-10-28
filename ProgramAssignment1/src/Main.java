/* *
 * Name: Guanhua Liu
 * G number: G01161931
 * Class: INFS 519
 * Project Assignment 1
 * */

import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Table table=new MyAddressBook();
		//create a new MyAddressBook instance
		Scanner s=new Scanner(System.in);
		//create a new Scanner
		String input=null;
		//set input string to be null
		do {
		System.out.println("");
		System.out.println("Add a contact (a)");
		System.out.println("Send a text message (m)");
		System.out.println("Look up a contact (l)");
		System.out.println("Update address (u)");
		System.out.println("Delete a contact (dc)");
		System.out.println("Delete a text message (dm)");
		System.out.println("Display all contacts (ac)");
		System.out.println("Display all message to a contact (am)");
		System.out.println("Quit (q)");
		//output all instructions
		input=s.next();
		//read next input
		switch(input) {
			case "q":   System.out.println("Exited! Thanks for using!");
						break;
						//quit the process
						
			case "a": 	System.out.println("Name: ");
					  	String key1=s.next();
					  	System.out.println("Address: ");
					  	String value1=s.next();
					  	if(!table.insert(key1, value1)) {
					  		System.out.println(key1+" is already existed in the book");
					  	}
					  	break;
					  	//allow user to input their name and address and insert into table
					  	
			case "m":   System.out.println("Name: ");
						String key2=s.next();
						String find2=table.lookUp(key2);
						if(find2 == null) {
							System.out.println(key2+ " is not in the book, message not sent");
							//error case
						}else {
							System.out.println("Message: ");
							String msg2=s.next();
							table.sendMessage(key2, msg2);
							System.out.println("Message -"+msg2+"- sent to "+key2);
							//allow user to input their message and add to array
						}
						break;
					  
			case "l":   System.out.println("Name: ");
						String key3=s.next();
						System.out.println("Address is "+table.lookUp(key3));
						break;
						//search for specific name in address book
					 
			case "u": 	System.out.println("Name: ");
						String key4=s.next();
						String find4=table.lookUp(key4);
						if(find4 ==null) {
							System.out.println(key4+ " is not in the book");
							//error case
						}else {
							System.out.println("Old Address is "+find4);
							System.out.println("New Address: ");
							String value4=s.next();
							table.update(key4, value4);
							//update the value with the new user input value
						};
						break;
					
						
			 
			case "dc":  System.out.println("Name to delete: ");
						String key5=s.next();
						if(!table.deleteContact(key5)) {
							System.out.println(key5+" is not in the book");
							//error case 
						};
						break;
						//execute the delete function
						
			case "dm":  System.out.println("Name: ");
						String key6=s.next();
						String find6=table.lookUp(key6);
						if(find6 == null) {
							System.out.println(key6+ " is not in the book, no messages deleted");
							//error case
						}else {
							table.deleteMessage(key6);
							//execute the delete message function
						}
						break;
						
			case "ac":  table.displayAll();
						break;
						//execute the display function
						
			case "am":  System.out.println("Name: ");
						String key7=s.next();
						if(table.lookUp(key7) == null) {
							System.out.println(key7+ " is not in the book");
							//error case
						}else {
							table.DisplayAllMessage(key7);
							//execute the display all message function
						}
						break;
						
			default:  	System.out.println("Input invalid opertaion, please re-enter!");
						//other case, let user re-enter input
						break;
		}
		
		}while(!input.equals("q"));
		//exit the program
	}
	

}
