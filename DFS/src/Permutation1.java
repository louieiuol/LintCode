import java.util.ArrayList;
import java.util.List;

public class Permutation1 {
	public List<List<Integer>> permute(int[] nums){
		List<List<Integer>> result=new ArrayList<List<Integer>>();
		if(nums != null) {
			dfs(result, nums, new boolean[nums.length], new ArrayList<Integer>());
		}
		return result;
	}

	private void dfs(List<List<Integer>> result, int[] nums, boolean[] visited, List<Integer> permutation) {
		// TODO Auto-generated method stub
		if(nums.length == permutation.size()) {
			result.add(new ArrayList<Integer>(permutation));
			return;
		}
		
		for(int i=0; i<nums.length; i++) {
			if (visited[i]) {
				continue;
			}
			permutation.add(nums[i]);
			visited[i]=true;
			dfs(result, nums, visited, permutation);
			visited[i]=false;
			permutation.remove(permutation.size()-1);
		} 
	}
}
