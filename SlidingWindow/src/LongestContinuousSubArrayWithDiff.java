//1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit

import java.util.*;

//用Java treemap 实现实时排序 (红黑树)
public class LongestContinuousSubArrayWithDiff {
    public int longestSubarray(int[] nums, int limit) {
        if(nums == null || nums.length == 0 || limit <  0) return 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int j = 0;
        int longest = 0;
        for(int i = 0; i < nums.length; i++){
            while(j < nums.length && withinLimit(map, limit)){
                map.put(nums[j], map.getOrDefault(nums[j], 0)+1);
                if(map.lastKey() - map.firstKey() <= limit){
                    longest = Math.max(longest, j-i+1);
                }
                j++;
            }
            if(map.lastKey() - map.firstKey() <= limit){
                longest = Math.max(longest, j-i);
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0)-1);
            if(map.get(nums[i]) <= 0){
                map.remove(nums[i]);
            }
        }
        return longest;
    }
    
    private boolean withinLimit(TreeMap<Integer, Integer> map, int limit){
        if(map.isEmpty()){
            return true;
        }
        return (map.lastKey() - map.firstKey()) <= limit;
    }
}
