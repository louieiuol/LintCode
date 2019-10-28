package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import basics.Order;
import basics.Score;
import basics.Time;

public class RandomAlgorithm {
		private final Time startDay;
		private final Time endDay;
		private ArrayList<Order> datalist;
		private ArrayList<Order> originList;
		private ArrayList<Order> optimal;
		private ArrayList<Order> reslist;
		private HashMap<Order, Score> scoreMap;
		private ArrayList<String> printlist;
		private long lastTime;
		private int NPS;
		private int GEN;

		public RandomAlgorithm(ArrayList<Order> datalist, Time startDay, Time endDay) {
			// TODO Auto-generated constructor stub
			this.startDay = startDay;
			this.endDay = endDay;
			this.datalist = datalist;
			optimal=new ArrayList<Order>();
			originList=new ArrayList<Order>();
			scoreMap = new HashMap<Order, Score>();
			printlist=new ArrayList<String>();
			reslist=new ArrayList<Order>();
			lastTime = 0;
		}

		public void init() {
			if (datalist.isEmpty()) {
				return;
			}
			
			// order data filter
			PriorityAlgorithm pa=new PriorityAlgorithm(datalist,startDay, endDay);
			pa.init();
			pa.start();
			originList.addAll(pa.getResult());
			optimal=originList;
			System.out.print("Original List is");
			NPS=pa.getTotalNPS();
			GEN=(int) (originList.size() * originList.size() );
		}
		
		public void start() {
			if(originList.size() == 0) return;
			int max=NPS;
			int k=0;
			ArrayList<Order> experiment=originList;
 			while(k<GEN) {
 				k++;
 				int index1 = (int) (Math.random()*experiment.size()-0);
 				int index2 = (int) (Math.random()*experiment.size()-0);
				ArrayList<Order> swapList=swap(experiment, index1, index2);
				System.out.print(k +"th, swap list is: \n");
				printList(swapList);
				int curr=(int) getSwitch(swapList);
				System.out.println("Curr NPS is :"+curr);
				max=NPS;
				if(curr>NPS) {
					optimal=swapList;
					max=curr;
					if((Math.random()*(100))>2) {
						experiment=swapList;
					}
				}else if(curr < NPS) {
					if((Math.random()*(100))<2) {
						experiment=swapList;
					}
				}else {
					optimal=originList;
					continue;
				}
			}
 			
			if(max>NPS) {
				System.out.print("Better found");
			}else {
				System.out.print("Not found");
			}
		}
		
		private ArrayList<Order> swap(ArrayList<Order> origin, int i , int j) {
			ArrayList<Order> swaplist=new ArrayList<>();
			swaplist.addAll(origin);
			Collections.swap(swaplist, i, j);
			return swaplist;
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

		
		private double getSwitch(ArrayList<Order> lst) {
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
			return Score.getNPS(datalist,temp);	
		}
		
		public void execute() {
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
		
		public ArrayList<Order> getResult(){
			return reslist;
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


		public int getTotalNPS() {
			// TODO Auto-generated method stub
			return this.NPS;
		}
	}

