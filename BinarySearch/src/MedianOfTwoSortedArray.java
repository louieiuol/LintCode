//65. Median of two Sorted Arrays
//中文English
//There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.
//
//Example
//Example 1
//
//Input:
//A = [1,2,3,4,5,6]
//B = [2,3,4,5]
//Output: 3.5
//Example 2
//
//Input:
//A = [1,2,3]
//B = [4,5]
//Output: 3
//Challenge
//The overall run time complexity should be O(log (m+n)).
//
//Clarification
//The definition of the median:
//
//The median here is equivalent to the median in the mathematical definition.
//
//The median is the middle of the sorted array.
//
//If there are n numbers in the array and n is an odd number, the median is A[(n-1)/2]A[(n−1)/2].
//
//If there are n numbers in the array and n is even, the median is (A[n / 2] + A[n / 2 + 1]) / 2(A[n/2]+A[n/2 +1])/2.
//
//For example, the median of the array A=[1,2,3] is 2, and the median of the array A=[1,19] is 10.

public class MedianOfTwoSortedArray {
	 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		 if(nums1 == null || nums2== null) return 0;
		 if(nums1.length>nums2.length) {
			 return findMedianSortedArrays(nums2, nums1);
		 }
		 //我们为了优化时间 只循环小的array
		 int sum=nums1.length + nums2.length;
		 int half=(sum+1)/2;
		 // 因为int/2向下取整 所以要+1
		 int start=0;
		 int end=nums1.length;
		 //这里不是 nums1.length -1 因为怕 nums2的开头正好是 中间值
		 while(start<end) {
			 //这道题因为也没有target 最好用start end指针缩成一点的办法
			 int mid=start+(end-start)/2;
			 if(nums2[half-mid-1] > nums1[mid]) {
				 //把m和比较
				 //如果A[m] < B[n-1] 则说明A中右边元素过小 需要往右移动找更大的    
				 start=mid+1;
				 //避免左右指针重合
			 }else {
				 end=mid;
				//反之左移动
			 }
		 }
		 int m=start;
		 int n=half-start;
		//确定 m n partition的位置  
		 
		 int med1=Math.max(m-1 < 0 ? Integer.MIN_VALUE : nums1[m-1], 
				 		   n-1 < 0 ? Integer.MIN_VALUE : nums2[n-1]);
		 
		 int med2=Math.min(m >= nums1.length ? Integer.MAX_VALUE : nums1[m],
				 			n >= nums2.length ? Integer.MAX_VALUE : nums2[n]);
		 
	        //如果最后 m n 越界则赋予极值 不参与比较
	        //在左边一半比较两个值谁更大  取大       
	        //在右边一半比较两个值谁更小  取小

		 if(sum %2 == 1) {
			 return med1;
			//奇数情况直接返回第一个的最大值
		 }else {
	            //偶数情况返回两者的平均值
			 return (med1+med2)*0.5;
		 } 
	 }
}
