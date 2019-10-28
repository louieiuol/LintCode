//360. Sliding Window Median
//中文English
//Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the array, find the median of the element inside the window at each moving. (If there are even numbers in the array, return the N/2-th number after sorting the element in the window. )
//
//Example
//Example 1:
//
//Input:
//[1,2,7,8,5]
//3
//Output:
//[2,7,7]
//
//Explanation:
//At first the window is at the start of the array like this `[ | 1,2,7 | ,8,5]` , return the median `2`;
//then the window move one step forward.`[1, | 2,7,8 | ,5]`, return the median `7`;
//then the window move one step forward again.`[1,2, | 7,8,5 | ]`, return the median `7`;
//Example 2:
//
//Input:
//[1,2,3,4,5,6,7]
//4
//Output:
//[2,3,4,5]
//
//Explanation:
//At first the window is at the start of the array like this `[ | 1,2,3,4, | 5,6,7]` , return the median `2`;
//then the window move one step forward.`[1,| 2,3,4,5 | 6,7]`, return the median `3`;
//then the window move one step forward again.`[1,2, | 3,4,5,6 | 7 ]`, return the median `4`;
//then the window move one step forward again.`[1,2,3,| 4,5,6,7 ]`, return the median `5`;
//Challenge
//O(nlog(n)) time

import java.util.*;

public class SlidingWindowMedium {
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code here
        List<Integer> result=new ArrayList<>();
        if(nums == null || nums.length == 0) return result;
        PriorityQueue<Integer> minHeap=new PriorityQueue<>();
        //min heap用来装1/2个比较大的数 按照小的在前面排列 
        PriorityQueue<Integer> maxHeap=new PriorityQueue<>(Collections.reverseOrder());
        //max heap用来装1/2(+1)个比较小的数 按照大的在前面排列
        for(int i=0; i<nums.length; i++){
            int number=nums[i];
            minHeap.offer(number);
            //先去min heap 
            maxHeap.offer(minHeap.poll());
            //再从min heap中拿出 加入max heap 
            balance(minHeap, maxHeap);
            //平衡两个heap 
            if(i >=k ){
            	//如果i大于窗口值 （从i=k）开始 i=k-1之后 
                if(maxHeap.peek() >= nums[i-k]){
                	//如果maxheap的最大值比当前值要大于或等于 
                    maxHeap.remove(nums[i-k]);
                    //那么从max heap里面remove
                }else{
                    minHeap.remove(nums[i-k]);
                    //否则从min heap里面remove 
                }
                balance(minHeap, maxHeap);
                //每次remove 后都要再平衡 
            }
            if(i >= k-1){
            	//刚刚到k-1的时候 我们开始加入中位数到结果里
            	//中位数是maxheap的第一个 
                result.add(maxHeap.peek());
            }
        }
        return result;
    }
    
    private void balance(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap){
        while(minHeap.size() > maxHeap.size()){
        	//先从min heap移到 max heap, min heap 应该保证不超过max heap的大小
            maxHeap.offer(minHeap.poll());
        }
        while(maxHeap.size() - minHeap.size() > 1){
        	//再从max heap移到min heap, max heap 比 min 最多只能多1  
            minHeap.offer(maxHeap.poll());
        }
    }
}
