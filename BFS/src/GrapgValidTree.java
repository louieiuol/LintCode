//178. Graph Valid Tree
//中文English
//Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
//
//Example
//Example 1:
//
//Input: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
//Output: true.
//Example 2:
//
//Input: n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
//Output: false.
//Notice
//You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
import java.util.*;


//判断图是否为树的2大要点
//1. n 个 vertex 对应 n-1 条边
//2. 所有点必须联通
public class GrapgValidTree {
    public boolean validTree(int n, int[][] edges) {
        // write your code here
        if(n == 0 || edges == null) return false;
        if(n-1 != edges.length) return false;
        //检查是否 n vertex == n-1 edges
        HashMap<Integer, HashSet<Integer>> map=buildMap(n, edges);
        //根据边建立双向图
        Queue<Integer> queue=new LinkedList<>();
        //图的连通性问题 bfs
        HashSet<Integer> set=new HashSet<>();
        queue.offer(0);
        set.add(0);
        //0作为图的起点
        while(!queue.isEmpty()){
            int curr=queue.poll();
            for(int next: map.get(curr)){
                if(set.contains(next)){
                    continue;
                }
                queue.offer(next);
                set.add(next);
            }
        }
        return set.size() == n;
        //返回是否每个边都被能访问
    }

    private HashMap<Integer, HashSet<Integer>> buildMap(int n, int[][] edges){
        HashMap<Integer, HashSet<Integer>> map=new HashMap<>();
        for(int i=0; i<n; i++){
            map.put(i, new HashSet<>());
        }
        for(int[] edge: edges){
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        return map;
    }
}
