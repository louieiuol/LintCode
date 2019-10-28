/*
1562. Number of restaurants
Give a List of data representing the coordinates[x,y] of each restaurant and the customer is at the origin[0,0]. Find n closest restaurants to the customer, where m is the furthest distance from n restaurants to the customer. If there are more than n restaurants in the list and the distance from the customer is not greater than m, then the first n restaurants will be returned in the order of the elements in the list.

Example
Given : n = 2 , List = [[0,0],[1,1],[2,2]]
Return : [[0,0],[1,1]]
Given : n = 3,List = [[0,1],[1,2],[2,1],[1,0]]
Return :[[0,1],[1,2],[2,1]]
Notice
1.Coordinates in range [-1000,1000]
2.n>0
3.No same coordinates*/
import java.util.*;
public class nearestResturant {
	public List<List<Integer>> nearestRestaurant(List<List<Integer>> restaurant, int n) {
		// Write your code here
		List<List<Integer>> res=new ArrayList<>();
		if(restaurant!=null && n>0){
			if(restaurant.size()>0 && n<=restaurant.size()){
				HashMap<Double, List<Integer>> map=new HashMap<>();
				for(List<Integer> ele: restaurant){
					double dist=calculateDist(ele.get(0),ele.get(1));
					System.out.println(dist);
					map.put(dist,ele);
				}
				Object[] lst=map.keySet().toArray();
				Arrays.sort(lst);
				List<List<Integer>> another=new ArrayList<>();
				for(int i=0; i<n;i++){
					if(map.size()>i){
						ArrayList<Integer> temp=new ArrayList<Integer>();
						temp.addAll(map.get(lst[i]));
						another.add(temp);
					}
				}
				for(List<Integer> ele:restaurant){
					if(another.contains(ele)){
						res.add(ele);
					}
				}

			}
		}
		return res;
	}
	private double calculateDist(int x, int y){
		return Math.sqrt(x*x+y*y);
	}
	class Point {
		int x, y;
		int distance;
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
			this.distance = x * x + y * y;
		}

	} 
	public List<List<Integer>> nearestRestaurant2(List<List<Integer>> restaurant, int n) {
		// Write your code here
		List<List<Integer>> rst = new ArrayList<>();
		if (restaurant == null || restaurant.size() == 0) {
			return rst;
		}
		if (n > restaurant.size()) {
			return rst;
		}
		PriorityQueue<Point> pq = new PriorityQueue<>();
		for (List<Integer> list : restaurant) {
			int x = list.get(0);
			int y = list.get(1);

			pq.offer(new Point(x, y));
			if (pq.size() > n) {
				pq.poll();
			}
		}
		int m = pq.peek().distance;
		for (List<Integer> list : restaurant) {
			int x = list.get(0);
			int y = list.get(1);
			int dis = x * x + y * y;
			if (dis <= m) {
				rst.add(list);
			}
		}
		return rst;
	}
}
