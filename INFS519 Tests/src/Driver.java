
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Depth tree=new Depth();
		tree.insert(3);
		tree.insert(5);
		tree.insert(2);
		tree.insert(7);
		tree.insert(4);
		tree.insert(8);
		tree.insert(6);
		System.out.println(tree.getHeight(8));
	}

}
