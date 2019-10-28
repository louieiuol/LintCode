//685. First Unique Number in Data Stream
//中文English
//Given a continuous stream of data, write a function that returns the first unique number (including the terminating number) when the terminating number arrives. If the unique number is not found, return -1.
//
//Example
//Example1
//
//Input: 
//[1, 2, 2, 1, 3, 4, 4, 5, 6]
//5
//Output: 3
//Example2
//
//Input: 
//[1, 2, 2, 1, 3, 4, 4, 5, 6]
//7
//Output: -1
//Example3
//
//Input: 
//[1, 2, 2, 1, 3, 4]
//3
//Output: 3

import java.util.*;

public class FirstUniqueInDataStream {
    public class ListNode{
        int val;
        ListNode next;
        ListNode prev;
        public ListNode(int val){
            this.val=val;
            this.next=null;
            this.prev=null;
        }
    }
    //建立双向链表 实现O(1)添加 
    
    ListNode head;
    ListNode tail;
    //头尾双指针实现O(1)添加 
    
    HashMap<Integer, ListNode> map;
    //记录和单次出现的元素 对应 ListNode的位置
    HashSet<Integer> set;
    //记录元素是否出现过
    
    public int firstUniqueNumber(int[] nums, int number) {
        // Write your code here
        if(nums == null || nums.length == 0) return -1;
        map=new HashMap<>();
        set=new HashSet<>();
        head=new ListNode(-1);
        //设置 dummy head
        tail=head;
        //设置 tail和head 开始
        head.next=tail;
        //head.next 指向 tail 
        tail.prev=head;
        //tail.prev 指向 head
        for(int i=0; i<nums.length; i++){
            add(nums[i]);
            //add number to list and HashMap
            if(nums[i] == number){
            	//当数字是要找的时候 返回第一个unique
                return findFirstUnique();
            }
        }
        return -1;
    }
    
    private void add(int number){
        if(!set.contains(number)){
        	//第一次出现的我们加入到map中和添加到链表里 同时对set也进行添加
            set.add(number);
            ListNode newNode=new ListNode(number);
            //通过从末尾添加来实现O(1)添加
            tail.next=newNode;
            //末尾添加新的node
            newNode.prev=tail;
            //新的node前面修改成末尾
            tail=newNode;
            //新的设置为末尾
            map.put(number, newNode);
            //加入map 
        }else if(map.containsKey(number)){
        	//如果第二次出现 我们要从map中删除 但是第三次或者更多次出现我们就不管 
            ListNode target=map.get(number);
            //获得ListNode的位置
            ListNode prevNode=target.prev;
            //找到target的PrevNode 
            ListNode nextNode=target.next;
            //找到targetNextNode
            prevNode.next=nextNode;
            //把prevNode的后面设为nextNode
            if(nextNode == null){
            	//如果是target是tail 那么nextNode为空  
                tail=prevNode;
                //把tail设置为prevNode 更新 tail 
            }else{
                nextNode.prev=prevNode;
                //否则nextNode 之前为prevNode
            }
            //target指空
            target.prev=null;
            target.next=null;
            //map删除
            map.remove(number);
        }
    }
    
    private int findFirstUnique(){
        return head.next == null ? -1 : head.next.val; 
    }
}
