//1007. Chalkboard XOR Game
//中文English
//We are given non-negative integers nums[i] which are written on a chalkboard. Alice and Bob take turns erasing exactly one number from the chalkboard, with Alice starting first. If erasing a number causes the bitwise XOR of all the elements of the chalkboard to become 0, then that player loses. (Also, we'll say the bitwise XOR of one element is that element itself, and the bitwise XOR of no elements is 0.)
//
//Also, if any player starts their turn with the bitwise XOR of all the elements of the chalkboard equal to 0, then that player wins.
//
//Determine if Alice has a winning stargety, assuming both players play optimally.
//
//Example
//Example 1:
//
//Input: [1, 1, 2]
//Output: false
//Explanation: 
//    Alice has two choices: erase 1 or erase 2. 
//    If she erases 1, the nums array becomes [1, 2]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 2 = 3. Now Bob can remove any element he wants, because Alice will be the one to erase the last element and she will lose. 
//    If Alice erases 2 first, now nums becomes [1, 1]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 1 = 0. Alice will lose.
//Example 2:
//
//Input: [1, 1, 1, 2]
//Output: true
//Explanation: Alice can erase 2 and the array becomes [1, 1, 1]. After Bob's erasure, the array becomes [1, 1] and 1 XOR 1 = 0.
//Notice
//1 <= nums.length <= 1000
//0 <= nums[i] <= 2^16

//如果初始异或和就是0, 那么 Alice 直接胜出.
//
//然后胜负关系仅仅与数组长度的奇偶性相关: 如果数组长度是奇数则 Alice 必败, 如果数组长度为偶数则 Alice 必胜.
//
//以下是简要的说明.
//
//我们这样定义最小异或组: 一些数的异或和为0, 而拿掉任意一个数之后异或和不为0. 原本的数组可能包含多个最小异或组.
//
//假设原数组包含 A1, A2, ..., An 这 n 个最小异或组, 同时剩下 B1, B2, ..., Bm 这 m 个独立的元素, 它们不再包含最小异或组.
//
//两个人都不想输, 所以一定会保证自己拿掉一个数之后还剩下 B 类的元素. 如果 m > 0, 他可以直接拿一个 B 类元素, 如果 m == 1, 他可以拿一个 A 类元素, 从而拆开了一个最小异或组. 这时 n, m 需要重新计算.
//
//无论如何, 总是可以不输的, 所以局面会僵持到拿走最后一个数.

public class ChalkboardXORGame {
    public boolean xorGame(int[] nums) {
    	int ans=0;
    	for(int ele: nums) {
    		ans=ans^ele;
    	}
    	return (ans == 0) || (nums.length % 2 == 0);
    }
}
