package HW11;
import java.util.*;
public class InstrumentedList<E> extends ForwardingList<E> {
	private int addCount = 0;	

	   public InstrumentedList(List<E> s){ 
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
