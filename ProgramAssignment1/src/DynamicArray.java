/* *
 * Name: Guanhua Liu
 * G number: G01161931
 * Class: INFS 519
 * Project Assignment 1
 * */

public class DynamicArray<T>{
	private int num;
	//set a number to count total elements in array
	//store T element
	private T[] array;
	//create a dynamic array
	
	@SuppressWarnings("unchecked")
	public DynamicArray() {
		this.num=0;
		this.array=(T[]) new Object[2];
	}
	//Dynamic array constructor 
	//Set numbers in array equals 0
	//create a new object array with size of 2
	
	@SuppressWarnings("unchecked")
	public boolean add(T ele) {
		if(ele !=null ) {
			//check if the element is null 
			if(this.num >0) {
				if(!isExists(this.array, ele)) {
					//if array doesn't contains the element 
					if(this.num < this.array.length) {
						// if the number in array is less than array length 
						// directly add to the end
						array[num]=ele;
					}else {
						//if the number in array is greater than array length
						//create the new array with double its length
						T[] newArray=(T[]) new Object[this.array.length*2];
						System.out.println("** Doubling message array");
						for(int i=0; i<num;i++) {
							newArray[i]=this.array[i];
						}
						//copy all the elements from old array to new array
						newArray[num]=ele;
						this.array=newArray;
						//set new array last element
					}
					this.num++;
					//increase the number 
					return true;
				}
			}else {
				this.array[0]=ele;
				this.num++;
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean remove(T ele) {
		if(ele!= null) {
			//check element is not null
			if(this.num>0) {
				int number=Integer.parseInt(ele.toString());
				number--;
				if(0<= number && number < this.num) {
					//check the element if exists in the array
					String message=this.array[number].toString();
					System.out.println("Message -"+message+"- deleted");
					if((this.num-1) < this.array.length/3) {
						// if the decrease the number by one less than 1/3 of current length
						//create a new array with 1/3 of the size 
						T[] newArray=(T[]) new Object[this.array.length/3];
						System.out.println("** Shrinking message array");
						int j=0;
						for(int i=0; i<this.num;i++) {
							if(!this.array[i].equals(message)) {
								newArray[j]=this.array[i];
								j++;
							}
						}
						this.array=newArray;
						//copy all the element in old array remove the last one to new array
					}else {
						T[] newArray=(T[]) new Object[this.array.length];
						int j=0;
						for(int i=0; i<this.num;i++) {
							if(!this.array[i].equals(message)) {
								newArray[j]=this.array[i];
								j++;
							}
						}
						this.array=newArray;
						//if it is not below 1/3 just set the last element to null
					}
					this.num--;
					//decrease the number
					return true;
				}
			}
		}
		return false;
	}
	
	//my own helper method to check if element exist in array
	private boolean isExists(T[] list, T ele) {
		if(list !=null && ele !=null) {
			for(int i=0; i<this.num;i++) {
				if(list[i]!=null) {
					if(list[i].equals(ele)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	//display all messages associate with key 
	public boolean display(T key) {
		if(this.num>0) {
			System.out.print("Message sent: ");
			for(int i=0; i<this.num; i++) {
				if(this.array[i]!=null){
					if(i+1<this.num) {
						System.out.print("-"+this.array[i].toString()+", ");
					}else {
						System.out.println("-"+this.array[i].toString());
					}
				}
			}
			return true;
		}
		return false;
	}

	
	public int getLength() {
		return this.num;
	}
	//return the length 
	
	public String getElement(int count) {
		if( count < this.num) {
			return this.array[count].toString();
		}else {
			return null;
		}
	}
	//return element 
}
