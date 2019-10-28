package basics;

import interfaces.OrderInter;
/**
 * 
 * @author Guanhua Liu
 * This class is used for implementing Order interface
 * 
 */
public class Order implements OrderInter{
	private String id;
	private int parsedId;
	private Position pos;
	private Time time;
	private long maxDis;
	private final Time dailyMaxTime;
	private static int K;
	
	//An order should contains id, position, time 3 fields
	
	public Order(String id, String dir, String time) {
		this.id=id;
		this.parsedId=Integer.parseInt(id.substring(2));
			this.pos=new Position(dir);
			this.time=new Time(time);
		this.dailyMaxTime=new Time(22,0,0); // Initialize max daily time is 22:00:00
		this.maxDis= (dailyMaxTime.getAbsoluteTime()-this.time.getAbsoluteTime())/2;
		K=1;
		//calculate maximum distance the order can be delivered
	}
	
	public Position getPos() {
		return pos;
	}
	
	public Time getTime() {
		return time;
	}
	
	public String getId() {
		return id;
	}
	
	public int getParsedId() {
		return parsedId;
	}
	@Override
	public String toString() {
		String str = "id: " + this.id + ", time: " + this.time.getAbsoluteTime() + ", distance: " + this.pos.getPos();
		return str;
	}

	//check 2 orders are in pre-defined short time
	public static boolean withinShortTime(Order o1, Order o2) {
		Time interval=new Time(0,K,0);
		long diff=Math.abs(o1.getTime().getAbsoluteTime() - o2.getTime().getAbsoluteTime());
	    if(diff<=interval.getAbsoluteTime()) {
			return true;
		}else {
			return false;
		}
	}

	//compare 2 order time 
	public int compare(Order o) {
		// TODO Auto-generated method stub
		long diff=this.getTime().getAbsoluteTime() - o.getTime().getAbsoluteTime();
	    if(diff > 0) {
			return 1;
		}else if(diff < 0){
			return -1;
		}else {
			return 0;
		}
	}

	//check order is deliverable according to order generate time
	public boolean isDeliverable() {
			if((pos.getOrderDistanceInSecond())< maxDis) {
				return true;
			}
		return false;
	}

	//check order is deliverable according to current time
	public boolean isDeliverable(long start) {
		if(this.time.getAbsoluteTime() > start) {
			long max=(dailyMaxTime.getAbsoluteTime()-this.time.getAbsoluteTime());
			//System.out.println("after sec remains"+max);
			if((pos.getOrderDistanceInSecond()*2) < max) {
				return true;
			}
			return false;
		}else {
			long max=(dailyMaxTime.getAbsoluteTime()-start);
			//System.out.println("before sec remains"+max);
			if((pos.getOrderDistanceInSecond()*2) < max) {
				return true;
			}
			return false;
		}
	}
	
}
