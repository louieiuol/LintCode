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

public class LiskovGenericSet<E> {

	private List<E> elements;

	public LiskovGenericSet() {   
		this.elements = new ArrayList<E>();
	}

	public void insert (E e) {
		if (!(isIn(e))) {
			elements.add(e);
		}
	}

	public void remove (E e) {
		elements.remove(e);
	}

	public boolean isIn(E e) {
		return elements.contains(e);
	}

	public int size() {
		return elements.size();
	}

	public E choose() {
		if (elements.size() == 0) throw new IllegalArgumentException();
		return elements.get(0);
	}

	// Conveniently, the rep state is exactly the abstract state
	public String toString() { 
		return elements.toString(); 
	}
}
