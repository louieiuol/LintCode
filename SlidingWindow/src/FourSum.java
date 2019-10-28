import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
	public List<List<Integer>> fourSum(int[] numbers, int target) {
    List<List<Integer>> result=new ArrayList<List<Integer>>();
    if(numbers != null && numbers.length >= 4){
        Arrays.sort(numbers);
        for(int a=0; a < numbers.length-3; a++){
            for(int b=a+1; b<numbers.length-2 ; b++){
                int c=b+1, d=numbers.length-1;
                while(c < d){
                    int sum= numbers[a]+numbers[b]+numbers[c]+numbers[d];
                    if(sum == target){
                        List<Integer> lst=new ArrayList<Integer>();
                        lst.add(numbers[a]);
                        lst.add(numbers[b]);
                        lst.add(numbers[c]);
                        lst.add(numbers[d]);
                        if(!result.contains(lst)){
                            result.add(lst);
                        }
                        c++;
                        d--;
                    }else if(sum < target){
                        c++;
                    }else{
                        d--;
                    }
                }
            }
        }
    }
    return result;
	}
}
