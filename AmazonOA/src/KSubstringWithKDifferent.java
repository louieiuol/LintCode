import java.util.*;
/*1639. K-Substring with K different characters
Given a string S and an integer K.
Calculate the number of substrings of length K and containing K different characters

Example
String: "abcabc"
K: 3

Answer: 3
substrings:  ["abc", "bca", "cab"]
String: "abacab"
K: 3

Answer: 2
substrings: ["bac", "cab"]*/
//k unique characters k size fixed, result unique
public class KSubstringWithKDifferent {
	public int KSubstringKDiff(String stringIn, int K) {
		if(stringIn != null && K >0){
			if(stringIn.length()>0){
				HashSet<String> res=new HashSet<>();
				HashSet<Character> set=new HashSet<>();
				for(int i=0; i<=stringIn.length()-K; i++){
					int pointer=i;
					while(pointer<stringIn.length() && set.size()<K){
						char c=stringIn.charAt(pointer);
						if(set.contains(c)){
							break;
						}
						set.add(c);
						pointer++;
						if(set.size()== K){
							res.add(stringIn.substring(i,pointer));
						}
					}
					set.clear();
				}
				return res.size();
			}
		}
		return 0;
	}
	// k unique characters size not fixed, result duplicate
	public int KContain(String stringIn, int K) {
		if(stringIn == null || K<=0) return 0;
		if(stringIn.length() == 0) return 0;
		int res=0;
		HashSet<Character> set= new HashSet<>();
		for(int left=0; left<stringIn.length()-K+1;left++) {
			int right=left;
			int cnt=0;
			while(right<stringIn.length()) {
				char c=stringIn.charAt(right);
				if(!set.contains(c)) {
					cnt++;
					set.add(c);
				}
				right++;
				if(cnt == K) {
					res++;
				}
				if(cnt>K){
                    break;
				}
			}
			set.clear();
		}
		return res;
}
	//k-1 unique character contains, unlimited length
	public int KLess(String stringIn, int K) {
		if(stringIn == null || K<=0) return 0;
		if(stringIn.length() == 0) return 0;
		HashSet<String> res=new HashSet<>();
		HashMap<Character,Integer> map= new HashMap<>();
		for(int left=0; left<stringIn.length()-K+1;left++) {
			int right=left;
			boolean flag=true;
			while(right<stringIn.length()) {
				char c=stringIn.charAt(right);
				if(!map.containsKey(c)) {
					map.put(c,1);
				}else {
					map.put(c, map.get(c)+1);
				}
				if(map.get(c) ==3 || (map.get(c) ==2 && !flag)){
					break;
				}
				if(map.get(c) == 2) flag=false;
				right++;
				if(map.size()== K-1) {
					res.add(stringIn.substring(left,right));
				}
			}
			map.clear();
		}
		return res.size();
	}
	//k-1 unique character contains, K length
	public int KLessKSize(String stringIn, int K) {
		if(stringIn == null || K<=0) return 0;
		if(stringIn.length() == 0) return 0;
		HashSet<String> res=new HashSet<>();
		HashMap<Character,Integer> map= new HashMap<>();
		for(int left=0; left<stringIn.length()-K+1;left++) {
			int right=left;
			boolean flag=true;
			while(right<stringIn.length()) {
				char c=stringIn.charAt(right);
				if(!map.containsKey(c)) {
					map.put(c,1);
				}else {
					map.put(c, map.get(c)+1);
				}
				if(map.get(c) ==3 || (map.get(c) ==2 && !flag)){
					break;
				}
				if(map.get(c) == 2) flag=false;
				right++;
				if(map.size()==K-1 && right-left == K) {
					res.add(stringIn.substring(left,right));
				}
				if(map.size()>K-1 || right-left >K ) {
					break;
				}
			}
			map.clear();
		}
		System.out.println(res);
		return res.size();
	}
}
