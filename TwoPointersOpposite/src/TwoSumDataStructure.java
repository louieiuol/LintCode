import java.util.HashMap;
import java.util.Iterator;

public class TwoSumDataStructure {
    HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
    /*
     * @param number: An integer
     * @return: nothing
     */
    public void add(int number) {
        // write your code here
        if(this.map.isEmpty()) {
    		this.map.put(number, 1);
    	}else if(map.containsKey(number)) {
    		this.map.replace(number, this.map.get(number), this.map.get(number)+1);
    	}else {
    		this.map.put(number, 1);
    	}
    }

    /*
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        // write your code here
        Iterator<Integer> it=this.map.keySet().iterator();
        while(it.hasNext()){
            long num=value-it.next();
            if(this.map.containsKey((int)num)){
                if(num*2 == value){
                    if(checktwice(value/2)) {
                        return true;
                    }
                }else{
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean checktwice(int value){
        if(this.map.containsKey(value)){
            if(this.map.get(value) >= 2){
                return true;
            }    
        }
        return false;
       
    }
}
