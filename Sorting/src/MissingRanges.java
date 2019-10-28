//641. Missing Ranges
//中文English
//Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
//
//Example
//Example 1
//
//Input:
//nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99
//Output:
//["2", "4->49", "51->74", "76->99"]
//Explanation:
//in range[0,99],the missing range includes:range[2,2],range[4,49],range[51,74] and range[76,99]
//Example 2
//
//Input:
//nums = [0, 1, 2, 3, 7], lower = 0 and upper = 7
//Output:
//["4->6"]
//Explanation:
//in range[0,7],the missing range include range[4,6]

import java.util.*;
public class MissingRanges {
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> res=new ArrayList<>();
		if(nums == null || nums.length == 0){
			addRange(res, lower, upper);
			return res;
		}
		
		addRange(res, lower, (long) nums[0]-1);
		for(int i=1; i<nums.length; i++) {
			addRange(res, (long) nums[i-1]+1, (long ) nums[i]-1);
			//array里面的值要转为long 接受Integer.MIN 和Integer.MAX
		}
		addRange(res, (long) nums[nums.length-1]+1, upper);
		return res;
	}
	
	private void addRange(List<String> res, long lower, long upper) {
		if(lower>upper) {
			return;
		}
		if(lower == upper) {
			res.add(""+lower);
		}else{
			res.add(lower+"->"+upper);
		}
	}
}
