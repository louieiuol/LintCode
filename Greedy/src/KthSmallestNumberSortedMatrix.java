import java.util.*;
//401. Kth Smallest Number in Sorted Matrix
//中文English
//Find the kth smallest number in a row and column sorted matrix.
//
//Each row and each column of the matrix is incremental.
//
//Example
//Example 1:
//
//Input:
//[
//  [1 ,5 ,7],
//  [3 ,7 ,8],
//  [4 ,8 ,9],
//]
//k = 4
//Output: 5
//Example 2:
//
//Input:
//[
//  [1, 2],
//  [3, 4]
//]
//k = 3
//Output: 3
//Challenge
//O*(klogn*) time, n is the maximum of the width and height of the matrix.
public class KthSmallestNumberSortedMatrix {
	public class Point{
		int x;
		int y;
		int val;
		public Point(int x, int y, int val) {
			this.x=x;
			this.y=y;
			this.val=val;
		}
	}
	
	public int kthSmallest(int[][] matrix, int k) {
		if(matrix == null || matrix[0] == null || k <=0) return -1;
		if(matrix.length == 0 || matrix[0].length == 0) return -1;
		PriorityQueue<Point> pq=new PriorityQueue<>((Point p1, Point p2)-> {
			return p1.val-p2.val;
		});
		//1. top() O(1)  2. push(XXX) O(logn) 3.poll() O(logn)
		int[] dx= {1, 0};
		int[] dy= {0, 1};
		int res=0;
		boolean[][] visited=new boolean[matrix.length][matrix[0].length];
		pq.offer(new Point(0,0,matrix[0][0]));
		visited[0][0]=true;
		while(k>0 && !pq.isEmpty()) {
			Point curr=pq.poll();
			res=curr.val;
			k--;
			for(int i=0; i<2;i++){
				int disx=curr.x+dx[i];
				int disy=curr.y+dy[i];
				if(disx<matrix.length && disy<matrix[0].length && !visited[disx][disy]) {
					pq.offer(new Point(disx, disy, matrix[disx][disy]));
					visited[disx][disy]=true;
				}
			}
		}
		return res;
		//O(klogk) heap最多装k个 因为每次遍历都有弹出的 跟n无关 不用遍历全部元素
	}
}
