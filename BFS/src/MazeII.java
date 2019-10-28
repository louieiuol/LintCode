//788. The Maze II
//This is the problem you have done. Do you remember how to solve it?
// My submissions history
// 
//Submission Time
//My Code
//Problem
//Run Time
//Language
//25 days ago	
//Accepted
//The Maze II	
//276 ms
//Java
//There's no more to show
//
//  
//788. The Maze II
//中文English
//There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
//
//Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
//
//The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
//
//Example
//Example 1:
//	Input:  
//	(rowStart, colStart) = (0,4)
//	(rowDest, colDest)= (4,4)
//	0 0 1 0 0
//	0 0 0 0 0
//	0 0 0 1 0
//	1 1 0 1 1
//	0 0 0 0 0
//
//	Output:  12
//	
//	Explanation:
//	(0,4)->(0,3)->(1,3)->(1,2)->(1,1)->(1,0)->(2,0)->(2,1)->(2,2)->(3,2)->(4,2)->(4,3)->(4,4)
//
//Example 2:
//	Input:
//	(rowStart, colStart) = (0,4)
//	(rowDest, colDest)= (0,0)
//	0 0 1 0 0
//	0 0 0 0 0
//	0 0 0 1 0
//	1 1 0 1 1
//	0 0 0 0 0
//
//	Output:  6
//	
//	Explanation:
//	(0,4)->(0,3)->(1,3)->(1,2)->(1,1)->(1,0)->(0,0)
//	
//Notice
//1.There is only one ball and one destination in the maze.
//2.Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
//3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
//4.The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

import java.util.*;
public class MazeII {
	//BFS +记忆化搜索
	class Point{
		int x;
		int y;
		int l;
		public Point(int x, int y, int l) {
			this.x=x;
			this.y=y;
			this.l=l;
		}
		//x y记录坐标 l记录起点到当前点的距离 （并非最优， 只是用来做短暂储存）
	}
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    	if(maze == null || maze.length == 0 || maze[0].length == 0) return -1;
    	if(start == null || start.length != 2 || destination == null || destination.length != 2) return -1;
    	//先判断edge case 
    	if(maze[start[0]][start[1]] == 1 || maze[destination[0]][destination[1]] == 1) return -1;
    	//如果起始点 和结束点为障碍物 返回-1
    	if(start[0] == destination[0] && start[1] == destination[1]) return 0;
    	int[][] map=new int[maze.length][maze[0].length];
    	//建立记忆化搜索数组 用于记录初始点到每个点的最短距离
    	//这里不用建立访问点数组 点可以被重复访问 也不用去修改原图
    	int[] dx= {0,1,-1,0};
    	int[] dy= {-1,0,0,1};
    	for(int i=0; i<map.length; i++) {
    		for(int j=0; j<map.length;j++) {
    			map[i][j]=Integer.MAX_VALUE;
    			//把所有map内的值都初始化为最大 
    		}
    	}
    	Queue<Point> queue=new LinkedList<>();
    	queue.offer(new Point(start[0], start[1], 0));
    	while(!queue.isEmpty()) {
    		Point curr=queue.poll();
    		if(curr.l>=map[curr.x][curr.y]) {
    			continue;
    			//如果当前点短暂储存的长度 比map现有长度还要大 那么跳过当前点
    		}
    		map[curr.x][curr.y]=curr.l;
    		//更新图到当前最小值
    		for(int j=0; j<4;j++) {
    			int len=curr.l;
    			//初始长度为当前点的长度 用于找到球的滚动最长距离
        		Point next=new Point(curr.x, curr.y, curr.l);
        		//创建下一个点根据当前点出发
    			while(next.x >=0 && next.x<maze.length && next.y>=0 && next.y<maze[0].length && maze[next.x][next.y] == 0) {
    				len++;
    				next.x+=dx[j];
    				next.y+=dy[j];
    				//只要点是合法的我们就让它沿着当前方向一直往前
    			}
    			//越界之后变为不合法了 所以我们退一步找到最大值
    			len--;
    			next.x-=dx[j];
    			next.y-=dy[j];
    			curr.l=len;
    			//更新curr点初始点到其距离 根据 len
    			queue.offer(next);
    		}
    	}
    	//当更新完map后 如果还是为最大值那么说明没有找到 否则返回记忆化搜索里面的最大值
    	if(map[destination[0]][destination[1]] == Integer.MAX_VALUE) return -1;
    	return map[destination[0]][destination[1]];
    }
}
