
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InsertionSort insert=new InsertionSort();
		InsertionSort.LinkList testlst=insert.new LinkList();
		testlst.add(4);
		testlst.add(2);
		testlst.add(1);
		testlst.add(3);
		int[] array= {4,2,1,3};
		InsertionSort.LinkList test2=insert.convertArray(array);
		Tree tree=new Tree();
		Tree.BinaryTree binarytree=tree.new BinaryTree();
		binarytree.add(3);
		binarytree.add(2);
		binarytree.add(4);
		binarytree.add(1);
		int size=tree.calculateSize(binarytree);
		//System.out.println(binarytree.size);
		//System.out.println(size);
		int height=tree.calculateHeight(binarytree);
		//System.out.println(height);
		tree.postorderTraversal(binarytree);
		tree.preorderTraversal(binarytree);
		tree.inorderTraversal(binarytree);
	}

}
