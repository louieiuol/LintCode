//415. Valid Palindrome
//中文English
//Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
//
//Example
//Example 1:
//
//Input: "A man, a plan, a canal: Panama"
//Output: true
//Explanation: "amanaplanacanalpanama"
//Example 2:
//
//Input: "race a car"
//Output: false
//Explanation: "raceacar"
//Challenge
//O(n) time without extra memory.
//
//Notice
//Have you consider that the string might be empty? This is a good question to ask during an interview.
//
//For the purpose of this problem, we define empty string as valid palindrome.
//


public class Palindrome {
    public boolean isPalindrome(String s) {
        // write your code here
        s=s.replaceAll("[^a-zA-Z1-9]","").toLowerCase().trim();
        //一定要学会使用replaceAll 去换掉里面所有的值 但是replaceAll不是mutable要重新赋值
        //学会使用regular expression ^表示反逻辑 a-z 表示所有的小写字母 A-Z表示所有的大写字母 0-9表示所有数字
        //利用trim去掉空格 注意trim也不是mutable
        if(s== null || s.length() == 0) return true;
        //然后进行空值判断 不算非字母的剩余部分是否为空
        char[] str=s.toCharArray();
        int left=0, right=str.length-1;
        while(left<right){
            if(str[left] != str[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
