//647. Find All Anagrams in a String
//中文English
//Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
//
//Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 40,000.
//
//The order of output does not matter.
//
//Example
//Example 1:
//
//Input : s =  "cbaebabacd", p = "abc"
//Output : [0, 6]
//Explanation : 
//The substring with start index = 0 is "cba", which is an anagram of "abc".
//The substring with start index = 6 is "bac", which is an anagram of "abc".

import java.util.*;
public class SubstringAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
    	List<Integer> res=new ArrayList<>();
    	if(s == null || p == null) return res;
    	if(s.length() < p.length()) return res;
    	//需要判断如果s比p的长度还要小 那么则返回空
    	char[] sArray=s.toCharArray();
    	char[] pArray=p.toCharArray();
    	//利用256位数组代替hash检查相同
    	int[] phash=new int[256];
    	int[] shash=new int[256];
    	for(int i=0; i<pArray.length; i++) {
    		phash[pArray[i]]++;
    	}
    	//初始化p数组对应的hash char对应的index正好是0-256位char的
    	int j=0;
    	for(int i=0; i<sArray.length; i++) {
    		while(j<i+pArray.length && j<sArray.length) {
    			shash[sArray[j++]]++;
    			if(isAnagram(phash, shash)) {
    				res.add(i);
    			}
    		}
    		shash[sArray[i]]--;
    	}
    	return res;	 
    }

	private boolean isAnagram(int[] phash, int[] shash) {
		// TODO Auto-generated method stub
		for(int i=0; i<256; i++) {
			if(phash[i]!=shash[i]) {
				return false;
			}
		}
		return true;
	}
}
