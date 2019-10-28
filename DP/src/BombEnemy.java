//553. Bomb Enemy
//中文English
//Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
//The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
//
//Example
//Example1
//
//Input:
//grid =[
//     "0E00",
//     "E0WE",
//     "0E00"
//]
//Output: 3
//Explanation:
//Placing a bomb at (1,1) kills 3 enemies
//Example2
//
//Input:
//grid =[
//     "0E00",
//     "EEWE",
//     "0E00"
//]
//Output: 2
//Explanation:
//Placing a bomb at (0,0) or (0,3) or (2,0) or (2,3) kills 2 enemies
//Notice
//You can only put the bomb at an empty cell.


public class BombEnemy {
	public int maxKilledEnemies(char[][] grid) {
		if(grid == null ||  grid.length == 0 || grid[0].length == 0) return  0;
		int row= grid.length; 
		int col= grid[0].length;
		int[][] up=new int[row][col];
		int[][] down=new int[row][col];
		int[][] left=new int[row][col];
		int[][] right=new int[row][col];
		//建立4个dp表 或者说是记忆化搜索表 记录当前点 grid[i][j]在上下左右区域的炸弹数
	
	
		//记录当前点 grid[i][j]上方区域的炸弹数 也就是从 0 到 i
		//累加是从把当前位置的上方的加到当前的 dp 也就是把 i-1 加给 i
 		for(int i=0; i<row;i++) {
			for(int j=0; j<col; j++) {
				if(grid[0][0] != 'W') {
					//如果当前点是墙则不做累加 
					if(grid[0][0] == 'E') {
						//如果当前点是炸弹 则累加1
						up[i][j]++;
					}
					if(i-1>=0) {
						//如果i-1在范围内
						up[i][j]+=up[i-1][j];
						//累加是从把当前位置的上方的加到当前的 dp 也就是把 i-1 加给 i
						//因为是上方暂时不考虑j 只考虑一个方向的
					}
				}
			}
		}
 		
 		
		//记录当前点 grid[i][j]下方区域的炸弹数 也就是从 i 到 row-1
		//累加是从把当前位置的下方的加到当前的 dp 也就是把 i+1 加给 i
 		for(int i=row-1; i>=0; i--) {
 			for(int j=0; j<col; j++) {
 				if(grid[i][j] != 'W') {
 					if(grid[i][j] == 'E') {
 						down[i][j]++;
 					}
 					if(i+1<row) {
 						down[i][j]+=down[i+1][j];
 					}
 				}
 			}
 		}
 		
		//记录当前点 grid[i][j]左方区域的炸弹数 也就是从 0 到 j
		//累加是从把当前位置的左方的加到当前的 dp 也就是把 j-1 加给 j		
 		for(int i=0; i<row; i++) {
 			for(int j=0; j<col; j++) {
 				if(grid[i][j] != 'W') {
 					if(grid[i][j] == 'E') {
 						left[i][j]++;
 					}
 					if(j-1>=0) {
 						left[i][j]+=left[i][j-1];
 					}
 				}
 			}
 		}
 	
 		
		//记录当前点 grid[i][j]右方区域的炸弹数 也就是从 j 到 col-1
		//累加是从把当前位置的右方的加到当前的 dp 也就是把 j+1 加给 j	
 		for(int i=0; i<col; i++) {
 			for(int j=col-1; j>=0; j--) {
 				if(grid[i][j] != 'W') {
 					if(grid[i][j] == 'E') {
 						right[i][j]++;
 					}
 					if(j+1<col) {
 						right[i][j]+=right[i][j+1];
 					}
 				}
 			}
 		}
 		
 		int counter=0;
 		for(int i=0;i<row; i++) {
 			for(int j=0; j<col;j++) {
 				if(grid[i][j] == '0') {
 					//对所有可以放置炸弹的点找上下左右四个方向总的炸弹个数 
 					int num=up[i][j]+down[i][j]+left[i][j]+right[i][j];
 					counter= Math.max(counter, num);
 					//取最大值
 				}
 			}
 		}
 		return counter;
	}
}
