//1272. Kth Smallest Element in a Sorted Matrix
//中文English
//Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
//
//Note that it is the kth smallest element in the sorted order, not the kth distinct element.
//
//Example
//Example1
//
//Input:
//[[ 1,  5,  9],[10, 11, 13],[12, 13, 15]]
//8
//Output: 13
//Example2
//
//Input:
//[[-5]]
//1
//Output: -5
//Challenge
//If k << n^2, what's the best algorithm?
//How about k ~ n^2?
//
//Notice
//You may assume k is always valid, 1 ≤ k ≤ n^2.


public class KthSmallestElementSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
    	if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
    	int start=matrix[0][0];
    	int end=matrix[matrix.length-1][matrix[0].length-1];
    	//找到最大值 最小值开始2分搜索
    	while(start < end) {
    		//非直接比较mid 和target valid问题 都要尽量吧 start < end的判断条件缩成一个点 (而不是 start +1 < end)
    		int mid=start+(end-start)/2;
    		int count= getCount(matrix, mid);
    		if(count < k) {
    			//数量小于k 需要更多元素 kth在后面
    			//在后面去找
    			start=mid+1;
    			//用趋进于值的方法时候要+1 不能和end一样 否则无限循环
    		}else {
    		    //数量大于k 证明kth 也在其中 
    			//在前面去找
    			end=mid;
    		}
    	}
    	return start;
    	//最后汇聚成一个点 直接返回start
    }

	private int getCount(int[][] matrix, int mid) {
		//需要花O(n)的时间
		// 从矩阵的左下角开始搜索 多少个数 <=mid 
		// 如果个数大于k 那么则包含k 返回true 否则返回false 
		int i=matrix.length-1; //i代表第一个坐标 左下
		int j=0; //j代表第二个坐标 左下 
		int result=0; //记录一共小于mid的元素个数
		while( i >=0 ) {
			while( j <matrix[0].length && matrix[i][j] <= mid) {
				j++;
			}
			result+=j;
			//j所停在的点就是该一整行满足条件的点
			i--;
		}
		return result;
	}
}

// O(nlog(max-min))
