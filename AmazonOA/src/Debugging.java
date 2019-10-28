
public class Debugging {
	// You can print the values to stdout for debugging
	public static int[] removeElement(int arr[], int index) {
		int i, len = arr.length;
		if (index < len) {
			for (i = index; i < len - 1; i++) {
				arr[i] = arr[i+1];
			}
			int rarr[] = new int[len - 1];
			for (i = 0; i < len - 1; i++) {
				rarr[i] = arr[i];
			}
			return rarr;
		} else
			return arr;
	} 

	public static void main(String args[]) {
		int[] arr={9,3,5,1,11,7}; 
		int[] arr2=Debugging.insertionsort(arr, 6);
		for(int i=0; i<arr2.length;i++) {
			System.out.println(arr2[i]);
		}
	}

	public static int find(int num) {
		int count = 0;
		int number=num;
		while(num != 0) {
			num = num/10;
			count++;
		}
		return number % count;
	}

	public static int[] sortArray(int[] arr) {
		int len = arr.length;
		int i, j, temp;
		for (i = 0; i <= len - 1; i++) {
			for (j = i; j < len; j++) {
				temp = 0;
				if (arr[i] >arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr; 
	}
	
	public static int[] sortArray2(int arr[]) {
		int i, max, location, j, temp, len = arr.length;
		for (i = 0; i < len; i++) {
			max = arr[i];
			location = i;
			for (j = i; j < len; j++) {
				if (max < arr[j]) { 
					max = arr[j];
					location = j;
				}
			}
			temp = arr[i];
			arr[i] = arr[location];
			arr[location] = temp;
		}
		return arr; 
	}
	
	public static void sortArray3(int arr[], int n) {
		for (int x = 0; x < n; x++) {
			int index_of_min = x;
			for (int y = x; y < n; y++) {
				if (arr[index_of_min] > arr[y]) {
					y = index_of_min;
				}
				int temp = arr[x];
				arr[x] = arr[index_of_min];
				arr[index_of_min] = temp;
			} 
		}
	}
	
	
	
	public static void bubbleSort1(int arr[], int n) {
	for (int x = 0; x < n; x++) {
	    for (int y = x; y < n; y++) {
	        if (arr[x] > arr[y]) {
	            int temp = arr[x];
	            arr[x] = arr[y];
	            arr[y] = temp;
	        }
	    } 
	}

	
	}

	public static void bubblesort2(int arr[], int n) {
	for (int x = 0; x < n; x++) {
	    int index_of_min = x;
	    for (int y = x; y < n; y++) {
	    if (arr[index_of_min] > arr[y]) {
	        y = index_of_min;
	    }
	        int temp = arr[x];
	        arr[x] = arr[index_of_min];
	        arr[index_of_min] = temp;
	      } 
	    }
	}

	public static int[] insertionsort(int arr[], int n) {
		for (int i = 1; i < n; i++) {
			if (arr[i - 1] > arr[i]) { 
				int temp = arr[i];
				int j = i;
				while (j > 0 && arr[j - 1] > temp) {
					arr[j] = arr[j - 1];
					j--; 
				}
				arr[j] = temp;
			}
		}
		return arr;
	}

	public static int[] replaceValues(int arr[]) {
		int i, j, len = arr.length;
		if (len % 2 == 0) {
			for (i = 0; i < len; i++)  {
				arr[i] = 0; 
			}
		} else {
			for (j = 0; j < len; j++) {
				arr[j] = 1; 
			}
		}
		return arr; 
	}
	
	public static int[] reverseArray(int[] arr) {
	    int i, temp, originalLen = arr.length;
	    int len = originalLen;
	        for (i = 0; i < originalLen / 2; i++) {
	            temp = arr[len -i-1];
	            arr[len - i - 1] = arr[i];
	            arr[i] = temp;
	            len -=1;
	    }
	    return arr;
	}
	
	public static void print4(int num) {
	    int i, print = 0;
	    if (num % 2 == 0) {
	        print = 0;
	        for (i = 0; i < num; i++) {
	            System.out.print(print + " ");
	            print += 2; 
	        }
	    } else { 
	        print = 1;
	        for (i = 0; i < num; i++) {
	            System.out.print(print + " ");
	            print += 2;
	        }
	    }
	}
	
	public static void print2(int i) {
        char ch = 'a';
        char print = ch;
	    for (int j = 0; j <=i; j++) {
	        System.out.print((print++));
	    }
	   System.out.println("");
	}
	
	public static void print3(int row) {
	    int x = 1;
	    for (int i = 1; i <= row; i++) {
	         for (int j = i; j > 0; j--) {
	             System.out.print(x + "" + x);
	         }
	         System.out.println();
	    }
	}
	
	    
}

