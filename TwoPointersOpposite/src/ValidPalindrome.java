//891. Valid Palindrome II
//中文English
//Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
//
//Example
//Example 1:
//
//Input: s = "aba"
//Output: true
//Explanation: Originally a palindrome.
//Example 2:
//
//Input: s = "abca"
//Output: true
//Explanation: Delete 'b' or 'c'.
//Example 3:
//
//Input: s = "abc"
//Output: false
//Explanation: Deleting any letter can not make it a palindrome.
//Notice
//The string will only contain lowercase characters.
//The maximum length of the string is 50000.

public class ValidPalindrome {
    public boolean validPalindrome(String s) {
        // Write your code here
        if(s == null || s.length() == 0) return true;
        int left=0;
        int right=s.length()-1;
        while(left<right){
            if(s.charAt(left) != s.charAt(right)){
            	//当遇到不匹配字符串 可以选择左边右移一位 或者右边左移一位 两种只要有一种能行即可
                return (check(s, left+1, right) || check(s, left, right-1));
            }
            left++;
            right--;
        }
        return true;
    }
    
    private boolean check(String s, int left, int right){
        while(left < right){
            if(s.charAt(left++)!=s.charAt(right--)){
                return false;
            }
        }
        return true;
    }
}
