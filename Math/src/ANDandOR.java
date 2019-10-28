
public class ANDandOR {
    public long getSum(int n, int[] nums) {
        // write your code here
        long minOR=nums[0];
        long maxOR=nums[0];
        long minAND=nums[0];
        long maxAND=nums[0];
        for(int i=1; i<nums.length; i++){
            minOR=Math.min(minOR, nums[i]);
            maxOR=maxOR|nums[i];
            minAND=minAND&nums[i];
            maxAND=Math.max(maxAND, nums[i]);
        }
        return minOR+maxOR+minAND+maxAND;
    }
}
