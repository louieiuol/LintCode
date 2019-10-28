package controllers;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import basics.Drone;
import basics.Order;
import basics.Score;
import basics.Time;

public class PriorityAlgorithm {
	private Time startDay;
	private Time endDay;
	private ArrayList<Order> datalist;
	private ArrayList<Order> sortedList;
	private ArrayList<Order> resList;
	private HashMap<Order, Score> scoreMap;
	private ArrayList<String> printList;
	private HashSet<Order> expiredSet;
	private long lastTime;
	private int NPS;
	
	public PriorityAlgorithm(ArrayList<Order> datalist, Time startDay, Time endDay) {
		// TODO Auto-generated constructor stub
		this.startDay=startDay;
		this.endDay=endDay;
		this.resList=new ArrayList<Order>();
		this.datalist=datalist;
		scoreMap=new HashMap<Order, Score>();
		sortedList=new ArrayList<Order>();
		printList=new ArrayList<String>();
		expiredSet=new HashSet<Order>();
		NPS=0;
	}

	public void init() {
		if(datalist.isEmpty()) {
			return;
		}
		//order data filter
		PriorityQueue<Order> pq=new PriorityQueue<Order>(new OrderComparator());
		for(Order o: datalist) {
			if(o.isDeliverable()) {
				pq.add(o);
			}
		}
		sortedList.addAll(pq);
		pq.clear();

		ArrayList<Order> beforelist=findOrderBefore(sortedList, lastTime);
		if(!beforelist.isEmpty()) {
			lastTime=sortedList.get(0).getTime().getAbsoluteTime();
			lastTime=Math.max(lastTime, startDay.getAbsoluteTime());
		}else {
			Order first=findNextOrder(sortedList,startDay.getAbsoluteTime());
			lastTime=first.getTime().getAbsoluteTime();
			lastTime=Math.max(lastTime, startDay.getAbsoluteTime());	
		}
		
		if(sortedList.size() ==0) {
			printList.add("NPS:"+ Score.getNPS(datalist,scoreMap));
			return;
		}
	}

	public void printList(ArrayList<Order> lst) {
		for(Order o:lst) {
			System.out.println(o.toString());
		}
	}

