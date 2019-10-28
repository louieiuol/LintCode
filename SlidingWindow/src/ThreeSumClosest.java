import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] numbers, int target) {
        // write your code here
        int sum=-1;
        if( numbers != null && numbers.length >= 3 ){
            Arrays.sort(numbers);
            int min=32677;
            for(int a=0; a<numbers.length-2; a++){
                int b=a+1, c=numbers.length-1;
                while(b < c){
                    int add= numbers[a]+numbers[b]+numbers[c];
                    if(add == target){
                        sum = target;
                        break;
                    }else if(add > target){
                        if(Math.abs(add-target) < min){
                            min= Math.abs(add-target);
                            sum= add;
                        }
                        c--;
                    }else{
                        if(Math.abs(add-target) < min){
                            min= Math.abs(add-target);
                            sum= add;
                        }
                        b++;
                    }
                }
            }
        }
        return sum;
    }
}
