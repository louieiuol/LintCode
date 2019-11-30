//16. Permutations II
//中文English
//Given a list of numbers with duplicate number in it. Find all unique permutations.
//
//Example
//Example 1:
//
//Input: [1,1]
//Output:
//[
//  [1,1]
//]
//Example 2:
//
//Input: [1,2,2]
//Output:
//[
//  [1,2,2],
//  [2,1,2],
//  [2,2,1]
//]
//Challenge
//Using recursion to do it is acceptable. If you can do it without recursion, that would be great!




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation2 {
    List<List<Integer>> result=new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0){
            result.add(new ArrayList<>());
            return result;
        }
        Arrays.sort(nums);
        //去重先排序 
        dfs(nums, new ArrayList<>(), new boolean[nums.length]);
        return result;
    }
    
    private void dfs(int[] nums, List<Integer> lst, boolean[] visited){
        if(lst.size() == nums.length){
            if(!result.contains(lst)){
                result.add(new ArrayList<>(lst));
            }
            return;
        }
        for(int i=0; i<nums.length; i++){
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
            	//不是第一个数字 且前后数字相等 如果前面未被访问过 证明是跳跃性选择 所以不考虑这种情况
                continue;
            }
            if(visited[i]){
                continue;
            }
            lst.add(nums[i]);
            visited[i]=true;
            dfs(nums, lst, visited);
            lst.remove(lst.size()-1);
            visited[i]=false;
        }
    }
}
