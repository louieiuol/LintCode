//615. Course Schedule
//中文English
//There are a total of n courses you have to take, labeled from 0 to n - 1.
//
//Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
//Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
//
//Example
//Example 1:
//
//Input: n = 2, prerequisites = [[1,0]]
//Output: true
//Example 2:
//
//Input: n = 2, prerequisites = [[1,0],[0,1]]
//Output: false

import java.util.*;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || numCourses <=0) return true;
        HashMap<Integer, List<Integer>> map=new HashMap<>();
        //创建从路径图：某个点指向能到的其他点的的有向图
        for(int i=0; i<numCourses; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        //对于所有可能要走的点 都建立表 如果是开始点 （不需要任何依赖的 能到达的list就为空）
        for(int i=0;i<prerequisites.length;i++) {
            map.get(prerequisites[i][1]).add(prerequisites[i][0]);
            //遍历要求表 添加关系 注意有可能一个课被多个课依赖
        }

        int[] indegree=new int[numCourses];
        //建立进入点的个数的表 index是被入点 value是个数
        //要确定层数 就要确定有多少个点能进入他 indegree只记录进入的个数
        //因为从0到n-1都被用到 可以选择用数组替代哈希 如果是字符是不行的

        for(int i=0; i<prerequisites.length; i++) {
            //找到所有被进入的点
            indegree[prerequisites[i][0]]++;
            //找到一次就+1 因为一个点可以被多个点进入
        }

        List<Integer> lst=new ArrayList<>();
        //建立top sort表
        Queue<Integer> queue=new LinkedList<>();
        //用queue实现BFS的层级遍历 0层是我们的起点 1层是下一层 ...
        //因为有可能是多个起点 需要寻找top sort 需要把能走到的点按层排序

        for(int i=0;i<numCourses; i++) {
            //初始化queue和list 把层级为0的放入初始队列
            //加入队列的是i不是个数
            if(indegree[i]== 0){
                queue.offer(i);
                lst.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int size=queue.size();
            for(int i=0;i<size;i++) {
                int curr=queue.poll();
                //从路径图中找从当前起点能通往的下一个点
                for(int next: map.get(curr)) {
                    indegree[next]--;
                    //每找到一个点 对应能走到的点的所有能走到的方法个数都-1 少一种走法了
                    //不用check if(!lst.contains(next)) 因为可能存在环状依赖 会形成永远走不到某点 导致queue永不为空 无限循环
                    //其实可以允许里面有环 但是不允许整个都是环 或者说不允许起点成为环
                    if(indegree[next] == 0) {
                        //如果到该走法需要的步数为0 那么加入到下一步
                        //可以理解成已知区域到未知区域的距离为0
                        queue.offer(next);
                        lst.add(next);
                        //注意next是点 而indegree[next]是到这个点的方法个数
                    }
                }
            }
        }

        return lst.size() == numCourses;
        //判断最后的top sort有没有走完所有点
    }
}
