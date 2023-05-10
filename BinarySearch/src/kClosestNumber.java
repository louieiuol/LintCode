//460. Find K Closest Elements
//中文English
//Given target, a non-negative integer k and an integer array A sorted in ascending order, find the k closest numbers to target in A, sorted in ascending order by the difference between the number and target. Otherwise, sorted in ascending order by number if the difference is same.
//
//Example
//Example 1:
//
//Input: A = [1, 2, 3], target = 2, k = 3
//Output: [2, 1, 3]
//Example 2:
//
//Input: A = [1, 4, 6, 8], target = 3, k = 3
//Output: [4, 1, 6]
//Challenge
//O(logn + k) time
//
//Notice
//The value k is a non-negative integer and will always be smaller than the length of the sorted array.
//Length of the given array is positive and will not exceed 10^410
//​Absolute value of elements in the array will not exceed 10^410

public class kClosestNumber {
	public int[] kCN(int[] a, int target, int k) {
		int result[]=new int[k];
		int index=0;
		
		if(a==null || a.length==0) {
			return result;
		}
		//策略是找到小于等于 target的最大值 
		//在利用双针 中心点枚举法 
		int left=findLeftLower(a,target);
		int right=left+1;
		//prepare the return array
		
		for(int i=0;i<k;i++) {
			//use for loop to find k numbers
			if(isLeftCloser(a,target,left,right)) {
				/*put the smaller one first*/
				result[index++]=a[left--];
				//if left smaller, put it into return array 
				//and increase the array index, also move 
				//left pointer to the next left one
			}else {
				result[index++]=a[right++];
			}
		}
		
		return result;
	}
	
	

	private boolean isLeftCloser(int[] a, int target, int left, int right) {
		// TODO Auto-generated method stub
		
		//special case analysis
		//no value on left, then right one is closer 
		if (left<0) {
			return false;
		}
		//no value on right, then the left is closer
		if(right>a.length) {
			return true;
		}
		//two distances are different
		//check the left distance is less than the right
			return target-a[left]<=a[right]-target;
		//two distance are same 
		//according to smaller one rule
		//left one is closer
	}



	private int findLeftLower(int[] a, int target) {
		// use binary search here
		// is find the last o problem 
		// so end=mid
		int start=0;
		int end=a.length-1;
		while(start+1<end) {
			int mid=start+(end-start)/2;
			if(a[mid]==target) {
				start=mid;
			}else if(a[mid]<target) {
				start=mid;
			}else {
				end=mid;
			}
		}
	
		if(a[end]<=target) {
			return end;
		}
		
		if(a[start]<=target) {
			return start;
		}
		
		return -1;
	}
	
	//O(logn + k) 
}
