//181. Flip Bits
//中文English
//Determine the number of bits required to flip if you want to convert integer n to integer m.
//
//Example
//Example 1:
//	Input: n = 31, m = 14
//	Output:  2
//	
//	Explanation:
//	(11111) -> (01110) there are two different bits.
//
//
//Example 2:
//	Input: n = 1, m = 7
//	Output:  2
//	
//	Explanation:
//	(001) -> (111) will change two bits.
//
//
//Notice
//Both n and m are 32-bit integers.


public class FlipBits {
    public int bitSwapRequired(int a, int b) {
        //决定有多少位需要换就是找有多少位不同
        int num=a ^ b;
        //异或能把不同位数变为1 相同位数变为0
        int counter=0;
        //套用消去最后一个1的模板 记录有多少个1
        while(num != 0){
            num &= (num-1);
            counter++;
        }
        return counter;
    }
}
