/*1020. All Paths From Source to Target
Given a directed, acyclic graph of N nodes. Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows: the nodes are 0, 1, ..., graph.length - 1. graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Notice
The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.
*/
import java.util.*;
public class allPath {
        // Write your code here
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            // Write your code here
            if(graph== null || graph.length ==0 || graph[0].length ==0) return res;
            HashMap<Integer, ArrayList<Integer>> map=new HashMap<>();
            for(int i=0; i<graph.length; i++){
                map.put(i, new ArrayList<Integer>());
                for( int j=0; j<graph[i].length; j++){
                    map.get(i).add(graph[i][j]);
                }
            }
            int dest=graph.length-1;
            dfs(map,0,dest,new ArrayList<Integer>());
            return res;
        }
        
        private void dfs(HashMap<Integer, ArrayList<Integer>> map, int source,
        int dest, ArrayList<Integer> path){
            if(source == 0){
                path.add(0);
            }
            if(source == dest){
                res.add(new ArrayList<>(path));
                return;
            }
            if(map.getOrDefault(source, new ArrayList<>()).size() == 0){
                return;
            }
            for(int next: map.get(source)){
                path.add(next);
                dfs(map, next, dest, path);
                path.remove(path.size()-1);
            }
        }
}
