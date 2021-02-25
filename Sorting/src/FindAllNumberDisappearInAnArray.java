//448. Find All Numbers Disappeared in an Array
import java.util.*;
public class FindAllNumberDisappearInAnArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result= new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            while(nums[i]-1 != i && nums[nums[i]-1] != nums[i]){
                swap(nums, i, nums[i]-1);
            }
        }
        for(int i=0; i< nums.length; i++){
            if(i != nums[i]-1){
                result.add(i+1);
            }
        }
        return result;
    }
    
    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
