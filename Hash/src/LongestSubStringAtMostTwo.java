//928. Longest Substring with At Most Two Distinct Characters
//中文English
//Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

import java.util.*;
public class LongestSubStringAtMostTwo {
	public int longestSubStringAtMostTwo(String s) {
		if(s == null || s.length() == 0) return 0;
		HashMap<Character, Integer> map=new HashMap<>();
		//map永远记录元素出现的最大index 
		int i=0;
		int max=0;
		int l=0;
		while(i < s.length()) {
			if(map.size() <= 2) {
				char c=s.charAt(i);
				map.put(c, i);
				i++;
			}
			if(map.size() > 2) {
				int left=s.length();
				for(int index: map.values()) {
					left=Math.min(left, index);
				}
				//找最小index的元素
				char c=s.charAt(left);
				map.remove(c);
				//移除的时候会把指针指向元素出现的最后一次 所以会把所有duplicate都移除掉
				l=left+1;
				//更新左指针
			}
			max=Math.max(max, i-l);
			//循环完后计算最大值
		}
		return max;
	}
}
