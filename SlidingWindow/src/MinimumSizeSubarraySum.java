//406. Minimum Size Subarray Sum
//中文English
//Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return -1 instead.
//
//Example
//Example 1:
//
//Input: [2,3,1,2,4,3], s = 7
//Output: 2
//Explanation: The subarray [4,3] has the minimal length under the problem constraint.
//Example 2:
//
//Input: [1, 2, 3, 4, 5], s = 100
//Output: -1
//Challenge
//If you have figured out the O(nlog n) solution, try coding another solution of which the time complexity is O(n).

public class MinimumSizeSubarraySum {
	//version 1: O(n^2) 
	public int minimumSize1(int[] nums, int s) {
		if(nums == null || nums.length == 0) return -1;
		int len=Integer.MAX_VALUE;
		//记录长度
		for(int i=0; i<nums.length; i++) {
			int sum=0;
			//记录从i到j的值
			for(int j=i; j<nums.length; j++) {
				sum+=nums[j];
				if(sum>=s) {
					len=Math.min(len, j-i+1);
					//j-i+1是i到j的长度
					break;
					//找到第一个 比s大的就要break 避免超时
				}
			}
		}
		if(len == Integer.MAX_VALUE) return -1;
		//如果len还是初始值 则说明没有比s大的
		return len;
	}
	//version2: O(2n)
	public int minimumSize2(int[] nums, int s) {
		if(nums == null || nums.length == 0) return -1;
		int len=Integer.MAX_VALUE;
		int sum= 0, j=0; //用sum来记录不回溯的值的和 用j来记录不回溯的最长坐标
		for(int i=0; i<nums.length;i++) {
			//此时i并没有参与加和计算 只是用于记录开始位置
			while(j<nums.length && sum<s ) {
				//移动后面的尾巴
				sum+=nums[j];
				//记录加和
				j++;
				//更新指针
			}
			//计算前面的不满足条件的结果的和
			//如果一次计算中 i=0到j中有这么段长度不满足条件 去掉nums[0] 剩下的记为sum 
			//那么i=1从到j那里 不会影响之前计算的从sum变化 只需要加上nums[i]值即可
			if(sum>=s) {
				//更新长度
				len=Math.min(len,j-i); //注意这里没有+1 j已经更新成j+1
				//这里不用break 已经跳出了while循环了
			}
			sum-=nums[i];
			//减去前面的头
		}
		if(len == Integer.MAX_VALUE) return -1;
		//如果len还是初始值 则说明没有比s大的
		return len;
	}
}
