/*110. Minimum Path Sum
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom
 right which minimizes the sum of all numbers along its path.
Example
Example 1:
	Input:  [[1,3,1],[1,5,1],[4,2,1]]
	Output: 7
	
	Explanation:
	Path is: 1 -> 3 -> 1 -> 1 -> 1


Example 2:
	Input:  [[1,3,2]]
	Output: 6
	
	Explanation:  
	Path is: 1 -> 3 -> 2

Notice
you can only go right or down in the path.*/
import java.util.*;
public class MinimumPath {
    public int minPathSum1(int[][] grid) {
        return dfs(0,0,grid);
    }   
    public int dfs(int i, int j, int[][] grid){
        if(i==grid.length-1 && j==grid[0].length-1){
            return grid[i][j];
        }
     
        if(i<grid.length-1 && j<grid[0].length-1){
            int r1 = grid[i][j] + dfs(i+1, j, grid);
            int r2 = grid[i][j] + dfs(i, j+1, grid);
            return Math.min(r1,r2);
        }
     
        if(i<grid.length-1){
            return grid[i][j] + dfs(i+1, j, grid);
        }
     
        if(j<grid[0].length-1){
            return grid[i][j] + dfs(i, j+1, grid);
        }
     
        return 0;
    }
    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int M = grid.length;
        int N = grid[0].length;
        int[][] sum = new int[M][N];

        sum[0][0] = grid[0][0];

        for (int i = 1; i < M; i++) {
            sum[i][0] = sum[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < N; i++) {
            sum[0][i] = sum[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
            }
        }
        return sum[M - 1][N - 1];
    }
}
