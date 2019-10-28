package HW1and2;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FindPrimeFactorTest {

	@Test
	public void testGeneral() throws Exception {
		List<Integer> a=new ArrayList<>();
		List<Integer> b=new ArrayList<>();
		a.add(12);
		a.add(25);
		a.add(18);
		a.add(8);
		b.add(6);
		b.add(2);
		b.add(3);
		b.add(2);
		assertTrue((FindPrimeFactor.findPrimeFactor(a, b)) == 2);
	}
	
	@Test
	public void testNull() throws Exception {
		List<Integer> a=new ArrayList<>();
		List<Integer> b=new ArrayList<>();
		a.add(null);
		a.add(10);
		a.add(15);
		a.add(8);
		b.add(2);
		b.add(5);
		b.add(5);
		b.add(2);
		assertTrue((FindPrimeFactor.findPrimeFactor(a, b)) == 1);
	}
	
	@Test
	public void testSize1() throws Exception {
		List<Integer> a=new ArrayList<>();
		List<Integer> b=new ArrayList<>();
		a.add(25);
		a.add(18);
		b.add(6);
		b.add(2);
		b.add(3);
		b.add(2);
		assertTrue((FindPrimeFactor.findPrimeFactor(a, b)) == 1);
	}
	
	@Test
	public void testSize2() throws Exception {
		List<Integer> a=new ArrayList<>();
		List<Integer> b=new ArrayList<>();
		a.add(25);
		a.add(18);
		a.add(10);
		a.add(8);
		b.add(3);
		b.add(4);
		b.add(5);
		assertTrue((FindPrimeFactor.findPrimeFactor(a, b)) == 2);
	}

}
