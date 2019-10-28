
public class QuickSort {
	public void quicksort(int[] nums, int left, int right) {
		if(left>=right) {
			return;
		}
		int pivot= nums[(left+right)/2];
		int index=partition(nums,left,right,pivot);
		quicksort(nums,left,index-1);
		quicksort(nums,index,right);
		
	}

	private int partition(int[] nums, int left, int right, int pivot) {
		// TODO Auto-generated method stub
		while(left<=right) {
			while(nums[left] < pivot) {
				left++;
			}
			
			while(nums[right] > pivot) {
				right--;
			}
			
			if(left<=right) {
				int tmp=nums[left];
				nums[left]=nums[right];
				nums[right]=tmp;
				left++;
				right--;
			}
		}
		return left;
	}
}
