//978. Basic Calculator
//中文English
//Implement a basic calculator to evaluate a simple expression string.
//
//The expression string may contain open '(' and closing parentheses ')', the plus '+' or minus sign '-', non-negative integers and empty spaces ' '.
//
//You may assume that the given expression is always valid.
//
//Example
//Example 1
//
//Input："1 + 1"
//Output：2
//Example 2
//
//Input："(1+(4+5+2)-3)+(6+8)" 
//Output：23
//Notice
//Do not use the eval built-in library function.

import java.util.*;
public class BasicCalculator {
    public int calculate(String s) {
    	if(s == null || s.length() == 0) return 0;
    	Stack<Integer> stack=new Stack<>();
    	//因为要计算括号 计算器的模拟算法是用Stack
    	int res=0;  //记录最终结果 
    	int num=0;  //记录当前数 
    	int sign=1; //记录+/-号 因为不包括负数 开始默认为+ 
    	char[] sArray=s.replaceAll(" ","").toCharArray();
    	//除掉空格变成char array
    	for(int i=0; i<sArray.length; i++) {
    		char c=sArray[i];
    		if(Character.isDigit(c)){
    			//如果是数字 那么需要考虑是第几次出现 每次出现用(c-'0')的相对位置计算
    			//num记录对应的位数 从0开始 每次进位*10 
    			num+=10*num+(int)(c-'0');
    		}
    		if(!Character.isDigit(c) || i == sArray.length -1){
    			if(c == '+') {
    				//遇到+/-号 计算前面的数 因为前面的数已经结束了 需要与其对应的符号进行结算
    				res+=sign*num;
    				//计算之前数字 符号*数字
    				num=0;	
    				//重置当前数
    				sign=1;
    				//更新符号为当前的+/- 等到下次运算符进行结算
    			}else if(c == '-') {
    				res+=sign*num;
    				num=0;
    				sign=-1;
    			}else if(c == '(') {
    				//左括号 先放入括号前result的值和sign 再重置 result和sign
    				//遇到左括号 我们先把之前计算结果放入stack 在放入之前的sign
    				stack.push(res);
    				stack.push(sign);
    				//为了括号内的计算重置符号为正
    				sign=1;
    				//为了括号内的计算重置总结果为正 之前结果已经被记录在stack中
    				//因为括号内运算优先 所以把res用于括号内的计算
    				res=0;
    			}else if(c == ')') {
    				//右括号 先计算括号内result的值 再重置当前num 
    				res+=sign*num;
    				//先计算括号内结果
    				num=0;
    				//重置当前数为0 不重置的话括号外还有符号会把之前括号内的num也算进去 而其实在（）结束时候已经加上了 所以会加是两遍
    				//eg: 1+(3+3)-1
    				res*=stack.pop();
    				//先计算符号 是之前左括号之前分配给括号的符号
    				res+=stack.pop();
    				//在把之前的Result和加上
    			}else {
    				//所以计算上最后未被计算的数
    				res+=sign*num;
    				break;
    			}
    		}
    	}
    	return res;
    }
}
