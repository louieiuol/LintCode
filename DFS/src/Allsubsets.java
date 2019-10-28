import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Allsubsets {
	public List<List<Integer>> Solution1(int[] nums){
		//dfs 
		//dynamic allocation 
		
		List<List<Integer>> result=new ArrayList<List<Integer>>();
		if(nums !=null) {
			Arrays.sort(nums);
			dfs(nums, 0, new ArrayList<Integer>(), result);
			return result;
		}
		return null;
	}

	private void dfs(int[] nums, int i, ArrayList<Integer> lst, List<List<Integer>> result) {
		if(i == nums.length) {
			result.add(new ArrayList<Integer>(lst));
			return;
		}
		
		
		lst.add(nums[i]);
		dfs(nums, i+1,lst, result);
		
		lst.remove(lst.size()-1);
		dfs(nums, i+1, lst, result);
		
	}
	
	public List<List<Integer>> Solution2(int[] nums){
		
		if(nums != null ) {
			List<List<Integer>> result=new ArrayList<List<Integer>>();
			Arrays.sort(nums);
			if(nums.length !=0 ) {
				dfs2(nums, 0, new ArrayList<Integer>(), result);
				return result;
			}
		}
		return null;
	}

	private void dfs2(int[] nums, int i, ArrayList<Integer> lst, List<List<Integer>> result) {
		// TODO Auto-generated method stub
		
		result.add(new ArrayList<Integer>(lst));
		
		for(int n=i; n<nums.length; n++) {
			lst.add(nums[i]);
			dfs(nums, i+1, lst, result);
			lst.remove(lst.size()-1);
		}
		
	}
	
	public List<List<Integer>> Solution3(int[] nums){
		if(nums != null) {
			List<List<Integer>> result=new ArrayList<List<Integer>>();
			Arrays.sort(nums);
			if(nums.length != 0) {
				Queue<List<Integer>> queue=new LinkedList<List<Integer>>();
				queue.add(new ArrayList<Integer>());
				while(!queue.isEmpty()) {
					List<Integer> subset=queue.poll();
					result.add(subset);
					
					for(int i=0; i< nums.length; i++) {
						if(subset.size() == 0 || subset.get(subset.size()-1) < nums[i]) {
							List<Integer> nextset=new LinkedList<Integer>(subset);
							nextset.add(nums[i]);
							queue.add(nextset);
						}
					}
				}
			}
			return result;
		}
		return null;
	}
	
	
}
