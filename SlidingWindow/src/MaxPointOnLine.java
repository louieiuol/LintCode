
/*186. Max Points on a Line
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example
Example 1:

Input:(1,2),(3,6),(0,0),(1,3).
Output:3
Example 2:

Input:(1,2),(2,3),(3,2).
Output:2*/

import java.util.*;
class Point {
      int x;
      int y;
      Point() { x = 0; y = 0; }
      Point(int a, int b) { x = a; y = b; }
  }

public class MaxPointOnLine {
	public int maxPoints(Point[] points) {
		if(points == null || points.length==0) return 0;
		int max=1; //如果不为空 则至少有一个点在任意一条线上
		//用来记录全局最大的个数
		HashMap<Double, Integer> map =new HashMap<>();
		//经典的用HashMap的题目 储存 key: slope, value: 个数 
		for(int i=0; i<points.length; i++) {
			//外层指针控制顶点，从该点出发构造直线，寻找最多多少个点能与其构造的直线同线
			//其实也是控制了 b 寻找不同的k
			map.clear();
			//每次改变顶点需要清空 map 我们是根据不同的顶点来构造map 的
			map.put((double) Integer.MIN_VALUE, 1);
			//放入初始的该点（包含1个该点）
			//假设所有点都和该点重合 用Integer.MIN_VALUE来表示k
			int dup=0;
			//储存重复点
			for(int j= i+1; j<points.length; j++) {
				//内层指针控制端点，构造相同或不同斜率的直线 
				if(points[i].x == points[j].x && points[i].y == points[j].y) {
					dup++;
					continue;
					//如果和顶点相同 增加重复值 进入下一个
				}
				double k= points[i].x- points[j].x == 0 ?
					 (double) Integer.MAX_VALUE: 
					 0.0+(double) (points[i].y- points[j].y)/ (double) (points[i].x-points[j].x);
			    //计算斜率 k= y1-y2/x1-x2 根据 y=kx+b 推导
			    //特殊情况 和 y轴平行的线 k=+infinity (x1-x2=0 分母为0) 我们用Integer MAX 来记为 k
				//另外一种和 x轴平行的线 k=0 表示 （y1-y2=0 分子为0）
				//0.0+是为了同化 +0.0 和 -0.0 在double里面表示不同 但其实都是表示 k=0
				if(map.containsKey(k)) {
					map.put(k, map.get(k)+1);
				}else {
					map.put(k, 2);
				}
				//如果没有 则顶点和端点 一起放入
				//如果有了 则只加入新点
			}
			for(int num: map.values()) {
				if(max < num+dup) {
					max=num+dup;
					//遍历每个k值 加入重复点和全局max比较 
				}
			}
		}
		return  max;
	}
}
