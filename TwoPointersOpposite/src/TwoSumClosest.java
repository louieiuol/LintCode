import java.util.*;
public class TwoSumClosest {
	 /* Given an array of integers, find two numbers such that they add up to a less than but is closest to target number. 
	 * The function twoSum should return the
	 *value of two number according to their original order.*/
	public int[] twosumclosestLess(int[] arr, int target) {
		int[] res=new int[2];
		if(arr==null) return res;
		if(arr.length==0) return res;
		Arrays.sort(arr);
		int diff=Integer.MAX_VALUE;
		int left=0, right=arr.length-1;
		while(left<right) {
			int sum=arr[left]+arr[right];
			if(sum<=target && (target-sum)<diff) {
				res[0]=arr[left];
				res[1]=arr[right];
				diff=(target-sum);
			}
			if(sum < target) {
				left++;
			}else {
				right--;
			}
		}
		return res;
	}
	/*Given an array nums of n integers, find two integers in nums such that the sum is closest to a given number, target.
	 * Return the difference between the sum of the two integers and the target.*/
    public int twosumClosest(int[] nums, int target) {
        // write your code here
		if(nums==null) return -1;
		if(nums.length==0) return -1;
		Arrays.sort(nums);
		int diff=Integer.MAX_VALUE;
		int left=0, right=nums.length-1;
		while(left<right) {
			int sum=nums[left]+nums[right];
			if(sum < target) {
			    diff=Math.min(diff, target-sum);
				left++;
			}else if(sum > target) {
			    diff=Math.min(diff, sum-target);
				right--;
			}else{
			    return 0;
			}
		}
		return diff;
    } 
    /*Given an array of integers, find how many unique pairs in the array such that their sum is equal to a specific target number.
     *  Please return the number of pairs.
     */
    public int twoSumPairs(int[] nums, int target) {
        // Write your code here
        if (nums == null || nums.length < 2)
            return 0;

        Arrays.sort(nums);
        int cnt = 0;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int v = nums[left] + nums[right];
            if (v == target) {
                cnt ++;
                left ++;
                right --;
                while (left < right && nums[right] == nums[right + 1])
                    right --;
                while (left < right && nums[left] == nums[left - 1])
                    left ++;
            } else if (v > target) {
                right --;
            } else {
                left ++;
            }
        }
        return cnt;
    }
	 public class TreeNode {
	      public int val;
	      public TreeNode left, right;
	      public TreeNode(int val) {
	          this.val = val;
	          this.left = this.right = null;
	     }
	 }	 
	 /*Given a binary search tree and a number n, find two numbers in the tree that sums up to n. */
    public int[] twoSum(TreeNode root, int n) {
        // write your code here
        if (root == null) {
            return null;
        }
        List<Integer> inorder = new ArrayList<>();
        inorderTraverse(root, inorder);
        int left = 0, right = inorder.size() - 1;
        while (left != right) {
            int leftVal = inorder.get(left);
            int rightVal = inorder.get(right);
            if (leftVal + rightVal == n) {
                return new int[] {leftVal, rightVal};
            } else if (leftVal + rightVal < n) {
                left++;
            } else {
                right--;
            }
        }
        
        return null;
    }
    private void inorderTraverse(TreeNode root, List<Integer> inorder) {
        if (root == null) {
            return;
        }    
        inorderTraverse(root.left, inorder);
        inorder.add(root.val);
        inorderTraverse(root.right, inorder);
    }
}
