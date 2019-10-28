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



import java.util.*;
public class SubSet {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res=new ArrayList<>();
		if(nums == null || nums.length ==0) {
			res.add(new ArrayList<Integer>());
			return res;
		}
		Arrays.sort(nums);
		//因为不存在重复的情况 可以使用bit operation来做 
		//需要先对集合进行排序 因为是递归我们没办法走回头路
		//一个集合的全子集 就是在找每个集合中元素对应出现的位置 比如[0 1 2]:  [ 1 2] 
		//我们外层需要构造每个子集的长度的循环 每个子集可能包含 0 1 2 ... 个元素
		//用从0 到 2进制 1的左移动来确定每层的个数
		//这个子集 可以用bit表示 110 我们把没有出现的位记为0 出现的位记为1 我们需要用 2^n个位来表示每个数是否出现 
		//我们需要 0 到 1向左移集合长度位 这么多个数来表示 所有可能的情况 注意这里是每个数出现的情况 跟具体的数字大小无关
		for(int i=0; i<(1 << nums.length); i++){
			//j是从0到1所需要移动的最大的位数 也就是这个数有多少位 因为是n个元素 我们需要n位去表示每个出现的次数
			List<Integer> lst=new ArrayList<>();
			for(int j=0; j < nums.length; j++) {
				if((i & (1 << j))> 0){
					//利用 & 检查该位上有没有1 如果有1 代表该集合中对于的数应该在集合里
					//j左移动 集合长度位的1 来对每种情况中对应哪些数在集合里进行判定 
					lst.add(nums[j]);
					//把这个坐标对应的数加入到list中
				}
			}
			res.add(lst);
			//把所有相同个数的子集加入集合 第一层循环 0 1 (2个) 00 01 10 11 (4个） 001 010 011 （8个）...  循环完后加入结果 
		}
		return res;
	}
	
	//给一个数 返回从0到该数的全部子集
	public List<List<Integer>> giveNumberAllSubsets(int n){
		List<List<Integer>> res=new ArrayList<>();
		for(int i=0; i < (1 << n); i++) {
			List<Integer> lst=new ArrayList<>();
			System.out.print("[");
			for(int j=0; j < n; j++) {
				if((i & (1 << j))>0 )  {
					lst.add(i);
					System.out.print(j+",");
				}
			}
			System.out.println("]");
			res.add(lst);
		}
		return res;
	}
}
