package HW11;
import java.util.*;
public class ForwardingCollection<E> {
	private final Collection<E> s;

	   public ForwardingCollection(Collection<E> s) { 
	       this.s = s; 
	    }

	   public boolean add(E e){ 
	       return s.add(e);     
	    }
	    
	   public boolean remove(Object o){ 
	       return s.remove(o);  
	    }

	   @Override public boolean equals(Object o){ 
			  return s.equals(o); 
	    }

	   @Override public int hashCode(){ 
	       return s.hashCode(); 
	    }

	   @Override public String toString(){ 
	       return s.toString(); 
	    }
}
