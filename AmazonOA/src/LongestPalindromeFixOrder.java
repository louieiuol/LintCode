/*627. Longest Palindrome
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that within those letters.
This is case sensitive, for example "Aa" is not considered a palindrome here.
Example
Example 1:

Input : s = "abccccdd"
Output : 4
Explanation :
One longest palindrome that in the string is "cccc", whose length is `4`.
Notice
Assume the length of given string will not exceed 1010.
*/

public class LongestPalindromeFixOrder {
	    /**
	     * @param s: a string which consists of lowercase or uppercase letters
	     * @return: the length of the longest palindromes that can be built
	     */
	    public int longestPalindrome(String s) {
	        // write your code here
	        if(s!=null){
	            if(s.length()>0){
	                int max=0;
	                for(int i=0;i<s.length();i++){
	                    int len=findmax(s,i,i);
	                    if(len>max){
	                        max=len;
	                    }
	                    len=findmax(s,i,i+1);
	                    if(len>max){
	                        max=len;
	                    }
	                }
	                return max;
	            }
	        }
	        return 0;
	    }
	    private int findmax(String s, int left, int  right){
	        int len=0;
	        while(left>=0 && right<s.length()){
	            if(s.charAt(left)!=s.charAt(right)){
	                break;
	            }
	            len+= left==right? 1:2;
	            left--;
	            right++;
	        }
	        return len;
	    }
}
