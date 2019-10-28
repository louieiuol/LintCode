//You are given a number n ( 3 <= n < 10^6 ) and you have to find nearest prime less than n?
//
//Examples:
//
//Input : n = 10
//Output: 7
//
//Input : n = 17
//Output: 13
//
//Input : n = 30
//Output: 29

import java.util.*;


public class NearestPrime {
	static int MAX=1000000; 
	  
	// array to store all primes less than 10^6 
	static ArrayList<Integer> primes = new ArrayList<Integer>();
	static void Sieve() 
	{ 
	    int n = MAX; 
	  
	    // In general Sieve of Sundaram, produces primes 
	    // smaller than (2*x + 2) for a number given 
	    // number x 
	    int nNew = (int)Math.sqrt(n); 
	  
	    // This array is used to separate numbers of the 
	    // form i+j+2ij from others where 1 <= i <= j 
	    int[] marked = new int[n / 2 + 500]; 
	  
	    // eliminate indexes which does not produce primes 
	    for (int i = 1; i <= (nNew - 1) / 2; i++) 
	        for (int j = (i * (i + 1)) << 1;  
	                j <= n / 2; j = j + 2 * i + 1) 
	            marked[j] = 1; 
	  
	    // Since 2 is a prime number 
	    primes.add(2); 
	  
	    // Remaining primes are of the form 2*i + 1 such 
	    // that marked[i] is false. 
	    for (int i = 1; i <= n / 2; i++) 
	        if (marked[i] == 0) 
	            primes.add(2 * i + 1); 
	} 
	  
	// modified binary search to find nearest prime less than N 
	static int binarySearch(int left,int right,int n) 
	{ 
	    if (left <= right) 
	    { 
	        int mid = (left + right) / 2; 
	  
	        // base condition is, if we are reaching at left 
	        // corner or right corner of primes[] array then 
	        // return that corner element because before or 
	        // after that we don't have any prime number in 
	        // primes array 
	        if (mid == 0 || mid == primes.size() - 1) 
	            return primes.get(mid); 
	  
	        // now if n is itself a prime so it will be present 
	        // in primes array and here we have to find nearest 
	        // prime less than n so we will return primes[mid-1] 
	        if (primes.get(mid) == n) 
	            return primes.get(mid - 1); 
	  
	        // now if primes[mid]<n and primes[mid+1]>n that 
	        // mean we reached at nearest prime 
	        if (primes.get(mid) < n && primes.get(mid + 1) > n) 
	            return primes.get(mid); 
	        if (n < primes.get(mid)) 
	            return binarySearch(left, mid - 1, n); 
	        else
	            return binarySearch(mid + 1, right, n); 
	    } 
	    return 0; 
	} 
}
