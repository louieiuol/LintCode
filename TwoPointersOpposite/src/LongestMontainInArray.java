//1424. Longest Mountain in Array
//中文English
//Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
//
//B.length >= 3
//There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
//(Note that B could be any subarray of A, including the entire array A.)
//Given an array A of integers, return the length of the longest mountain.
//
//Return 0 if there is no mountain.
//
//Example
//Example 1:
//
//Input: [2,1,4,7,3,2,5]
//Output: 5
//Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
//Example 2:
//
//Input: [2,2,2]
//Output: 0
//Explanation: Explanation: There is no mountain.
//Challenge
//Can you solve it using only one pass?
//Can you solve it in O(1) space?
//Notice
//0 <= A.length <= 10000
//0 <= A[i] <= 10000

public class LongestMontainInArray {
    public int longestMountain(int[] A) {
        // write your code here
        int res=0;
        int up=0;
        int down=0;
        for(int i=1; i<A.length; i++){
            if(down > 0 && A[i-1] <A[i] || A[i-1] == A[i]){
            	//如果遇到朝下还记录有数字 那么方向如果不是递减 则重置计数
            	//如果遇到前后两个相等的 也重置计数 
                up=down=0;
            }
            if(A[i-1] < A[i]){
                up++;
            }
            if(A[i-1] > A[i]){
                down++;
            }
            if(up > 0 && down >0){
            	//up 和 down 都不为0时候 进行返回最大值 
                res=Math.max(res, up+down+1);
            }
        }
        return res;
    }
}
