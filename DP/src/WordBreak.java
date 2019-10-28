
/*107. Word Break
中文English
Given a string s and a dictionary of words dict, determine if s can be break into a space-separated sequence of one or more dictionary words.

Example
Example 1:
	Input:  "lintcode", ["lint", "code"]
	Output:  true

Example 2:
	Input: "a", ["a"]
	Output:  true
	*/



import java.util.*;
public class WordBreak {
    public boolean wordBreak(String s, Set<String> dict) {
        // write your code here
        if(s == null || dict == null ) return false;
        if(s.length() == 0 && dict.size() == 0) return true;
        int maxLength=findMax(dict);
        
        // 长度为n的单词 有n + 1个切割点 比如: _l_i_n_t_ step1: create dp array
        boolean[] dp=new boolean[s.length()+1];
        // 当s长度为0时 step2: initialize state
        dp[0]=true;
        
        //step3: double for loop dp 
        for(int i=1;i < dp.length ; i++){
            for(int j=1; j<=i && j<=maxLength ; j++){
            	//i - j 表示从 i 点开始往前j个点的位置
                String word=s.substring(i-j,i);
                //如果此str在词典中 并且 str之前的 字符串可以拆分   
                if(dict.contains(word) && dp[i-j]){
                	//step4: update dp array and marginal condition
                    dp[i]=true;
                    break;
                }
            }
        }
        //step5: return final state
        return dp[s.length()];
    }
    
    private int findMax(Set<String> set){
        int len=Integer.MIN_VALUE;
        for(String s:set){
            len=Math.max(s.length(),len);
        }
        return len;
    }
}
