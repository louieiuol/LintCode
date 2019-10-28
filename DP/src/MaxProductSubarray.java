//191. Maximum Product Subarray
//中文English
//Find the contiguous subarray within an array (containing at least one number) which has the largest product.
//
//Example
//Example 1:
//
//Input:[2,3,-2,4]
//Output:6
//Example 2:
//
//Input:[-1,2,4,1]
//Output:8
public class MaxProductSubarray {
	 public int maxProduct(int[] nums) {
		 if(nums == null || nums.length == 0) return 0;
		 if(nums.length == 1) return nums[0];
		 int[] maxArray=new int[nums.length];
		 int[] minArray=new int[nums.length];
		 //构造dp array 
		 //为什么是dp问题？ 假设从index i * 到j是这个数组中所有的值的最大乘积 是前面数的最大乘积 * 当前[j]的值
		 //而前面数的最大乘积又是从 [j-2]来构建 [j-1] 所以 如果要求以j为结尾的子数组的最大乘积, 先要求以j - 1为结尾的最大乘积
		 //因为当前数的乘积是根据前一个数是正数或者负数来决定 所以需要分别记录最大值和最小值
		 maxArray[0]=nums[0];
		 minArray[0]=nums[0];
		 //初始化dp
		 for(int i=1; i< nums.length ;i++) { //注意是从1 开始
			 maxArray[i]=Math.max(nums[i], Math.max(maxArray[i-1]* nums[i], minArray[i-1]* nums[i]));
			 minArray[i]=Math.min(nums[i], Math.min(maxArray[i-1]* nums[i], minArray[i-1]* nums[i]));
			 //最大值的取值取决于前面的乘积 如果为0 那么从当前开始 重新计算 如果为正数 那么最大值为 上个最大值乘以当前值 如果为负数 
			 //那么最大值为 上个数的最小值乘以当前值 把3个放在一起比较 计算当前最大 存入maxArray中 最小值取值也如此
		 }
		 int max=nums[0];
		 for(int i=0; i<nums.length; i++) {
			 max=Math.max(max, maxArray[i]);
		 }
		 return max;
	 }
}
