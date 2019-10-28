//611. Knight Shortest Path
//中文English
//Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier) with a source position, find the shortest path to a destination position, return the length of the route.
//Return -1 if destination cannot be reached.
//
//Example
//Example 1:
//
//Input:
//[[0,0,0],
// [0,0,0],
// [0,0,0]]
//source = [2, 0] destination = [2, 2] 
//Output: 2
//Explanation:
//[2,0]->[0,1]->[2,2]
//Example 2:
//
//Input:
//[[0,1,0],
// [0,0,1],
// [0,0,0]]
//source = [2, 0] destination = [2, 2] 
//Output:-1
//Clarification
//If the knight is at (x, y), he can get to the following positions in one step:
//
//(x + 1, y + 2)
//(x + 1, y - 2)
//(x - 1, y + 2)
//(x - 1, y - 2)
//(x + 2, y + 1)
//(x + 2, y - 1)
//(x - 2, y + 1)
//(x - 2, y - 1)
//Notice
//source and destination must be empty.
//Knight can not enter the barrier.
//Path length refers to the number of steps the knight takes.



import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class KnightShortestPath {
		public int shortestPath(boolean[][] grid, Point source, Point destination) {
			if(grid == null || grid.length == 0 || grid[0].length == 0) return -1;
	        int[] dx={1, 1, -1, -1, 2, 2, -2, -2};
	        int[] dy={2,-2,  2, -2, 1,-1,  1, -1};
	        //利用方向数组 方便遍历 
	        int row=grid.length;
	        int col=grid[0].length;
	        Queue<Point> queue=new LinkedList<>();
	        queue.offer(source);
	        //初始化queue 把起点作为第一个开始
	        grid[source.x][source.y]=true;
	        //把起点的是否访问过设为false 
	        int counter=0;
	        while(!queue.isEmpty()){
	            int size=queue.size();
	            //其他都是BFS标准步骤
	            for(int i=0; i<size; i++){
	                Point curr=queue.poll();
	                if(curr.x == destination.x && curr.y == destination.y){
	                    return counter;
	                }
	                for(int j=0; j<8; j++){
	                    Point next=new Point(curr.x+dx[j], curr.y+dy[j]);
	                    if(next.x >=0 && next.x<row && next.y>=0 && next.y<col){
	                        if(!grid[next.x][next.y]){
	                            queue.offer(next);
	                            grid[next.x][next.y]=true;
	                        }
	                    }
	                }
	            }
	            //计数器注意是在每层循环的时候进行累加 在for外面 我们是统计起点到终点的步数 
	            //每次拿到queue的size 代表我们选定好要走这一步 for中循环的是同一层内走的可能性 应该看成一个整体的一步
	            counter++;
	        }
	        return -1;
		}
}
