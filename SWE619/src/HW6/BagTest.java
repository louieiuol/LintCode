package HW6;
/**
 * @author Yoon Chae(G01066504)
 * @author Guanhua Liu(G01161931)
 * @author An Nguyen (G00593022)
 *
 * Guanhua Liu did the Junit test and comments.
 * Yoon Chae did the Bag class constructor and methos implementation.
 * An Nguyen did the sub-type question analysis. 
 */

//ANSWER TO LISKOV QUESTION:
//Our group believes that the Bag class is not a legitimate sub-type
//of the LiskovGenericSet because the size, insert, and remove method does not fulfill
//the property rule.  Thus, the of the substitution principle has been
//violated.  See our test below of how the size, insert, and remove method has a different
//effect of the evolution property size().

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class BagTest {
	private Bag<String> b1;
	private LiskovGenericSet<String> l1;
	private void init() {
		ArrayList<Integer> lst=new ArrayList<>();
		Collections.reverse(lst);
		b1 = new Bag<String>();
	    l1 = new LiskovGenericSet<String>();
	}
	
	private void initBag() {
        b1.insert("cat");
        b1.insert("cat");
        b1.insert("dog");
        b1.insert("rat");
        b1.insert("rat");
        b1.insert("rat");
	}
	
	private void initSet() {
        l1.insert("cat");
        l1.insert("cat");
        l1.insert("dog");
        l1.insert("rat");
        l1.insert("rat");
        l1.insert("rat");
	}
	
	public void initTest(){
		init();
		initBag();
		initSet();
	}
	
    @Test
    public void bagSizeTest() throws Exception {
    	initTest();
        assertEquals(6, b1.size());
        ///*** Note the differing evolution property from the LiskovGenericSet*****\\\\\
    }

    @Test
    public void setSizeTest() throws Exception {
    	initTest();
        assertEquals(3, l1.size());
    }
    
    @Test
    public void bagInsertTest() throws Exception {
    	initTest();
        b1.insert("cat");
        assertEquals(7, b1.size());
        ///*** Note the differing evolution property from the LiskovGenericSet*****\\\\\
    }
    
    @Test
    public void setInsertTest() throws Exception {
    	initTest();
        l1.insert("cat");
        assertEquals(3, l1.size());
    }
    
    @Test
    public void bagremove() throws Exception {
    	initTest();
        b1.remove("cat");
        assertEquals(5, b1.size());
        ///*** Note the differing evolution property from the LiskovGenericSet*****\\\\\
    }
    
    @Test
    public void liskovremove() throws Exception {
    	initTest();
        l1.remove("cat");
        assertEquals(2, l1.size());
    }
    
    

}
