/*Given a string containing n lowercase letters, 
 * the string needs to be divided into several continuous substrings, 
 * the letter in the substring should be same, 
 * and the number of letters in the substring does not exceed k,
 * and output the minimal substring number meeting the requirement.

Example
Example1
 
Input: s = "aabbbc", k = 2
Output: 4
Explanation:
we can get "aa", "bb", "b", "c" four substring.
Example2

input: s = "aabbbc", k = 3
Output: 3
we can get "aa", "bbb", "c" three substring.
*/
public class LeastSubString {
    public int getAns(String s, int k) {
        // Write your code here
        if(s!=null){
            if(s.length()>0 && k>0){
            	int n=s.length() , ans=1, cnt=1;
            	for(int i=1; i<n ;i++) {
            		if(s.charAt(i) == s.charAt(i-1) && cnt<k) {
            			cnt++;
            		}else {
            			ans++;
            			cnt=1;
            		}
            	}
            	return ans;
            } 
        }
        return 0;
    }
}
