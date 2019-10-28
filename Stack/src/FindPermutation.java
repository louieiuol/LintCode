//884. Find Permutation
//中文English
//By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a decreasing relationship between two numbers, 'I' represents an increasing relationship between two numbers. And our secret signature was constructed by a special integer array, which contains uniquely all the different number from 1 to n (n is the length of the secret signature plus 1). For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing special string that can't represent the "DI" secret signature.
//
//On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer to the given secret signature in the input.
//
//Example
//Example 1:
//	Input:  str = "DI"
//	Output:  [2,1,3]
//
//Example 2:
//	Input: str = "I"
//	Output:  [1,2]
//	
//Notice
//The input string will only contain the character 'D' and 'I'.
//The length of input string is a positive integer and will not exceed 10,000.
//

/*
我们不难看出，只有D对应的位置附近的数字才需要变换，而且变换方法就是倒置一下字符串，我们要做的就是通过D的位置来确定需要倒置的子字符串的起始位置和长度即可。
通过观察，我们需要记录D的起始位置i，还有D的连续个数k，那么我们只需要在数组中倒置[i, i+k]之间的数字即可，根据上述思路可以写出代码如下:*/

import java.util.*;
public class FindPermutation {
    public int[] findPermutation(String s) {
        if(s == null || s.length() == 0) return null;
        int[] res=new int[s.length()+1];
        //因为是decrease 或者 increase 结果中要比原string额外多一个数 
        //因为 对于I位置的元素是保持1到n的原来位置 我们只需要利用stack对碰到I位置时处理
        Stack<Integer> stack=new Stack<>();
        int j=0; //j用来记录 res数组已经填满的位置
        //对整个string进行遍历
        for(int i=0; i<s.length(); i++) {
            //遇到I入栈并且弹出栈内所有的元素填满之前的位置到空 
        	if(s.charAt(i) == 'I') {
        		stack.add(i+1);
        		//加入栈的数最后会要给res数组赋值 而对应的是从1-n所以加入栈的元素比原来的位置+1 
        		while(!stack.isEmpty()) {
        			res[j]=stack.pop();
        			j++;
        			//弹出栈内所有的值 给j所对应没被填满的数进行填补 
        		}
        	}else {
                //遇到D就只用入栈 记录那些未被分配的值
        		stack.add(i+1);
        	}
        }
        stack.add(s.length()+1);
        //最后一个值 需要加入栈 对已经填满的位置j到末尾位置再进行一边清空栈
        while(!stack.isEmpty()) {
        	res[j++]=stack.pop();
        }
        return res;
    }
}
