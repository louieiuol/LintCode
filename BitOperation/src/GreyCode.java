//411. Gray Code
//中文English
//The gray code is a binary numeral system where two successive values differ in only one bit.
//
//Given a non-negative integer n representing the total number of bits in the code, find the sequence of gray code. A gray code sequence must begin with 0 and with cover all 2n integers.
//
//Example
//Example 1:
//
//Input: 1
//Output: [0, 1]
//Example 2:
//
//Input: 2
//Output: [0, 1, 3, 2]
//Explanation:
//  0 - 00
//  1 - 01
//  3 - 11
//  2 - 10
//Challenge
//O(2n) time.
import java.util.*;
public class GreyCode {
    public List<Integer> grayCode1(int n) {
    	List<Integer> res=new ArrayList<Integer>();
    	for(int i=0; i< (1 << n); i++) {
    		res.add(i ^ (i >> 1));
    	}
    	return res;
    }
//一个数字对应的格雷编码的计算方式是:
//将其二进制第一位(从高位数)与0异或, 得到的结果为格雷码的第一位
//之后依次将原数的第i位与生成的格雷码第i-1位做异或运算, 即可得到格雷码的第i位
    
    
    public List<Integer> grayCode2(int n){
    	List<Integer> res=new ArrayList<Integer>();
    	if( n == 0) {
    		res.add(0);
    		return res;
    	}
    	res=grayCode2(n-1);
    	for(int i=res.size()-1; i>=0; i--) {
    		int num=res.get(i);
    		num += (1 << n);
    		res.add(num);
    	}
    	return res;
    }
    
//我们可以使用递归来做。规律是：
//一部分是n-1位格雷码，再加上 1<<(n-1)和n-1位格雷码的逆序的和。
}
