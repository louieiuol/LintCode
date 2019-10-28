

public class InterleavingPN {
    public void rerange(int[] A) {
        // write your code here
        if(A!= null && A.length >=2){
            int left=0, right=A.length-1;
            while(left< right){
                while(A[left] < 0){
                    left++;
                }
                
                while(A[right] > 0){
                    right--;
                }
                
                if(left<right){
                    int tmp=A[left];
                    A[left]=A[right];
                    A[right]=tmp;
                    left++;
                    right--;
                }
            }
            
            if(left > A.length - left){
                interleave(A,1,A.length-1);
            }else if(left < A.length - left){
                interleave(A,0,A.length-2);
            }else{
                interleave(A,0,A.length-1);
            }
        }
    }
    
    private void interleave(int[] array, int begin, int end){
        while(begin < end){
            int tmp=array[begin];
            array[begin]=array[end];
            array[end]=tmp;
            begin +=2;
            end -=2;
        }
    }
    
    
    
    
}
