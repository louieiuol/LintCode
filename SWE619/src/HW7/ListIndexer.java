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



import java.util.ArrayList;
import java.util.List;

public class ListIndexer<E> implements Indexer<E> {
    //OVERVIEW:  An arbitrary list of elements that implements the
    //  Indexer interface.

    //Variables
    public List<E> list;

    //Constructors
    public ListIndexer(){ list = new ArrayList<>();}

    public ListIndexer(List <E> listParam){
        list = listParam;
    }

    //Methods
    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    public void add(E e){
        list.add(e);
    }
    
    @Override
    public boolean contains(E x) {
    	return list.contains(x);
    }
    
}
