//84. Single Number III
//中文English
//Given 2*n + 2 numbers, every numbers occurs twice except two, find them.
//
//Example
//Example 1:
//	Input:  [1,2,2,3,4,4,5,3]
//	Output:  [1,5]
//
//Example 2:
//	Input: [1,1,2,3,4,4]
//	Output:  [2,3]
//	
//Challenge
//O(n) time, O(1) extra space.

import java.util.*;
//
//负数的表示方式是：
//
//1.先将负数原码表示出来（原码就是正数的二进制表示方式，列如：-5的原码就是5的二进制）
//
//2.将负数的原码取反
//
//3.将负数原码取反后加1，这样表示出来的二进制就是该负数的二进制的表示方式


public class SingleNumberIII {
    public List<Integer> singleNumberIII(int[] A) {
        // write your code here
        List<Integer> res=new ArrayList<>();
        if(A == null || A.length == 0) return res;
        //用于记录，区分“两个”数组
        int diff=0;
        for(int i=0;i<A.length;i++){
            diff ^= A[i];
        }
        //取最后一位1
        //先介绍一下原码，反码和补码
        //原码，就是其二进制表示（注意，有一位符号位）
        //反码，正数的反码就是原码，负数的反码是符号位不变，其余位取反
        //补码，正数的补码就是原码，负数的补码是反码+1
        //在机器中都是采用补码形式存
        //diff & (-diff)就是取diff的最后一位1的位置
        diff &= (-diff);
        int[] array=new int[2];
        for(int i=0; i<A.length;i++){
            if((A[i] & diff) == 0){
                array[0] ^=A[i];
            }else{
                array[1] ^=A[i];
            }
        }
        res.add(array[0]);
        res.add(array[1]);
        return res;
    }
}

//有了第一题的基本的思路，我们可以将数组分成两个部分，每个部分里只有一个元素出现一次，其余元素都出现两次。那么使用这种方法就可以找出这两个元素了。
//不妨假设出现一个的两个元素是x，y，那么最终所有的元素异或的结果就是等价于res = x^y。
//并且res！=0
//
//为什么呢？ 如果res 等于0，则说明x和y相等了！！！！
//因为res不等于0，那么我们可以一定可以找出res二进制表示中的某一位是1。
//
//对于x和y，一定是其中一个这一位是1，另一个这一位不是1！！！细细琢磨， 因为如果都是0或者都是1，怎么可能异或出1
//对于原来的数组，我们可以根据这个位置是不是1就可以将数组分成两个部分。x，y一定在不同的两个子集中。
//
//而且对于其他成对出现的元素，要么都在x所在的那个集合，要么在y所在的那个集合。
//对于这两个集合我们分别求出单个出现的x 和 单个出现的y即可。

