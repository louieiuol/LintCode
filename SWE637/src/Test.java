import static org.junit.Assert.*;

public class Test {

	@org.junit.Test
	public void test() {
		Point a=new Point(1,1);
		Point b=new Point(0,1);
		Point c=new Point(1,2);

		assertEquals(a.compareTo(b),0);
		assertEquals(b.compareTo(c),0);
		assertEquals(a.compareTo(c),0);
	}
	
	
	 @org.junit.Test public void CorrectTest() 
	 { 
		 PointCorrect a=new PointCorrect(1,0); 
		 PointCorrect b=new PointCorrect(1,0);
		 PointCorrect c=new PointCorrect(1,0);
	  
	  assertEquals(a.compareTo(b),0); assertEquals(b.compareTo(c),0);
	  assertEquals(a.compareTo(c),0); }
	 
}
