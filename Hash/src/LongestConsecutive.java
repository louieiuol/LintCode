import java.util.*;
/*124. Longest Consecutive Sequence
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Example
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Clarification
Your algorithm should run in O(n) complexity.*/

public class LongestConsecutive {
    public int longestConsecutive(int[] num) {
        if(num == null || num.length == 0) return 0;
        HashSet<Integer> set=new HashSet<>();
        //use a HashSet to store all elements
        for(int i=0; i< num.length; i++){
            set.add(num[i]);
        }
        //add all elements into set
        int max=1;
        //at least 1 minimum consecutive number
        for(int i=0; i<num.length ; i++){
            int curr=num[i];
            int down=curr-1;
            int up=curr+1;
            if(set.contains(curr)) {
            	set.remove(curr);
            }
            //如果set中有current 先清除current 
            while(set.contains(down)){
                set.remove(down);
                down--;
            }
            //HashSet contains method cost O(1) 
            //though use while loop, the first time it access set, it will only traversal non duplicate elements
            //the second time we reach the number again in for loop, but number has been removed from set, so it won't access twice
           
            while(set.contains(up)){
                set.remove(up);
                up++;
            }
            max=Math.max(max,up-down-1);
            // Assume set is 1 2 3 up becomes 4 down is 0 so 4 - 0 - 1 = 3
            // Assume set is -1 -2 -3 down become -4 up is 0 so 0 - (-4) -1 = 3
            //Why? because up becomes 1 more than current max, down is 1 less than current min, so it counted twice, then we deduct 1
        }
        return max;
    }
}
