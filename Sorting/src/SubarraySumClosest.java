//139. Subarray Sum Closest
//中文English
//Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and last number.
//
//Example
//Example1
//
//Input: 
//[-3,1,1,-3,5] 
//Output: 
//[0,2]
//Explanation: [0,2], [1,3], [1,1], [2,2], [0,4]
//Challenge
//O(nlogn) time
//
//Notice
//It is guaranteed that the sum of any numbers is in [-2^{31},2^{31}-1][−2
import java.util.*;

public class SubarraySumClosest {
    public int[] subarraySumClosest(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0 || nums.length == 1) return new int[]{0,0};
        //要考虑长度为1的情况 
        List<int[]> prefix=new ArrayList<>();
        //list 放prefix sum和对应的 index
        prefix.add(new int[]{nums[0], 0});
        //加入第一个值
        for(int i=1; i<nums.length; i++){
            int[] temp=new int[2];
            temp[0]=prefix.get(i-1)[0]+nums[i];
            temp[1]=i;
            prefix.add(temp);
        }
        prefix.add(new int[]{0, -1});
        //加入空的array的sum, 0的位置已经被占用 用-1表示开始
        //排序所有的prefix sum
        Collections.sort(prefix, (a, b) -> (a[0]-b[0]));
        int min=Integer.MAX_VALUE;
        int[] result=new int[2];
        //因为最近的两个prefix sum之差一定是和最小的 比较每个临近的2个prefix sum之差 记录最小值
        for(int i=1; i < prefix.size(); i++){
            int sub=prefix.get(i)[0]-prefix.get(i-1)[0];
            if(sub < min){
                min=sub;
                result[0]=prefix.get(i)[1];
                result[1]=prefix.get(i-1)[1];
            }
        }
        Arrays.sort(result);
        result[0]++;
        //左边的index不在subarray里
        return result;
    }
}
