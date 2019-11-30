//18. Subsets II
//中文English
//Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
//
//Example
//Example 1:
//
//Input: [0]
//Output:
//[
//  [],
//  [0]
//]
//Example 2:
//
//Input: [1,2,2]
//Output:
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//]
//Challenge
//Can you do it in both recursively and iteratively?
//
//Notice
//Each element in a subset must be in non-descending order.
//The ordering between two subsets is free.
//The solution set must not contain duplicate subsets.

import java.util.*;
public class SubsetsII {
    List<List<Integer>> result=new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0){
            result.add(new ArrayList<>());
            return result;
        }
        Arrays.sort(nums);
        //去重要sort 
        dfs(nums, 0, new ArrayList<>());
        return result;
    }
    
    private void dfs(int[] nums, int index, List<Integer> lst){
        if(index > nums.length){
        	//index 等于nums.length的时候还要加入一次结果 所以大于的时候才返回
            return;
        }
        result.add(new ArrayList<>(lst));
        for(int i=index; i<nums.length; i++){
            if(i != index && nums[i-1] == nums[i]){
            	//如果不是第一个(这里不是跟0比) 并且 前后两个相等 不让他们跳着选  
                continue;
            }
            lst.add(nums[i]);
            dfs(nums, i+1, lst);
            lst.remove(lst.size()-1);
        }
    }
}
