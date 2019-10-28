import java.util.*;

public class SlidingWindowMedian {
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code here
        List<Integer> result=new ArrayList<>();
        PriorityQueue<Integer> minHeap=new PriorityQueue<>();
        //min heap装比自己大的 按照由小到大排序
        PriorityQueue<Integer> maxHeap=new PriorityQueue<>(Collections.reverseOrder());
        //max heap装比自己小的 按照由大到小排序 
        for(int i=0; i<nums.length; i++){
            int number=nums[i];
            maxHeap.offer(number);
            minHeap.offer(maxHeap.poll());
            //两边都过一遍元素 然后保持两个heap平衡
            balance(minHeap, maxHeap);
            //进行remove操作
            if(i >= k){
            	//每当走到超过k个元素时 （k对于index k-1) 移走前面的第i-k个元素
                if(nums[i-k] <= maxHeap.peek()){
                	//max heap的第一个就是median 来确定元素在哪个heap
                    maxHeap.remove(nums[i-k]);
                }else{
                    minHeap.remove(nums[i-k]);
                }
            }
            balance(minHeap, maxHeap);
            //移除后再平衡heap 
            if(i >= k-1){
            	//等到超过k个的元素在heap里时候 （index为k-1） 读取中位数 max heap的第一个median
                result.add(maxHeap.peek());
            }
        }
        return result;
    }
    
    private void balance(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap){
    	//始终保持min heap比 max heap大1个
        while(minHeap.size() > maxHeap.size()){
            maxHeap.offer(minHeap.poll());
            //如果min heap过多则移除后向max heap移动
        }
        
        while(maxHeap.size() - minHeap.size() > 1){
            minHeap.offer(maxHeap.poll());
            //如果max heap比min heap多出超过一个 则向min heap移动
        }
    }
}
