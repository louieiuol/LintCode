//Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
//Each element in the array represents your maximum jump length at that position.
//
//Your goal is to reach the last index in the minimum number of jumps.
//
//Example:
//
//Input: [2,3,1,1,4]
//Output: 2
//Explanation: The minimum number of jumps to reach the last index is 2.
//    Jump 1 step from index 0 to 1, then 3 steps to the last index.
//Note:
//
//You can assume that you can always reach the last index.
//Leetcode 45
//
//Frog Hopping

import java.util.*;
public class JumpGameII {
    public int jump(int[] nums) {
        if(nums.length==1) return 0;
        int[] dp=new int[nums.length];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
   
        for(int i=0;i<nums.length;i++){
            
            for(int j=1;j<=nums[i];j++){
                if(i+j<dp.length)   dp[i+j]=Math.min(dp[i]+1,dp[i+j]);
             
            }
         

        }
        return dp[nums.length-1];
    }
}
