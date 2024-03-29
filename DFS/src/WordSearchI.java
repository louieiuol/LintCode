/*123. Word Search
中文English
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example
Example 1:

Input：["ABCE","SFCS","ADEE"]，"ABCCED"
Output：true
Explanation：
[    
     A B C E
     S F C S 
     A D E E
]
(0,0)A->(0,1)B->(0,2)C->(1,2)C->(2,2)E->(2,1)D
Example 2:

Input：["z"]，"z"
Output：true
Explanation：
[ z ]
(0,0)z

*/

//深度优先搜索 跟那种普通的2维4方向找不一样 需要一个boolean同样大小的matrix 记录是否被访问过 
//不能用Hashset记录Point的方式 因为那样无法回溯 
//不能用BFS
//找到一个即可返回 减少时间复杂度 
//!!! 外部dfs也要回溯 

public class WordSearchI {
    int[] dx={-1,0,0,1};
    int[] dy={0,-1,1,0};
    class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    public boolean exist(char[][] board, String word) {
        // write your code here
        if(board == null || board.length == 0 || board[0].length == 0) return false;
        boolean[][] visited=new boolean[board.length][board[0].length];
        //
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                Point p=new Point(i,j);
                //让 dfs 内部进行回溯 
                if(dfs(board, word, p, 0, visited)){
                    return true;  
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, String word, Point curr, int index, boolean[][] visited){
        if(board[curr.x][curr.y] != word.charAt(index)){
            return false;
        }
        if(index == word.length()-1 && board[curr.x][curr.y] == word.charAt(index)){
            return true;
        }
        visited[curr.x][curr.y]=true;
        //对当前格子进行回溯 
        for(int i=0; i<4; i++){
            Point next=new Point(curr.x+dx[i], curr.y+dy[i]);
            if(next.x >= 0 && next.x < board.length && next.y >= 0 && next.y < board[0].length && !visited[next.x][next.y]){
            	//处理下一个时候不要回溯了
                if(dfs(board, word, next, index+1, visited)){
                    return true;
                }
            }
        }
        visited[curr.x][curr.y]=true;        
        return false;
    }
}
