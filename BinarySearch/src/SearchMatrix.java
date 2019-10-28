//28. Search a 2D Matrix
//中文English
//Write an efficient algorithm that searches for a value in an m x n matrix.
//
//This matrix has the following properties:
//
//Integers in each row are sorted from left to right.
//The first integer of each row is greater than the last integer of the previous row.
//Example
//Example 1:
//	Input:  [[5]],2
//	Output: false
//	
//	Explanation: 
//	false if not included.
//	
//Example 2:
//	Input:  [
//    [1, 3, 5, 7],
//    [10, 11, 16, 20],
//    [23, 30, 34, 50]
//],3
//	Output: true
//	
//	Explanation: 
//	return true if included.
//Challenge
//O(log(n) + log(m)) time

public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        //从左下角开始搜索
        int i=matrix.length-1;
        int j=0;
        while(i>=0 && j<matrix[0].length){
            if(matrix[i][j] > target){
                i--;
            }else if(matrix[i][j] < target){
                j++;
            }else{
                return true;
            }
        }
        return false;
    }
	//这个是O(m+n)的解法 并不是最优解
    
    
    //2分法
    public boolean searchMatrix2(int[][] matrix, int target) {
        // write your code here
        if(matrix == null || matrix.length ==0 || matrix[0].length == 0) return false;
        //find the max row that number is less than target
        int start=0;
        int end=matrix.length-1;
        //第一次2分确定row的位置 找到比target小的最大row
        while(start + 1 < end){
            int mid=start+(end-start)/2;
            if(matrix[mid][0] < target){
                start=mid;
            }else if(matrix[mid][0] > target){
                end=mid;
            }else{
            	//如果中间遇到target 就返回
                return true;
            }
        }
        int row=0;
    	//最后还要判断 遇到target 就返回
        if(matrix[end][0] == target || matrix[start][0] == target ){
            return true;
        }
        //last o 的问题 先匹配 end
        if(matrix[end][0] < target){
            row=end;
        }else if(matrix[start][0] < target){
            row=start;
        }else{
            return false;
        }
        start=0;
        end=matrix[0].length-1;
        //第二次2分确定col的位置 找target 
        while(start + 1 < end){
            int mid=start+(end-start)/2;
            if(matrix[row][mid] < target){
                start=mid;
            }else if(matrix[row][mid] > target){
                end=mid;
            }else{
                return true;
            }
        }
        //最后再判断一次是否找到了target 
        if(matrix[row][start] == target || matrix[row][end] == target){
            return true;
        }
        return false;
    } 
    
    //O(mlogn + nlogm)
}
