package HW11;

import java.util.Map;

public class InstrumentedMap <K,V> extends ForwardingMap<K,V>{
	public InstrumentedMap(Map<K, V> s) {
		super(s);
	}

	private int addCount = 0;	
	
	   @Override public V put(K k, V v){ 
	       addCount++; 
	       return super.put(k,v); 
	    }

	   public int getAddCount(){ 
	       return addCount; 
	   }
}
