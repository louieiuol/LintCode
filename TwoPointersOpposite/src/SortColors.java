
public class SortColors {
	public void sortColors(int[] nums) {
        // write your code here
        if(nums != null && nums.length != 0){
            int left=0, right=nums.length-1;
            while(left <= right){
                while(left <= right && nums[left] == 0){
                    left++;
                }
                
                while(left <= right && ((nums[right] == 1) || (nums[right] == 2))){
                    right--;
                }
                
                if(left<= right){
                    int tmp=nums[left];
                    nums[left]=nums[right];
                    nums[right]=tmp;
                    left++;
                    right--;
                }
            }
            
            right=nums.length-1;
            while(left <= right){
                while(left <= right && nums[left] == 1 ){
                    left++;
                }
                
                while(left <= right && nums[right] == 2){
                    right--;
                }
                
                if(left <= right){
                    int tmp=nums[left];
                    nums[left]=nums[right];
                    nums[right]=tmp;
                    left++;
                    right--;
                }
            }
        }
    }
	public void rainbowSort(int[] colors, int k) {
		if(colors != null && colors.length >= k){
            quicksort(colors, 0, colors.length-1, 1, k);
        }
	}
	
	public void quicksort(int[] colors, int start, int end, int from, int to){
        if(from >= to){
            return;
        }
        
        if(start >= end ){
            return;
        }
        
        int left=start, right=end;
        int pivot=(from + to)/2;
        while(left <= right){
            while(left <= right && colors[left] <= pivot ){
                left++;
            }
            
            while(left <= right && colors[right] > pivot ){
                right--;
            }
            
            if(left <= right){
                int tmp= colors[left];
                colors[left]=colors[right];
                colors[right]=tmp;
                left++;
                right--;
            }
        }
        
        quicksort(colors, start, right, from, pivot);
        quicksort(colors, left, end, pivot+1, to);
    }
}
