//15. Permutations
//中文English
//Given a list of numbers, return all possible permutations.
//
//Example
//Example 1:
//
//Input: [1]
//Output:
//[
//  [1]
//]
//Example 2:
//
//Input: [1,2,3]
//Output:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
//Challenge
//Do it without recursion.
//
//Notice
//You can assume that there is no duplicate numbers in the list.



import java.util.ArrayList;
import java.util.List;

public class Permutation1 {
	public List<List<Integer>> permute(int[] nums){
		List<List<Integer>> result=new ArrayList<List<Integer>>();
		if(nums != null) {
			//构建一个同样长度的 visited 数组 来标记每个元素是否被访问过
			dfs(result, nums, new boolean[nums.length], new ArrayList<Integer>());
		}
		return result;
	}

	private void dfs(List<List<Integer>> result, int[] nums, boolean[] visited, List<Integer> permutation) {
		if(nums.length == permutation.size()) {
			//list 的长度和元素个数相等时我们加入
			result.add(new ArrayList<Integer>(permutation));
			return;
		}
		
		for(int i=0; i<nums.length; i++) {
			//因为要找到所有可能性 每次都从0开始循环
			if (visited[i]) {
				//如果已经被访问 跳过 
				continue;
			}
			permutation.add(nums[i]);
			visited[i]=true;
			dfs(result, nums, visited, permutation);
			//回溯 取消标记那些之前被选中的点
			visited[i]=false;
			permutation.remove(permutation.size()-1);
		} 
	}
}
