/*162. Set Matrix Zeroes
中文English
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

Example
Example 1:

Input:[[1,2],[0,3]]
Output:[[0,2],[0,0]]
		
Example 2:

Input:[[1,2,3],[4,0,6],[7,8,9]]
Output:[[1,0,3],[0,0,0],[7,0,9]]
Challenge
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?	*/	

public class SetMatrixZeros {
	//this solution using no extra space, time complexity is O(n^2)
	public void setZeroes(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        //用两个判断作为 row col两行是否包含零的判断
        boolean row_zeros=false;
        boolean col_zeros=false;
        for(int i=0;i<matrix.length;i++){
            if(matrix[i][0] == 0){
                col_zeros=true;
                break;
            }
        }
        for(int j=0;j<matrix[0].length;j++){
            if(matrix[0][j] == 0){
                row_zeros=true;
                break;
            }
        }
        //判断初始行是否包含0
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][j]== 0){
                    matrix[0][j]=0;
                    matrix[i][0]=0;
                }
            }
        }
        //更新从1开始的行，如果包含0， 把对应的 row col的i j值更新为0
        
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][0]== 0 || matrix[0][j] == 0){
                    matrix[i][j]=0;
                }
            }
        }
        //对于的row col为0的 去更新所有的点
        
        if(row_zeros){
            for(int j=0;j<matrix[0].length;j++){
               matrix[0][j] = 0;
            }
        }
        
        if(col_zeros){
            for(int i=0;i<matrix.length;i++){
                matrix[i][0] = 0;
            }
        }
        //最后更新row col
    }
}
