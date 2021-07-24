//395. Longest Substring with At Least K Repeating Characters
public class LongestSubStringWithAtLeastK {
    public int longestSubstring(String s, int k) {
        if(s.length() < k) return 0;
        int[] hash = new int[26];
        char[] sArray = s.toCharArray();
        for(int i=0; i<sArray.length; i++){
            hash[sArray[i]-'a']++;
        }
        for(int i=0; i<26; i++){
            if(hash[i] > 0 && hash[i] < k){
                int max = 0;
                //注意max 不能放在循环外面, 每次发现有不合法的character
                //则立即分离一个character, 而不是发现全部不合法后一起分离
                char c = (char)(i+'a');
                String[] parts = s.split(String.valueOf(c));
                for(String part: parts){
                    max = Math.max(max, longestSubstring(part, k));
                }
                return max;
            }
        }
        return s.length();
    }
}
