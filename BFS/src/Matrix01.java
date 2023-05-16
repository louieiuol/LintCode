//974. 01 Matrix
//中文English
//Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
//
//The distance between two adjacent cells is 1.
//
//Example
//Example 1:
//
//Input:
//[[0,0,0],
// [0,0,0],
// [0,0,0],
// [0,0,0],
// [0,0,0]]
//Output:
//[[0,0,0],
// [0,0,0],
// [0,0,0],
// [0,0,0],
// [0,0,0]]
//Example 2:
//
//Input:
//[[0,1,0,1,1],
// [1,1,0,0,1],
// [0,0,0,1,0],
// [1,0,1,1,1],
// [1,0,0,0,1]]
//Output:
//[[0,1,0,1,2],
// [1,1,0,0,1],
// [0,0,0,1,0],
// [1,0,1,1,1],
// [1,0,0,0,1]]
//Notice
//The number of elements of the given matrix will not exceed 10,000.
//There are at least one 0 in the given matrix.
//The cells are adjacent in only four directions: up, down, left and right.

//BFS 简化成多点出发更新最短距离的问题

import java.util.*;

public class Matrix01 {
    class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return matrix;
        boolean[][] visited=new boolean[matrix.length][matrix[0].length];
        int[] dx= {-1, 0, 0 , 1};
        int[] dy= {0, -1, 1 , 0};
        Queue<Point> queue=new LinkedList<>();
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    queue.offer(new Point(i, j));
                    visited[i][j]=true;
                }
            }
        }
        while(queue.isEmpty()) {
            int size=queue.size();
            //按照层级更新
            for(int i=0; i<size; i++) {
                Point curr=queue.poll();
                for(int j=0; j<4;j++) {
                    Point next=new Point(curr.x+dx[j], curr.y+dy[j]);
                    if(next.x>=0 && next.x<matrix.length && next.y>=0 && next.y<matrix[0].length) {
                        //检查范围
                        if(!visited[next.x][next.y] && matrix[next.x][next.y] != 0) {
                            //没有被访问过 并且不是0
                            matrix[next.x][next.y]=matrix[curr.x][curr.y]+1;
                            //类似于记忆化搜索 节约空间 下一个点到0的距离是由当前点再走一步得到的 所以是当前点的到0距离+1
                            //更新距离
                            visited[next.x][next.y]=true;
                            //标记已经被访问
                            queue.offer(next);
                            //加入队列
                        }
                    }
                }
            }
        }
        return matrix;
    }
}
