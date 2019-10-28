//875. Longest Line of Consecutive One in Matrix
//中文English
//Given a 01 matrix, find the longest line of consecutive 1 in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
//
//Example
//Example 1:
//
//Input: 
//  [[0,1,1,0],
//   [0,1,1,0],
//   [0,0,0,1]]
//Output: 3
//Explanation: (0,1) (1,2) (2,3)
//Example 2:
//
//Input: [[0,0],[1,1]]
//Output: 2
//Notice
//The number of elements in the given matrix will not exceed 10,000.


public class LongestLineofConsecutiveOneinMatrix {
	public int longestLine(int[][] M) {
		int len=0;
		if(M == null || M.length  == 0 || M[0].length ==0) return len;
		//横竖求每条线上最长的值
		for(int i=0; i<M.length; i++) {
			int counter=0;
			//定义一个local counter
			for(int j=0; j<M[0].length; j++) {
				counter= M[i][j] == 1 ? counter+1 : 0;
				//如果接下来的是1 那么增加 否则变为0
				len=Math.max(len, counter);
				//每次内循环后都跟最大值比较 如果外循环比较 因为有时候会被制空 会找不到最值了
			}
		}
		
		//求横竖的时候就是把 i j换过来 要一直记住i对应 M.length 也是对应M 第一个位置 j是对应M[0].length 也是对应第二个位置 
		//换variable的位置方便记忆
		for(int j=0; j<M[0].length; j++) {
			int counter=0;
			for(int i=0; i<M.length; i++) {
				counter=M[i][j] == 1 ? counter+1 : 0;
				len=Math.max(len, counter);
			}
		}
		
		//对斜着 diagonal 的进行求最值 对i的范围内 对i j进行两种变化 
		//斜向上走是 x++ y++ 向下走是 x-- y++ (因为x对应i不为0是有范围变化 y对应0无法减少了 只能增加)
		for(int i=0; i<M.length; i++) {
			int counter=0; 
			//x y都增长情况 
			//因为x 对应i 所以x初始为i （i在变化）y 初始为0 要增长的话的范围是 小于M.length (因为i对应的最长值）y要增长的话是小于 M[0].length （对应j的最长值）
			for(int x=i, y=0; x<M.length && y<M[0].length; x++, y++) {
				counter= M[x][y] == 1 ? counter+1 : 0;
				len=Math.max(len, counter);
			}
			//重新设为0 分两边计算 
			//x减少 y增加情况
			counter=0;
			//x一开始不为0是 减少的最值是0 所以是>=0 y的最值还是j的最长值
			for(int x=i, y=0; x>=0 && y<M[0].length; x--, y++) {
				counter=M[x][y] == 1 ? counter+1 : 0;
				len=Math.max(len, counter);
			}
		}
		
		//对反斜线 anti-diagonal 进行求最值 对j的范围内 对i j进行变化
		for(int j=0; j<M[0].length; j++) {
			int counter=0;
			//x y都增长情况 
			//因为x 对应i 所以x初始为0 y对应j （j在变化） x 要增长的话的范围是 小于M.length (因为i对应的最长值）y要增长的话是小于 M[0].length （对应j的最长值）
			for(int x=0, y=j; x<M.length && y<M[0].length; x++, y++) {
				counter=M[x][y] == 1 ? counter+1 : 0;
				len=Math.max(len, counter);
			}
			//y一开始不为0 减少到的最值是0 所以是>=0 x的最值还是i的最长值
			counter=0;
			for(int x=0, y=j; x<M.length && y>=0; x++, y--) {
				len=Math.max(len, counter);
			}
		}
		return len;
	}
}
