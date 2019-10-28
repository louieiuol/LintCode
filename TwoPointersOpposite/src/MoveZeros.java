
public class MoveZeros {
	/*This following method can keep the original order*/
	public void moveZerosSame(int[] nums) {
		int left=0, right=0;
		//left pointer points to the first zero on the left 
		//right pointer points to the first non-zero on the left
		while( right < nums.length) {
			if(nums[right] !=0 ) {
				int tmp=nums[left];
				nums[left]=nums[right];
				nums[right]=tmp;
				//swap the first zero position and first non-zero position
				//swap left and right
				left++;
				//found a non-zero left value move forward to find zero position
			}
			right++;
			//normal increase right pointer to find non-zero value
		}
	}
	
	/*This following method cannot keep original order*/
	public void moveZerosOpposite(int[] nums) {
		int left=0, right=nums.length-1;
		//left pointer points to the first zero on the left
		//right pointer points to the first non-zero on the right
		while(left<right) {
			 if(nums[left] == 0) {
				 int tmp=nums[left];
				 nums[left]=nums[right];
				 nums[right]=tmp;
				 //swap found zero value on the left with right pointer
			 }else {
				 left++;
				 //move forward pointer to find first zero value on the left
			 }
			 
			 if(nums[right] != 0) {
				 int tmp=nums[right];
				 nums[right]=nums[left];
				 nums[left]=tmp;
				 //swap found non-zero value on the right with left pointer
			 }else {
				 right--;
				 //move backward pointer to find first non-zero value on the right
			 }
		}
		
	}

}
