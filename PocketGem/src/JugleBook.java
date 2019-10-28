import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * coding2 是JugleBook，给一个数组代表捕食关系。index代表物种，每个数字代表这个物种的捕食者，可以越过多级捕食，问最少分为多少组，每组的生物不会互相捕食。总的来说就是求图里面的最长路径
*/
public class JugleBook {
	public int juglebook(int[] arr) {
	Queue<Integer> q = new LinkedList<Integer>();
	boolean[] visited = new boolean[arr.length];
	int maxDepth = 0;
	for(int i = 0; i < arr.length; i++) {
	if(!visited[i]) {
	q.offer(i);
	}
	int depth = 1;
	while(!q.isEmpty()) {
	int cur = q.poll();
	visited[cur] = true;
	if(arr[cur] != -1) {
	q.offer(arr[cur]);
	depth++;
	}
	}
	if(depth > maxDepth) {
	maxDepth = depth;
	}
	}
	return maxDepth;
	}
	
}
