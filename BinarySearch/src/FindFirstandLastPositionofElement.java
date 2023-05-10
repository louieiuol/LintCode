//题目描述
//评论 (1.3k)
//题解(1682)
//提交记录
//34. Find First and Last Position of Element in Sorted Array
//Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
//
//If target is not found in the array, return [-1, -1].
//
//Follow up: Could you write an algorithm with O(log n) runtime complexity?
//
// 
//
//Example 1:
//
//Input: nums = [5,7,7,8,8,10], target = 8
//Output: [3,4]
//Example 2:
//
//Input: nums = [5,7,7,8,8,10], target = 6
//Output: [-1,-1]
//Example 3:
//
//Input: nums = [], target = 0
//Output: [-1,-1]
// 
//
//Constraints:
//
//0 <= nums.length <= 105
//-109 <= nums[i] <= 109
//nums is a non-decreasing array.
//-109 <= target <= 109


//Leetcode 34
public class FindFirstandLastPositionofElement {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new int[]{-1, -1};
        if(nums.length == 1){
            if(nums[0] == target){
                return new int[]{0, 0};
            }else{
                return new int[]{-1, -1};
            }
        }
        int[] res = new int[2];
        res[0] = findFirst(nums, target);
        res[1] = findLast(nums, target);
        return res;
    }

    private int findFirst(int[] nums, int target){
        int start = 0;
        int end = nums.length-1;
        while(start+1 < end){
            int mid = start + (end-start)/2;
            if(nums[mid] == target){
                while(mid >= 0 && nums[mid] == target){
                    mid--;
                }
                return mid+1;
            }else if(nums[mid] < target){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(nums[start] == target){
            return start;
        }
        if(nums[end] == target){
            return end;
        }
        return -1;
    }

    private int findLast(int[] nums, int target){
        int start = 0;
        int end = nums.length-1;
        while(start+1 < end){
            int mid = start + (end-start)/2;
            if(nums[mid] == target){
                while(mid < nums.length && nums[mid] == target){
                    mid++;
                }
                return mid-1;
            }else if(nums[mid] < target){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(nums[end] == target){
            return end;
        }
        if(nums[start] == target){
            return start;
        }
        return -1;
    }
}
