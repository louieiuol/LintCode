//17. Subsets
//中文English
//Given a set of distinct integers, return all possible subsets.
//
//Example
//Example 1:
//
//Input: [0]
//Output:
//[
//  [],
//  [0]
//]
//Example 2:
//
//Input: [1,2,3]
//Output:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//]
//Challenge
//Can you do it in both recursively and iteratively?
//
//Notice
//Elements in a subset must be in non-descending order.
//The solution set must not contain duplicate subsets.



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
	public List<List<Integer>> Solution1(int[] nums){
		List<List<Integer>> result=new ArrayList<List<Integer>>();
		if(nums !=null) {
			Arrays.sort(nums);
			//一定要先排序 才能结果按照顺序返回
			dfs(nums, 0, new ArrayList<Integer>(), result);
			//扔一个index 记录当前开始遍历的元素的位置 
			return result;
		}
		return null;
	}

	private void dfs(int[] nums, int i, ArrayList<Integer> lst, List<List<Integer>> result) {
		if(i == nums.length) {
			//当index 到末尾的时候 加入结果 
			result.add(new ArrayList<Integer>(lst));
			return;
		}
		
		
		lst.add(nums[i]);
		//选择当前结果 
		dfs(nums, i+1,lst, result);
		
		lst.remove(lst.size()-1);
		//不选择当前结果 回溯 
		dfs(nums, i+1, lst, result);
		
	}
	
}
