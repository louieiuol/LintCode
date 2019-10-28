package HW7;
/**
 * @author Yoon Chae(G01066504)
 * @author Guanhua Liu(G01161931)
 * @author An Nguyen (G00593022)
 *
 * Guanhua Liu did the Junit test and comments.
 * Yoon Chae did the Indexer interface and ListIndexer implementation.
 * An Nguyen did the Search class implementation.
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class jUnitTests {

	@Test
	public void testAddAndSize() {
		ListIndexer<Integer> i=new ListIndexer<>();
		i.add(1);
		i.add(12);
		i.add(3);
		assertTrue(i.size()==3);
	}
	
	
	@Test
	public void testContain() {
		ListIndexer<Integer> i=new ListIndexer<>();
		i.add(1);
		i.add(12);
		i.add(3);
		assertTrue(i.contains(3));
	}
	
	@Test
	public void testSearch() throws NullPointerException, ClassNotFoundException {
		ListIndexer<Integer> i=new ListIndexer<>();
		i.add(1);
		i.add(12);
		i.add(3);
		int index=Search.search(i, 3);
		assertEquals(index, 2);
	}

	@Test
	public void testGet() {
		ListIndexer<Integer> i=new ListIndexer<>();
		i.add(1);
		i.add(12);
		i.add(3);
		assertTrue(i.get(2) == 3);
	}
	
}
