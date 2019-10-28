//594. strStr II
//Implement strStr function in O(n + m) time.
//
//strStr return the first index of the target string in a source string. The length of the target string is m and the length of the source string is n.
//If target does not exist in source, just return -1.
//
//Example
//Example 1:
//
//Input：source = "abcdef"， target = "bcd"
//Output：1
//Explanation：
//The position of the first occurrence of a string is 1.
//Example 2:
//
//Input：source = "abcde"， target = "e"
//Output：4
//Explanation：
//The position of the first occurrence of a string is 4.

public class StrStrII {
// using KMP to implement it in O(n+m) time.
    public int strStr2(String source, String target) {
    	if(source == null || target == null) return -1;
    	if(target.length() == 0) return 0;
    	if(source.equals(target)) return 0;
    	if(source.length() == 0) return -1;
    	int mod=Integer.MAX_VALUE / 113;
    	int hash_value=0;
    	for(int i=0; i<target.length(); i++) {
    		hash_value= (hash_value * 31 + target.charAt(i)) % mod;
    	}
    	int pre_value=1;
    	for(int i=0; i<target.length()-1; i++) {
    		pre_value= pre_value * 31 % mod;
    	}
    	int value=0;
    	for(int i=target.length()-1; i<source.length(); i++){
    		if(i >= target.length()){
    			value=(value-pre_value * source.charAt(i-target.length())) % mod;
    			if(value < 0) {
    				value += mod;
    			}
    		}
    		value = (value * 31 + source.charAt(i)) % mod;
    		if(i >= target.length() && value == hash_value) {
    			if(target.equals(source.substring(i-target.length()+1, i+1))) {
    				return i-target.length()+1;
    			}
    		}
    	}
    	return -1;
    }
}
