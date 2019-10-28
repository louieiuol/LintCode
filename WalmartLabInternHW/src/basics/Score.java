package basics;

import java.util.ArrayList;
import java.util.HashMap;

import interfaces.ScoreInter;

/**
 * 
 * @author Guanhua Liu
 * This class is used for implementing Score interface
 * 
 */
public class Score implements ScoreInter {
	Time time;
	int rank;
	//A score contains a rank field to represent promoters and detractors 
	//, and contains a order time field
	public Score(Time start, Time end) {
		long pass=end.getAbsoluteTime()-start.getAbsoluteTime();
		this.time=new Time(pass);
		/**
		 * 
		 * @return integer
		 * 
		 * Rank is based on waiting time.
		 * Waiting Time = Time user get the goods - Time when user make an order
		 * Waiting Time < 0 : Invalid
		 * 0 <= Waiting Time <= 1 hour 30 minutes                 : Promoter
		 * 1 hour 30 minutes < Waiting Time <= 3 hours 40 minutes : Neutral
		 * 3 hours 40 minutes < Waiting Time                      : Detractor
		 * 
		 * if it is promoter return 2
		 * if it is neutral return 1
		 * if it is detractor return 0
		 * 
		 */
		Time timeDivide1=new Time(1,30,0);
		Time timeDivide2=new Time(3,40,0);
		if(time.getAbsoluteTime()>=0 && time.getAbsoluteTime()<timeDivide1.getAbsoluteTime()) {
			rank=2;
		}else if(time.getAbsoluteTime() >= timeDivide1.getAbsoluteTime() && time.getAbsoluteTime() <= timeDivide2.getAbsoluteTime()) {
			rank=1;
		}else {
			rank=0;
		}
	}
	
	public Score(int rank, Time time) {
		this.rank=rank;
		this.time=time;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	//calculate  NPS score base on scores in HashMap
	//NPS range : -100  <=  NPS <=100
	public static int getNPS(ArrayList<Order> lst, HashMap<Order, Score> map) {
	if (!lst.isEmpty()) {
		int promoter = 0;
		int neutral = 0;
		int detractor = 0;
		for (Order o : map.keySet()) {
			if (map.get(o).getRank() == 2)
				promoter++;
			else if (map.get(o).getRank() == 1)
				neutral++;
			else
				detractor++;
		}
		detractor = lst.size()-promoter-neutral;
		return (int) ( (promoter + 0.0) * 100 / lst.size() - (0.0 + detractor) * 100 / lst.size());
	} else {
		return 0;
	}
}
    
}
