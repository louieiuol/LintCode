import java.util.Arrays;

//945. Task Scheduler
//中文English
//Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
//
//However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
//
//You need to return the least number of intervals the CPU will take to finish all the given tasks.
//
//Example
//Example1
//
//Input: tasks = ['A','A','A','B','B','B'], n = 2
//Output: 8
//Explanation:
//A -> B -> idle -> A -> B -> idle -> A -> B.
//Example2
//
//Input: tasks = ['A','A','A','B','B','B'], n = 1
//Output: 6
//Explanation:
//A -> B -> A -> B -> A -> B.
//Notice
//The number of tasks is in the range [1, 10000].
//The integer n is in the range [0, 100].

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
    	if(tasks == null || tasks.length == 0 ) return -1;
    	int[] hash=new int[26];
    	//用26位的数组作为hash 存储task里面每个字母出现的次数
    	for(int i=0; i<tasks.length; i++) {
    		hash[tasks[i]-'A']++;
    	}
    	//按照字母顺序对应到26个位置放入 进行累加
    	Arrays.sort(hash);
    	//按照元素出现的个数有小到大进行排序
    	int num=25;
    	while(num >= 0 && hash[num]==hash[25]) {
    		num--;
    	}
    	//找到最多出现的元素出现的的次数
    	//25-num
    	return Math.max(tasks.length , (hash[25]-1)*(n+1)+25-num);
    	//应用公式
    	//（最多任务数-1）*（n + 1） + （相同最多任务的任务个数）和 任务总数量中的最大值
    	// 公式算出的值可能会比数组的长度小 此时要取数组的长度
    }
}
