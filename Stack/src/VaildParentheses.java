
/*
1089. Valid Parenthesis String
中文English
Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example
Input: "()"
Output: True

Input: "(*)"
Output: True

Input: "(*))"
Output: True*/

import java.util.Stack;

public class VaildParentheses {
	public int vaildParentheses(String str) {
		if(str==null) return -1;
		if(str.length()==0) return 0;
		Stack<Character> stack=new Stack<>();
		int count=0;
		for(int i=0; i<str.length();i++) {
			char c=str.charAt(i);
			if(c!='[' && c!=']') {
				return -1;
			}
			if(c=='[') {
				stack.push(c);
			}else {
				if(!stack.isEmpty()) {
					stack.pop();
					count++;
				}
			}
		}
		return count;
	}
   public boolean checkValidString(String s) {
       // Write your code here
       if(s==null) return false;
       if(s.length()==0) return true;
       int left=0, lp=0;
       //use left to count # of left (
       //use lp tp count # of left and *
       for(int i=0;i<s.length();i++){
           char c=s.charAt(i);
           if(c == '('){
               left++;
               lp++;
               //increase # of left and left and *
           }else if(c == '*'){
               if(left>0){
                   left--;
               }
               lp++;
           }else{
               if(left>0){
                   left--;
               }
               lp--;
               if(lp<0){
                   return false;
               }
           }
       }
       if(left<0 || left>0){
           return false;
       }
       return true;
   }
}
