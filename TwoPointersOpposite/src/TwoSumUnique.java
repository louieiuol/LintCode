import java.util.Arrays;

public class TwoSumUnique {
	public int twoSum6(int[] nums, int target) {
		//alternative way: quick sort
        // write your code here
		Arrays.sort(nums);
        int left=0, right= nums.length-1;
        int counter=0;
        while(left < right){
            int sum=nums[left]+nums[right];
            if(sum == target){
                while((left<right) && (nums[left]==nums[left+1])){
                    left++;
                }
                while((left<right) && (nums[right]==nums[right-1])){
                    right--;
                }
                left++;
                right--;
                counter++;
            }else if(sum < target){
                left++;
            }else{
                right--;
            }
        }
        return counter;
    }
}
