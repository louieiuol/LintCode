//384. Longest Substring Without Repeating Characters
//中文English
//Given a string, find the length of the longest substring without repeating characters.
//
//Example
//Example 1:
//
//Input: "abcabcbb"
//Output: 3
//Explanation: The longest substring is "abc".
//Example 2:
//
//Input: "bbbbb"
//Output: 1
//Explanation: The longest substring is "b".
//Challenge
//time complexity O(n)s
public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
		if(s == null || s.length() == 0) return 0;
		int len=0, j=0;
		int[] hash=new int[256];
		for(int i=0; i<s.length(); i++) {
			while(j < s.length() && hash[s.charAt(j)] == 0) {
				hash[s.charAt(j)] =1;
				len=Math.max(len, j-i+1);
				j++;
			}
			hash[s.charAt(i)]=0;
		}
		return len;
	}
}
