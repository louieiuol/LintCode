package HW11;
import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;
public class junitTest {
	
	
	@Test 
	public void testSet() {
		HashSet<Integer> s1=new HashSet<>();
		s1.add(1);
		InstrumentedSet<Integer> set1=new InstrumentedSet<>(s1);
		System.out.println("set1"+set1.toString());
		assertTrue(set1.equals(set1));
	}

	@Test
	public void testCollection() {
		ArrayList<Integer> l1=new ArrayList<>();
		l1.add(1);
		ArrayList<Integer> l2=new ArrayList<>();
		l2.add(1);
		InstrumentedCollection<Integer> coll1=new InstrumentedCollection<>(l1);
		System.out.println("coll1"+coll1.toString());
		InstrumentedCollection<Integer> coll2=new InstrumentedCollection<>(l2);
		System.out.println("coll2"+coll2.toString());
		assertTrue(coll1.equals(coll2));
	}
	

	@Test
	public void testCollection2() {
		ArrayList<Integer> l1=new ArrayList<>();
		l1.add(1);
		ArrayList<Integer> l2=new ArrayList<>();
		l2.add(1);
		InstrumentedCollection<Integer> coll1=new InstrumentedCollection<>(l1);
		System.out.println("coll1"+coll1.toString());
		InstrumentedCollection<Integer> coll2=new InstrumentedCollection<>(l2);
		System.out.println("coll2"+coll2.toString());
		assertTrue(coll2.equals(coll1));
	}
	
	
	@Test
	public void testList() {
		ArrayList<Integer> l1=new ArrayList<>();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		ArrayList<Integer> l2=new ArrayList<>();
		l2.add(1);
		l2.add(2);
		l2.add(3);
		InstrumentedList<Integer> lst1=new InstrumentedList<>(l1);
		System.out.println("instr list1"+lst1.toString());
		InstrumentedList<Integer> lst2=new InstrumentedList<>(l2);
		System.out.println("instr list2"+lst2.toString());
		assertTrue(lst1.equals(lst2));
	}
	
	@Test
	public void testMap() {
		HashMap<Integer,Integer> m1=new HashMap<>();
		m1.put(1, 2);
		HashMap<Integer,Integer> m2=new HashMap<>();
		m2.put(1, 2);
		InstrumentedMap<Integer,Integer> map1=new InstrumentedMap<>(m1);
		System.out.println("map1"+map1.toString());
		InstrumentedMap<Integer,Integer> map2=new InstrumentedMap<>(m2);
		System.out.println("map2"+map2.toString());
		assertTrue(map1.equals(map2));
	}
}
