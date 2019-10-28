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


public interface Indexer<E> {
    //OVERVIEW:  All subtypes of Indexer provide a means to search for
    // and element at a particular index in an arbitrary collection.

    public E get(int x) throws IndexOutOfBoundsException;
    //EFFECTS:  Throws IndexOutOfBoundsException if x is not in range of Indexes,
    // else, returns an element of the index that is equal to x.

    public int size();
    //EFFECTS:  returns the size of a collection as an int.
    
	public boolean contains(E x);
	
}

