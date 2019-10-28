//778. Pacific Atlantic Water Flow
//Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
//
//Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
//
//Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
//
//Example
//Example 1:
//
//Input:
//matrix = 
//[[1,2,2,3,5],
//[3,2,3,4,4],
//[2,4,5,3,1],
//[6,7,1,4,5],
//[5,1,1,2,4]]
//Output:
//[[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
//Explanation:
//Pacific ~ ~ ~ ~ ~
//      ~ 1 2 2 3 5 *
//      ~ 3 2 3 4 4 *
//      ~ 2 4 5 3 1 *
//      ~ 6 7 1 4 5 *
//      ~ 5 1 1 2 4 *
//        * * * * * Atlantic
//Example 2:
//
//Input:
//matrix =
//[[1,2],
//[4,3]]
//Output:
//[[0,1],[1,0],[1,1]]
//Notice
//1.The order of returned grid coordinates does not matter.
//2.Both m and n are less than 150.

import java.util.*;
public class PacificAtlanticWaterFlow {
	public List<List<Integer>> pacificAtlantic(int[][] matrix) {
		List<List<Integer>> res=new ArrayList<>();
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
		boolean[][] visitedPacific=new boolean[matrix.length][matrix[0].length];
		boolean[][] visitedAlantic=new boolean[matrix.length][matrix[0].length];
		//构建两个数组分别记录每个点能否分别访问太平洋和大西洋
		for(int i=0; i<matrix.length; i++) {
			//从上往下循环 寻找上下两边能到太平洋和大西洋的点 放入访问图 设置初始高度为最低 带入横纵坐标
			//上面点开始到大西洋 下面点开始到太平洋
			dfs(matrix, visitedPacific, Integer.MIN_VALUE, i, matrix[0].length);
			dfs(matrix, visitedAlantic, Integer.MIN_VALUE, i, 0);
		}
		for(int i=0; i<matrix[0].length; i++) {
			//从左往右循环 寻找左右两边能到太平洋和大西洋的点 放入访问图 设置初始高度为最低 带入横纵坐标
			//左边点开始到大西洋 右边点开始到太平洋
			dfs(matrix, visitedPacific, Integer.MIN_VALUE, matrix.length, i);
			dfs(matrix, visitedAlantic, Integer.MIN_VALUE, 0, i);
		}
		//遍历访问数组中的每个点 如果在相同点都能抵达两个洋 那加入返回列表
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				if(visitedPacific[i][j] && visitedAlantic[i][j]) {
					List<Integer> lst=new ArrayList<>();
					lst.add(i);
					lst.add(j);
					res.add(lst);
				}
			}
		}
		return res;
	}
	int[] dx= {-1, 0, 0, 1};
	int[] dy= {0, -1, 1, 0};
	private void dfs(int[][] matrix, boolean[][] visited, int height, int x, int y) {
		if(x<0 || x>=matrix.length || y<0 || y>=matrix[0].length) {
			//如果越界直接返回
			return;
		}
		if(matrix[x][y] <height || visited[x][y]) {
			//从外到内寻找高度大于等于的现有点的高度的 遇到小的返回 已经访问过返回
			return;
		}
		visited[x][y]=true;
		for(int i=0; i<4; i++) {
			dfs(matrix, visited, matrix[x][y], x+dx[i], y+dy[i]);
		}
	}
}
