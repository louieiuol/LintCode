//82. Single Number
//中文English
//Given 2 * n + 1 numbers, every numbers occurs twice except one, find it.
//
//Example
//Example 1:
//
//Input：[1,1,2,2,3,4,4]
//Output：3
//Explanation:
//Only 3 appears once
//Example 2:
//
//Input：[0,0,1]
//Output：1
//Explanation:
//Only 1 appears once
//Challenge
//One-pass, constant extra space.
public class SingleNumber {
//	因为只有一个数恰好出现一个，剩下的都出现过两次，所以只要将所有的数异或起来，
//	就可以得到唯一的那个数，因为相同的数出现的两次，异或两次等价于没有任何操作！
    public int singleNumber(int[] nums) {
        int result = 0, n = nums.length;
        for (int i = 0; i < n; i++)
        {
            result ^= nums[i];
        }
        return result;
    }
}

//a ^ b ^ b = a  对一个数异或两次等价于没有任何操作！