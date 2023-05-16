import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//137. Clone Graph
//中文English
//Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors. Nodes are labeled uniquely.
//
//You need to return a deep copied graph, which has the same structure as the original graph, and any changes to the new graph will not have any effect on the original graph.
//
//Example
//Example1
//
//Input:
//{1,2,4#2,1,4#4,1,2}
//Output:
//{1,2,4#2,1,4#4,1,2}
//Explanation:
//1------2
// \     |
//  \    |
//   \   |
//    \  |
//      4
//Clarification
//How we serialize an undirected graph: http://www.lintcode.com/help/graph/
//
//Notice
//You need return the node with the same label as the input node.



public class CloneGraph {
    class UndirectedGraphNode {
        int label;
        ArrayList<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    }


    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        UndirectedGraphNode root=null;
        if(node!=null) {
            ArrayList<UndirectedGraphNode> lst=BFStraversal(node);
            //using BFS to find all the vertexes in the graph
            HashMap<UndirectedGraphNode, UndirectedGraphNode> map=new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
            //create a HashMap to store new-old relations
            for(UndirectedGraphNode nodes: lst) {
                if(nodes!=null) {
                    map.put(nodes, new UndirectedGraphNode(nodes.label));
                }
            }
            //copy all the vertexes value in old graph to new graph and store into HashMap
            for(UndirectedGraphNode key: map.keySet()) {
                for(UndirectedGraphNode neighbor: key.neighbors) {
                    map.get(key).neighbors.add(map.get(neighbor));
                    //use map's original key to find key and neighbors
                    //use map's get original key to find clone key
                    //clone node neighbors add clone node
                    //add dependency inside not link with original node
                }
            }
            //copy all the edges in old graph to new graph and store into HashMap
            root=map.get(node);
        }
        return root;
    }


    private ArrayList<UndirectedGraphNode> BFStraversal(UndirectedGraphNode node) {
        // TODO Auto-generated method stub
        Queue<UndirectedGraphNode> queue=new LinkedList<UndirectedGraphNode>();
        //using queue to perform BFS traversal\
        ArrayList<UndirectedGraphNode> lst=new ArrayList<UndirectedGraphNode>();
        //using ArrayList to store all vertexes
        queue.add(node);
        lst.add(node);
        //put two elements into our queue and ArrayList
        while(!queue.isEmpty()) {
            int size=queue.size();
            for(int i=0; i<size; i++){
                UndirectedGraphNode curr=queue.poll();
                if(curr!=null) {
                    for(UndirectedGraphNode edge: curr.neighbors) {
                        if(!lst.contains(edge)) {
                            //check if list has contains this edge
                            lst.add(edge);
                            queue.add(edge);
                        }
                    }
                }
            }
        }
        return lst;
    }
}
