
/*Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
*/

import java.util.LinkedList;
import java.util.Queue;

public class StackWithQueue {
	class MyStack {
		private Queue<Integer> queue1;
		private Queue<Integer> queue2;
		
	    /** Initialize your data structure here. */
	    public MyStack() {
	    	queue1=new LinkedList<Integer>();
	    	queue2=new LinkedList<Integer>();
	    }
	    
	    /** Push element x onto stack. */
	    public void push(int x) {
	        if(queue1.isEmpty())
	    		queue2.add(x);
	    	else
	    		queue1.add(x);
	    }
	    
	    /** Removes the element on top of the stack and returns that element. */
	    public int pop() {
	        return pop_top_helper();
	    }
	    
	    /** Get the top element. */
	    public int top() {
	        int ele= pop_top_helper();
	        if(queue1.isEmpty()) {
	        	queue2.add(ele);
	        }else if(queue2.isEmpty()) {
	            queue1.add(ele);
	        }
	        return ele;
	    }
	    
	    private int pop_top_helper() {
	    	if(empty()) {
	    		return -1;
	    	}
	    	if(queue1.isEmpty()) {
	    		return pop_top_helper_helper(queue2, queue1);
	    	}else if(queue2.isEmpty()) {
	    		return pop_top_helper_helper(queue1, queue2);
	    	}
	    	return -1;
	    }
	    
	    private int pop_top_helper_helper(Queue<Integer> queue1,Queue<Integer> queue2) {
	    	int count=0;
	    	int size=queue1.size();
			while(!queue1.isEmpty()) {
				count++;
				if(size==count) {
					int ele=queue1.poll();
					return ele;
				}else {
					queue2.add(queue1.remove());
				}
			}
			return -1;
	    }
	    
	    /** Returns whether the stack is empty. */
	    public boolean empty() {
	    	if(queue1.isEmpty() && queue2.isEmpty()) {
	    		return true;
	    	}
	    	return false;
	    }
	}

	/**
	 * Your MyStack object will be instantiated and called as such:
	 * MyStack obj = new MyStack();
	 * obj.push(x);
	 * int param_2 = obj.pop();
	 * int param_3 = obj.top();
	 * boolean param_4 = obj.empty();
	 */
}
