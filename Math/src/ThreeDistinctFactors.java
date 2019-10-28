//728. Three Distinct Factors
//中文English
//Given a positive integer n (1 <= n <= 10^18). Check whether a number has exactly three distinct factors, return true if it has exactly three distinct factors, otherwise false.
//
//Example
//Example1
//
//Input: n = 9
//Output: true
//Explanation:
//Number 9 has exactly three factors: 1, 3, 9, so return true.
//Example2
//
//Input: n = 10
//Output: false
//Explanation:
//Number 10 has four factors: 1, 2, 5, 10, so return false.
//		
public class ThreeDistinctFactors {
	public boolean isThreeDisctFactors(long n) {
		if(n <=0 || n>Long.MAX_VALUE) return false;
		if(n == 1) return false;
		double sqrt=Math.sqrt(n);
		int num=(int) sqrt;
		if(num - sqrt == 0) {
			return isPrime(num);
		}
		return false;
	}
	
	private boolean isPrime(int num) {
		for(int i=2; i< num/2; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
