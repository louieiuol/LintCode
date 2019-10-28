import java.util.Arrays;
import java.util.HashMap;

public class TwoSumTarget {
	
    public int[] twoSum(int[] numbers, int target) {
    // write your code here
    int[] result={-1,-1};
	boolean flag=false; 
	int[] copy=new int[numbers.length];
	for(int i=0; i< numbers.length; i++) {
 	   copy[i]=numbers[i];
    }
	
    Arrays.sort(numbers);
    if(numbers!=null && numbers.length >=2){
    int left=0, right=numbers.length-1;
        while(left < right){
            if((numbers[left]+numbers[right])>target){
                right--;
            }else if((numbers[left]+numbers[right])<target){
                left++;
            }else{
                left=numbers[left];
                right=numbers[right];
                flag=true;
                break;
            }
        }
        
       if(flag){
            for(int i=0; i < numbers.length; i++) {
    	        if(copy[i] == left) {
    		        result[0]=i;
    		        break;
    	        }
            }
        
            for(int i=0; i < numbers.length; i++) { 
    	        if(copy[i] == right) {
    	            if(i == result[0]){
    	                continue;
    	            }else{
    	                result[1]=i;
    	                break;
    	            }
    	        }
            }
       
            if(result[0]> result[1]) {
       	        int tmp=result[0];
       	        result[0]=result[1];
       	        result[1]=tmp;
            }
        }
    }
    return result;
}
    
    public int[] twoSumHash(int[] numbers, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.get(numbers[i]) != null) {
                int[] result = {map.get(numbers[i]), i};
                return result;
            }
            map.put(target - numbers[i], i);
        }
        int[] result = {};
        return result;
    }
}
