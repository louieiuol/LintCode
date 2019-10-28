//623. K Edit Distance
//中文English
//Given a set of strings which just has lower case letters and a target string, output all the strings for each the edit distance with the target no greater than k.
//
//You have the following 3 operations permitted on a word:
//
//Insert a character
//Delete a character
//Replace a character
//Example
//Example 1:
//
//Given words = `["abc", "abd", "abcd", "adc"]` and target = `"ac"`, k = `1`
//Return `["abc", "adc"]`
//Input:
//["abc", "abd", "abcd", "adc"]
//"ac"
//1
//Output:
//["abc","adc"]
//
//Explanation:
//"abc" remove "b"
//"adc" remove "d"
//Example 2:
//
//Input:
//["acc","abcd","ade","abbcd"]
//"abc"
//2
//Output:
//["acc","abcd","ade","abbcd"]
//
//Explanation:
//"acc" turns "c" into "b"
//"abcd" remove "d"
//"ade" turns "d" into "b" turns "e" into "c"
//"abbcd" gets rid of "b" and "d"

import java.util.*;

public class KEditDistance {
    public class TrieNode{
        TrieNode[] children;
        boolean hasWord;
        String word;
        TrieNode(){
            this.children=new TrieNode[26];
            this.hasWord=false;
            this.word=null;
        }
        public void insert(String word, int index){
            if(index == word.length()){
                this.hasWord=true;
                this.word=word;
                return;
            }
            int pos=word.charAt(index)-'a';
            if(this.children[pos] == null){
                this.children[pos]=new TrieNode();
            }
            this.children[pos].insert(word, index+1);
        }
    }
    TrieNode root=new TrieNode();
    List<String> result=new ArrayList<>();
    public List<String> kDistance(String[] words, String target, int k) {
        // write your code here
        for(String word: words){
            root.insert(word, 0);
        }
        int[] dp=new int[target.length()+1];
        for(int i=0; i<=target.length(); i++){
            dp[i]=i;
        }
        dfs(root, dp, target, k);
        return result;
    }
    
    private void dfs(TrieNode curr, int[] dp, String target, int k){
        if(curr.hasWord && dp[target.length()] <= k){
            result.add(curr.word);
        }
        for(int index=0; index<26; index++){
            if(curr.children[index] == null) continue;
            int[] temp=new int[target.length()+1];
            for(int i=0; i<=target.length(); i++){
                temp[i]=dp[i]+1;
            }
            for(int i=1; i<=target.length(); i++){
                temp[i]=Math.min(temp[i], dp[i-1]+1);
            }
            for(int i=1; i<=target.length(); i++){
                if(target.charAt(i-1)-'a' == index){
                    temp[i]=Math.min(temp[i], dp[i-1]);
                }
                temp[i]=Math.min(temp[i-1]+1, temp[i]);
            }
            dfs(curr.children[index], temp, target, k);
        }
    }
}
