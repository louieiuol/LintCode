
//464. Sort Integers II
//中文English
//Given an integer array, sort it in ascending order in place. Use quick sort, merge sort, heap sort or any O(nlogn) algorithm.
//
//Example
//Example1:
//
//Input: [3, 2, 1, 4, 5], 
//Output: [1, 2, 3, 4, 5].
//Example2:
//
//Input: [2, 3, 1], 
//Output: [1, 2, 3].


public class QuickSort {
    public void sortIntegers2(int[] A) {
        if(A == null || A.length == 0) return;
        quicksort(A, 0, A.length-1);
    }	

	public void quicksort(int[] nums, int left, int right) {
		if(left>=right) {
			return;
		}
		int pivot= nums[(left+right)/2];
		int index=partition(nums,left,right,pivot);
		quicksort(nums,left,index-1);
		quicksort(nums,index,right);
		
	}

	private int partition(int[] nums, int left, int right, int pivot) {
		
		while(left<=right) {
			while(nums[left] < pivot) {
				left++;
			}
			
			while(nums[right] > pivot) {
				right--;
			}
			
			if(left<=right) {
				int tmp=nums[left];
				nums[left]=nums[right];
				nums[right]=tmp;
				left++;
				right--;
			}
		}
		return left;
	}
}
