import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
	public List<List<Integer>> threeSum(int[] numbers) {
        List<List<Integer>> result=new ArrayList<List<Integer>>();
        // write your code here
        if(numbers!=null && numbers.length>=3){
        	Arrays.sort(numbers);
            for(int a=0; a< numbers.length; a++){
            	//keep a <=b <= c in order
                int b=a+1, c=numbers.length-1;
                while(b<c){
                	//two pointer method
                    if( numbers[b]+numbers[c] < -numbers[a]){
                        b++; 
                    }else if (numbers[b]+ numbers[c] > -numbers[a]){
                        c--;
                    }else{
                        List<Integer> lst=new ArrayList<Integer>();
                        lst.add(numbers[a]);
                        lst.add(numbers[b]);
                        lst.add(numbers[c]);
                        if(!result.contains(lst)) {
                        	result.add(lst);
                        }
                        b++;
                        c--;
                    }    
                }
            }
        }
        return result;
    }
}
