/*1514. Robot Room Cleaner
中文English
Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.*/
/*
 * Example Example 1:
 * 
 * Input： room = [ [1,1,1,1,1,0,1,1], [1,1,1,1,1,0,1,1], [1,0,1,1,1,1,1,1],
 * [0,0,0,1,0,0,0,0], [1,1,1,1,1,1,1,1] ], row = 1, col = 3 Explanation： All
 * grids in the room are marked by either 0 or 1. 0 means the cell is blocked,
 * while 1 means the cell is accessible. The robot initially starts at the
 * position of row=1, col=3. From the top left corner, its position is one row
 * below and three columns right. Example 2:
 * 
 * Input： room = [ [1,1,1,1,1,0,1,1], [1,1,1,1,1,0,1,1], [1,0,1,1,1,1,1,1],
 * [1,0,0,1,0,0,0,1], [1,1,1,1,1,1,1,1] ], row = 2, col = 3 Explanation： All
 * grids in the room are marked by either 0 or 1. 0 means the cell is blocked,
 * while 1 means the cell is accessible. The robot initially starts at the
 * position of row=2, col=3. From the top left corner, its position is two rows
 * below and three columns right. Notice The input is only given to initialize
 * the room and the robot's position internally. You must solve this problem
 * "blindfolded". In other words, you must control the robot using only the
 * mentioned 4 APIs, without knowing the room layout and the initial robot's
 * position. The robot's initial position will always be in an accessible cell.
 * The initial direction of the robot will be facing up. All accessible cells
 * are connected, which means the all cells marked as 1 will be accessible by
 * the robot. Assume all four edges of the grid are all surrounded by wall.
 */

import java.util.*;
public class RobotRoomCleaner {
	interface Robot {
		  // returns true if next cell is open and robot moves into the cell.
		  // returns false if next cell is obstacle and robot stays on the current cell.
		  boolean move();

		  // Robot will stay on the same cell after calling turnLeft/turnRight.
		  // Each turn will be 90 degrees.
		  void turnLeft();
		  void turnRight();

		  // Clean the current cell.
		  void clean();
		}

	    int[] dx = {-1, 0, 1, 0};
	    int[] dy = {0, 1, 0, -1};  
	    //左下右上顺序 
	    public void cleanRoom(Robot robot) {
	        if(robot == null) return;
	        //对应 x y robot's direction visited set
	        dfs(robot, 0, 0, 0, new HashSet<String>());
	    }
	    
	    private void dfs(Robot robot, int x, int y, int dir, Set<String> visited){
	        robot.clean();
	        //清理当前格子
	        for(int i=0; i<4; i++){
	        	//4次转向 清理所有可能区间 
	            int nextX=x+dx[dir];
	            int nextY=y+dy[dir];
	            String nextPos=nextX+" "+nextY;
	            if(!visited.contains(nextPos) && robot.move()){
	            	//如果未被访问过 并且可以移动 
	    	        visited.add(nextPos);
	    	        //加入将要访问的格子 
	                dfs(robot, nextX, nextY, dir, visited);
	                //沿用同一方向 dfs下个格子
	                robot.turnRight();
	                robot.turnRight();
	                //格子清理完毕后返回当前格子 180度转向 移动回原来方向 
	            }
	            robot.turnRight();
	            //右转 到下一个方向 顺时针清理 
	            dir=(dir+1) % 4;
	            //方向+1 %4 保证范围 0-4
	        }
	        //4次方向遍历完后 退出当前格子 
	        //180度转向 移动回原来方向 
            robot.turnRight();
            robot.turnRight();	 
            //朝原方向移动
	        robot.move();
	    }
}
