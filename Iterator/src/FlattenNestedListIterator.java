//528. Flatten Nested List Iterator
//中文English
//Given a nested list of integers, implement an iterator to flatten it.
//
//Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
//Example
//Example1
//
//Input: list = [[1,1],2,[1,1]]
//Output: [1,1,2,1,1]
//Example2
//
//Input: list = [1,[4,[6]]]
//Output: [1,4,6]
//Notice
//You don't need to implement the remove method.


//两种办法 1.recursive 
//        2.Stack (重点掌握)


import java.util.*;
public class FlattenNestedListIterator {
	public interface NestedInteger {
		 
		      // @return true if this NestedInteger holds a single integer,
		      // rather than a nested list.
		      public boolean isInteger();
		 
		      // @return the single integer that this NestedInteger holds,
		      // if it holds a single integer
		      // Return null if this NestedInteger holds a nested list
		      public Integer getInteger();
		 
		      // @return the nested list that this NestedInteger holds,
		      // if it holds a nested list
		      // Return null if this NestedInteger holds a single integer
		      public List<NestedInteger> getList();
		  }
	
	public class NestedIterator implements Iterator<Integer> {
		ArrayList<Integer> lst;
	    int index;
	    public NestedIterator(List<NestedInteger> nestedList) {
	        // Initialize your data structure here.
	        lst=new ArrayList<Integer>();
	        flattenAdd(lst, nestedList);
	        index=0;
	    }
	    
	    private void flattenAdd(ArrayList<Integer> lst, List<NestedInteger> nestedList){
	        for(NestedInteger num: nestedList){
	            if(num.isInteger()){
	                lst.add(num.getInteger());
	            }else{
	                flattenAdd(lst, num.getList());
	            }
	        }
	    }

	    // @return {int} the next element in the iteration
	    @Override
	    public Integer next() {
	        // Write your code here
	        return lst.get(index++);
	    }

	    // @return {boolean} true if the iteration has more element or false
	    @Override
	    public boolean hasNext() {
	        // Write your code here
	        if(index == lst.size()) return false;
	        return true;
	    }
	}
	
/*
 * public class NestedIterator implements Iterator<Integer> {
 * 
 * private Stack<NestedInteger> stack;
 * 
 * private void pushListToStack(List<NestedInteger> nestedList) {
 * 		Stack<NestedInteger> temp = new Stack<>(); 
 * 		for (NestedInteger nested : nestedList) { temp.push(nested); }
 * 		 while (!temp.isEmpty()) {
 * 			 stack.push(temp.pop()); 
 *      } 
 * }
 * 
 * public NestedIterator(List<NestedInteger> nestedList) { stack = new
 * Stack<>(); pushListToStack(nestedList); }
 * 
 * // @return {int} the next element in the iteration
 * 
 * @Override public Integer next() { if (!hasNext()) { 
 * 	return null; } 
 *  return stack.pop().getInteger(); 
 * }
 * 
 * // @return {boolean} true if the iteration has more element or false
 * 
 * @Override public boolean hasNext() { while (!stack.isEmpty() &&
 * !stack.peek().isInteger()) { pushListToStack(stack.pop().getList()); }
 * 
 * return !stack.isEmpty(); }
 */
	    
}
