//605. Sequence Reconstruction
//中文English
//Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 10^4. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
//
//Example
//Example 1:
//
//Input: false
//Explanation:
//[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
//Example 2:
//
//Input: org = [1,2,3], seqs = [[1,2]]
//Output: false
//Explanation:
//The reconstructed sequence can only be [1,2].
//Example 3:
//
//Input: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
//Output: true
//Explanation:
//The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
//Example 4:
//
//Input:org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
//Output:true
import java.util.*;

public class SequenceReconstruction {
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        //本题关键是要找到seqs 和 org之间的关系是 seq中有很多关系 我们对每个点来说 从每个点出发的下一步 可以形成点到点的关系图
        //同理每一个点被入 时候存在一个入度关系 可以对此建立入度图 来进行拓扑排序 看看seq 所有可能的排序 形成的是不是和org 相同且唯一
        if(org == null || seqs == null) return false;
        HashMap<Integer, List<Integer>> graph=new HashMap<>();
        HashMap<Integer, Integer> indegree=new HashMap<>();
        //类似topological sorting类题目 需要建立关系图和入度表
        for(int i=0; i<seqs.length; i++){
            for(int j=0; j<seqs[i].length; j++){
                graph.putIfAbsent(seqs[i][j],new ArrayList<Integer>());
                //我们需要对每个点能到的点保存图 所以如果是新的点 我们需要建立对于能到点的列表
                //对于重复点 我们稍后处理他们的能到的点的list
                indegree.putIfAbsent(seqs[i][j],0);
                //刚刚进入的点 我们把它被入的度记为0
                if(j>0){
                    //如果不是每一列中第一个进入的 那么 就存在被上一个点入 我们需要增加他们的入度
                    //对应的图上的理解就是 1 -> 2 形成 一种点到点的关系 或者说一种顺序
                    graph.get(seqs[i][j-1]).add(seqs[i][j]);
                    //把上一步能走到的地方加入当前点
                    indegree.put(seqs[i][j], indegree.get(seqs[i][j])+1);
                    //把当前点对应的入度+1
                }
            }
        }
        //检查图中pair的个数是否对应org的长度
        //为什么呢？ 如果入度关系pair超过org 则说明不为一种关系 如果低于org 则说明构不成一种关系
        if(indegree.size() != org.length ) return false;
        Queue<Integer> queue=new LinkedList<>();
        int index=0; //把当前对应的org的位置数的index记为0

        //topological sorting 原则
        //把所有对应入度为0的点加入起点
        for(int key: indegree.keySet()){
            if(indegree.get(key) == 0){
                queue.offer(key);
            }
        }
        while(!queue.isEmpty()){
            int size=queue.size();
            //如果queue中包含不止一种起点 返回false
            //因为我们只有一种org顺序 不能有多种路径可以走
            if(size != 1) return false;
            int curr=queue.poll();
            if(org[index++] != curr) return false;
            //每次所走的路径必须和 org 一样
            for(int next: graph.get(curr)){
                //获取curr 表中能走到的点
                indegree.put(next, indegree.get(next)-1);
                //更新indegree 中的入度图
                if(indegree.get(next) == 0){
                    //当入度为0时加入下一个要去的点
                    queue.offer(next);
                }
            }
        }
        return index == org.length;
    }
}
