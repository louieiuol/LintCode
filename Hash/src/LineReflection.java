//908. Line Reflection
//中文English
//Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.
//
//Example
//Example1
//
//Input: [[1,1],[-1,1]]
//Output: true
//Example2
//
//Input: [[1,1],[-1,-1]]
//Output: false
//Challenge
//Could you do better than O(n2)?
//		
		
import java.util.*;
public class LineReflection {
	public boolean isReflected(int[][] points) {
		if(points == null || points.length == 0 || points[0].length == 0) return true;
		HashMap<Integer, HashSet<Integer>> map=new HashMap<>();
		//建一个Map key装 x值 value装 一系列对应的y值 这样做能使时间复杂度降到O(n)
		int max=Integer.MIN_VALUE;
		int min=Integer.MAX_VALUE;
		//因为如果存在以y为对称的轴 必然最大值和最小值也对称 所以我们通过记录最大最小值来找到中轴
		for(int i=0; i<points.length; i++) {
			max=Math.max(max, points[i][0]);
			min=Math.min(min, points[i][0]);
			//记录min max的最值来确定中轴
			map.putIfAbsent(points[i][0], new HashSet<Integer>()); 
			//用HashSet代替ArrayList来减小时间复杂度
			map.get(points[i][0]).add(points[i][1]);
		}
		//以y点为分类 把每个相同x坐标的y都放入同一个Set
		double line=(double) (max+min)/2;
		//计算中轴是取最大最小值的平均值
		for(int i=0; i<points.length; i++) {
				int value= (int) (2*line-points[i][0]);
				//中轴公式 num大-中轴=中轴-num小 变形则为num1=2*中轴-num2
				if(!map.containsKey(value) || !map.get(value).contains(points[i][1])) {
					//map中需要有与其对应的值 而且那个与其对应的值必须在同一条y轴
					return false;
				}
		}
		return true;
	}
}
