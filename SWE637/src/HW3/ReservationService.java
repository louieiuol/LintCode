package HW3;

public class ReservationService {
	// instance variables, constructors, other methods omitted for now
	RankingService rankingService;
	int count=0;
	public void reserve (Customer customer) {
		this.rankingService = RankingServices.getRankingService();
		// more code that uses the ranking service by calling
		// public Rank getRank(Customer)
		// on the rankingService object
		this.rankingService.add(customer, new Rank(count % 3));
		count++;
	}
	
	
	public void setRankingService( RankingService rankingService ){
		this.rankingService=rankingService;
	}
}
