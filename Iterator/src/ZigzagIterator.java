//540. Zigzag Iterator
//中文English
//Given two 1d vectors, implement an iterator to return their elements alternately.
//
//Example
//Example1
//
//Input: v1 = [1, 2] and v2 = [3, 4, 5, 6]
//Output: [1, 3, 2, 4, 5, 6]
//Explanation: 
//By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
//Example2
//
//Input: v1 = [1, 1, 1, 1] and v2 = [3, 4, 5, 6]
//Output: [1, 3, 1, 4, 1, 5, 1, 6]

import java.util.*;
public class ZigzagIterator {
    int index;
    List<Integer> lst;
    		/*
    	    * @param v1: A 1d vector
    	    * @param v2: A 1d vector
    	    */
    	    Iterator<Integer> it1;
    	    Iterator<Integer> it2;
    	    int change;
    	    //simple solution with alternative counter 
    	    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
    	        // do intialization if necessary
    	        it1=v1.iterator();
    	        it2=v2.iterator();
    	        change=0;
    	    }

    	    /*
    	     * @return: An integer
    	     */
    	    public int next() {
    	        // write your code here
    	        change++;
    	        if((change % 2 == 1 && it1.hasNext()) || !it2.hasNext()){
    	            return it1.next();
    	        }else if((change % 2 == 0 && it2.hasNext()) || !it1.hasNext()){
    	            return it2.next();
    	        }else {
    	            return -1;
    	        }
    	    }

    	    /*
    	     * @return: True if has next
    	     */
    	    public boolean hasNext() {
    	        // write your code here
    	        return it1.hasNext() || it2.hasNext();
    	    }
}
