//5. Kth Largest Element
//中文English
//Find K-th largest element in an array.
//
//Example
//Example 1:
//
//Input:
//n = 1, nums = [1,3,4,2]
//Output:
//4
//Example 2:
//
//Input:
//n = 3, nums = [9,3,2,4,8]
//Output:
//4
//Challenge
//O(n) time, O(1) extra memory.
//
//Notice
//You can swap elements in the array

//quick select的经典应用 
//时间复杂度 O(logn)

public class KthLargestElements {
	 public int kthLargestElement(int k, int[] nums) {
		 	if(nums == null || nums.length == 0|| k < 1 || k > nums.length ) return -1;
	        // 如果 k 的范围不在 [1,nums.length]之间 返回 -1
	        return quickSelect(nums, 0, nums.length - 1, nums.length-k);
	        //我们把array按照 从小到大排序 找k个最大的就是找length-k个最小的 
	    }

	    private int quickSelect(int[] nums, int left, int right, int k) {
	    	if(left == right) return nums[k];
	    	//当left >= right 的时候 直接返回 nums[k]位置的数 即已经找到了 
	        int pivot = nums[left+(right-left)/2];
	        //选取 中点为pivot
	        int i = left, j = right;
	        //初始化左右两根 相向双针 
	        while (i <= j) {
	        	//左边小于等于右边时候 
	            while (i <= j && nums[i] < pivot) {
	            	//找到左边数第一个大于pivot的数 也就是使左边的都小于k
	                i++;
	            }
	            while (i <= j && nums[j] > pivot) {
	            	//找到右边数第一个小于pivot的数 也就是使右边的都大于k
	                j--;
	            }
	            if (i <= j) {
	            	//如果仍然在 i<=j的范围内 调换 i j 
	            	swap(nums, i, j);
	            	//i j同时增减 
	                i++;
	                j--;
	            }
	        }

	        //在i >= j之后  
	        if (k <= j) {
	        	//如果 第length-k个最小的 在j前面或者相等 那么从前面 left 到 j去找 
	            return quickSelect(nums, left, j, k);
	        }
	        
	        if (k >= i) {
	        	//如果 第length-k个最小的 在i后面或者相等 那么从后面 i 到 right去找
	            return quickSelect(nums, i, right, k);
	        }
	        //返回 第length-k个最小的
	        return nums[k];
	    }
	    
	    private void swap(int[] nums, int a ,int b) {
            int tmp = nums[a];
            nums[a] = nums[b];
            nums[b] = tmp;	    	
	    }
}
