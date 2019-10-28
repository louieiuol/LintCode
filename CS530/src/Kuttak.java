import java.util.Scanner;

public class Kuttak {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
	      int p = s.nextInt();
	      int q = s.nextInt();
	      int vals[] = pulverizer(p, q);
	      System.out.println("gcd(" + p + ", " + q + ") = " + vals[0]);
	      System.out.println(vals[1] + "(" + p + ") + " + vals[2] + "(" + q + ") = " + vals[0]);
	}
	
	static int[] pulverizer(int a, int b) {
		// if ( b == 0) {
		//	 return new int[]{a, 1, 0};
		// }
		 
		 int q = a / b;
		 int r = a % b;
		 // b = 0 × a + 1 × b; r = a − qb
		 	return kuttak(b,r,0,1,1,-q);
		}
		
	
	
	static int[] kuttak(int x, int y, int s1, int t1, int s2, int t2) {
		if (y == 0) {
			return new int[] {x,s1,t1};
		}
		int q = x / y;
		int r = x % y;
		return kuttak(y, r, s2, t2, s1-q*s2, t1-q*t2);
	}
}
