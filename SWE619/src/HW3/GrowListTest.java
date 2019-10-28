package HW3;

import static org.junit.Assert.*;

import org.junit.Test;

public class GrowListTest {
	
	GrowList lst;
	
	
	
	@Test
	public void testValues() {
		lst=new GrowList();
		lst=lst.add(1);
		lst=lst.add(2);
		lst=lst.add("louie");
		System.out.println(lst.toString());
		assertEquals(lst.toString(), "[1,2,louie]");
	}

	@Test
	public void testType1() {
		lst=new GrowList();
		lst=lst.add(1);
		lst=lst.add(2);
		lst=lst.add("louie");
		assertTrue(lst.get(0) instanceof Integer);
	}
	
	@Test
	public void testType2() {
		lst=new GrowList();
		lst=lst.add(1);
		lst=lst.add(2);
		lst=lst.add("louie");
		assertTrue(lst.get(2) instanceof String);
	}

	@Test
	public void testType3() {
		lst=new GrowList();
		lst=lst.add(1);
		lst=lst.add(2);
		lst=lst.add(3);
		lst=lst.set(1, "louie");
		assertTrue(lst.get(1) instanceof String);
	}

	@Test
	public void testType4() {
		lst=new GrowList();
		lst=lst.add(1);
		lst=lst.add(2);
		lst=lst.add(3);
		lst=lst.set(1, "louie");
		assertTrue(lst.get(2) instanceof Integer);
	}

}
