import java.util.*;
public class PalindromePartitioning {
	List<List<String>> result=new ArrayList<>();
    public List<List<String>> partition(String s) {
        // write your code here
        if(s == null || s.length() == 0) return result;
        if(s.length() == 1){
            ArrayList<String> lst=new ArrayList<>();
            lst.add(s);
            result.add(lst);
            return result;
        }
        dfs(s, 0, new ArrayList<String>());
        return result;
    }
    private void dfs(String s, int index, ArrayList<String> lst){
        if(index == s.length()){
            result.add(new ArrayList<String>(lst));
            return;
        }
        for(int i=index; i<s.length() ;i++){
            String sub=s.substring(index, i+1);
            if(!isPalindrome(sub)){
                continue;
                //如果不是Palindrome 跳过当前分割
            }
            lst.add(sub);
            //如果是Palindrome 加入当前分割
            dfs(s,i+1,lst);
            //指针移动到下一次分割
            lst.remove(lst.size()-1);
            //回溯法去掉当前分割
        }
    }
    
    private boolean isPalindrome(String s){
    	//判断是否为 Palindrome
        if(s.length() == 0 || s.length() == 1) return true;
        if(s.length()/2 == 0){
            int left=0;
            int right=s.length()-1;
            while(left<right){
                if(s.charAt(left)!=s.charAt(right)){
                    return false;
                }
                left++;
                right--;
            }
        }else{
            int left=0;
            int right=s.length()-1;
            while(left<=right){
                if(s.charAt(left)!=s.charAt(right)){
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}
