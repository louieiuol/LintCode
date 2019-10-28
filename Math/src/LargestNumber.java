
/*184. Largest Number
中文English
Given a list of non negative integers, arrange them such that they form the largest number.

Example
Example 1:

Input:[1, 20, 23, 4, 8]
Output:"8423201"
Example 2:

Input:[4, 6, 65]
Output:"6654"
Challenge
Do it in O(nlogn) time complexity.

Notice
The result may be very large, so you need to return a string instead of an integer.*/

import java.util.*;
public class LargestNumber {
    String res="";
    public String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0){
            return res;
        }
        String[] strArray=new String[nums.length];
        //转换成String 数组
        for(int i=0; i<nums.length; i++){
            strArray[i]=Integer.toString(nums[i]);
            System.out.println(strArray[i]);
        }
        //利用String中对应的 hash来比较 究竟是 a+b 还是 b+a比较大
        //如果要ascending order a-b or (a)compareTo(b)
        //如果要descending order b-a or (b)compareTo(a)
        Arrays.sort(strArray,
            (String s1, String s2) -> {
                return (s2+s1).compareTo(s1+s2);
            });
        //秀一波lambda function
        //常规anonymous function
		/*
		 * Arrays.sort(strArray, new Comparator<String>(){
		 * @Override public int compare(String s1, String s2){ return
		 * (s2+s1).compareTo(s1+s2); } });
		 */
        
        for(int i=0; i<strArray.length ; i++){
            res+=strArray[i];
        }
        //聚合成一个String
        int i=0;
        for(i=0; i<res.length(); i++){
            if(res.charAt(i) != '0'){
                break;
            }
        }
        //从第一个非0的开始返回剩余整数
        if(i==res.length()){
            return "0";
        }
        //如果全是0 则返回一个0
        return res.substring(i);
        //返回 string from index i+1 to end
    }
}
