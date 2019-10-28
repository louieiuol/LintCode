/*Minimum number of operation required to convert number x into y
Given a initial number x and two operations which are given below:

Multiply number by 2.
Subtract 1 from the number.
The task is to find out minimum number of operation required to convert number x into y using only above two operations. We can apply these operations any number of times.

Constraints:
1 <= x, y <= 10000
*/

	import java.util.HashSet; 
	import java.util.LinkedList; 
	import java.util.Set; 
	  

public class BrokenCalculator {
	class GFG  
	{ 
	    int val; 
	    int steps; 
	  
	    public GFG(int val, int steps)  
	    { 
	        this.val = val; 
	        this.steps = steps; 
	    } 
	} 
	public int minOperations(int src,  int target){ 
	  
	        Set<GFG> visited = new HashSet<>(1000); 
	        LinkedList<GFG> queue = new LinkedList<GFG>(); 
	  
	        GFG node = new GFG(src, 0); 
	  
	        queue.offer(node); 
	        visited.add(node); 
	  
	        while (!queue.isEmpty())  
	        { 
	            GFG temp = queue.poll(); 
	            visited.add(temp); 
	  
	            if (temp.val == target) 
	            { 
	                return temp.steps; 
	            } 
	  
	            int mul = temp.val * 2; 
	            int sub = temp.val - 1; 
	  
	            // given constraints 
	            if (mul > 0 && mul < 1000)  
	            { 
	                GFG nodeMul = new GFG(mul, temp.steps + 1); 
	                queue.offer(nodeMul); 
	            } 
	            if (sub > 0 && sub < 1000)  
	            { 
	                GFG nodeSub = new GFG(sub, temp.steps + 1); 
	                queue.offer(nodeSub); 
	            } 
	        } 
	        return -1; 
	    }
}
