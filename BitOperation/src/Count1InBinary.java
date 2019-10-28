//365. Count 1 in Binary
//中文English
//Count how many 1 in binary representation of a 32-bit integer.
//
//Example
//Example 1:
//
//Input: 32
//Output: 1
//Explanation:
//32(100000), return 1.
//Example 2:
//
//Input: 5
//Output: 2
//Explanation:
//5(101), return 2.
//Challenge
//If the integer is n bits with m 1 bits. Can you do it in O(m) time?
		
public class Count1InBinary {
    public int countOnes(int num) {
    	//直接利用消除最后一个1的原理 再赋值给原来的数 反复进行迭代
        int counter=0;
        while(num != 0){
            num = num & (num-1);
            counter++;
        }
        return counter;
    }
}
