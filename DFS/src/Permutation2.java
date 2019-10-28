import java.util.ArrayList;
import java.util.List;

public class Permutation2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        // write your code here
        List<List<Integer>> result=new ArrayList<List<Integer>>();
        if(nums!=null && nums.length!=0){
            dfs(result, nums, new boolean[nums.length], new ArrayList<Integer>());
        }else {
        	result.add(new ArrayList<Integer>());
        }
        return result;
    }

	private void dfs(List<List<Integer>> result, int[] nums, boolean[] visited, ArrayList<Integer> permutation) {
		if(permutation.size() == nums.length) {
			int counter=0;
			boolean flag=false;
			for (List<Integer> lst: result) {
				for(int i=0; i<lst.size();i++) {
					if(lst.get(i) == permutation.get(i)) {
						counter++;
					}
				}
				
				if(counter == permutation.size()) {
					flag=true;
					break;
				}else {
					counter=0;
				}
			}
			
			if(!flag) {
				result.add(new ArrayList<Integer>(permutation));
			}
			return;
		}
		
		for(int i=0; i< nums.length; i++) {
			if(visited[i]) {
				continue;
			}
			
			permutation.add(nums[i]);
			visited[i]=true;
			dfs(result,nums,visited,permutation);
			visited[i]=false;
			permutation.remove(permutation.size()-1);
		}
	}
}
