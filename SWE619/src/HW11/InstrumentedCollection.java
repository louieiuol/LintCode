package HW11;
import java.util.*;
public class InstrumentedCollection<E> extends ForwardingCollection<E>{
	   private int addCount = 0;	

	   public InstrumentedCollection(Collection<E> s){ 
	       super(s); 
	    }

	   @Override public boolean add(E e){ 
	       addCount++; 
	       return super.add(e); 
	    }

	   public int getAddCount(){ 
	       return addCount; 
	    }
	  
	}
