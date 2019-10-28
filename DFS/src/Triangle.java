/*109. Triangle
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

Example
example 1
Given the following triangle:

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

example 2
Given the following triangle:

[
     [2],
    [3,2],
   [6,5,7],
  [4,4,8,1]
]
The minimum path sum from top to bottom is 12 (i.e., 2 + 2 + 7 + 1 = 12).*/

/*
使用DFS 加记忆矩阵的解法.

mem[i][j]表示第i行第j列的解。它的解可以由下一行推出：mem[i][j] = Math min (mem[i+1][j] + mem[i+1][j+1])*/

public class Triangle {
    public int minimumTotal(int[][] triangle) {
        if(triangle == null || triangle[0] == null) return -1;
        if(triangle.length == 0 || triangle[0].length == 0) return -1;
        
        int[][] memory=new int[triangle.length][triangle.length];
        for(int i=0; i<triangle.length; i++){
            for(int j=0; j<triangle.length;j++){
                memory[i][j]=Integer.MAX_VALUE;
                //initialize all variables to max value
            }
        }
        return memorySearch(0,0,triangle,memory);
        //perform dfs from start 
    }
    
    private int memorySearch(int i, int j, int[][] triangle, int[][] memory){
        if(i >= triangle.length){
            return 0;
        }
        //dfs end case: out of boundary
        
        if(memory[i][j] != Integer.MAX_VALUE){
            return memory[i][j];
        }
        //if point has reached return value itself, return itself
        
        memory[i][j]=Math.min(memorySearch(i+1, j, triangle, memory), 
        memorySearch(i+1, j+1, triangle,memory))+triangle[i][j];
        //current solution is dependent on the min value of the solution of next line j or j+1 value
        //and add with current triangle value
        return memory[i][j];
        //return current solution
    }
}
