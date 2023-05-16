//986. Battleships in a Board
//中文English
//Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
//
//You receive a valid board, made of only battleships or empty slots.
//Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
//At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
//Example
//Example1
//
//Input:
//X..X
//...X
//...X
//Output: 2
//Explanation:
//In the above board there are 2 battleships.
//Example2
//
//Input:
//...X
//XXXX
//...X
//Explanation:
//This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
//Challenge
//Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?


public class BattleshipInABoard {
    int result=0;
    int[] dx= {0,0,-1,1};
    int[] dy= {-1,1,0,0};
    public int countBattleships(char[][] board) {
        if(board == null || board.length == 0) return 0;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(board[i][j] == 'X') {
                    result++;
                    board[i][j] ='.';
                    int nextX = i;
                    int nextY = j;
                    for(int k=0; k<4; k++) {
                        if(nextX + dx[k] >= 0 && nextX + dx[k] < board.length && nextY+dy[k] >=0
                                && nextY + dy[k] < board[0].length && board[nextX+dx[k]][nextY+dy[k]] == 'X' ) {
                            board[nextX+dx[k]][nextY+dy[k]]='.';
                            nextX+=dx[k];
                            nextY+=dy[k];
                        }
                    }
                }
            }
        }
        return result;
    }
}
