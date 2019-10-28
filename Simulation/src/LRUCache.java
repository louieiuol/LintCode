//134. LRU Cache
//中文English
//Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
//
//get(key) Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
//set(key, value) Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
//Finally, you need to return the data from each get.
//
//Example
//Example1
//
//Input:
//LRUCache(2)
//set(2, 1)
//set(1, 1)
//get(2)
//set(4, 1)
//get(1)
//get(2)
//Output: [1,-1,1]
//Explanation：
//cache cap is 2，set(2,1)，set(1, 1)，get(2) and return 1，set(4,1) and delete (1,1)，because （1,1）is the least use，get(1) and return -1，get(2) and return 1.
//Example 2:
//
//Input：
//LRUCache(1)
//set(2, 1)
//get(2)
//set(3, 2)
//get(2)
//get(3)
//Output：[1,-1,2]
//Explanation：
//cache cap is 1，set(2,1)，get(2) and return 1，set(3,2) and delete (2,1)，get(2) and return -1，get(3) and return 2.



import java.util.*;
public class LRUCache {
	class ListNode {
	    int key;
	    int val;
	    ListNode prev;
	    ListNode next;

	    ListNode(int key, int val) {
	        this.key = key;
	        this.val = val;
	        this.prev = this.next = null;
	    }
	}
	    private ListNode dummy;
	    private ListNode tail;
	    private int capacity;
	    private Map<Integer, ListNode> map;
	    
	    public LRUCache(int capacity) {
	        //constructor
	        this.capacity = capacity;
	        dummy = new ListNode(0, 0);
	        tail = dummy;
	        map = new HashMap<>();
	    }

	    public int get(int key) {
	        if (!map.containsKey(key)) {
	            return -1;
	        }
	        //key exists in the LRUCache
	        ListNode current = map.get(key);
	        
	        //把当前节点删掉，再放到链表的头部
	        //分两种情况，1： 如果当前节点是最后一个节点 2: 如果当前节点在链表中间
	        if (current.next == null) {
	            current.prev.next = null;
	            tail = tail.prev;
	        } else {
	            current.prev.next = current.next;
	            current.next.prev = current.prev;
	        }
	        
	        //把删除的节点放到链表的头部， 表示链表头部永远存放的是the most recently used node
	        moveToFront(current);
	        return map.get(key).val;
	    }
	    
	    public void set(int key, int value) {
	        if (get(key) != -1) {
	            map.get(key).val = value;
	            return;
	        }
	        
	        if (map.size() == capacity) {
	        	//如果缓存已满，删掉链表尾部的节点，给即将添加的新节点腾位子
	            map.remove(tail.key);
	            tail.prev.next = null;
	            tail = tail.prev;
	        }
	        //添加新的节点在链表的头部
	        ListNode newNode = new ListNode(key, value);
	        map.put(key, newNode);
	        moveToFront(newNode);
	    }
	    
	    private void moveToFront(ListNode node) {
	        if (dummy.next == null) {
	            dummy.next = node;
	            node.prev = dummy;
	            tail = node;
	        } else {
	            node.next = dummy.next;
	            node.next.prev = node;
	            dummy.next = node;
	            node.prev = dummy;
	        }
	    }
    
    //实现 LRU Cache再判断啥时候miss就好了，返回miss数。建议看看用LinkedHashMap实现lru cache, 代码很简洁。
    
    public int cacheMiss(int[] array, int size) {
    	if(array ==null || array.length == 0) return 0;
    	int cnt=0;
    	List<Integer> list=new LinkedList<Integer>();
    	for(int i=0; i<array.length; i++) {
    		if(list.contains(array[i])) {
    			list.remove(new Integer(array[i]));
    		}else {
    			cnt++;
    			if(list.size() == size) {
    				list.remove(0);
    			}
    		}
    		list.add(array[i]);
    	}
    	return cnt;
    }
    
}
