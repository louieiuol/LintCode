//989. Array Nesting
//中文English
//A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the longest length of set S, where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.
//
//Suppose the first element in S starts with the selection of element A[i] of index = i, the next element in S should be A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding right before a duplicate element occurs in S.
//
//Example
//Example1
//
//Input: [5,4,0,3,1,6,2]
//Output: 4
//Explanation: 
//A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
//
//One of the longest S[K]:
//S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
//Example2
//
//Input: [0,1,2]
//Output: 1
//Notice
//N is an integer within the range [1, 20,000].
//The elements of A are all distinct.
//Each element of A is an integer within the range [0, N-1].

//根据题意观察可以得知 A[0]=5 A[5]=6 A[6]=2 A[2]=0 A[0]已经被访问了 length = 4 这样的环状关系决定了结果的最大长度 枚举法遍历每个数 都找环状最长值来求解

public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        // Write your code here
    	if(nums == null || nums.length == 0) return 0;
    	boolean[] visited=new boolean[nums.length];
    	//建立访问数组标记已经被访问过的值
    	int res=0;
    	//设初始结果为0
    	for(int i=0; i<nums.length; i++) {
    		if(visited[i]) {
    			//如果已经被访问过跳过当前数字
    			continue;
    		}
    		int len=0;
    		//当前的长度为0
    		int curr=i;
    		//当前值设为当前坐标
    		while(curr<nums.length && !visited[curr]) {
    			visited[curr]=true;
    			//标记当前为true
    			len++;
    			//长度+1
    			curr=nums[curr];
    			//当前数设为array中的下一个
    		}
    		res=Math.max(res, len);
    		//while循环完后求最值 
    	}
    	return res;
    	//时间复杂度 O(n)每个元素只访问了一遍 
    	//空间复杂度 O(n)用于来判断数组中的数字是否被访问过
    }
}
