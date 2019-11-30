//1307. Verify Preorder Sequence in Binary Search Tree
//中文English
//Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
//
//You may assume each number in the sequence is unique.
//
//Follow up:
//Could you do it using only constant space complexity?
//
//Example
//Example1
//
//Input: preorder = [1,3,2]
//Output: true
//Example2
//
//Input: preorder = [1,2]
//Output: true

import java.util.*;

public class VerifyPreorderSequenceBinarySearchTree {
    public boolean verifyPreorder(int[] preorder) {
		/*
		 * 1) Create an empty stack. 
		 * 2) Initialize root as INT_MIN. 
		 * 3) Do following for
		 * every element pre[i] a) If pre[i] is smaller than current root, return false.
		 * b) Keep removing elements from stack while pre[i] is greater then stack top.
		 * Make the last removed item as new root (to be compared next). At this point,
		 * pre[i] is greater than the removed root (That is why if we see a smaller
		 * element in step a), we return false) c) push pre[i] to stack (All elements in
		 * stack are in decreasing order)
		 */
        if(preorder == null || preorder.length == 0) return true;
        int min=Integer.MIN_VALUE;
        Stack<Integer> stack=new Stack<>();
        for(int num: preorder){
            if(num < min){
                return false;
            }
            while(!stack.isEmpty() && stack.peek() < num){
                min=stack.pop();
            }
            stack.push(num);
        }
        return true;
    }
}
