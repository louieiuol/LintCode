//Example1
//
//Input:
//[[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
//Output:
//[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
//
//Explanation:
//the 2D grid is:
//INF  -1  0  INF
//INF INF INF  -1
//INF  -1 INF  -1
//  0  -1 INF INF
//the answer is:
//  3  -1   0   1
//  2   2   1  -1
//  1  -1   2  -1
//  0  -1   3   4
//Example2
//
//Input:
//[[0,-1],[2147483647,2147483647]]
//Output:
//[[0,-1],[1,2]]


import java.util.*;
public class WallsAndGates {
    class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    public void wallsAndGates(int[][] rooms) {
        if(rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        int[] dx={1,0,0,-1};
        int[] dy={0,1,-1,0};
        //这道题需要反着想 把所有点为0的加入queue 一层一层往外找 走到能去的INF的点 更新到当前距离
        //因为是找存在的INF 相当于整个room成为了visited 所以不需要额外空间存visited
        Queue<Point> queue=new LinkedList<>();
        for(int i=0; i<rooms.length; i++){
            for(int j=0; j<rooms[0].length; j++){
                if(rooms[i][j] == 0){
                    queue.offer(new Point(i,j));
                }
            }
        }
        int dis=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            dis++;
            for(int i=0; i<size; i++){
                Point curr=queue.poll();
                for(int j=0;j<4;j++){
                    Point next=new Point(curr.x+dx[j], curr.y+dy[j]);
                    if(next.x>=0 && next.x<rooms.length && next.y>=0 && next.y<rooms[0].length){
                        if(rooms[next.x][next.y] == Integer.MAX_VALUE){
                            queue.offer(next);
                            rooms[next.x][next.y]=dis;
                        }
                    }
                }
            }
        }
    }
}
