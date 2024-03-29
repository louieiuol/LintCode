//526. Load Balancer
//中文English
//Implement a load balancer for web servers. It provide the following functionality:
//
//Add a new server to the cluster => add(server_id).
//Remove a bad server from the cluster => remove(server_id).
//Pick a server in the cluster randomly with equal probability => pick().
//At beginning, the cluster is empty. When pick() is called you need to randomly return a server_id in the cluster.
//
//Example
//Example 1:
//
//Input:
//  add(1)
//  add(2)
//  add(3)
//  pick()
//  pick()
//  pick()
//  pick()
//  remove(1)
//  pick()
//  pick()
//  pick()
//Output:
//  1
//  2
//  1
//  3
//  2
//  3
//  3
//Explanation: The return value of pick() is random, it can be either 2 3 3 1 3 2 2 or other.

import java.util.*;
public class LoadBalancer {
	// do intialization if necessary
	private int n;
	private HashMap<Integer, Integer> map;
	//实现O(1)的时间内查找 用hash来实现 key是 server的id value是对应的index
	private List<Integer> lst;
	//因为需要pick random 动态的array是比较方便的
	public LoadBalancer() {
		n=0; //用于记录数组的长度
		map=new HashMap<>();
		lst=new ArrayList<>();
	}
	
	
    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
	public void add(int server_id) {
		if(!map.containsKey(server_id)) {
			//如果不存在才需要添加 hash查找是O（1）
			map.put(server_id, n);
			//map存server的id 以及对应的index
			lst.add(server_id);
			n++;
		}
	}
    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
	public void remove(int server_id) {
		if(map.containsKey(server_id)) {
			//如果存在才需要删除 hash查找是O（1）
			int index=map.get(server_id);
			//找到对应server_id的所在array的位置
			lst.set(index, lst.get(n-1));
			//删除想要实现O（1） 我们只能找到后与末尾元素交换 然后删除末尾			
			//lst的index的位置赋值末尾的数值
			map.put(lst.get(n-1), index);
			//map里也更新末尾的数现在所在的位置
			//map重新放入末尾 对应位置是要删除这个数的位置
			lst.remove(n-1);
			map.remove(server_id);
			n--;
		}
	}
    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
	public int pick() {
		return lst.get((int) Math.random()*(n-1));
	}
}


