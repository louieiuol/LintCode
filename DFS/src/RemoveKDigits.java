import java.util.*;

/*Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-k-digits
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/


public class RemoveKDigits {
	long min=Long.MAX_VALUE;
    public String removeKdigits1(String num, int k) {
    	if(num.length() <= k) return "0";
    	dfs(0, "", num ,k);
    	return String.valueOf(min);
    }
    
	private void dfs(int index, String path, String num, int k) {
		if(index == num.length()) {
			if(path.length() + k == num.length()) {
				min = Math.min(min, Long.parseLong(path));
			}
			return;
		}
		String temp = path;
		path += num.charAt(index);
		dfs(index+1, path, num, k);
		path = temp;
		dfs(index+1, path, num, k);
	}
	
	public String removeKdigits(String num, int k) {
        if(num.length() <= k){
            return "0";
        }
        //单调栈 保持栈中元素单调递增
        Stack<Integer> stack = new Stack<>();
        char[] numArray = num.toCharArray();
        for(int i=0; i<numArray.length; i++){
            int curr = (int)numArray[i] - (int)'0';
            //如果栈不为空且顶元素大于当前元素，同时满足k > 0，则让栈顶元素出栈 （保证单调性)
            //否则往栈中推
            while(!stack.isEmpty() && stack.peek() > curr && k > 0){
                stack.pop();
                k--;
            }
            stack.push(curr);
        }
        //如果还有剩余的k，则将栈顶元素依次顺出（因为是单调的，出去的元素一定都是全局最大）
        while(!stack.isEmpty() && k > 0){
            stack.pop();
            k--;
        }
        List<Integer> reverseList = new ArrayList<>();
        while(!stack.isEmpty()){
            reverseList.add(stack.pop());
        }
        //反转列表 （因为出栈顺序是先进后出，不是原数字位数顺序)
        Collections.reverse(reverseList);
        int index = 0;
        //去掉0开头的位数
        while(index < reverseList.size()){
            if(reverseList.get(index) != 0){
                break;
            }
            index++;
        }
        reverseList = reverseList.subList(index, reverseList.size());
        if(reverseList.isEmpty()) return "0";
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < reverseList.size(); i++){
            sb.append(reverseList.get(i));
        }
        return sb.toString();
    }
}
