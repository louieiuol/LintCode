import java.util.Stack;

//363. Trapping Rain Water
//Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
//
//Trapping Rain Water
//
//Example
//Example 1:
//
//Input: [0,1,0]
//Output: 0
//Example 2:
//
//Input: [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6
//Challenge
//O(n) time and O(1) memory


public class TrappingRainWater {
	 public int trapRainWater(int[] heights) {
		 if(heights== null || heights.length == 0) return 0;
		 int left=0, right=heights.length-1, res=0;
		 int leftmax=heights[left], rightmax=heights[right];
		 //逆向双针分别记录左右两边端点
		 //两个值分别记录左右两边最大值
		 while(left<right) {
			 leftmax=Math.max(leftmax, heights[left]);
			 rightmax=Math.max(rightmax, heights[right]);
			 //当前左右两边最大值是当前值和之前的值的最大值
			 if(leftmax<rightmax) {
				 //找到小一点的那边开始记录储水(木桶原理：装水量由小的那边决定）
				 res+=leftmax-heights[left];
				 left++;
				 //记录落差，右移指针
			 }else {
				 res+=rightmax-heights[right];
				 right++;
				 //记录落差，左移指针
			 }
		 }
		 return res;
	 }
//	 单调栈解法, 
//	 算法的核心在于找到左边和右边第一个比某个位置高的那个部分, 那个部分就可以存水. 作为算法的思路如下:
//
//	 把高度依次放入栈中
//	 1.1 如果当前高度小于或者等于前面的柱子, 那么就记录这个柱子高度, 然后继续
//	 1.2 如果当前高度(curH)大于前面的柱子, 那么我们就知道前面的柱子可能能盛水:
//	 1.1.1 从栈中取得前面的柱子的高度, 设为prevH
//	 1.1.2 从栈中取得前面的柱子的前面的高度, 设为prevprevH
//	 1.1.3 整个区间的高度就是当前的柱子以及它前面的柱子的前面的高度最小值减去前面的住子的高度: min(curH, prevprevH) - prevH
//	 1.1.4 整个区间的长度就是当前的柱子减去前面的柱子的前面的index, 然后再减1: index(curH) - index(prevprevH) - 1
//	 1.1.5 最后加入结果
//	 时间复杂度是O(n), 空间复杂度也是O(n)


	    public int trapRainWater2(int[] heights) {
	        // write your code here
	        if(heights == null || heights.length <= 2) {
	          return 0;
	        }
	        
	        int res = 0;
	        Stack<Integer> stack = new Stack<>();
	        
	        for(int i = 0; i < heights.length; i++) {
	          int curh = heights[i];
	          while(!stack.isEmpty() && curh > heights[stack.peek()]) {
	            int prevh = stack.pop();
	            if(!stack.isEmpty()) {
	              int h = Math.max(Math.min(curh, heights[stack.peek()]) - heights[prevh], 0);
	              int w = i - stack.peek() - 1;
	              res += h * w;
	            }
	          }
	          stack.push(i);
	        }
	        
	        return res;
	    }
}
