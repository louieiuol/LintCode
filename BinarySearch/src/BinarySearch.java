//
//457. Classical Binary Search
//Find any position of a target number in a sorted array. Return -1 if target does not exist.
//
//Example
//Example 1:
//
//Input: nums = [1,2,2,4,5,5], target = 2
//Output: 1 or 2
//Example 2:
//
//Input: nums = [1,2,2,4,5,5], target = 6
//Output: -1
//Challenge
//O(logn) time


public class BinarySearch {
	public int binarySearch(int[] nums, int target) {
		if(nums==null || nums.length ==0) {
			return -1;
		}
		int start=0, end=nums.length-1;
		while(start+1<end) {
			int mid=start+(end-start)/2;
			if(nums[mid]==target) {
				start=mid;
			}else if(nums[mid]<target) {
				start=mid;
			}else {
				end=mid;
			}
		}
		
		if(nums[end]==target) {
			return end;
		}
		
		if(nums[start]==target) {
			return start;
		}
		return -1;
	}
}

//O(logn)
