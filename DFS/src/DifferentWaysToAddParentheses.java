//1309. Different Ways to Add Parentheses
//中文English
//Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
//
//Example
//Example1
//
//Input: "2-1-1"
//Output: [0, 2]
//Explanation:
//((2-1)-1) = 0
//(2-(1-1)) = 2
//Example2
//
//Input: "2*3-4*5"
//Output: [-34, -14, -10, -10, 10]
//Explanation:
//(2*(3-(4*5))) = -34
//((2*3)-(4*5)) = -14
//((2*(3-4))*5) = -10
//(2*((3-4)*5)) = -10
//(((2*3)-4)*5) = 10

import java.util.*;
//分治型 dfs
public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        // write your code here
    	List<Integer> result=new ArrayList<>();
    	if(input.length() == 0) return result;
    	for(int i=0; i<input.length(); i++) {
    		//遍历string 
    		char c=input.charAt(i);
    		if(c == '+' || c=='-' || c== '*') {
    			//对符号处进行拆分 跳过符号拆成前后两串 
    			List<Integer> part1=diffWaysToCompute(input.substring(0, i));
    			List<Integer> part2=diffWaysToCompute(input.substring(i+1));
    			//对结果里的数字合集进行循环排列 + - *操作
    			for(int num1: part1) {
    				for(int num2: part2) {
    					int ans=0;
    					if(c == '+') {
    						ans=num1+num2;
    					}
    					if(c == '-') {
    						ans=num1-num2;
    					}
    					if(c == '*') {
    						ans=num1*num2;
    					}
    					//把每个结果加入当前list
    					result.add(ans);
    				}
    			}
    		}
    	}
    	if(result.size() == 0) {
    		//如果对于input全是数字的情况 单个数字 无法拆分 所以没有加入元素 
    		//则加入单个数字进入result
    		result.add(Integer.valueOf(input));
    	}
    	return result;
    }
}
