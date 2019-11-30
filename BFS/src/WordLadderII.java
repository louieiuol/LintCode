/*121. Word Ladder II
中文English
Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, output sequence in dictionary order.
Transformation rule such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
Example
Example 1:

Input：start = "a"，end = "c"，dict =["a","b","c"]
Output：[["a","c"]]
Explanation：
"a"->"c"
Example 2:

Input：start ="hit"，end = "cog"，dict =["hot","dot","dog","lot","log"]
Output：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation：
1."hit"->"hot"->"dot"->"dog"->"cog"
2."hit"->"hot"->"lot"->"log"->"cog"
The dictionary order of the first sequence is less than that of the second.
Notice
All words have the same length.
All words contain only lowercase alphabetic characters.
At least one solution exists.
*/

import java.util.*;

public class WordLadderII {
    List<List<String>> result=new ArrayList<>();
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        HashMap<String, List<String>> map=new HashMap<>();
        HashMap<String, Integer> distMap=new HashMap<>();
        dict.add(start);
        dict.add(end);
        bfs(end, start, dict, map, distMap);
        List<String> lst=new ArrayList<>();
        lst.add(start);
        dfs(start, end, map, distMap, lst);
        return result;
    }
    
    private void bfs(String start, String end, Set<String> dict, HashMap<String, List<String>> map, HashMap<String, Integer> distMap){
        Queue<String> queue=new LinkedList<>();
        queue.offer(start);
        distMap.put(start, 0);
        for(String str: dict){
            map.put(str, new ArrayList<>());
        }
        while(!queue.isEmpty()){
            String curr=queue.poll();
            List<String> nextList=getNextList(curr, dict);
            map.get(curr).addAll(nextList);
            for(String next: nextList){
                if(distMap.containsKey(next)){
                    continue;
                }
                distMap.put(next, distMap.get(curr)+1);
                queue.offer(next);
            }
        }
    }
    
    private List<String> getNextList(String curr, Set<String> dict){
        List<String> nextList=new ArrayList<>();
        for(int i=0; i<curr.length(); i++){
            char[] currArray=curr.toCharArray();
            for(char c='a'; c<='z'; c++){
                if(currArray[i] == c){
                    continue;
                }
                currArray[i]=c;
                String temp=new String(currArray);
                if(dict.contains(temp)){
                    nextList.add(temp);
                }
            }
        }
        return nextList;
    }
    
    private void dfs(String start, String end, HashMap<String, List<String>> map, HashMap<String, Integer> distMap, List<String> lst){
        if(start.equals(end)){
            result.add(new ArrayList<>(lst));
            return;
        }
        for(String next: map.get(start)){
            if(distMap.containsKey(next) && distMap.get(start) == distMap.get(next)+1){
                lst.add(next);
                dfs(next, end, map, distMap, lst);
                lst.remove(lst.size()-1);
            }
        }
    }
}
