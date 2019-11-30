//90. k Sum II
//中文English
//Given n unique postive integers, number k (1<=k<=n) and target.
//
//Find all possible k integers where their sum is target.
//
//Example
//Example 1:
//
//Input: [1,2,3,4], k = 2, target = 5
//Output:  [[1,4],[2,3]]
//Example 2:
//
//Input: [1,3,4,6], k = 3, target = 8
//Output:  [[1,3,4]]

import java.util.ArrayList;
import java.util.List;

public class Ksum2 {
	 public List<List<Integer>> kSumII(int[] A, int k, int target) {
	        // write your code here
		 if(A != null && k>0) {
			 List<List<Integer>> result=new ArrayList<List<Integer>>();
			 if(A.length >0) {
				 dfs(A, 0, k, target, new ArrayList<Integer>(), result);
			 }
			 return result;
		 }
		 return null;
	 }

	private void dfs(int[] a, int index, int k, int target, ArrayList<Integer> lst, List<List<Integer>> result) {
		if(k == 0 && target == 0 ) {
			result.add(new ArrayList<Integer>(lst));
			return;
		}
		
		if(k<0 || target <0 || index>a.length) {
			return;
		}
		
		dfs(a, index+1, k , target, lst, result);
		lst.add(a[index]);
		dfs(a, index+1, k-1, target-a[index], lst, result);
		lst.remove(lst.size()-1);
	}
}
