package HW11;
import java.util.*;
public class ForwardingMap<K,V> implements Map< K,V> {
	 private final Map<K,V> s;

	   public ForwardingMap(Map<K,V> s) { 
	       this.s = s; 
	    }

	   public V put(K k, V v){ 
	        return s.put(k, v);
	   }
	   
	   public V remove(Object key){ 
	       return s.remove(key);
	    }

	    //Compares the specified object with this map for equality.
	   @Override public boolean equals(Object o){ 
	       return s.equals(o);  
	    }

	   @Override public int hashCode(){ 
	       return s.hashCode(); 
	    }

	   @Override public String toString(){ 
	       return s.toString(); 
	    }

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
