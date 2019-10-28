//919. Meeting Rooms II
//中文English
//Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
//
//Example
//Example1
//
//Input: intervals = [(0,30),(5,10),(15,20)]
//Output: 2
//Explanation:
//We need two meeting rooms
//room1: (0,30)
//room2: (5,10),(15,20)
//Example2
//
//Input: intervals = [(2,7)]
//Output: 1
//Explanation: 
//Only need one meeting room

import java.util.*;
public class MeetingRooms {
	
	
	  public class Interval {
		      int start, end;
		      Interval(int start, int end) {
		          this.start = start;
		          this.end = end;
		      }
		 }
	// Write your code here
	public int minMeetingRooms(List<Interval> intervals) {
		if(intervals == null || intervals.size() == 0) return 0;
		Collections.sort(intervals, (Interval i1, Interval i2) ->{
			return i1.start-i2.start;
		});
		//先对起始时间进行排序 优先放入开始时间早的
		PriorityQueue<Interval> pq=new PriorityQueue<>((Interval i1, Interval i2) ->{
			return i1.end-i2.end;
		});
		//pq对结束时间进行排序 在放入很多个区间的情况下 有先对结束时间早的安排看看能不能放下下一个区间
		int res=1;
		//至少需要一个房间
		pq.offer(intervals.get(0));
		//初始化pq
		for(int i=1; i<intervals.size(); i++){
			//从下一个开始进行考虑 
			Interval curr=pq.poll();
			//pq中拿出时间最早结束的
			if(curr.end>intervals.get(i).start){
				//如果之前最早的结束时间比当前的开始还晚 那么必然不能在同一个房间
				res++;
				//结果++ 然后把新的区间加入pq
				pq.offer(intervals.get(i));
			}else{
				//如果之前最早的结束时间比当前开始的早那么两个都可以在一个房间内 取两个的最晚结束的作为结束 
				curr.end=Math.max(curr.end, intervals.get(i).end);
			}
			//还是要把刚刚拿出来的放回去 一起排序 因为可能又成为了比较短的 （刚刚加入的curr 可能会被延长结束时间）
			pq.offer(curr);
		}
		return res;
	}
}
