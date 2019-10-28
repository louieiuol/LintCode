//156. Merge Intervals
//中文English
//Given a collection of intervals, merge all overlapping intervals.
//
//Example
//Example 1:
//
//Input: [(1,3)]
//Output: [(1,3)]
//Example 2:
//
//Input:  [(1,3),(2,6),(8,10),(15,18)]
//Output: [(1,6),(8,10),(15,18)]
//Challenge
//O(n log n) time and O(1) extra space.

//区间问题要画图 需要对开始点进行排序

import java.util.*;
public class MergeInterval {
	public class Interval {
		int start, end;
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> res=new ArrayList<>();
		if(intervals == null || intervals.size() == 0) return res;
		Collections.sort(intervals, (Interval v1, Interval v2) -> {
			return v1.start-v2.start;
		});
		//需要对之前的区间按照起始值的大小进行由小到大排序
		Interval last=null;
		//设上一个区间为空
		for(Interval interval: intervals) {
			//如果是初始区间 （刚开始没有分配） 或者 上个指针区间的结束小于当前区间的开头 （无法merge）
			if(last == null || last.end<interval.start) {
				res.add(interval);
				//结果表中加入当前新区间
				last=interval;
				//上个区间则为当前区间
			}else {
				last.end=Math.max(last.end, interval.end);
				//因为开头已经排序好 开始时间已经取到最小值 我们比较谁结束比较晚 变成当前区间的结束值
			}
		}
		return res;
	}
}