	public void start() {
		if(sortedList.size() == 0) return;
		Order curr;
		while(sortedList.size()>0) {
			//System.out.print("Sorted List is+");
			//printList(sortedList);
			ArrayList<Order> beforelist=findOrderBefore(sortedList, lastTime);
			//System.out.print("Before List:"+beforelist.size());
			if(beforelist.size()> 0 && stillContainsDeliverable(beforelist, lastTime)) {
					ArrayList<Order> scoreList=findHighestScoreGreaterThan0(beforelist, lastTime);
					if(scoreList.size()>0) {
						curr=farestTimeButNearestDistance(scoreList, lastTime);
					}else { 
						curr=findNextOrder(sortedList, lastTime);
					}
			}else {
				curr=findNextOrder(sortedList,lastTime);
			}
			if(curr==null) {
				break;
			}
			
			long prev=lastTime;
			long start=Math.max(lastTime, curr.getTime().getAbsoluteTime());
			lastTime=start;
			if(!curr.isDeliverable(start)) {
				sortedList.remove(curr);
				expiredSet.add(curr);
				lastTime=prev;
				continue;
			}
 			long cost=curr.getPos().getOrderDistanceInSecond();
			long receive=start + cost;
			long end=start+ cost*2;
			if(end > endDay.getAbsoluteTime()) {
				break;
			}
			Score score=new Score(new Time(curr.getTime().getAbsoluteTime()), new Time(receive));
			scoreMap.put(curr, score);
			resList.add(curr);
			Time prevTime=new Time(lastTime);
			Time receiveTime=new Time(receive);
			Time endTime=new Time(end);
			String str=curr.getId()+" "+prevTime.toHour()+ ":" +prevTime.toMinute() + ":"+ prevTime.toSecond()+"\n";
			lastTime=endTime.getAbsoluteTime();
			sortedList.remove(curr);
			printList.add(str);
		}
		if(!expiredSet.isEmpty()) {
			for(Order o: expiredSet) {
				if(!resList.contains(o)) {
					long prev=lastTime;
					long start=Math.max(lastTime, o.getTime().getAbsoluteTime());
					lastTime=start;
					if(!o.isDeliverable(start)) {
						sortedList.remove(o);
						lastTime=prev;
						continue;
					}
					long cost=o.getPos().getOrderDistanceInSecond();
					long receive=start + cost;
					long end=start+ cost*2;
					if(end > endDay.getAbsoluteTime()) {
						break;
					}
					Score score=new Score(new Time(o.getTime().getAbsoluteTime()), new Time(receive));
					scoreMap.put(o, score);
					resList.add(o);
					Time prevTime=new Time(lastTime);
					Time receiveTime=new Time(receive);
					Time endTime=new Time(end);
					String str=o.getId()+" "+prevTime.toHour()+ ":" +prevTime.toMinute() + ":"+ prevTime.toSecond()+"\n";
					printList.add(str);
				}
			}
		}
	printList.add("NPS:"+ Score.getNPS(datalist,scoreMap));
	this.NPS=Score.getNPS(datalist,scoreMap);
}


private Order findNextOrder(ArrayList<Order> lst, long last) {
		// TODO Auto-generated method stub
	ArrayList<Order> currlst=findHighestScoreAfterGreaterThan0(lst, last);
	if(currlst.isEmpty()) {
		return findInQueue(lst);
	}else {
		return cloestTimeButNearestDistance(currlst,lastTime);
	}
}

private Order cloestTimeButNearestDistance(ArrayList<Order> lst, long lastTime) {
	long sum=Long.MAX_VALUE;
	int index=0;
	for(int i=0; i<lst.size();i++) {
		long time=lst.get(i).getTime().getAbsoluteTime()-lastTime;
		long total=time-lst.get(i).getPos().getOrderDistanceInMinute()*60;
		if(total<sum) {
			sum=total;
			index=i;
		}
	}
	return lst.get(index);
}

private ArrayList<Order> findHighestScoreAfterGreaterThan0(ArrayList<Order> lst, long lastTime2) {
	HashMap<Integer, ArrayList<Order>> map=new HashMap<Integer, ArrayList<Order>>();
	map.put(2, new ArrayList<Order>());
	map.put(1, new ArrayList<Order>());
	map.put(0, new ArrayList<Order>());
	int maxScore=0;
	for(Order o: lst) {
		Score s=getScore(o, o.getTime().getAbsoluteTime());
		map.get(s.getRank()).add(o);
		if(maxScore < s.getRank()) {
			maxScore=s.getRank();
		}
	}
	if(maxScore>0) {
		return map.get(maxScore);
	}else {
		return new ArrayList<Order>();
	}
}

private Order farestTimeButNearestDistance(ArrayList<Order> scoreList, long lastTime) {
	long sum=Long.MIN_VALUE;
	int index=0;
	for(int i=0; i<scoreList.size();i++) {
		long time=lastTime-scoreList.get(i).getTime().getAbsoluteTime();
		long total=time-scoreList.get(i).getPos().getOrderDistanceInMinute()*60;
		if(total>sum) {
			sum=total;
			index=i;
		}
	}
	return scoreList.get(index);
}

public ArrayList<Order> getResult() {
	return resList;
}
private boolean stillContainsDeliverable(ArrayList<Order> beforelist, long last) {
	for(Order o: beforelist) {
		if(o.isDeliverable(last)) {
			return true;
		}
	}
	return false;
}

private Order findInQueue(ArrayList<Order> lst) {
	if(lst==null) return null;
	if(lst.size() == 0) return null; 
	PriorityQueue<Order> pq=new PriorityQueue<Order>(new OrderComparator());
	for(Order o: lst) {
		if(!pq.contains(o)) {
			pq.offer(o);
		}
	}
	return pq.peek();
}

private ArrayList<Order> findHighestScoreGreaterThan0(ArrayList<Order> lst, long last) {
	// TODO Auto-generated method stub
	HashMap<Integer, ArrayList<Order>> map=new HashMap<Integer, ArrayList<Order>>();
	map.put(2, new ArrayList<Order>());
	map.put(1, new ArrayList<Order>());
	map.put(0, new ArrayList<Order>());
	int maxScore=0;
	for(Order o: lst) {
		Score s=getScore(o, last);
		map.get(s.getRank()).add(o);
		if(maxScore < s.getRank()) {
			maxScore=s.getRank();
		}
	}
	if(maxScore>0) {
		return map.get(maxScore);
	}else {
		return new ArrayList<Order>();
	}
}


public int getTotalNPS() {
	return (int) this.NPS;
}

private Score getScore(Order o, long last) {
	// TODO Auto-generated method stub
	long start=last;
	if(!o.isDeliverable(start)) {
		return new Score(0, new Time(22, 0 ,1));
	}
	long cost=o.getPos().getOrderDistanceInSecond();
	long receive=start + cost;
	long end=start+ cost*2;
	if(end > endDay.getAbsoluteTime()) {
		return new Score(0, new Time(22,0,1));
	}
	Score score=new Score(new Time(o.getTime().getAbsoluteTime()), new Time(receive));
	return score;
}

private ArrayList<Order> findOrderBefore(ArrayList<Order> lst, long last) {
	ArrayList<Order> res=new ArrayList<>();
	if(lst!=null) {
		for(Order o: lst) {
			if(o.getTime().getAbsoluteTime() <= last) {
				if(getScore(o, last).getRank() == 0) {
					expiredSet.add(o);
					continue;
				}
				if(!res.contains(o)) {
					res.add(o);
				}
			}
		}
	}
	return res;
}


public ArrayList<String> getUnDeliveryOrder(){
	ArrayList<Order> udlist=new ArrayList<Order>();
	ArrayList<String> udlistPrint=new ArrayList<String>();
	for(Order od: datalist) {
		if(!resList.contains(od)){
			udlist.add(od);
		}
	}
	for(Order ud: udlist) {
		udlistPrint.add(ud.toString());
	}
	return udlistPrint;
}


public ArrayList<String> getPrint(){
	return printList;
}

class OrderComparator implements Comparator<Order> {
	@Override
	public int compare(Order o1, Order o2) {
		if(o1.getTime().getAbsoluteTime() == o2.getTime().getAbsoluteTime() || Order.withinShortTime(o1,o2)) {	
			if(o1.getPos().getOrderDistanceInSecond() < o2.getPos().getOrderDistanceInSecond()) return -1;
			else if(o1.getPos().getOrderDistanceInSecond() > o2.getPos().getOrderDistanceInSecond()) return 1;
			return 0;
		}else {
			return o1.compare(o2);
		}
	}
}
}
