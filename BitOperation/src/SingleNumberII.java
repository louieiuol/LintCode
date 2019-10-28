//83. Single Number II
//中文English
//Given 3*n + 1 non-negative integer, every numbers occurs triple times except one, find it.
//
//Example
//Example 1:
//
//Input:  [1,1,2,3,3,3,2,2,4,1]
//Output:  4
//Example 2:
//
//Input: [2,1,2,2]
//Output:  1	
//Challenge
//One-pass, constant extra space.

public class SingleNumberII {
	public int singleNumberII(int[] A) {
		if(A == null || A.length == 0) return -1;
		int one=0, two=0;
		for(int i=0;i<A.length;i++) {
			one=(one ^ A[i]) & ~two;
			two=(two ^ A[i]) & ~one;
		}
		return one;
	}
}

//因为其他数是出现三次的，也就是说，对于每一个二进制位，如果只出现一次的数在该二进制位为1，那么这个二进制位在全部数字中出现次数无法被3整除。
//对于每一位，我们让Two，One表示当前位的状态。
//
//我们看Two和One里面的每一位的定义，对于ith(表示第i位)：
//
//如果Two里面ith是1，则ith当前为止出现1的次数模3的结果是2
//如果One里面ith是1，则ith目前为止出现1的次数模3的结果是1
//注意Two和One里面不可能ith同时为1，因为这样就是3次，每出现3次我们就可以抹去（消去）。那么最后One里面存储的就是每一位模3是1的那些位，
//综合起来One也就是最后我们要的结果。
//
//如果B表示输入数字的对应位，Two+和One+表示更新后的状态
//那么新来的一个数B，此时跟原来出现1次的位做一个异或运算，&上~Two的结果(也就是不是出现2次的)，那么剩余的就是当前状态是1的结果。
//同理Two ^ B （2次加1次是3次，也就是Two里面ith是1，B里面ith也是1，那么ith应该是出现了3次，此时就可以消去，设置为0），我们相当于会消去出现3次的位。
//但是Two ^ B也可能是ith上Two是0，B的ith是1，这样Two里面就混入了模3是1的那些位！！！怎么办？我们得消去这些！
//我们只需要保留不是出现模3余1的那些位ith，而One是恰好保留了那些模3余1次数的位，`取反不就是不是模3余1的那些位ith么？最终对(~One+)取一个&即可。
