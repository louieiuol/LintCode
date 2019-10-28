//585. Maximum Number in Mountain Sequence
//中文English
//Given a mountain sequence of n integers which increase firstly and then decrease, find the mountain top.
//
//Example
//Example 1:
//
//Input: nums = [1, 2, 4, 8, 6, 3] 
//Output: 8
//Example 2:
//
//Input: nums = [10, 9, 8, 7], 
//Output: 10


public class FindMaxMountainArray {
    public int mountainSequence(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0) return -1;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[nums.length-1]);
        //判断array 至少3个点 从而可以跟左右两边的比较 
        int start=1;
        int end=nums.length-2;
        //从第二个点开始 到倒数第二个点结束
        while(start + 1 < end){
            int mid=start+(end-start)/2;
            if(nums[mid] < nums[mid-1] && nums[mid] > nums[mid+1] ){
            	//如果是递减区间 往左边找
                end = mid;
            }else if (nums[mid] > nums[mid-1] && nums[mid] < nums[mid+1]){
            	//如果是递增区间 往右边找
                start =mid;
            }else{
            	//比两边都大的情况直接返回 
                return nums[mid];
            }
        }
        //最后还要和开头结尾的4个点比较 返回最大值 
        return Math.max(nums[start], Math.max(nums[end], Math.max(nums[0], nums[nums.length-1])));
    }
}
