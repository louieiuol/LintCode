//179. Update Bits
//中文English
//Given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to set all bits between i and j in N equal to M (e g , M becomes a substring of N start from i to j)
//
//Example
//Example 1:
//
//Input: N=(10000000000)2 M=(10101)2 i=2 j=6
//Output: N=(10001010100)2
//Example 2:
//
//Input: N=(10000000000)2 M=(11111)2 i=2 j=6
//Output: N=(10001111100)2
//Challenge
//Minimum number of operations?
//
//Clarification
//You can assume that the bits j through i have enough space to fit all of M. That is, if M=10011， you can assume that there are at least 5 bits between j and i. You would not, for example, have j=3 and i=2, because M could not fully fit between bit 3 and bit 2.
//
//Notice
//In the function, the numbers N and M will given in decimal, you should also return a decimal number.

public class UpdateBits {
    public int updateBits(int n, int m, int i, int j) {
        //1. 构造一个都是1的32位bit(-1) << 32 
    	//2. 对该bit 左移 32-j位 0会填补左边的区域 我们安排0给不需要的位置的个数 32-j是高于j位的个数 
    	//3. 对该bit 逻辑右移 (负数变为正数 1->0) 31-j+i位 0会填补右边的区域 因为我们需要给右边安排0 是0到i位置的个数+j到32位的个数和
    	//因为是逻辑右移 最高位已经帮你变为正数了 我们所需移动的少一位
    	//4. 对该bit 左移 i位 0会填补i个位置 现在bit变成了两边都是0中间是1的样子 
    	//5. 取反 变成两边1中间0 
    	//6. 取& 清空n上对应的位数
    	//6. 取| 和m左移i位 （匹配到对应的位数) 
    	int bit= (-1) << 32;
    	bit= bit << (32-j);
    	bit= bit >>> (32-j+i);
    	bit= bit << i;
    	bit= (~bit) & n;
    	return bit | (m << i);
    }
}
