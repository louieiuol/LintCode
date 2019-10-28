//980. Basic Calculator II
//中文English
//Implement a basic calculator to evaluate a simple expression string.
//
//The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
//
//You may assume that the given expression is always valid.
//
//Example
//Example 1:
//
//Input:
//"3+2*2"
//Output:
//7
//Example 2:
//
//Input:
//" 3/2 "
//Output:
//1
//Notice
//Do not use the eval built-in library function.

import java.util.*;
public class BasicCalculatorII {
    public int calculate(String s) {
        // Write your code here
    	if(s == null || s.length() == 0) return 0;
    	char[] sArray=s.replaceAll(" ", "").toCharArray();
    	Stack<Integer> stack=new Stack<>();
    	int res=0;
    	int num=0;
    	char sign='+';
    	for(int i=0; i<sArray.length; i++) {
    		char c=sArray[i];
    		if(Character.isDigit(c)) {
    			//和之前一样累加num
    			num=num*10+(c-'0');
    		}
    		if(! Character.isDigit(c) || i == sArray.length-1) {
    			//遇到符号时候 改为对上一个符号进行处理运算结果 把当前sign置为当前符号
    			if(sign == '+') {
    				//之前是+运算 直接存stack
    				stack.push(num);
    			}else if(sign == '-') {
    				//之前是-运算 直接把负值存stack
    				stack.push(-num);
    			}else if(sign == '*') {
    				//之前是*运算 先拿出最近一次存的值 进行乘积运算 之后再放入 为了之后的加减运算
    				//乘积优先运算把结果算出后在放入
    				stack.push(stack.pop()*num);
    			}else if(sign == '/') {
    				//之前是/运算 先拿出最近一次存的值 进行除法运算 之后再放入 为了之后的加减运算
    				//除法优先运算把结果算出后在放入
    				stack.push(stack.pop()/num);
    			}
    			sign=c;
    			//符号变为当前符号
    			num=0;
    			//累计数字置为0
    		}
    	}
    	while(!stack.isEmpty()) {
    		res+=stack.pop();
    		//加减运算放到最后运行
    	}
    	return res;
    }
}
