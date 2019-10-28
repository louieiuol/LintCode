//911. Maximum Size Subarray Sum Equals k
//中文English
//Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
//
//Example
//Example1
//
//Input:  nums = [1, -1, 5, -2, 3], k = 3
//Output: 4
//Explanation:
//because the subarray [1, -1, 5, -2] sums to 3 and is the longest.
//Example2
//
//Input: nums = [-2, -1, 2, 1], k = 1
//Output: 2
//Explanation:
//because the subarray [-1, 2] sums to 1 and is the longest.
//Challenge
//Can you do it in O(n) time?
//
//Notice
//The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

import java.util.*;
public class MaximumSizeSubarraySumEqualsK {
	//方法1 prefix sum的2重循环 O(n^2) 不推荐
	public int maxSubArrayLen(int[] nums, int k) {
		int res=0;
		if(nums == null || nums.length == 0) return res;
		int[] prefixSum=new int[nums.length+1];
		//建prefix sum时用比原来长度+1的array装 默认 array[0]=0
		HashMap<Integer, Integer> map=new HashMap<>();
		//建hashmap压缩查找时间 Key 存Sum value 存index 
		map.put(k, 0);
		//设立初始Sum为k 在index 为0的位置
		for(int i=1; i<=nums.length; i++) {
			//从1-n进行循环 从而加把 0 到 n-1都加到prefixSum 里面
			prefixSum[i]=prefixSum[i-1]+nums[i-1];
			if(map.containsKey(prefixSum[i])) {
				//如果map中已经存过所要找的sum 证明从上次访问点和这次访问点正好差k 中间的值的sum为k 那么比较是否最大距离
				res=Math.max(res, i-map.get(prefixSum[i]));
			}
			if(!map.containsKey(prefixSum[i]+k)) {
				//如果map中不包括当前sum加k的值时 （重复的我们不再记录了 因为第一次一定是最小的 剩下的减去的值一定比之前值大）
				//我们记录将来可能会与当前在+k后重合的点 
				map.put(prefixSum[i]+k, i);
				//我们给未来会走到的点先预留个值 并加入当前index
			}
		}
		return res;
	}
}
