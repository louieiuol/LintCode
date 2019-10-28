
public class PartitionArray {
    public int partitionArray(int[] nums, int k) {
        // write your code here
        int result=0;
        if(nums != null && nums.length >=2 ){
            int left=0, right=nums.length-1;
            while(left <= right){
                while(left <= right && nums[left] < k){
                    left++;
                }
                
                while(left <= right && nums[right] >= k){
                    right--;
                }
                
                if( left<= right){
                    int tmp= nums[left];
                    nums[left]= nums[right];
                    nums[right]= tmp;
                    right--;
                    left++;
                }
            }
            
            result=left;
        }
        return result;
    }
}
