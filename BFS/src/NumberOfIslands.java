import java.util.LinkedList;
import java.util.Queue;


public class NumberOfIslands {
    public class Coordinate{
        int x,y;
        public Coordinate(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    public int numIslands(boolean[][] grid) {
        int count=0;
        if(grid!=null) {
            if( grid.length >0 || grid[0].length >0) {
                for(int i=0; i< grid.length; i++) {
                    for(int j=0; j<grid[0].length; j++) {
                        if(grid[i][j]) {
                            markByBfs(grid, i ,j);
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private void markByBfs(boolean[][] grid, int i, int j) {
        // TODO Auto-generated method stub
        int[] dx= {1,-1,0,0};
        int[] dy= {0,0,1,-1};
        Queue<Coordinate> queue=new LinkedList<Coordinate>();
        queue.add(new Coordinate(i,j));
        grid[i][j]=false;
        while(!queue.isEmpty()) {
            int size=queue.size();
            for(int index=0; index< size ; index++) {
                Coordinate location=queue.poll();
                for(int range=0; range<4; range++) {
                    Coordinate temp=new Coordinate(location.x+dx[range],location.y+dy[range]);
                    if(inBound(temp,grid)) {
                        if(grid[temp.x][temp.y]) {
                            queue.add(temp);
                            grid[temp.x][temp.y]=false;
                        }
                    }
                }
            }
        }
    }

    private boolean inBound(Coordinate temp,boolean[][] grid) {
        // TODO Auto-generated method stub
        if(temp.x >=0 && temp.y >=0 && temp.x <grid.length && temp.y < grid[0].length) {
            return true;
        }
        return false;
    }



}
