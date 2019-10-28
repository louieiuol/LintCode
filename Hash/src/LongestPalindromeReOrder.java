
/*627. Longest Palindrome
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Example
Example 1:

Input : s = "abccccdd"
Output : 7
Explanation :
One longest palindrome that can be built is "dccaccd", whose length is `7`.
Notice
Assume the length of given string will not exceed 1010.
*/
import java.util.*;

public class LongestPalindromeReOrder {
    /**
     * @param s: a string which consists of lowercase or uppercase letters
     * @return: the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        // write your code here
        if(s==null) return 0;
        if(s.length()==0) return 0;
        HashMap<Character, Integer> map=new HashMap<>();
        for(int i=0; i<s.length();i++){
            char c=s.charAt(i);
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else{
                map.put(c,1);
            }
        }   
        int res=0;
        boolean flag=false;
        for(char c: map.keySet()){
            int num=map.get(c);
            if(num % 2 ==0) {
                res+=num;
            }else if(num %2 == 1 && !flag) {
                res+=num; 
                flag=true;
            }else if(num%2 == 1 && flag) {
                res+=(num-1);
            }else if(num == 1 && !flag) {
                res++;
                flag=true;
            }else{
                
            }
        }
        return  res;
    }
}
