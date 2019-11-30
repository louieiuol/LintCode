//653. Expression Add Operators
//中文English
//Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
//
//Example
//Example 1:
//
//Input:
//"123"
//6
//Output: 
//["1*2*3","1+2+3"]
//Example 2:
//
//Input:
//"232"
//8
//Output: 
//["2*3+2", "2+3*2"]
//Example 3:
//
//Input:
//"105"
//5
//Output:
//["1*0+5","10-5"]
//Example 4:
//
//Input:
//"00"
//0
//Output:
//["0+0", "0-0", "0*0"]
//Example 5:
//
//Input:
//"3456237490",
//9191 
//Output: 
//[]

import java.util.*;
//枚举型dfs
public class ExpressionAddOperators {
	List<String> result=new ArrayList<>();
	//result记录总结果 
    public List<String> addOperators(String nums, int target) {
    	if(nums == "" || nums.length() == 0) return result;
    	//dfs 传入 原始 number, target, start index, 组合成的string, sum of numbers, last number
    	dfs(nums, target, 0, "", 0, 0);
    	return result;
    }
    
    private void dfs(String nums, int target, int index, String curr, long sum, long prev) {
    	if(index == nums.length()){
    		//递归的出口 指针指到结尾
    		if(sum == target) {
    			//如果sum 等于 target 结果加入
    			result.add(curr);
    		}
    		return;
    	}
    	for(int i=index; i<nums.length(); i++) {
    		//从index 开始遍历string
    		long num=Long.parseLong(nums.substring(index, i+1));
    		//提取从index 到 index+1和之后的组成的number 
    		if(index == 0) {
    			//如果是第一个数字 直接转string sum为本身 last number 也是本身
    			dfs(nums, target, i+1, ""+num, num, num);
    		}else {
    			//进行 + - * 运算 注意- * 号last number也会跟着改变 
    			dfs(nums, target, i+1, curr+"+"+num, sum+num, num);
    			dfs(nums, target, i+1, curr+'-'+num, sum-num, -num);
    			//*号要把加的值减去出来 再用之前的值乘以现在的  
    			dfs(nums, target, i+1, curr+"*"+num, sum-prev+prev*num, prev*num);
    		}
    		// 0之后不能有别的数字 0不能作为别的数字的开头 05/007这些情况全部跳过 
    		if(num == 0) {
    			break;
    		}
    	}
    }
}
