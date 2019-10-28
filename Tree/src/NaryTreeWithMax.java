
/*597. Subtree with Maximum Average
Given a n-ary tree, find the subtree with maximum average. Return the root of the subtree.

Example
Example 1

Input：
         1
     /      \
   - 5       11
 /  |  \    /  \
1   2  3  4    -2 
Output：11(it's a TreeNode)
Example 2

Input：
     1
   / |  \
 -5  3   11
Output：11(it's a TreeNode)
Notice
LintCode will print the subtree which root is your return node.
It's guaranteed that there is only one subtree with maximum average.
 */

import java.util.*;
public class NaryTreeWithMax {
	public class ComponentNode{
		public int val;
		public ArrayList<ComponentNode> components;
		public ComponentNode(){
			components =new ArrayList<ComponentNode>();
		}
		public ComponentNode(int tensure){
			this.val=tensure;
			this.components=new ArrayList<ComponentNode>();
		}
	}
	class TreeFamily {
		ComponentNode root;
		int sum;
		int size;
		public TreeFamily(ComponentNode node, int sum, int size) {
			root = node;
			this.sum = sum;
			this.size = size;
		}
	}
	private TreeFamily res = null;
	public ComponentNode highestSpeed(ComponentNode root) {
		helper(root);
		return res.root;
	}
	private TreeFamily helper(ComponentNode root) {
		if(root == null) {
			return new TreeFamily(null, 0, 0);
		}
		// if(root.components == null || root.componenets.size() == 0) 
		//    return new TreeFamily(root, root.val, 1); 
		List<TreeFamily> nodes = new ArrayList<>();
		for(ComponentNode t: root.components) {
			nodes.add(helper(t));
		}
		int childSum = 0;
		int childSize = 0;
		for(TreeFamily child: nodes) {
			childSum += child.sum;
			childSize += child.size;
		}
		TreeFamily tmp = new TreeFamily(root, childSum + root.val, childSize + 1);
		if(res == null || tmp.sum * res.size > tmp.size * res.sum) {
			if(tmp.size > 1) // leaf node not included
				res = tmp;
		}
		return tmp;
	}

	public class ResultType{
		int sum;
		int size;
		public ResultType(int sum, int size){
			this.sum=sum;
			this.size=size;
		}
	}
	public class CategoryNode{
		public int value;
		public ArrayList<CategoryNode> subCategoryNode;
		public CategoryNode(){
			subCategoryNode =new ArrayList<CategoryNode>();
		}
		public CategoryNode(int tensure){
			this.value=tensure;
			this.subCategoryNode=new ArrayList<CategoryNode>();
		}
	}
	CategoryNode result=null;
	ResultType max=null;
	public CategoryNode getMostPopularNode(CategoryNode root) {
		helper(root);
		return result;
	}
	private ResultType helper(CategoryNode root){
		if(root == null ) return new ResultType(0,0); 
		int sum=0, size=0;
		ArrayList<ResultType> lst=new ArrayList<>();
		for(int i=0; i<root.subCategoryNode.size();i++){
			ResultType subresult=helper(root.subCategoryNode.get(i));
			lst.add(subresult);
		}
		for(int i=0;i<lst.size();i++){
			sum+=lst.get(i).sum;
			size+=lst.get(i).size;
		}
		ResultType curr=new ResultType(
				sum+root.value,
				size+1
				);
		if(result == null || curr.sum* max.size > max.sum* curr.size){
			//if(curr.size > 1) // leaf node not included
				result=root;
				max=curr;
		}
		return curr;
	}
}
