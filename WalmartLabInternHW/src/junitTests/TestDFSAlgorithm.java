package junitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import basics.Order;
import basics.Time;
import controllers.BFAlgorithm;
import controllers.PriorityAlgorithm;

public class TestDFSAlgorithm {

	@Test
	public void testWithNearDeadlineData() {
		Time start=new Time(6,0,0);
		Time end=new Time(22,0,0);
		ArrayList<Order> datalist=new ArrayList<Order>();
		datalist.add(new Order("WM001", "N11W50", "19:00:00"));
		datalist.add(new Order("WM002", "S11E21", "19:12:55"));
		datalist.add(new Order("WM003", "N11E5", "21:05:50" ));
		datalist.add(new Order("WM004", "N5E5", "21:05:50" ));
		BFAlgorithm controller=new BFAlgorithm(datalist, start, end);
		controller.init();
		controller.start();
		System.out.println("Delivered: "+ controller.getPrint());
		System.out.println("UnDelivered: "+ controller.getUnDeliveryOrder());
		System.out.println("NPS: "+ controller.getTotalNPS());
		assertTrue(controller.getTotalNPS() == 50);
	}
	
	
	@Test public void testWithLargeLengthData() {
		Time start=new Time(6,0,0);
		Time end=new Time(22,0,0);
		ArrayList<Order> datalist=new ArrayList<Order>();
		datalist.add(new Order("WM001", "N11W50", "19:00:00"));
		datalist.add(new Order("WM002", "S11E21", "19:12:55"));
		datalist.add(new Order("WM003", "N11E5", "21:05:50" ));
		datalist.add(new Order("WM004", "N5E5", "21:05:50" ));
		datalist.add(new Order("WM005", "N11W5", "05:11:50"));
		datalist.add(new Order("WM006", "S3E2", "05:11:55"));
		datalist.add(new Order("WM007", "N7E50", "05:31:50" ));
		datalist.add(new Order("WM008", "N11E5", "06:11:50" ));
		BFAlgorithm controller=new BFAlgorithm(datalist, start, end);
		controller.init();
		controller.start();
		System.out.println("Delivered: "+ controller.getPrint());
		System.out.println("UnDelivered: "+ controller.getUnDeliveryOrder());
		System.out.println("NPS: "+ controller.getTotalNPS());
		assertTrue(controller.getTotalNPS() == 62);
	}
	
	@Test
	public void testWithExpiredData() {
		Time start=new Time(6,0,0);
		Time end=new Time(22,0,0);
		ArrayList<Order> datalist=new ArrayList<Order>();
		datalist.add(new Order("WM001", "N11W50", "00:00:00"));
		datalist.add(new Order("WM002", "S110E110", "05:59:00"));
		datalist.add(new Order("WM003", "N11E5", "21:05:50"));
		BFAlgorithm controller=new BFAlgorithm(datalist, start, end);
		controller.init();
		controller.start();
		System.out.println("Delivered: "+ controller.getPrint());
		System.out.println("UnDelivered: "+ controller.getUnDeliveryOrder());
		System.out.println("NPS: "+ controller.getTotalNPS());
		assertTrue(controller.getTotalNPS() == 0);
	}

}
