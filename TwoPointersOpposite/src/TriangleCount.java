import java.util.Arrays;
public class TriangleCount {
    public int triangleCount(int[] S) {
        // write your code here
        //set a counter 
        int counter=0;
        //check length
        if(S!=null && S.length >=3 ){
            Arrays.sort(S);
            //sort array
            for(int c=S.length-1; c >1 ; c--){
            //the sum of two smallest side is greater than the third
                int a=0, b=c-1;
                // a <= b <= c
                while(a < b){
                    if(S[a] + S[b] <= S[c]){
                            a++;
                    }else{
                        counter += b-a;
                        b--;
                    }
                }
            }
        }
        return counter;
    }
}
