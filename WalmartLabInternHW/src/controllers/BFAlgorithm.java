package controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import basics.Order;
import basics.Score;
import basics.Time;

public class BFAlgorithm {
	private Time startDay;
	private Time endDay;
	private ArrayList<Order> datalist;
	private ArrayList<Order> sortedList;
	private ArrayList<Order> optimal;
	private ArrayList<Order> reslist;
	private HashMap<Order, Score> scoreMap;
	private ArrayList<String> printlist;
	private long lastTime;
	private ArrayList<ArrayList<Order>> res = new ArrayList<ArrayList<Order>>();
	private int maxScore;
	private int NPS;

	public BFAlgorithm(ArrayList<Order> datalist, Time startDay, Time endDay) {
		// TODO Auto-generated constructor stub
		this.startDay=startDay;
		this.endDay=endDay;
		this.reslist=new ArrayList<Order>();
		this.datalist=datalist;
		sortedList=new ArrayList<Order>();
		optimal=new ArrayList<Order>();
		maxScore= 0;
	}

	public void init() {
		if (datalist.isEmpty()) {
			return;
		}
		
		// order data filter
		for (Order o : datalist) {
			if (o.isDeliverable()) {
				sortedList.add(o);
			}
		}
		if(sortedList.isEmpty()) {
			printlist.add("NPS: "+Score.getNPS(datalist,scoreMap));
			return;
		}
		ArrayList<ArrayList<Order>> permute = allPermute(sortedList);
		if (permute.size() == 0)
			return;
		int index = 0;
		for (int i = 0; i < permute.size(); i++) {
			int currScore=getScoreSum(permute.get(i));
			if(maxScore < currScore){
				maxScore=currScore;
				index=i;
			}
		}
		System.out.print("max Score is"+maxScore);
		
		optimal = permute.get(index);
		System.out.print("Optimal list is: ");
		printList(optimal);
		lastTime = optimal.get(0).getTime().getAbsoluteTime();
		lastTime = Math.max(lastTime, startDay.getAbsoluteTime());
	}
	
	public HashMap<Order, Score> getMap(){
		return scoreMap;
	}
			
	private void printList(ArrayList<Order> lst) {
		// TODO Auto-generated method stub
		for(Order o: lst) {
			System.out.println(o.toString());
		}
	}

	public ArrayList<Order> getOptimal() {
		return this.optimal;
	}

	private int getScoreSum(ArrayList<Order> lst) {
		// TODO Auto-generated method stub
		if (lst.size() == 0) {
			return 0;
		}
		long begin = lst.get(0).getTime().getAbsoluteTime();
		begin = Math.max(begin, startDay.getAbsoluteTime());
		HashMap<Order, Score> temp=new HashMap<Order, Score>();
		for (int i = 0; i < lst.size(); i++) {
			Order curr = lst.get(i);
			long prev=begin;
			long start= Math.max(begin, curr.getTime().getAbsoluteTime());
			begin=start;
			if (!curr.isDeliverable(start)) {
				//begin=start;
				begin=prev;
				continue;
			}
			long cost = curr.getPos().getOrderDistanceInSecond();
			long receive=start+cost;
			long end = start + cost*2;
			if (end > endDay.getAbsoluteTime()) {
				break;
			}
			begin=end;
			Score score = new Score(curr.getTime(), new Time(receive));
			temp.put(curr, score);
		}
		return getScore(temp);	
	}

	private ArrayList<ArrayList<Order>> allPermute(ArrayList<Order> lst) {
		if (lst == null || lst.size() == 0) {
			res.add(new ArrayList<Order>());
			return res;
		}
		boolean[] visited = new boolean[lst.size()];
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		dfs(lst, new ArrayList<Order>(), visited);
		return res;
	}

	private void dfs(ArrayList<Order> origin, ArrayList<Order> lst, boolean[] visited) {
		// TODO Auto-generated method stub
		if (lst.size() == origin.size()) {
			res.add(new ArrayList<Order>(lst));
			return;
		}
		for (int i = 0; i < origin.size(); i++) {
			if (visited[i]) {
				continue;
			}
			lst.add(origin.get(i));
			visited[i] = true;
			dfs(origin, lst, visited);
			visited[i] = false;
			lst.remove(lst.size() - 1);
		}
	}

	public void start() {
		if (optimal.size() == 0)
			return;
		for (int i = 0; i < optimal.size(); i++) {
			Order curr = optimal.get(i);
			long prev=lastTime;
			long start = Math.max(lastTime, curr.getTime().getAbsoluteTime());
			lastTime=start;
			if (!curr.isDeliverable(start)) {
				lastTime=prev;
				continue;
			}
			long cost = curr.getPos().getOrderDistanceInSecond();
			long receive= start+cost;
			long end = start + cost*2;
			if (end > endDay.getAbsoluteTime()) {
				break;
			}
			lastTime=end;
			Score score = new Score(curr.getTime(), new Time(receive));
			scoreMap.put(curr, score);
			reslist.add(curr);
			Time prevTime = new Time(lastTime);
			Time receiveTime = new Time(receive);
			Time endTime = new Time(end);
			String str=curr.getId()+" "+prevTime.toHour()+ ":" +prevTime.toMinute() + ":"+ prevTime.toSecond()+"\n";
			printlist.add(str);
		}
		this.NPS=(int) Score.getNPS(datalist,scoreMap);
		printlist.add("NPS : "+this.NPS);
	}
	
	
	public int getScore(HashMap<Order, Score> map) {
		if(map.size()== 0) return 0;
		else {
			int score=0;
			for(Order o: map.keySet()) {
				score += map.get(o).getRank();
			}
			return score;
		}
	}

	
	public ArrayList<Order> getResult(){
		return this.reslist;
	}
	public ArrayList<String> getPrint() {
		return printlist;
	}
	
	public ArrayList<String> getUnDeliveryOrder(){
		ArrayList<Order> udlist=new ArrayList<Order>();
		ArrayList<String> udlistPrint=new ArrayList<String>();
		for(Order od: datalist) {
			if(!reslist.contains(od)) {
				udlist.add(od);
			}
		}
		for(Order ud: udlist) {
			udlistPrint.add(ud.toString());
		}
		return udlistPrint;
	}

	class OrderComparator implements Comparator<Order> {
		@Override
		public int compare(Order o1, Order o2) {
			if (o1.getTime().getAbsoluteTime() == o2.getTime().getAbsoluteTime() || Order.withinShortTime(o1, o2)) {
				if (o1.getPos().getOrderDistanceInSecond() < o2.getPos().getOrderDistanceInSecond())
					return -1;
				else if (o1.getPos().getOrderDistanceInSecond() > o2.getPos().getOrderDistanceInSecond())
					return 1;
				return 0;
			} else {
				return o1.compare(o2);
			}
		}
	}

	public int getTotalNPS() {
		// TODO Auto-generated method stub
		return NPS;
	}

	public long getlastTime() {
		// TODO Auto-generated method stub
		return lastTime;
	}

}
