import java.util.*;
public class EvaluateReversePolishNotation {
	
//	逆波兰表达式是更利于计算机运算的表达式形式, 需要用到栈(先进后出的数据结构).
//
//	遍历表达式:
//
//	碰到数字则入栈
//	碰到运算符则连续从栈中取出2个元素, 使用该运算符运算然后将结果入栈
    public int evalRPN(String[] tokens) {
    	if(tokens == null || tokens.length == 0) return 0;
    	Stack<Integer> stack=new Stack<>();
    	String operations="+-*/";
    	for(int i=0;i<tokens.length;i++) {
    		String s=tokens[i];
    		if(!operations.contains(s)) {
    			stack.push(Integer.parseInt(s));
    		}else {
    			int res=0;
    			int num1=stack.pop();
    			int num2=stack.pop();
    			if(s.equals("+")) {
    				res=num2+num1;
    			}
    			if(s.equals("-")) {
    				res=num2-num1;
    			}
    			if(s.equals("*")) {
    				res=num2*num1;
    			}
    			if(s.equals("/")) {
    				res=num2/num1;
    			}
    			stack.push(res);
    		}
    	}
    	return stack.peek();
    }
}
