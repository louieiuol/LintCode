/*1211. Zuma Game
中文English
Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.

Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.

Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.

Example
Example 1:

Input: "WRRBBW", "RB"
Output: -1
Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
Example 2:

Input: "WWRRBBWW", "WRBRW"
Output: 2
Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
Example 3:

Input:"G", "GGGGG"
Output: 2
Explanation: G -> G[G] -> GG[G] -> empty 
Example 4:

Input: "RBYYBBRRB", "YRBGB"
Output: 3
Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty 
Notice
You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.*/

//分治型dfs
public class ZumaGame {
    public int findMinStep(String board, String hand) {
    	if(board == null || board.length() == 0) return 0;
    	int[] hands=new int[26];
    	//all capitalized character so need 26 spaces 
    	for(int i=0; i<hand.length(); i++) {
    		//看做hashmap 有球的位置++
    		hands[hand.charAt(i)-'A']++;
    	}
    	return dfs(board, hands);
    }
    
    private int dfs(String board, int[] hands) {
    	if(board.length() == 0) return 0; 
    	//先考虑递归的出口 
    	int i=0; 
    	//指针循环遍历board
    	int result=Integer.MAX_VALUE;
    	//初始化返回值为最大值 
    	while(i < board.length()) {
    		int j=i;
    		//用j来记录初始值 
    		while(i<board.length() && board.charAt(j) == board.charAt(i)) {
    			//颜色相同时候我们一直找 颜色不同break循环 
    			i++;
    		}
    		int cnt=3-(i-j);
    		//至少需要3个颜色相同的球才能消掉 3-能消掉球的个数 就知道所需要颜色相同的球的个数了 
    		if(hands[board.charAt(j)] >= cnt) {
    			//如果我们口袋里有足够数量能消掉该颜色球的个数 
    			int used= cnt >= 0 ? cnt : 0;
    			//小于0 我们不需要用球 
    			hands[board.charAt(j)] -= used;
    			//假如我们用球去消掉了 当前情况 
    			int ans=dfs(board, hands);
    			//进行dfs 拿到当前解法的最小值 
    			if(ans > 0) {
    				//如果有解法 进行和当前解法的最小值比 
    				result=Math.min(ans, result);
    			}
    			hands[board.charAt(j)] += used;
    			//如果不使用这种解法 加回来 回溯 
    		}
    	}
    	//如果存在解法 返回result 不存在 返回-1
    	return result == Integer.MAX_VALUE ? -1 : result;
    }
}
