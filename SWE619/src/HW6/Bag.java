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


import java.util.*;

public class Bag<E> {
    // rep: map each object to the count of that object in this
    // rep-inv:  range of map contains only positive integers
    // Example:  A bag of 2 cats and a dog is map = { cat=2, dog=1 }

    private Map<E, Integer> map;
    private int size;
    public Bag() {
        map = new HashMap<E, Integer>();
        size = 0;
        //we need to return the cardinality of the map
        //"cardinality" means the number of total elements
        //so we need extra field for storing count of total element
        //in the bag
    }

    // add 1 occurrence of e to this
    public void insert(E e) {
        if (isIn(e)) {
            map.put(e, map.get(e) + 1);
        } else {
            map.put(e, 1);
        }
        size++;
        //increase the counter when we add
    }

    // remove 1 occurrence of e from this
    public void remove(E e) {
    	if(isIn(e)) {
    		//we need to check the element we want to delete
    		//if it is in the bag or not 
    		if(map.get(e) == 1) {
    			map.remove(e);
    		} else {
    			int count = map.get(e);
    			map.put(e, --count);
    		}
    		size--;
    		//if it is in the bag, decrease the size
        }
    }

    // return true if e is in this
    public boolean isIn(E e) {
    	return map.containsKey(e);
    }

    // return cardinality of this
    public int size() {
    	return size;
    	//map.size() only return # of keys in bag, not total elements
    }

    // if this is empty throw ISE
    // else return arbitrary element of this
    public E choose() {
        if(map.size()==0) throw new IllegalStateException();
        List<E> keys = new ArrayList<E>(map.keySet());
        Collections.shuffle(keys);
        return (E)keys.get(0);
    }

    // conveniently, the <E,Integer> map is exactly the abstract state
    public String toString() {
        return map.toString();
    }

    public boolean repOK() {
    	//EFFECTS: Returns true if the rep invariant holds for this
    	//otherwise returns false
    		for (Map.Entry<E, Integer> entry : map.entrySet()) {
    			if (entry.getValue() <= 0) return false;
    		}
    		return true;
    }

}

