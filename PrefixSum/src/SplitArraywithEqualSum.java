//877. Split Array with Equal Sum
//中文English
//Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:
//
//0 < i, i + 1 < j, j + 1 < k < n - 1
//Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
//We define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.
//
//If it exists, return true, otherwise return false.
//
//Example
//Example 1:
//
//Input: [1,3,2,1,3,2,1,3,2,1,3]
//Output: true
//Explanation: (2,5,8) => Four subarrays are all [1, 3].
//Example 2:
//
//Input: [1,2,1,2,1,2,1]
//Output: true
//Explanation: (1,3,5)
//Notice
//1.1 <= n <= 2000.
//2. Elements in the given array will be in range [-1,000,000, 1,000,000].
import java.util.*;

public class SplitArraywithEqualSum {
	public boolean splitArray(List<Integer> nums) {
		if(nums == null || nums.size() == 0) return false;
		//因为要计算sum 搭建前缀和数组 
		int[] prefixSum=new int[nums.size()];
		prefixSum[0]=nums.get(0);
		for(int i=1; i<nums.size(); i++) {
			//S[i]=A[i]+S[i-1]
			prefixSum[i]=prefixSum[i-1]+nums.get(i);
		}
		for(int j=3; j<nums.size(); j++) {
			//从中点分割
			Set<Integer> set=new HashSet<>();
			//用set节省一次判断
			for(int i=1; i<j-1; i++) {
				//给0和 j-1 分别预留一个位置 切割中间的点寻找可能值
				if(prefixSum[j-1]-prefixSum[i] == prefixSum[i-1]) {
					//求从 i+1到 j-1的值 A[i+1] + ...A[j-1] = S[j-1]-S[i] 
					//求从 0 到 i-1的值 A[0]+ ... A[i-1]= S[i-1]-S[-1](0)
					set.add(prefixSum[i-1]);
					//加入可能备选值
				}
			}
			for(int k=j+2; k<nums.size()-1; k++) {
				//给j+1 length-1 分别预留一个位置 切割中间的点寻找可能值
				if((prefixSum[k-1]-prefixSum[j] == prefixSum[nums.size()-1]- prefixSum[k])
						&& (set.contains(prefixSum[nums.size()-1]- prefixSum[k]))) {
					//求从j+1 到 k-1 A[j+1]+...A[k-1] = S[k-1]-S[j]
					//求从k+1 到 length-1 A[k+1]+ .... A[length-1] =S[length-1]-S[k]
					//判断是否与之前的set中的值相等
					return true;
				}
			}
		}
		//没找到返回false
		return  false;
	}
}
