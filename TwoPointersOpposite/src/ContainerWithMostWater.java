//
//383. Container With Most Water
//中文English
//Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
//
//Example
//Example 1:
//
//Input: [1, 3, 2]
//Output: 2
//Explanation:
//Selecting a1, a2, capacity is 1 * 1 = 1
//Selecting a1, a3, capacity is 1 * 2 = 2
//Selecting a2, a3, capacity is 2 * 1 = 2
//Example 2:
//
//Input: [1, 3, 2, 2]
//Output: 4
//Explanation:
//Selecting a1, a2, capacity is 1 * 1 = 1
//Selecting a1, a3, capacity is 1 * 2 = 2
//Selecting a1, a4, capacity is 1 * 3 = 3
//Selecting a2, a3, capacity is 2 * 1 = 2
//Selecting a2, a4, capacity is 2 * 2 = 4
//Selecting a3, a4, capacity is 2 * 1 = 2
//Notice
//You may not slant the container.

public class ContainerWithMostWater {
    public int maxArea(int[] heights) {
        // write your code here
        if(heights == null || heights.length == 0) return 0;
        int left=0, right=heights.length-1, ans=0;
        //木桶问题：逆向双针解决最简单
        while(left<right){
            ans=Math.max(ans, calculate(left, right, heights));
            //计算当前木桶储水量 并储存最大值
            //因为最大储水量在两个左右最大的木板之间，所以我们移动矮板寻找高板
            if(heights[left]<heights[right]){
                left++;
                //如果左边矮 移动左指针 
            }else{
                right--;
                //如果右边矮，移动右指针
            }
        }
        return ans;
    }
    
    private int calculate(int left, int right, int[] heights){
        return (right-left)*Math.min(heights[left],heights[right]);
        //储水量计算是 长度乘以高度， 高度由两边短的决定
    }
}
