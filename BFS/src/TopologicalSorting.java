//127. Topological Sorting
//中文English
//Given an directed graph, a topological order of the graph nodes is defined as follow:
//
//For each directed edge A -> B in graph, A must before B in the order list.
//The first node in the order can be any node in the graph with no nodes direct to it.
//Find any topological order for the given graph.
//
//Example
//For graph as follow:
//
//picture
//
//The topological order can be:
//
//[0, 1, 2, 3, 4, 5]
//[0, 2, 3, 1, 5, 4]

//Challenge
//Can you do it in both BFS and DFS?
//
//Clarification
//Learn more about representation of graphs
//
//Notice
//You can assume that there is at least one topological order in the graph.




import java.util.*;

public class TopologicalSorting {
    class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) {
            label = x; neighbors = new ArrayList<DirectedGraphNode>();
        }
    }
    /*
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> result=new ArrayList<DirectedGraphNode>();
        if(graph!=null) {
            if(graph.size() >0) {
                HashMap<DirectedGraphNode, Integer> map=new HashMap<DirectedGraphNode, Integer>();
                for(DirectedGraphNode node: graph) {
                    for(DirectedGraphNode neighbor: node.neighbors) {
                        if(map.containsKey(neighbor)) {
                            map.put(neighbor, map.get(neighbor)+1);
                        }else {
                            map.put(neighbor,1);
                        }
                    }
                }

                Queue<DirectedGraphNode> queue=new LinkedList<DirectedGraphNode>();
                for(DirectedGraphNode node: graph) {
                    if(!map.containsKey(node)) {
                        queue.add(node);
                        result.add(node);
                    }
                }

                while(!queue.isEmpty()) {
                    int size=queue.size();
                    for(int i=0; i< size;i++) {
                        DirectedGraphNode current=queue.poll();
                        for(DirectedGraphNode neighbor: current.neighbors) {
                            map.put(neighbor, map.get(neighbor)-1);
                            if(map.get(neighbor) == 0) {
                                queue.add(neighbor);
                                result.add(neighbor);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
