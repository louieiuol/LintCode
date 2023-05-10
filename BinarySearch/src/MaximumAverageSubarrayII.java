//617. Maximum Average Subarray II
//中文English
//Given an array with positive and negative numbers, find the maximum average subarray which length should be greater or equal to given length k.
//
//Example
//Example 1:
//
//Input:
//[1,12,-5,-6,50,3]
//3
//Output:
//15.667
//Explanation:
// (-6 + 50 + 3) / 3 = 15.667
//Example 2:
//
//Input:
//[5]
//1
//Output:
//5.000
//Notice
//It's guaranteed that the size of the array is greater or equal to k.


public class MaximumAverageSubarrayII {
    public double maxAverage(int[] nums, int k) {
    	if(nums == null || nums.length == 0 || k == 0) return 0;
    	//先找到array的最大值和最小值 猜中间的值来做贪心 
    	int minValue=Integer.MAX_VALUE;
    	int maxValue=Integer.MIN_VALUE;
    	for(int num: nums) {
    		minValue=Math.min(minValue, num);
    		maxValue=Math.max(maxValue, num);
    	}
    	double start=minValue;
    	double end=maxValue;
    	//返回结果是double 我们这里start/end是返回值也用double
    	while(start + 0.00001 < end) {
    		//因为也是猜最小值的方法 我们需要趋近一个值 然后又是double 所以需要加一个极小值
    		double mid=start+(end-start)/2;
    		if(isValid(nums, k ,mid)) {
    			//如果是符合条件的 想尽量的大 往右边移动
    			start=mid;
    		}else {
    			//如果不符合条件 往小的移动
    			end=mid;
    		}
    	}
    	//最后剩余一个点 返回start
    	return start;
    }

    //O(n)
	private boolean isValid(int[] nums, int k, double avg) {
		//合法的点满足 长度 >=k,  avg值要 >=mid
		double rightSum=0, leftSum=0, leftMinSum=0;
		//left, right, left min 来记录sliding window 的值
		//初始化 rightSum 先走k个值 来保证长度>=k
		for(int i=0; i<k; i++) {
			rightSum+=nums[i]- avg; 
			//每次走的 时候减去average
		}
		for(int i=k; i<nums.length; i++) {
			if(rightSum >= leftMinSum) {
				//每次都和avg判断 只要有大于就返回true
				return true;
			}
			rightSum += nums[i]-avg;
			leftSum +=nums[i-k]-avg;
			//rightSum 都根据当前结果累加 注意减去average 方便比较
			leftMinSum=Math.min(leftMinSum, leftSum);
			//left min sum根据之前的leftSum最小值更新
		}
		//最后再判断一次 没有返回false
		if(rightSum >= leftMinSum) {
			return true;
		}
		return false;
	}
    
    //O(nlog(max + min))
}
