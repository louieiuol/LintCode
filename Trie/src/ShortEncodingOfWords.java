//1390. Short Encoding of Words
//中文English
//Given a list of words, we may encode it by writing a reference string S and a list of indexes A.
//
//For example, if the list of words is ["time", "me", "bell"], we can write it as S = "time#bell#" and indexes = [0, 2, 5].
//
//Then for each index, we will recover the word by reading from the reference string from that index until we reach a "#" character.
//
//What is the length of the shortest reference string S possible that encodes the given words?
//
//Example
//**Input**: words = ["time", "me", "bell"]
//**Output**: 10
//**Explanation**: S = "time#bell#" and indexes = [0, 2, 5].
//Notice
//1 <= words.length <= 2000.
//1 <= words[i].length <= 7.
//Each word has only lowercase letters.
import java.util.*;

public class ShortEncodingOfWords {
    class TrieNode{
        TrieNode[] children;
        String word;
        public TrieNode(){
            children=new TrieNode[26];
            word=null;
        }
    }
    
    class Trie{
        TrieNode root;
        int size;
        public Trie(String[] words){
            root=new TrieNode();
            for(String word: words){
                TrieNode curr=root;
                for(int i=word.length()-1; i>=0; i--){
                    int index=word.charAt(i)-'a';
                    if(curr.children[index] == null){
                        curr.children[index]=new TrieNode();
                        size++;
                    }
                    curr=curr.children[index];
                }
                curr.word=word;
            }
        }
        
        public int countLeaves(TrieNode root, List<String> words){
            int sum=0;
            if(root == null){
                return sum;
            }
            boolean isLeave=true;
            for(int i=0; i<26; i++){
                if(root.children[i] != null){
                    isLeave=false;
                }
            }
            if(isLeave){
                if(words.contains(root.word)){
                    return root.word.length()+1;
                }
                return 0;
            }else{
                for(int i=0; i<26; i++){
                    sum+=countLeaves(root.children[i], words);    
                }
                return sum;
            }
        }
    }
    Trie root;
    public int minimumLengthEncoding(String[] words) {
        if(words == null || words.length == 0) return 0;
        root=new Trie(words);
        return root.countLeaves(root.root, Arrays.asList(words));
    }
}
