//
//142. O(1) Check Power of 2
//中文English
//Using O(1) time to check whether an integer n is a power of 2.
//
//Example
//Example 1:
//	Input: 4
//	Output: true
//
//
//Example 2:
//	Input:  5
//	Output: false
//
//Challenge
//O(1) time

public class CheckPowerOf2 {
    public boolean checkPowerOf2(int n) {
        //因为如果是2的次方 只允许有一个1在二进制的位数上
    	//利用消除最后一个1的原理 判定是不是为0
    	//所以只需要O(1)时间复杂度
    	return n>0 && (n & (n-1)) == 0;
    }
}
