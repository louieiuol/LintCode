
public class AVLDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVL avlTree=new AVL();
		avlTree.insert("5", "5");
		avlTree.insert("2", "2");
		avlTree.insert("8", "8");
		avlTree.insert("4", "4");
		avlTree.insert("6", "6");
		avlTree.insert("1", "1");
		avlTree.insert("3", "3");
		avlTree.delete("6");
		avlTree.displayAll();
		System.out.println(avlTree.isAVL());
	}
}
