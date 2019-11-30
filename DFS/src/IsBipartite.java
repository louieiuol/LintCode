/*1031. Is Graph Bipartite?
中文English
Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists. Each node is an integer between 0 and graph.length - 1.

There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example
Example 1:

Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
  The graph looks like this:
  0----1
  |    |
  |    |
  3----2
  We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:

Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
  The graph looks like this:
  0----1
  | \  |
  |  \ |
  3----2
  We cannot find a way to divide the set of nodes into two independent subsets.
Notice
graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].*/



public class IsBipartite {
    public boolean isBipartite(int[][] graph) {
        // Write your code here
        if(graph == null || graph.length == 0) return true;
        int[] colors=new int[graph.length];
        //0表示未染色 1和-1表示已经染色
        for(int i=0; i<colors.length; i++){
            //对每个元素进行一次遍历 
        	if(colors[i] == 0){
            	//如果未被染色 我们试图去染固定一种颜色
                boolean success=dfs(graph, i, colors, 1);
                //染色失败我们返回false 染色成功继续染色
                if(!success){
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean dfs(int[][] graph, int index, int[] colors, int color){
        if(colors[index] != 0){
        	// 如果经过的点 已经被染色 我们对比是否染上对应的颜色 
            return colors[index] == color;
        }
        //对该点进行染色 
        colors[index]=color;
        for(int vertex: graph[index]){
        	//遍历可以经过的所有的点 染上相反的颜色 
            boolean flag=dfs(graph, vertex, colors, -color);
            //染色失败 返回false
            if(!flag){
                return false;
            }
        }
        return true;
    }
}
