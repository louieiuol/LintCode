package junitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import basics.Order;
import basics.Time;
import controllers.PriorityAlgorithm;

public class TestMarginalData {

	@Test
	public void OrderTimeNearDeadline1() {
		Time start=new Time(6,30,0);
		Order order=new Order("WM040", "S1W1", "21:56:01");
		assertEquals(false,order.isDeliverable(start.getAbsoluteTime()));
	}
	

	@Test
	public void OrderTimeNearDeadline2() {
		Time start=new Time(6,30,0);
		Order order=new Order("WM040", "S1W1", "21:55:59");
		System.out.println(order.getPos().getOrderDistanceInSecond());
		assertEquals(true,order.isDeliverable(start.getAbsoluteTime()));
	}
	
	@Test
	public void StartTimeNearDeadline1() {
		Time start=new Time(21,56,1);
		Order order=new Order("WM040", "S1W1", "06:30:00");
		System.out.println(order.getPos().getOrderDistanceInSecond());
		assertEquals(false,order.isDeliverable(start.getAbsoluteTime()));
	}
	
	
	@Test
	public void StartTimeNearDeadline2() {
		Time start=new Time(21,55,59);
		Order order=new Order("WM040", "S1W1", "06:30:00");
		System.out.println(order.getPos().getOrderDistanceInSecond());
		assertEquals(true,order.isDeliverable(start.getAbsoluteTime()));
	}

}
