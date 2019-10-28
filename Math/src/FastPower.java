
public class FastPower {
	public int fastPower(int a, int b, int n) {
		if(n==1) {
			return a % b;
		}
		
		if(n==0) {
			return 1 % b;
		}
		
		long product=fastPower(a,b,n/2);
		
		product = (product * product) % b;
		if(a % 2==1) {
			product = (product * a) % b;
		}
		
		return (int) product;
	}
}
