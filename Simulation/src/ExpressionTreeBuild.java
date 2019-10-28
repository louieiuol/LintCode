/*367. Expression Tree Build
中文English
The structure of Expression Tree is a binary tree to evaluate certain expressions.
All leaves of the Expression Tree have an number string value. All non-leaves of the Expression Tree have an operator string value.

Now, given an expression array, build the expression tree of this expression, return the root of this expression tree.

Example
Example 1:

Input: ["2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"]
Output: {[-],[*],[/],[2],[6],[+],[+],#,#,#,#,[23],[7],[1],[2]}
Explanation:
The expression tree will be like

	                 [ - ]
	             /          \
	        [ * ]              [ / ]
	      /     \           /         \
	    [ 2 ]  [ 6 ]      [ + ]        [ + ]
	                     /    \       /      \
	                   [ 23 ][ 7 ] [ 1 ]   [ 2 ] .

After building the tree, you just need to return root node `[-]`. 
Example 2:

Input: ["10","+","(","3","-","5",")","+","7","*","7","-","2"]
Output: {[-],[+],[2],[+],[*],#,#,[10],[-],[7],[7],#,#,[3],[5]} 
Explanation:
The expression tree will be like
 	                       [ - ]
	                   /          \
	               [ + ]          [ 2 ]
	           /          \      
	       [ + ]          [ * ]
              /     \         /     \
	  [ 10 ]  [ - ]    [ 7 ]   [ 7 ]
	          /    \ 
                [3]    [5]
After building the tree, you just need to return root node `[-]`.
Clarification
See wiki:
Expression Tree
*/

import java.util.*;

public class ExpressionTreeBuild {
	class ExpressionTreeNode{
		String val;
		ExpressionTreeNode left;
		ExpressionTreeNode right;
		public ExpressionTreeNode(String val) {
			this.val=val;
			this.left=null;
			this.right=null;
		}
	}
	
	
	 class TreeNode{
		 //建立TreeNode class 包含优先级 和 node 
		 //TreeNode自己不建树
	        int level;
	        ExpressionTreeNode node;
	        public TreeNode(int level, ExpressionTreeNode node){
	            this.level=level;
	            this.node=node;
	        }
	    } 
	    public ExpressionTreeNode build(String[] expression) {
	        // write your code here
	        if(expression == null || expression.length == 0) return null;
	        int base=0;
	        //设置最初优先级 
	        Stack<TreeNode> stack=new Stack<>();
	        //单调栈 把优先级从大到小排列 用于添加树
	        for(String str: expression){
	            if(str.equals("(")){
	            	//遇到括号 base+3 括号不记录于树中 直接跳过
	                base+=3;
	                continue;
	            }
	            if(str.equals(")")){
	            	//遇到反括号 base-3 括号不记录于树中 直接跳过
	                base-=3;
	                continue;
	            }
	            int level=getLevel(base, str);
	            //根据符号或者数字 找到对应的优先级 
	            TreeNode newNode=new TreeNode(level, new ExpressionTreeNode(str));
	            //新建TreeNode 和ExpressionNode
	            while(!stack.isEmpty() && stack.peek().level >= level){
	            	//排出所有stack顶部所有优先级比当前优先级高和相等的元素 （自顶向下优先级 越来越高）
	            	//保持栈的单调性
	            	//新的node的左侧连上stack所弹出的最后一个
	            	//每次新的一个符号或者元素 我们从右边加入（TreeNode的level并不代表Expression Tree 的层数）同层的优先级一定相等 不同的优先级也可能相等
	                newNode.node.left=stack.pop().node;
	            }
	            if(!stack.isEmpty()){
	            	//如果不是root 那么新的node 加入当前stack顶部右边 
	                stack.peek().node.right=newNode.node;
	            }
	            //stack加入新的node 
	            stack.push(newNode);
	        }
	        ExpressionTreeNode result=null;
	        while(!stack.isEmpty()){
	        	//弹出所有stack元素 找到root 
	            result=stack.pop().node;
	        }
	        return result;
	    }
	    
	    private int getLevel(int base, String symbol){
	        if(symbol.equals("+") || symbol.equals("-")) return base+1;
	        //+ -号 层级+1
	        if(symbol.equals("*") || symbol.equals("/")) return base+2;
	        //* /号 层级+2
	        return Integer.MAX_VALUE;
	        //数字在最底层
	    }
}
