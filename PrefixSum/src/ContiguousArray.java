//994. Contiguous Array
//中文English
//Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
//
//Example
//Example 1:
//
//Input: [0,1]
//Output: 2
//Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
//Example 2:
//
//Input: [0,1,0]
//Output: 2
//Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
//Notice
//The length of the given binary array will not exceed 50,000.
import java.util.*;
public class ContiguousArray {
	public int findMaxLength(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		Map<Integer, Integer> prefix=new HashMap<>(); //key store sum, value store index
		int sum=0;
		int len=0;
		prefix.put(0, -1);
		//initialize prefix, store 0 value at -1 index ( easy for calculate 1 - (-1) =2 )
		for(int i=0; i<nums.length; i++) {
			if(nums[i] == 0) {
				sum--;
			}else {
				sum++;
			}
			//increase or decrease sum by 0 or 1
			if(prefix.containsKey(sum)) {
				//if the sum has been reached before, we will assume they will be equal number of +1/-1
				//therefore it contains same number of 0 and 1
				//we calculate the length by i-index and compare with max length
				len=Math.max(len, i-prefix.get(sum));
			}else {
				prefix.put(sum, i);
				//if sum has not been reached, we record the index of current i
			}
		}
		return len;
	}
}
