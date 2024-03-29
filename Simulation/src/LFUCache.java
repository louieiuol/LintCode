
//24. LFU Cache
//中文English
//LFU (Least Frequently Used) is a famous cache eviction algorithm.
//For a cache with capacity k, if the cache is full and need to evict a key in it, 
//the key with the lease frequently used will be kicked out.
//Implement set and get method for LFU cache.
//
//Example
//Input:
//LFUCache(3)
//set(2,2)
//set(1,1)
//get(2)
//get(1)
//get(2)
//set(3,3)
//set(4,4)
//get(3)
//get(2)
//get(1)
//get(4)
//
//Output:
//2
//1
//2
//-1
//2
//1
//4
import java.util.*;

public class LFUCache {
	HashMap<Integer, Integer> valueMap; // store key-value relation
	HashMap<Integer, Integer> countMap; // store key-frequency relation
	HashMap<Integer, ArrayList<Integer>> freqMap;//store frequency-list of key relation
	int capacity; 
	int minFreq; // store minimum frequency
	
    /*
    * @param capacity: An integer
    */public LFUCache(int capacity) {
        // do intialization if necessary
    	this.capacity=capacity;
    	this.valueMap=new HashMap<>();
    	this.countMap=new HashMap<>();
    	this.freqMap=new HashMap<>();
    	freqMap.put(1, new ArrayList<>());
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // write your code here
    	if(valueMap.containsKey(key)) {
    		valueMap.put(key, value);
    		//加入value Map
    		get(key);
    		//调用 get() 方法刷新 frequency 
    		return;
    	}
    	if(valueMap.size() >= capacity) {
    		//超过capacity的时候 
    		int lowFreqKey=freqMap.get(minFreq).get(0);
    		//找到最低频率对应的key
    		freqMap.get(minFreq).remove(Integer.valueOf(lowFreqKey));
    		//freq map对应的最低freq的list中除掉这个key
    		valueMap.remove(lowFreqKey);
    		//value map移除这个key
    	}
    	valueMap.put(key, value);
    	//value Map 加入key value
    	minFreq=1;
    	//把最低频率设为1
    	countMap.put(key, 1);
    	//countMap 里添加 key对应的frequency 为1
    	freqMap.get(1).add(key);
    	//freqMap 在1的列表里添加 key
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
    	if(!valueMap.containsKey(key)) return -1;
    	int freq=countMap.get(key); //get current key's frequency
    	freqMap.get(freq).remove(Integer.valueOf(key)); //remove current key from frequency list
    	if(freq == minFreq && freqMap.get(freq).size() == 0) minFreq++; //如果到达最低频率 并且不存在最低的key minFreq++
    	countMap.put(key, ++freq); 
    	//countMap 记录key和++频率
    	freqMap.putIfAbsent(freq, new ArrayList<>());
    	//新的频率放入新的列表
    	freqMap.get(freq).add(key);
    	//找到对应列表加入key
    	return valueMap.get(key);
    }
}
