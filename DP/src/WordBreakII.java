//
//582. Word Break II
//中文English
//Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
//
//Return all such possible sentences.
//
//Example
//Example 1:
//
//Input："lintcode"，["de","ding","co","code","lint"]
//Output：["lint code", "lint co de"]
//Explanation：
//insert a space is "lint code"，insert two spaces is "lint co de".
//Example 2:
//
//Input："a"，[]
//Output：[]
//Explanation：dict is null.

import java.util.*;
public class WordBreakII {
	HashMap<String, List<String>> map=new HashMap<>();
	public List<String> wordBreak(String s, Set<String> wordDict) {
		List<String> result=new ArrayList<>();
		if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) return result;
		return  dfs(s, wordDict);
	}
	private List<String> dfs(String s, Set<String> wordDict) {
		//递归的出口 如果记忆化搜索直接能找到当前string 
		//直接返回当前map得到的当前string的对应拆分
		if(map.containsKey(s)) {
			return map.get(s);
		}
		List<String> result=new ArrayList<>();
		//新建结果list
		if(s.isEmpty()) {
			//如果已经到了结尾 返回空的list
			return result;
		}
		if(wordDict.contains(s)) {
			//如果字典包含整个s 结果加入整个s
			result.add(s);
		}
		for(int i=1; i<s.length(); i++) {
			//因为完整单词必须全部完全拆分才能放入结果 所以我们先确定一个可以拆分的
			//要从0开始找
			//按位置分别对应去拆分0到i位置的单词
			String prefix=s.substring(0,i);
			if(!wordDict.contains(prefix)) continue;
			//如果前面的拆分不合法 直接跳过 
			String suffix=s.substring(i);
			//开始拆分后面的
			List<String> parts=dfs(suffix,wordDict);
			//dfs去遍历该单词能拆的 所有情况 搜集所有情况 存入 list
			for(String str: parts) {
				//用前缀单词拼接每一个字符串 然后加入结果 
				result.add(prefix+ " "+ str);
			}
		}
		//记忆对应单次能找到的所有拆分结果
		map.put(s, result);
		//返回结果
		return result;
	}
	
}
