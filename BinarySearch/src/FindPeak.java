
public class FindPeak {
    public int findPeak(int[] A) {
        // write your code here
    	//选择从第一个1 和 倒数第一个开始搜索 这样不会越界
        int start=1;
        int end=A.length-2;
        while(start + 1 < end){
            int mid=start+(end-start)/2;
            if(A[mid] > A[mid-1]){
                start=mid;
            }else if( A[mid] > A[mid+1]){
                end=mid;
            }else{
                start=mid;
                //OOXX问题无所谓
            }
        }
        return A[start] > A[end] ? start : end;
    }
}
