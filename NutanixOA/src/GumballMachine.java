//3 个 gum ball machine, 容量 max = 1000; 每个machine 里的gum ball 都要是一种颜色.
//一个开车每天来refill, 只带一种颜色 --->  List<String> a    eg. [red, green, blue, red, white]
//如果带的颜色和machine 有match, 填满; 没有的match的话(就是三个machine都不是卡车带的颜色), 倒掉里面gum ball数量最少的, 然后填满;
//每天 每个  machine 里的 gum ball 卖出 10 个. 每个 1 cent,
//给一个List<String> a, 求 total waste (Double)
//eg.
//[red, green, blue, red, white]
//
//[Red 990]
//[Red 980] [Green 990]
//[Red 970] [Green 980] [blue 990]
//[Red 990] [Green 970] [blue 980]
//[Red 980] [white 990] [blue 970]  ---> totalwaste = 9.7
import java.util.*;

public class GumballMachine {
	public double gumballMachine(List<String> list) {
		Set<String> set=new HashSet<>(); //存是否来过
		Map<String, Integer> map=new HashMap<>(); // 存当前颜色 和第X天装满
		PriorityQueue<String> q = new PriorityQueue<>((s1, s2) -> map.get(s1)- map.get(s2));   //minheap
		double totalwaste=0;
		for (int i = 0; i < list.size(); i++) {
		    map.put(list.get(i), i);
			if(set.contains(list.get(i))) {
				q.remove(list.get(i));
				q.offer(list.get(i));
			}else{
		        q.offer(list.get(i));
		        set.add(list.get(i));
		    }
		    if (q.size() > 3) {
		      String s = q.poll();
		      double left=1000-(i-map.get(s))*10;
		      totalwaste += left / 100;
		      set.remove(list.get(i));
		    }
		}

		return totalwaste;
	}
}
