//1. A + B Problem
//中文English
//Write a function that add two numbers A and B.
//
//Example
//Example 1:
//
//Input:  a = 1, b = 2
//Output: 3	
//Explanation: return the result of a + b.
//Example 2:
//
//Input:  a = -1, b = 1
//Output: 0	
//Explanation: return the result of a + b.
//Challenge
//Of course you can just return a + b to get accepted. But Can you challenge not do it like that?(You should not use + or any arithmetic operators.)
//
//Clarification
//Are a and b both 32-bit integers?
//
//Yes.
//Can I use bit operation?
//
//Sure you can.
//Notice
//There is no need to read data from standard input stream. 
//Both parameters are given in function aplusb, you job is to calculate the sum and return it.

public class AplusB {
    public int aplusb(int a, int b) {
        // write your code here
    	while(b != 0) {
    		int a1= a ^ b; //异或运算又叫非进位加和运算
    		int b1= (a & b) << 1; //寻找会进位的位数 右移一位
    		a=a1;
    		b=b1;
    		//重复此过程知道b归零 两种归零可能性 1.&运算找不到继续进位的值那么返回结果 2. 因为超过限定的bit 向左一直在移动到尽头归零 返回当前能计算的结果
    	}
    	return a;
    	//a值保留加和
    }
}
