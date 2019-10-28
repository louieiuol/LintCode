//INFS 519
//Name Guanhua Liu
//G number G01161931
//Programming Asssignment 3
//BST part

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Table {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file=new File("contacts.txt");
		//create the file as the input parameter
		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		//create a new buffer reader
		MyAddressBook myBook=new MyAddressBook();
		//create a new address book
		String line=" ";
		//Initialize the array to store information
		while(line!=null) {
			line=bufferedReader.readLine();
			String key=line;
			//read the first line as the key 
			line=bufferedReader.readLine();
			String value=line;
			//read the second line as the value
			myBook.insert(key, value);
			//store it to tree structure 
		}
		@SuppressWarnings("resource")
		Scanner scaner=new Scanner(System.in);
		//create a new scanner
		boolean flag=true;
		//set the flag to be true
		String keyboard="";
		//create a empty keyboard string
		while(flag) {
			System.out.println("1. Add a contact (a)");
			System.out.println("2. Look up a contact (l)");
			System.out.println("3. Update address (u)");
			System.out.println("4. Delete a contact (d)");
			System.out.println("5. Display all contacts (ac)");
			System.out.println("6. Save and exit (q)");
			System.out.println("ENTER CHOICE: ");
			//output all menu options
			String input=scaner.nextLine();
			//read the next input input
			switch(input) {
				case "1": 
						//perform insert operation
						System.out.println("Name: ");
						String key1=scaner.nextLine();
						System.out.println("Address: ");
						String value1=scaner.nextLine();
						if(!myBook.insert(key1, value1)) {
							System.out.println(key1+" is already existed in the book");
						}
						//if it exists in address book, print error message
						break;
						
				//same as case 1
				case "a": 
					//perform insert operation
					System.out.println("Name: ");
					String key_1=scaner.nextLine();
					System.out.println("Address: ");
					String value_1=scaner.nextLine();
					if(!myBook.insert(key_1, value_1)) {
						System.out.println(key_1+" is already existed in the book");
					}
					//if it exists in address book, print error message
					break;
				  	
				case "2": 
						//perform lookup operation
						System.out.println("Name: ");
						String key2=scaner.nextLine();
						System.out.println("Address is "+myBook.lookUp(key2));
						break;
				
				//same as case 2
				case "l": 
					//perform lookup operation
					System.out.println("Name: ");
					String key_2=scaner.nextLine();
					System.out.println("Address is "+myBook.lookUp(key_2));
					break;
				
					
				case "3": 
						//perform update operation
						System.out.println("Name: ");
						String key3=scaner.nextLine();
						String find3=myBook.lookUp(key3);
						if(find3 ==null) {
							System.out.println(key3+ " is not in the book");
							//if key not in the book, print error message
						}else {
							System.out.println("Old Address is "+find3);
							System.out.println("New Address: ");
							String value3=scaner.nextLine();
							myBook.update(key3, value3);
							//update the value with the new user input value
						}
						break;

				//same a case 3
				case "u": 
					//perform update operation
					System.out.println("Name: ");
					String key_3=scaner.nextLine();
					String find_3=myBook.lookUp(key_3);
					if(find_3 ==null) {
						System.out.println(key_3+ " is not in the book");
						//if key not in the book, print error message
					}else {
						System.out.println("Old Address is "+find_3);
						System.out.println("New Address: ");
						String value_3=scaner.nextLine();
						myBook.update(key_3, value_3);
						//update the value with the new user input value
					}
					break;
				
				case "4":
						System.out.println("Name to delete: ");
					 	String key4=scaner.nextLine();
					 	if(!myBook.deleteContact(key4)) {
					 		System.out.println(key4+" is not in the book");
					 		//if key is not in the book, print error message
					 	}
					 	break;
					 	//execute the delete function
				
					 	
				//same as case 4
				case "d":
						System.out.println("Name to delete: ");
						String key_4=scaner.nextLine();
						if(!myBook.deleteContact(key_4)) {
							System.out.println(key_4+" is not in the book");
							//if key is not in the book, print error message
						};
						break;
						//execute the delete function
					
				case "5": 
					 	myBook.displayAll();
					 	//perform display function
					 	break;
					
			
				//same as case 5	 	
				case "ac": 
						myBook.displayAll();
						//perform display function
						break;

				case "6":
						myBook.save();
						//perform save function
						flag=false;
						//set the flag to be false to break the outside loop
						break;
						
				//same as case 6
				case "q":
						myBook.save();
						//perform save function
						flag=false;
						//set the flag to be false to break the outside loop
						break;		
					
				default:
					 	System.out.println("Input invalid opertaion, please re-enter!");
						//other case, let user re-enter input
						break;
			}
			
			if(!flag) {
				break;
				//break the outside loop
			}
			
			boolean keyboardflag=true;
			//set keyboard flag as true
			while(keyboardflag) {
				System.out.println("press ENTER to continue..");
				keyboard=scaner.nextLine();
				//check if user input "enter" as his next input
				if(keyboard.equals("")) {
					keyboardflag=false;
				}
			}
		}
	}

}
