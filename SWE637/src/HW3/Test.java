package HW3;

import static org.junit.Assert.*;

public class Test {

	@org.junit.Test
	public void test() {
		ReservationService reservationService = new ReservationService();
		RankingService fakeRankingService = new FakeRankingService (); // inherits from RankingService
		reservationService.setRankingService(fakeRankingService);
		Customer c1=new Customer("John Smith");
		Customer c2=new Customer("Jane Doe");
		Customer c3=new Customer("Louie Liu");
		Customer c4=new Customer("Hao Yan");
		assertTrue(reservationService.rankingService.order.isEmpty());
		reservationService.reserve(c1);
		reservationService.reserve(c2);
		reservationService.reserve(c3);
		reservationService.reserve(c4);
		assertEquals(4, reservationService.rankingService.order.size());
		assertEquals(0, reservationService.rankingService.getRank(c1).rank);
		assertEquals(1, reservationService.rankingService.getRank(c2).rank);
		assertEquals(0, reservationService.rankingService.getRank(c4).rank);
	}

}
