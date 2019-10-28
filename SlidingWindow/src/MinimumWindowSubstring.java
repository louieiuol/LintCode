//32. Minimum Window Substring
//中文English
//Given a string source and a string target, find the minimum window in source which will contain all the characters in target.
//
//Example
//Example 1:
//
//Input:
//""
//""
//Output:
//""
//Example 2:
//
//Input:
//"ADOBECODEBANC"
//"ABC"
//Output:
//"BANC"
//Challenge
//Can you do it in time complexity O(n) ?
//
//Clarification
//Should the characters in minimum window has the same order in target?
//
//Not necessary.
//Notice
//If there is no such window in source that covers all characters in target, return the emtpy string "".
//If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in source.
//The target string may contain duplicate characters, the minimum window should cover all characters including the duplicate characters in target.

public class MinimumWindowSubstring {
	public String minWindow(String source , String target) {
		if(source == null || target== null) return "";
		if(source.length()<target.length()) return "";
		String res="";
		int len=Integer.MAX_VALUE, j=0;
		int[] sourceHash=new int[256];
		int[] targetHash=new int[256];
		for(int i=0; i<target.length(); i++) {
			targetHash[target.charAt(i)]+=1;
		}
		for(int i=0; i<source.length();i++) {
			while(j<source.length() && !isInclude(sourceHash, targetHash)) {
				sourceHash[source.charAt(j)]+=1;
				j++;
			}
			if(isInclude(sourceHash, targetHash)) {
				if(j-i<len) {
					len=j-i;
					res=source.substring(i,j);
				}
			}
			sourceHash[source.charAt(i)]-=1;
		}
		return res;
	}
	private boolean isInclude(int[] sourceHash, int[] targetHash) {
		for(int i=0; i<256;i++) {
			if(sourceHash[i]<targetHash[i]) {
				return false;
			}
		}
		return true;
	}
}
