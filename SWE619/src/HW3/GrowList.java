package HW3;

import java.util.*;
//GrowList is a mutable list that only gets longer.
public class GrowList <E> {

private Map<Integer,E> values;

public GrowList() { 
	values = new HashMap<Integer,E>();
	}

private GrowList(Map<Integer, E> map) {
	values=new HashMap<Integer,E>();
	values.putAll(map);
}
// add to the end of the list
// producer
public GrowList<E> add(E o) {
	GrowList<E> newlist=new GrowList<E>(this.values);
	newlist.values.put(size(), o);
	return newlist;
}

// number of values in list
public int size() { return values.size(); }

// get ith value in list
// observer
public E get(int i) { 
 if (! inRange(i)) throw new IndexOutOfBoundsException("GrowList.get");
 return values.get(i);
}

// update ith value in list;  return previous value or null if none
// producer
public GrowList<E> set(int i, E o) {
	GrowList<E> newlist=new GrowList<E>(this.values);
	newlist.values.put(i,o);
	return newlist;
}

//observer
public E peak(int i, E o) {
	return this.get(i);
}


// private helper method
private boolean inRange(int i) { return (i >= 0) && (i < size()); }

public String toString() {
  if (size() == 0) return "[]";
  String result = "[";
  for (int i = 0; i < size()-1; i++) {
      result += values.get(i) + ",";
  }
  return result + values.get(size() - 1) + "]";
}

public static void main(String[] args) {
 GrowList<String> list = new GrowList<String>();

 System.out.println("list is:" + list);
 list.add("cat");
 System.out.println("list is:" + list);
 list.add("dog");
 System.out.println("list is:" + list);
 list.set(1,"bat");
 System.out.println("list is:" + list);

}
}
