public class ProgramStack<T> {
	private int num;
	//set a number to count total elements in array
	//store T element
	private T[] array;
	//create a dynamic array

	@SuppressWarnings("unchecked")
	public ProgramStack() {
		this.num=0;
		this.array=(T[]) new Object[2];
	}
	//Dynamic array constructor 
	//Set numbers in array equals 0
	//create a new object array with size of 2

	@SuppressWarnings("unchecked")
	public void push(T item) {
		if(item !=null ) {
			//check if the element is null 
			if(this.num >0) {
				//if array doesn't contains the element 
				if(this.num < this.array.length) {
					// if the number in array is less than array length 
					// directly add to the end
					array[num]=item;
				}else {
					//if the number in array is greater than array length
					//create the new array with double its length
					T[] newArray=(T[]) new Object[this.array.length*2];
					for(int i=0; i<num;i++) {
						newArray[i]=this.array[i];
					}
					//copy all the elements from old array to new array
					newArray[num]=item;
					this.array=newArray;
					//set new array last element
				}
			}else {
				this.array[0]=item;
			}
			this.num++;
		}
	}

	@SuppressWarnings("unchecked")
	public T pop() {
		if(this.num>0) {
			T ele=this.array[this.num-1];
			if((this.num-1) < this.array.length/3) {
				// if the decrease the number by one less than 1/3 of current length
				//create a new array with 1/3 of the size 
				T[] newArray=(T[]) new Object[this.array.length/3];
				for(int i=0; i<this.num-1;i++) {
					newArray[i]=this.array[i];
				}
				this.array=newArray;
				//copy all the element in old array remove the last one to new array
			}else {
				T[] newArray=(T[]) new Object[this.array.length];
				for(int i=0; i<this.num-1;i++) {
					newArray[i]=this.array[i];
				}
				this.array=newArray;
				//if it is not below 1/3 just set the last element to null
			}
			this.num--;
			//decrease the number
			return ele;
		}
		return null;
	}

	public T peek() {
		if(this.num>0) {
			return this.array[this.num-1];
			//return the top element in the stack
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		this.num=0;
		this.array=(T[])new Object[2];
		//reset stack
	}

}
