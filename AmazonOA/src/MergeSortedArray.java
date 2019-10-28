
/*6. Merge Two Sorted Arrays
中文English
Merge two given sorted integer array A and B into a new sorted integer array.

Example
Example 1:
	Input:  A=[1], B=[1]
	Output: [1,1]
	
	Explanation: 
	return array merged.

Example 2:
	Input:  A=[1,2,3,4], B=[2,4,5,6]
	Output: [1,2,2,3,4,4,5,6]
	
	Explanation: 
	return array merged.

Challenge
How can you optimize your algorithm if one array is very large and the other is very small?
Ans: create a hash map memorizes the indexes that requires A insert into B in an array
key is index of B, value is value in array A
create a fresh new result list, length is A + B
make a pointer points to first of memory list 
when copy B list to result list, if index equals the value of pointer
which points to, put that value corresponding to the key into the result array
until the copy is done.
*
*/
import java.util.*;
public class MergeSortedArray {
	public int[] mergeSortedArray(int[] A, int[] B) {
		// write your code here
		// write your code here
		if(A == null || B == null) return null;
		if(A.length == 0 || B.length == 0) return new int[0];
		int[] res=new int[A.length+B.length];
		int left=0, right=0;
		int index=0;
		while(index<res.length){
			if(left == A.length && right == B.length) break;
			else if(left== A.length && right < B.length) res[index]=B[right++];
			else if(right == B.length && left < A.length) res[index]=A[left++];
			else{
				if(A[left]<B[right]) res[index]=A[left++];
				else res[index]=B[right++];
			}
			index++;
		}
		return res;
	}
	
	
	/*
	 * 486. Merge K Sorted Arrays Given k sorted integer arrays, merge them into one
	 * sorted array.
	 * 
	 * Example Example 1:
	 * 
	 * Input: [ [1, 3, 5, 7], [2, 4, 6], [0, 8, 9, 10, 11] ] Output: [0, 1, 2, 3, 4,
	 * 5, 6, 7, 8, 9, 10, 11] Example 2:
	 * 
	 * Input: [ [1,2,3], [1,2] ] Output: [1,1,2,2,3] Challenge Do it in O(N log k).
	 * 
	 * N is the total number of integers. k is the number of arrays.
	 */
	
	  public int[] mergeKSortedArrays(int[][] arrays) {
        if(arrays.length == 0) return new int[0];
        if(arrays[0].length == 0) return new int[0];
        int total=0;
        for(int i=0; i<arrays.length;i++){
            for(int j=0; j<arrays[i].length;j++){
                total++;
            }
        }
        int[] res=new int[total];
        PriorityQueue<Integer> pq=new PriorityQueue<>(total, new Comparator<Integer>(){
            public int compare(Integer p1,Integer p2){
                return p1-p2;
            };
        });
        for(int i=0; i<arrays.length;i++){
            for(int j=0; j<arrays[i].length;j++){
                pq.add(arrays[i][j]);
            }
        }
        int index=0;
        while(!pq.isEmpty() && index<res.length){
            res[index]=pq.poll();
            index++;
        }
        return res;
	}
}
