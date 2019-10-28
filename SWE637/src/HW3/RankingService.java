package HW3;
import java.util.*;
public class RankingService { 
	HashMap<Rank, List<Customer>> order;
	
	public RankingService() {
		this.order=new HashMap<>();
	}
	
	public boolean add(Customer customer, Rank rank) {
		order.putIfAbsent(rank, new ArrayList<Customer>());
		order.get(rank).add(customer);
		return true;
	}
	
	public Rank getRank (Customer customer) {
		for(Rank rank: order.keySet()) {
			for(Customer c: order.get(rank)) {
				if(c.name.equals(customer.name)) {
					return rank;
				}
			}
		}
		return new Rank(-1);
	}

}
