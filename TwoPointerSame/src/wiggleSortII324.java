import java.util.Arrays;
/*324. Wiggle Sort II
Medium
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:

Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].
Example 2:

Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2].
Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?*/

public class wiggleSortII324 {
	public void wiggleSort(int[] nums) {
        if(nums == null || nums.length == 0) return;
        Arrays.sort(nums); // sort array first 
        int[] res=new int[nums.length];
        boolean flag=true;
        //use flag to flip between small and large numbers
        if(nums.length %2 ==0){
        //even number situation
        int right=nums.length-1;
        //set large number list start at end of sorted array
        int left=nums.length/2-1;
        //set small number list start at middle of sorted array
        //avoid same number put together
        for(int i=0;i<nums.length; i++){
            if(flag){
                res[i]=nums[left];
                left--;
                //decrease pointer
            }else{
                res[i]=nums[right];
                right--;
                //decrease pointer
            }
            flag=!flag;
        }
        }else{
        //odd number situation
        int right=nums.length-1;
        int left=nums.length/2;
        for(int i=0;i<nums.length; i++){
            if(flag){
                res[i]=nums[left];
                left--;
            }else{
                res[i]=nums[right];
                right--;
            }
            flag=!flag;
        }           
        } 
        
        //deep copy of the array
        for(int i=0; i<nums.length; i++){
            nums[i]=res[i];
        }
        return;
    }
}
