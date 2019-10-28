import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
/*135. Combination Sum
中文English
Given a set of candidtate numbers candidates and a target number target. Find all unique combinations in candidates where the numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Example
Example 1:

Input: candidates = [2, 3, 6, 7], target = 7
Output: [[7], [2, 2, 3]]
Example 2:

Input: candidates = [1], target = 3
Output: [[1, 1, 1]]
Notice
All numbers (including target) will be positive integers.
Numbers in a combination a1, a2, … , ak must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak)
Different combinations can be in any order.
The solution set must not contain duplicate combinations.*/
public class CombinationSum {
    ArrayList<List<Integer>> result=new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        if(candidates == null || candidates.length == 0){
            ArrayList<Integer> newlist=new ArrayList<>();
            result.add(newlist);
            return result;
        }
        Arrays.sort(candidates);
        //we want unique combinations and non-descending order
        //so we put same elements close to each other
        dfs(candidates, 0, new ArrayList<Integer>(), target);
        return result;
    }
    
    private void dfs(int[] candidates, int index, ArrayList<Integer> lst ,int target){
        if(target == 0){
            result.add(new ArrayList<Integer>(lst));
            return;
        }
        //递归的出口
        if(target < 0){
            return;
        }
        //递归的边界
        
        for(int i=index; i<candidates.length; i++){
            if(i > 0 && candidates[i] == candidates[i-1]){
                continue;
            }
            //除去所有重复情况
            lst.add(candidates[i]);
            dfs(candidates, i, lst, target-candidates[i]);
            //index 还是当前 index,下次dfs 还是从当前index开始 考虑了重复情况
            lst.remove(lst.size()-1);
        }
    }
}
