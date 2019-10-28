//912. Best Meeting Point
//中文English
//A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
//
//Example
//Example 1:
//
//Input:
//[[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]
//Output:
//6
//
//Explanation:
//The point `(0,2)` is an ideal meeting point, as the total travel distance of `2 + 2 + 2 = 6` is minimal. So return `6`.
//Example 2:
//
//Input:
//[[1,1,0,0,1],[1,0,1,0,0],[0,0,1,0,1]]
//Output:
//14

import java.util.*;

//一条线上取中心是这条直线上其余点到其的最短距离
//平面上取二维的中心是平面上其余点到其的最短距离

public class BestMeetingPoint {
        public int minTotalDistance(int[][] grid) {
            // Write your code here
            List<Integer> dx=new ArrayList<>(); //利用两个arraylist 储存坐标
            List<Integer> dy=new ArrayList<>();
            
            for(int i=0; i<grid.length; i++){
                for(int j=0; j<grid[0].length; j++){
                    if(grid[i][j] == 1){
                    	//找到对应为1的点 两个list分别记录坐标
                        dx.add(i);
                        dy.add(j);
                    }
                }
            }
            //分别计算距离
            return calculateDis(dx) + calculateDis(dy);
        }
        
        private int calculateDis(List<Integer> lst){
        	//先sort每个list 
            Collections.sort(lst);
            int len=lst.size();
            //中间点即是中点 
            int mid= (lst.get((len-1)/2) + lst.get(len/2)) / 2;
            //一种包含奇数偶数的写法
            int result=0;
            for(int num: lst){
                result+=Math.abs(num-mid);
            }
            //分别计算到中点的距离
            return result;
        }
}
