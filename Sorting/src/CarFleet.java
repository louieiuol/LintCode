/*1477. Car Fleet
中文English
N cars are going to the same destination along a one lane road. The destination is target miles away.

Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.

A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.

The distance between these two cars is ignored - they are assumed to have the same position.

A car fleet is some non-empty set of cars driving at the same position and same speed. Note that a single car is also a car fleet.

If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.

How many car fleets will arrive at the destination?

Example
Example 1:

Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
Output: 3
Explanation:
The cars starting at 10 and 8 become a fleet, meeting each other at 12.
The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
The cars starting at 5 and 3 become a fleet, meeting each other at 6.
Note that no other cars meet these fleets before the destination, so the answer is 3.
Example 2:

Input: target = 20, position = [6,2,17], speed = [3,9,2]
Output: 2
Explanation:
The cars starting at 6 and 2 become a fleet, meeting each other at 18.
The other cars from the 17th car can't catch up with it, so it's a team.
Note that no other cars meet these fleets before the destination, so the answer is 2.
Notice
1.0 <= N <= 10 ^ 4
2.0 < target <= 10 ^ 6
3.0 < speed[i] <= 10 ^ 6
4.0 <= position[i] < target
5.All initial positions are different.
*/

import java.util.*;
public class CarFleet {
	//按位置离终点近的排序 算每辆车到达终点的时间
	//时间越长车越慢 
	//记录当前最长时间max 
	//如果当前车到达终点时间 <= max, 能追上 汇入 fleet 
	//如果当前车到达终点时间 >max, 成为下一个fleet开头
    public int carFleet(int target, int[] position, int[] speed) {
        // Write your code here
        if(position == null || position.length == 0 || speed == null || speed.length == 0){
            return 0;
        }
        int len=position.length;
        int[][] cars=new int[len][2];
        for(int i=0; i<position.length; i++){
            cars[i][0]=position[i];
            cars[i][1]=speed[i];
        }
        //合并位置和速度 
        Arrays.sort(cars, (a,b) -> (b[0] - a[0]));
        //按照距离进行排序 最近的距离在前面
        double maxTime=0;
        int counter=0;
        for(int i=0; i<len; i++){
            double time=(double) (target-cars[i][0]) / cars[i][1];
            if( time > maxTime){
                maxTime=time;
                counter++;
            }
            //比上次时间还要长 那么就无法合并 需要额外的计数
        }
        return counter;
    }
}
