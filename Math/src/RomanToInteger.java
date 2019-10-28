import java.util.*;

//419. Roman to Integer
//中文English
//Given a roman numeral, convert it to an integer.
//
//The answer is guaranteed to be within the range from 1 to 3999.
//
//Example
//Example 1:
//
//Input: "IV"
//Output: 4
//Example 2:
//
//Input: "XCIX"
//Output: 99
//Clarification
//What is Roman Numeral?
//类似题目 418.Integer to Roman
public class RomanToInteger {
    public int romanToInt1(String s) {
        if(s == null || s.length() == 0) return 0;
        char[] charArray=s.toCharArray();
        Map<Character,Integer> map=new HashMap<>();
        //利用map建立数字和罗马数字对应关系 
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int result=map.get(charArray[s.length()-1]);
        //因为罗马数字中后面数字可能大于前面的 正着找遇到后面比前面大则前面变负数，但是负数已经记录到结果中了
        //所以用倒着找来确定更好
        //把最后一个值记为结果
        for(int i=s.length()-2; i>=0;i--){
        	//从倒数第二个来找 
            if(map.get(charArray[i])<map.get(charArray[i+1])){
            	//如果当前数小于后面数结果减去当前数
                result-=map.get(charArray[i]);
            }else{
            	//如果当前数大于后面数结果加上当前数
                result+=map.get(charArray[i]);
            }
        }
        return result;
    }
    //这种方法更好 正向思维 从0一直到n发现后面比前面大减两次前面的
    public int romanToInt2(String s) {
        // write your code here
        if(s == null || s.length() == 0) return 0;
        Map<Character,Integer> map=new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        char[] sArray=s.toCharArray();
        int sum=0;
        for(int i=0; i<s.length();i++){
            sum+=map.get(sArray[i]);
            if(i+1<s.length() && map.get(sArray[i])<map.get(sArray[i+1])){
                sum-=(map.get(sArray[i])*2);
            }
        }
        return sum;
    }
}
