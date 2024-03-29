
/*189. First Missing Positive
中文English
Given an unsorted integer array, find the first missing positive integer.

Example
Example 1:

Input:[1,2,0]
Output:3
Example 2:

Input:[3,4,-1,1]
Output:2
Challenge
Your algorithm should run in O(n) time and uses constant space.*/

//利用类似桶排序的方式来把当前元素插入到对应的地方
//尽管是两层循环 但是桶排序的average是 O(n)
//但是我们不能直接使用桶排序 那样会造成额外空间
public class FindMissingPositive {
	public int firstMissingPositive(int[] A) {
		if(A == null) return 1; //如果为空 那么返回第一个丢失的1
		for(int i=0; i<A.length ; i++) {
			while(A[i]<=A.length && A[i]>0 && A[A[i]-1] != A[i]) {
				//用while 里面要确保两个被调换的元素都能到他们分别对应的各自的位置
				//A[i]需满足 该数组对应的长度区间内 0<A[i]<=A.length 如不满足 停止对换 不满足时无法在数组内继续找到其对应的位置
				//因为要把当前元素插入到对应的地方 那么A[i]-1应当与i互换 则需要判定 A[index1]!=A[index2]
				//如果是两个相同的元素 则会出现死循环 无限的对换 如果对应位置已经被正确元素占用 那么不需要去对换
				//注意顺序
				//eg: 1 4 3 4 
				swap(A, i , A[i]-1);
			}
		}
		
		for(int i=0; i<A.length ; i++) {
			if(A[i]!=i+1) {
				return i+1;
			}
			//在排好序之后 返回缺失的元素
		}
		return A.length+1;
		//如果没有缺失的 则返回下一个
	}
	private void swap(int[] A, int i, int j) {
		int temp=A[i];
		A[i]=A[j];
		A[j]=temp;
	}
}
