/*159. Find Minimum in Rotated Sorted Array
中文English
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element. using O(logn)

Example
Example 1:

Input：[4, 5, 6, 7, 0, 1, 2]
Output：0
Explanation：
The minimum value in an array is 0.
Example 2:

Input：[2,1]
Output：1
Explanation：
The minimum value in an array is 1.*/


public class FindMinRotatedArray {
	public int findMinRotatedArray(int a[]) {
		if(a==null || a.length==0){
			return -1;
		}
		//如果使用开始点判断，必须先判断该数组是否旋转过，因为没扭过的数组start点不是中间点而是边界本身
        if (a[a.length-1] >= a[0]){
            return a[0];
        }
		int start=0, end=a.length-1;
		int target=a[start];
		//把目标点设为中间任意点，而中间点旋转过正好在尾部
		while(start+1 < end) { //控制区间大小
			int mid=start+(end-start)/2; //取中间点
			if(a[mid]<target) { 
				end=mid; //如果中间点小于等于目标点，在前面到中间寻找
			}else {
				start=mid; //如果中间点大于目标点，在中间到后面寻找
			}
		}
		//返回两边端点中的最小点
		return Math.min(a[start], a[end]);
	}
}
