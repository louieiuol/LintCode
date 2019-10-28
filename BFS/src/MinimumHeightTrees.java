//1298. Minimum Height Trees
//中文English
//For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.
//
//Format
//The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
//
//You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0]and thus will not appear together in edges.
//
//Example
//Example1
//
//Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
//Output: [1]
//Explanation:
//        0
//        |
//        1
//       / \
//      2   3
//Example2
//
//Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
//Output: [3, 4]
//Explanation:
//     0  1  2
//      \ | /
//        3
//        |
//        4
//        |
//        5
//Notice
//(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
//
//(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.



import java.util.*;

/*这道题可以用拓扑排序的思想来做，虽然不是有序图，但是当节点入度为1时，必然为叶子节点。
由此可见当我们用BFS从叶子节点一层一层搜索下去时，最后一组的节点就是最短高度根节点。
我们的目标是找到那个根节点。

创建图
统计每个节点的入度
把所有入度为1的节点放入queue （叶子节点）
从每个叶子节点找到他们各自的根节点，把他们的入度减一
当根节点入度为1时， 放入queue
最后的一组queue就是最后的根节点， 也就是答案
*/
public class MinimumHeightTrees {
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //看到题目中给出的 nodes 是 0 to n-1这种 要联想到topological sorting 
        List<Integer> result=new ArrayList<>();
        if(n == 0 || edges.length == 0){
            result.add(0);
            return result;
        }
        HashMap<Integer, List<Integer>> map=new HashMap<>();
        HashMap<Integer, Integer> indegree=new HashMap<>();
        for(int[] edge: edges){
            map.putIfAbsent(edge[0], new ArrayList<>());
            map.putIfAbsent(edge[1], new ArrayList<>());
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
            indegree.put(edge[0], indegree.getOrDefault(edge[0], 0)+1);
            indegree.put(edge[1], indegree.getOrDefault(edge[1], 0)+1);
        }
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0; i<n ; i++){
            if(indegree.get(i) == 1){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            result.clear();
            int size=queue.size();
            for(int i=0; i<size; i++){
                int curr=queue.poll();
                result.add(curr);
                for(int next: map.get(curr)){
                    indegree.put(next, indegree.get(next)-1);
                    if(indegree.get(next) == 1){
                        queue.offer(next);
                    }
                }
            }
        }
        return result;
    }
}
