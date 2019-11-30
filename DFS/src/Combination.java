import java.util.*;

/*152. Combinations
中文English
Given two integers n and k. Return all possible combinations of k numbers out of 1, 2, ... , n.

Example
Example 1:

Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Example 2:

Input: n = 4, k = 1
Output: [[1],[2],[3],[4]]
Notice
You can return all combinations in any order, but numbers in a combination should be in ascending order.*/
public class Combination {
    List<List<Integer>> result=new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        // write your code here
        if( n <=0 || k<=0){
            result.add(new ArrayList<Integer>());
            return result;
        }
        //放入第一个数字 放入最后一个数字 n 
        dfs(n, 1, k, new ArrayList<Integer>());
        //we don't want duplicates, so we need a index to start 
        return result;
    }
    private void dfs(int n, int index, int k, ArrayList<Integer> lst){
        if(lst.size() == k){
        	// lst长度为k时候 加入结果 
            result.add(new ArrayList<Integer>(lst));
            return;
        }
        for(int i=index; i<=n; i++){
        	//每次都从index开始 
            lst.add(i);
            dfs(n, i+1, k, lst);
            //走到下一个元素，避免重复
            lst.remove(lst.size()-1);
        }
    }
}
