
/*613. High Five

There are two properties in the node student id and scores, to ensure that each student will have at least 5 points, find the average of 5 highest scores for each person.

Example
Example 1:

Input: 
[[1,91],[1,92],[2,93],[2,99],[2,98],[2,97],[1,60],[1,58],[2,100],[1,61]]
Output:
1: 72.40
2: 97.40

Example 2:

Input:
[[1,90],[1,90],[1,90],[1,90],[1,90],[1,90]]
Output: 
1: 90.00
*/
import java.util.*;
public class HighFive {
	public class Record {
		     public int id, score;
		     public Record(int id, int score){
		          this.id = id;
		          this.score = score;
		      }
		  } 
    public Map<Integer, Double> highFive(Record[] results) {
        // Write your code here
        Map<Integer, Double> res=new HashMap<>();
        if(results == null) return res;
        if(results.length == 0) return res;
        HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
        for(int i=0; i<results.length;i++){
            Record rc=results[i];
            if(!map.containsKey(rc.id)){
                map.put(rc.id, new ArrayList<>());
                map.get(rc.id).add(rc.score);
            }else {
                if(map.get(rc.id).size()<5){
                    map.get(rc.id).add(rc.score);
                }else{
                    map.get(rc.id).add(rc.score);
                    map.get(rc.id).remove(findMin(map.get(rc.id)));
                }
            }
        }    
        for(int key: map.keySet()){
            res.put(key, calculateAve(map.get(key)));
        }
        return res;
    }
    private int findMin(ArrayList<Integer> lst){
        if(lst.size()>0){
            int min=lst.get(0);
            for(int i=0; i<lst.size(); i++){
                if(lst.get(i)<min){
                    min=lst.get(i);
                }
            }
            return lst.indexOf(min);
        }
        return 0;
    }
    private double calculateAve(ArrayList<Integer> lst){
        if(lst.size()>0){
            double sum=0;
            for(int i=0; i<lst.size(); i++){
                sum+=lst.get(i);
            }
            return sum/lst.size();
        }
        return 0;
    }
	public Map<Integer, Double> highFive2(Record[] results) {
		Map<Integer, Double> result=new HashMap<>();
		if(results==null) return result;
		if(results.length == 0) return result;
		HashMap<Integer, PriorityQueue<Record>> map=new HashMap<>();
		for(Record r: results){
			if(!map.containsKey(r.id)){
				PriorityQueue<Record> qp=new PriorityQueue<>(5, new Comparator<Record>() {
					public int compare(Record r1, Record r2) {
						return r1.score-r2.score;
					}
				});
				map.put(r.id, qp);
			}
			map.get(r.id).add(r);
			if(map.get(r.id).size() >5){
				map.get(r.id).poll();
			}
		}
		for(Map.Entry<Integer, PriorityQueue<Record>> entry: map.entrySet()){
			double avg=0;
			double size=entry.getValue().size();
			while(!entry.getValue().isEmpty()){
				avg+=entry.getValue().poll().score;
			}
			avg /= size;
			result.put(entry.getKey(), avg);
		}
		return result;
	}
}
