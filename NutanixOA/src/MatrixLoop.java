import java.util.*;

public class MatrixLoop {
//	给一个matrix，不同的字母代表不同的颜色，一个matrix里面可能有多个颜色。问这个matrix里面是否有某个同一个颜色形成的loop。
//	比如：
//	B B B B B
//	B G G G B
//	B G B G B
//	B G G G B
//	B B B G B         
//	这个例子return True，因为G组成了一个loop （实际上B也组成了loop

	class Point{
		int x;
		int y;
		char color;
		int dir;
		// -1为初始方向
		// 0 左边
		// 1 下面
		// 2 右边
		// 3 上面
		Point(int x, int y, char color, int dir){
			this.x=x;
			this.y=y;
			this.color=color;
			this.dir=dir;
		}
	}
	
	int[] dx= {-1, 0 , 1 ,0};
	int[] dy= {0, -1, 0, 1};
	
	public boolean findLoop(char[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				if(search(matrix, i, j, matrix[i][j])) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean search(char[][] matrix, int x, int y, char color) {
		Queue<Point> queue=new LinkedList<>();
		queue.offer(new Point(x, y, color, -1));
		int step=0;
		while(!queue.isEmpty()) {
			Point curr=queue.poll();
			step++;
			for(int i=0; i<4; i++) {
				int nextX=curr.x+dx[i];
				int nextY=curr.y+dy[i];
				if(isValidRange(nextX, nextY, matrix)){
					Point next=new Point(nextX, nextY, matrix[nextX][nextY], i);
					if(next.color == color && notPrevDirection(next.dir,curr.dir)) {
						if(next.x == x && next.y == y && step>1) {
							return true;
						}
						//System.out.println("x:"+next.x + " "+ "y:"+next.y);
						queue.offer(next);
					}
				}
			}
		}
		return false;
	}

	private boolean notPrevDirection(int dirCurr, int dirPrev) {
		// TODO Auto-generated method stub
		if(dirCurr == 0 && dirPrev == 2) return false;
		if(dirCurr == 1 && dirPrev == 3) return false;
		if(dirCurr == 2 && dirPrev == 0) return false;
		if(dirCurr == 3 && dirPrev == 1) return false;
		return true;
	}

	private boolean isValidRange(int nextX, int nextY, char[][] matrix) {
		// TODO Auto-generated method stub
		return nextX>=0 && nextX<matrix.length && nextY>=0 && nextY<matrix[0].length;
	}
	
}
