//427. Generate Parentheses
//中文English
//Given n, and there are n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//Example
//Example 1:
//
//Input: 3
//Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]
//Example 2:
//
//Input: 2
//Output: ["()()", "(())"]

import java.util.*;

//回溯. 逐个字符添加, 生成每一种组合.
public class GenerateParentheses {
	List<String> res=new ArrayList<String>();
	public List<String> generateParenthesis(int n) {
		if(n <=0) {
			res.add("");
			return res;
		}
		//一个状态需要记录的有: 当前字符串本身, 总共需要左括号数量, 总共需要右括号数量.
		dfs("", n, n);
		return res;
	}
	
	private void dfs(String s, int left, int right) {
		if(left == 0 && right == 0) {
			//如果剩余右括号数量等于0 并且剩余右括号也等于0, 那么当前字符串即是一种组合, 放入解中.
			res.add(s);
			return;
		}
		//如果仍然剩余左右括号：
		if(left>0) {
			//如果剩余左括号大于0, 添加左括号, 剩余左括号减少1, 递归进入下一层
			dfs(s+"(", left-1, right);
		}
		if(right>0 && left<right) {
			//如果剩余右括号大于0 并且 剩余左括号小于剩余右括号,（不能相等因为相等不允许右括号先开始） 
			//那么此时能添加一个右括号, 剩余右括号减少1，递归进入下一层
			dfs(s+")", left, right-1);
		}
	}
}
