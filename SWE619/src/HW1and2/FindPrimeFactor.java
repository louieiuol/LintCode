package HW1and2;
/**
 * @author Yoon Chae(G01066504)
 * @author Guanhua Liu(G01161931)
 * @author An Nguyen (G00593022)
 * 
 * Guanhua Liu did the JavaDoc part
 * Yoon Chae did the main part of the findPrimeFactor() method and prime() method
 * An Nguyen did the pre-conditions transform into exceptions
 */



import java.util.List;
public class FindPrimeFactor {
	/**
	 *
	 * @param a list of integers
	 * @param b list of integers
	 * @return return the least index at which b[i] is a prime factor of a[i]
	 * @throws NullPointerException if a or b is null 
	 * @throws IllegalArgumentException if no prime factor is found within list 'b'
	 * @require <pre> a is not null and b is not null and [there is some index i where b[i] is 
     *   both prime and a factor of a[i] ] </pre>
     * @ensure return value greater than or equals 0 
	 */
	public static int findPrimeFactor(List<Integer> a, List<Integer> b) throws NullPointerException, IllegalArgumentException{
		if(a == null || b== null) {
			throw new NullPointerException();
		}
		
		if(a.size() == 0 || b.size() == 0) {
			throw new IllegalArgumentException();
		}
		int minIndex=a.size() > b.size()?b.size(): a.size();
		for(int i=0; i<minIndex; i++) {
			if(a.get(i)!=null && b.get(i)!=null) {
				if(isPrime(b.get(i)) && (a.get(i) % b.get(i)) ==0 ) {
					return i;
				}
			}
		}
		throw new IllegalArgumentException();
		
	}

	/**
	 * 
	 * @param num
	 * @return
	 */
	private static boolean isPrime(int num) {
		if(num>1) {
			for(int i=2; i<=num/2;i++) {
				if((num%i) ==0) return false;
			}
			return true;
		}
		return false;
	}
}
