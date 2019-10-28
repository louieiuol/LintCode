//982. Arithmetic Slices
//中文English
//A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
//
//For example, these are arithmetic sequence:
//
//1, 3, 5, 7, 9
//7, 7, 7, 7
//3, -1, -5, -9
//The following sequence is not arithmetic.
//
//1, 1, 2, 5, 7
//A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
//
//A slice (P, Q) of array A is called arithmetic if the sequence:
//A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
//
//The function should return the number of arithmetic slices in the array A.
//
//Example
//Example1
//
//Input: [1, 2, 3, 4]
//Output: 3
//Explanation:
//for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
//Example2
//
//Input: [1, 2, 3]
//Output: 1


public class ArithmeticSlices {
    public int numberOfArithmeticSlices(int[] A) {
        // 节省时间的Two pointer
    	// 因为 start 到 end 之间每个数的差值valid 
    	// 那么我们求 和 start 到 end+1之间是否valid 只用查看 end和end+1的元素是不是和之前具有相同的差值
    	// 所以可以省去一遍的start再到end的计算
        if(A == null || A.length < 3) return 0;
        int res=0;
        for(int i=0; i<A.length-2; i++){
        	//设定start index从0到 len-2 (至少要3个数)
            int diff=A[i+1]-A[i];
            //我们先计算第一次的差值
            for(int j=i+2; j<A.length;j++){
            	//end index 是从start index 到结尾的
                if(A[j]-A[j-1] == diff){
                	//我们只计算最后一次end index和前一个数的差值
                    res++;
                    //结果++
                }else{
                    break;
                    //跳出内循环 当前start index找不到合适的了
                }
            }
        }
        return res;
    }
}
