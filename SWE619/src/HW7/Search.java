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

public class Search<E extends Comparable<E>>{
//OVERVIEW:  A class that provides a search method for collection that extends the Indexer interface.
	
	
    public static <E extends Comparable<E>> int search (Indexer<E> c, E x) throws NullPointerException, ClassNotFoundException {
        //EFFECTS: If c is null throws NullPointerException, else
        //  if x is in c returns an index where x can be found,
        //  else throws ClassNotFoundException

        if (c==null || x == null) throw new NullPointerException();
        for(int i = 0; i < c.size(); i++){
            if(c.get(i) == null) throw new NullPointerException();
            E a = c.get(i);
            if(c.contains(x)) {
            	if(a.compareTo(x)==0){
            		System.out.println("Generic found at index: " + i);
            		return i;
            	}
            }
        }

        throw new ClassNotFoundException();
    }


}


