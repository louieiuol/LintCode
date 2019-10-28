//640. One Edit Distance
//中文English
//Given two strings S and T, determine if they are both one edit distance apart.
//
//Example
//Example 1:
//
//Input: s = "aDb", t = "adb" 
//Output: true
//Example 2:
//
//Input: s = "ab", t = "ab" 
//Output: false
//Explanation:
//s=t ,so they aren't one edit distance apart

public class OneEditDistance {
	public boolean isOneEditDistance(String s, String t) {
		if(s == null || t == null) return false;	
		if(s.length()>t.length()) return isOneEditDistance(t, s);
		//重新call function 保证s 长度比 t小
		if(t.length() - s.length() >1) return false;
		//如果t比s 长度超过1 false
		char[] sArray=s.toCharArray();
		char[] tArray=t.toCharArray();
		if(s.length() == t.length()) {
			if(t.equals(s)) return false;
			//根据题意 完全相等的两个 false
			int counter =0; 
			for(int i=0; i<sArray.length; i++) {
				if(sArray[i]!=tArray[i]) {
					counter++;
				}
			}
			return counter == 1 ? true : false;
			//如果不相同的个数超过1 false 
		}else {
			for(int i=0; i<sArray.length; i++) {
				if(sArray[i]!=tArray[i]) {
					return s.substring(i).equals(t.substring(i+1));
					//当遇到 在i位时 s[i]不和t[i]相等了 我们判断之后的substring 是否相等
				}
			}
		}
		//其他情况一律false
		return false;
	}
}
