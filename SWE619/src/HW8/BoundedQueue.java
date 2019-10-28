package HW8;



/**
 * @author Yoon Chae(G01066504)
 * @author Guanhua Liu(G01161931)
 * @author An Nguyen (G00593022)
 *
 * Guanhua Liu did the BoundedQueue test and comments.
 * Yoon Chae did the BoundedQueue basic constructor and methods. 
 * An Nguyen did the putAll() and getAll() methods.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
public class BoundedQueue<E> {
    //OVERVIEW: A class that stores a queue of generic type objects
    //RI: size > 0, rep must be less than size

    //Variables
    protected List<E> rep;
    protected int size = 0;  //max size of rep

    //Constructor
    public BoundedQueue(int size) {
        if (size > 0) {
            this.size = size;
            rep = new ArrayList<>();
        }else {
        	throw new IllegalStateException("BoundedQueue()");
        }
    }

    //Methods
    public boolean isEmpty() {
        return (getCount() == 0);
    }

    public boolean isFull() {
        return (getCount() == size);
    }

    public int getCount() {
        return rep.size();
    }

    public void put(E e) {
        if (e != null && getCount() < size) {
            rep.add(e);
        }
    }

    public E get() {
        E result = null;
        if (!isEmpty()) {
            result = rep.get(0);
            rep.remove(0);
        } else {
            throw new IllegalStateException("BoundedQueue.get");
        }
        return result;
    }

    public void putAll(Iterable<? extends E> src) {
        //EFFECTS: puts all elements from an Iterable type into rep until
        //rep isFull and discards remaining elements in Iterable.
        for (E t : src) { put(t); }
    }

    //Get Items from collection
    public Collection<? extends E> getAll () {
        //EFFECTS: removes all elements from rep until rep isEmpty.
    	
    	Collection<? extends E> coll=this.rep;
        while (!isEmpty()) {
            get();
        }
        return coll;
    }

    public boolean repOK() {
        //EFFECTS: Returns true if the rep invariant holds for this
        //otherwise returns false
    	if(rep == null) return  false;
        if(rep.size() > size) return false;
        return true;
    }

    public static void main(String args[]) {
        BoundedQueue<Integer> queue = new BoundedQueue<>(10);
        for (int i = 0; !queue.isFull(); i++) {
            queue.put(i);
            System.out.println("put: " + i);
        }
        queue.put(null);
        //System.out.println("put: " + queue.toString());
        while (!queue.isEmpty()) {
            System.out.println("get: " + queue.get());
        }
    }

}

