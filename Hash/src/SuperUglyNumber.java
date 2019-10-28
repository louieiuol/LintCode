//518. Super Ugly Number
//中文English
//Write a program to find the nth super ugly number.
//
//A super ugly number is a positive integer in which all prime factors are within a given set of prime numbers.
//
//For example, given [2, 7, 13, 19], then [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the first 12 super ugly numbers.
//
//Example
//Example 1:
//
//Input: n = 6, [2,7,13,19]
//Output: 13
//Example 2:
//
//Input: n = 11, [2,3,5]
//Output: 15
//Notice
//1 is a super ugly number for any given primes.
//0 < k ≤ 100, 0 < n ≤ 10^610
//​6
//​​  , 0 < primes[i] < 1000
import java.util.*;
public class SuperUglyNumber {
	public int nthSuperUglyNumber(int n, int[] primes) {
		if(n <=0 || primes == null || primes.length == 0) return 0;
		HashSet<Long> set=new HashSet<>();
		PriorityQueue<Long> pq=new PriorityQueue<>();
		set.add(1L);
		pq.add(1L);
		while(n > 0) {
			long curr=pq.poll();
			if(n == 1) {
				return (int) curr;
			}
			for(int prime: primes) {
				long next=curr*prime;
				if(next> Integer.MAX_VALUE) {
					continue;
				}
				if(!set.contains(next)) {
					set.add(next);
					pq.offer(next);
				}
			}
		}
		return 1;
	}
}
