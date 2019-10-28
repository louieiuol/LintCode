//654. Sparse Matrix Multiplication
//中文English
//Given two Sparse Matrix A and B, return the result of AB.
//
//You may assume that A's column number is equal to B's row number.
//
//Example
//Example1
//
//Input: 
//[[1,0,0],[-1,0,3]]
//[[7,0,0],[0,0,0],[0,0,1]]
//Output:
//[[7,0,0],[-7,0,3]]
//Explanation:
//A = [
//  [ 1, 0, 0],
//  [-1, 0, 3]
//]
//
//B = [
//  [ 7, 0, 0 ],
//  [ 0, 0, 0 ],
//  [ 0, 0, 1 ]
//]
//
//
//     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
//AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
//                  | 0 0 1 |
//Example2
//
//Input:
//[[1,0],[0,1]]
//[[0,1],[1,0]]
//Output:
//[[0,1],[1,0]]

public class SparseMatrixMultiplication {
	public int[][] multiply(int[][] A, int[][] B) {
		if(A == null || B == null) return null;
		if(A.length == 0 || A[0].length == 0 || B.length == 0|| B[0].length == 0) return null;
		int[][] res=new int[A.length][B[0].length];
		for(int i=0; i<A.length; i++) {
			for(int j=0; j<B[0].length; j++) {
				for(int k=0; k<A[0].length; k++) {
					res[i][j]+=A[i][k]*B[k][j];
				}
			}
		}
		return res;
	}
}
