//871. Minimum Factorization
//中文English
//Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.
//
//If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.
//
//Example
//Example 1:
//
//Input: 48
//Output: 68
//Example 2:
//
//Input: 15
//Output: 35


public class MinimumFactorization {
	public int smallestFactorization(int a) {
		long res=0; //创建返回值 注意因为要跟Integer MAX判断是否超过 我们用long
		int base=1; //从最低位寻找乘积相乘最小的
		//因为要找到最小的乘积我们让低的digit的base 去先乘比较大的数 
		//这样a除掉比较大的数剩下的比较小的数方便找结果 所以循环从9到2往下
		for(int i=9; i>=2; i++) {
			//当可以被当前 i 整除时候 我们把a除掉这个数以后继续运算 
			while(a % i == 0) {
				a /=i;
				//继续寻找下一个因子 
				res +=i*base;
				//结果加上这个数乘以当前digit的值
				if(res > Integer.MAX_VALUE) {
					//如果结果大于最大值 返回0
					return 0;
				}
				base *=10;
				//digit每次乘10
			}
		}
		return res == 1 ? (int) res : 0;
		//如果最后能被整除 返回res转换为int 否则返回0 （没有找到）
	}
}
