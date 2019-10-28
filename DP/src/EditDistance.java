/*119. Edit Distance

Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example
Given word1 = "mart" and word2 = "karma", return 3.
*/
//http://www.cnblogs.com/yuzhangcmu/p/4190264.html

public class EditDistance {
    public int minDistance(String word1, String word2) {
        // write your code here
        if(word1 == null || word2 == null) return -1;
        int[][] matrix=new int[word1.length()+1][word2.length()+1];
        for(int i=0; i<word1.length()+1; i++){
            for(int j=0; j<word2.length()+1;j++){
                if(i == 0){
                    matrix[i][j]=j;
                }else if( j == 0){
                    matrix[i][j]=i;
                }else{
                    if(word1.charAt(i-1)==word2.charAt(j-1)){
                        matrix[i][j]=matrix[i-1][j-1];
                    }else{
                        matrix[i][j]=Math.min(matrix[i-1][j-1], matrix[i][j-1]);
                        matrix[i][j]=Math.min(matrix[i][j], matrix[i-1][j]);
                        matrix[i][j]++;
                    }
                }
            }
        }
        return matrix[word1.length()][word2.length()];
    }
}
