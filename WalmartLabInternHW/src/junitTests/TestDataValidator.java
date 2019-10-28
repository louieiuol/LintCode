package junitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import data.DataValidator;

public class TestDataValidator {
	@Test
	public void testForValid() {
		DataValidator dv=new DataValidator("WM003", "N11W5", "05:11:50");
		assertEquals(true,dv.validate());
	}
	
	@Test
	public void testForInValidId1() {
		DataValidator dv=new DataValidator("MW003", "N11W5", "05:11:50");
		assertEquals( false, dv.validate());
	}
	
	@Test
	public void testForInValidId2() {
		DataValidator dv=new DataValidator("WMUUU", "N11W5", "05:11:50");
		assertEquals( false, dv.validate());
	}
	@Test
	public void testForInValidLocation1() {
		DataValidator dv=new DataValidator("WM003", "E11N5", "05:11:50");
		assertEquals( false, dv.validate());
	}
	
	@Test
	public void testForInValidLocation2() {
		DataValidator dv=new DataValidator("WM003", "33300", "05:11:50");
		assertEquals( false, dv.validate());
	}

	
	@Test
	public void testForInValidTime1() {
		DataValidator dv=new DataValidator("WM003", "N3E5", "33&11&50");
		assertEquals( false, dv.validate());
	}
	
	@Test
	public void testForInValidTime2() {
		DataValidator dv=new DataValidator("WM003", "N10E50", "30:11:50");
		assertEquals( false, dv.validate());
	}
	
	@Test
	public void testForInValidTime3() {
		DataValidator dv=new DataValidator("WM003", "N10E50", "23:11:88");
		assertEquals( false, dv.validate());
	}
}
