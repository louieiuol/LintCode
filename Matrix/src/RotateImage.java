//161. Rotate Image
//中文English
//You are given an n x n 2D matrix representing an image.
//Rotate the image by 90 degrees (clockwise).
//
//Example
//Example 1：
//
//Input:[[1,2],[3,4]]
//Output:[[3,1],[4,2]]
//Example 2:
//
//Input:[[1,2,3],[4,5,6],[7,8,9]]
//Output:[[7,4,1],[8,5,2],[9,6,3]]
//Challenge
//Do it in-place.

public class RotateImage {
    public void rotate(int[][] matrix) {
        // write your code here
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        for(int i=0; i<matrix.length/2; i++){
            for(int j=0; j<(matrix.length+1)/2;j++){
            	//for even n: it is okay to traversal only 1/4 of entire image, 1/2 of array
            	//for odd n: we need to include traversal through middle and half of middle of entire image
            	// this 6 blocks after 4 times of rotation will overlap entire image (for odd n: middle part never change)
            	// X X X * *
            	// X X X * *
            	// * * * * *
            	// * * * * *
            	// * * * * *
            	//i j range are different to avoid re-rotate map
                int temp=matrix[i][j]; 
                //store current initial value as tmp
                matrix[i][j]=matrix[matrix.length-1-j][i];
                //begin rotation, replace current value with next rotation value
                matrix[matrix.length-1-j][i]=matrix[matrix.length-1-i][matrix.length-1-j];
                //next rotation, replace the last changed value to its next rotation value
                matrix[matrix.length-1-i][matrix.length-1-j]=matrix[j][matrix.length-1-i];
                matrix[j][matrix.length-1-i]=temp;
                //after 4 times rotation, place the current value with initial value
            }
        }
    }
}
