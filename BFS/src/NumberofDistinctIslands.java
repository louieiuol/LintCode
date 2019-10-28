//860. Number of Distinct Islands
//中文English
//Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical). You may assume all four edges of the grid are surrounded by water.
//
//Count the number of distinct islands. An island is considered to be the same as another if and only if one island has the same shape as another island (and not rotated or reflected).
//
//Notice that:
//
//11
//1
//and
//
// 1
//11
//are considered different island, because we do not consider reflection / rotation.
//
//Example
//Example 1:
//
//Input: 
//  [
//    [1,1,0,0,1],
//    [1,0,0,0,0],
//    [1,1,0,0,1],
//    [0,1,0,1,1]
//  ]
//Output: 3
//Explanation:
//  11   1    1
//  1        11   
//  11
//   1
//Example 2:
//
//Input:
//  [
//    [1,1,0,0,0],
//    [1,1,0,0,0],
//    [0,0,0,1,1],
//    [0,0,0,1,1]
//  ]
//Output: 1
//Notice
//The length of each dimension in the given grid does not exceed 50.

import java.util.*;
public class NumberofDistinctIslands {
	
	class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	public int numberofDistinctIslands(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
		HashSet<List<List<Integer>>> set=new HashSet<>();
		//利用set去掉重复形状的island，重复形状就是重复每个点的长宽
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				//对每个点进行bfs，返回一系列的横纵坐标组成的list，包含了组成list的形状，也就是每个点长宽组合成的形状
				List<List<Integer>> shape=bfs(grid, i, j);
				//把每种形状对应的长宽都加入set
				set.add(shape);
			}
		}
		return set.size();
		//返回独一无二的形状的island
	}
	
	
	private List<List<Integer>> bfs(int[][] grid, int i, int j){
		int[] dx= {-1, 0, 0, 1};
		int[] dy= {0, -1, 1, 0};
		//方向数组
		List<List<Integer>> res=new ArrayList<>();
		//return 该岛屿每个点到起点的长宽所代表的形状 会返回 [[长,宽], [长,宽], [长,宽]]
		Queue<Point> queue=new LinkedList<>();
		queue.offer(new Point(i, j));
		//加入起点
		grid[i][j]=1;
		//已经访问过的点直接原图标记1 
		while(queue.isEmpty()) {
			//层级遍历
			int size=queue.size();
			for(int level=0; level<size; level++) {
				Point curr=queue.poll();
				List<Integer> lst=new ArrayList<>();
				lst.add(curr.x-i);
				lst.add(curr.y-j);
				//当前访问点的长宽减去初始起点的位置
				res.add(lst);
				//加入返回列表
				for(int k=0; k<4; k++) {
					Point next=new Point(curr.x+dx[k], curr.y+dy[k]);
					//寻找下一个可能的点
					if(next.x >= 0 && next.x<grid.length && next.y>=0 && next.y < grid.length) {
						//看是否在可用范围内
						if(grid[next.x][next.y] == 0) {
							//看是否访问过
							grid[next.x][next.y]=1;
							//修改为访问过
							queue.offer(next);
						}
					}
				}
			}
		}
		return res;
	}
}
