//392. House Robber
//中文English
//You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
//
//Example
//Example 1:
//
//Input: [3, 8, 4]
//Output: 8
//Explanation: Just rob the second house.
//Example 2:
//
//Input: [5, 2, 1, 3]
//Output: 8
//Explanation: Rob the first and the last house.
//Challenge
//O(n) time and O(1) memory.

public class HouseRobber {
    public long houseRobber(int[] A) {
    	if(A == null || A.length == 0) return 0;
    	long[] dp=new long[A.length+1];
    	//需要额外空间dp[0]空间记录初始状态
    	dp[0]=0;
    	dp[1]=A[1];
    	//记录最开始的和分别为0和1，为啥需要两个初始值，因为小偷不能抢劫连续的两个房子
    	//所以最后状态需要在两个之间做出选择
    	//搭建DP循环 长度是DP自己的长度
    	for(int i=0;i<dp.length; i++) {
    		//A [5 2 1 3]
    		//DP[0 5 . . .]
    		//DP[i]代表盗贼来到第i个房子前所携带的财富
    		//DP当前的值是在前面的值和当前值加两个前的值取最大值 
    		//小偷有两个选择 1. 可以偷当前：总和为两个前的加上当前A[i-1]的值 注意因为坐标不对等 我们的DP比A多一个
    		// 2.可以选择偷之前的
    		//当前最优解为两种情况中最大的
    		dp[i]=Math.max(dp[i-1], dp[i-2]+A[i-1]);
    	}
    	return dp[dp.length-1];
    	//dp的出口
    		
    }
}
