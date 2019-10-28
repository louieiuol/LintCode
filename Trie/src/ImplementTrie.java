//442. Implement Trie (Prefix Tree)
//中文English
//Implement a Trie with insert, search, and startsWith methods.
//
//Example
//Example 1:
//
//Input:
//  insert("lintcode")
//  search("lint")
//  startsWith("lint")
//Output:
//  false
//  true
//Example 2:
//
//Input:
//  insert("lintcode")
//  search("code")
//  startsWith("lint")
//  startsWith("linterror")
//  insert("linterror")
//  search("lintcode“)
//  startsWith("linterror")
//Output:
//  false
//  true
//  false
//  true
//  true
//Notice
//You may assume that all inputs are consist of lowercase letters a-z.

public class ImplementTrie {
	public class Trie {
		public class TrieNode{
			TrieNode[] children;
			boolean hasWord;
			public TrieNode() {
				children=new TrieNode[26];
				hasWord=false;
			}
			public void insert(String word, int index) {
				if(index == word.length()) {
					this.hasWord=true;
					return;
				}
				int pos=word.charAt(index)-'a';
				if(this.children[pos] == null) {
					this.children[pos]=new TrieNode();
				}
				this.children[pos].insert(word, index+1);
			}
			public TrieNode find(String word, int index) {
				if(index == word.length()) {
					return this;
				}
				int pos=word.charAt(index)-'a';
				if(this.children[pos] == null) {
					return null;
				}
				return this.children[pos].find(word, index+1);
			}
		}
		TrieNode root;
	    public Trie() {
	        // do intialization if necessary
	    	root=new TrieNode();
	    }

	    /*
	     * @param word: a word
	     * @return: nothing
	     */
	    public void insert(String word) {
	        // write your code here
	    	if(word == null || word.length() == 0) return;
	    	root.insert(word, 0);
	    }

	    /*
	     * @param word: A string
	     * @return: if the word is in the trie.
	     */
	    public boolean search(String word) {
	        // write your code here
	    	TrieNode result=root.find(word, 0);
	    	return result!= null && result.hasWord;
	    }

	    /*
	     * @param prefix: A string
	     * @return: if there is any word in the trie that starts with the given prefix.
	     */
	    public boolean startsWith(String prefix) {
	        // write your code here
	    	if(prefix == null || prefix.length() == 0) return false;
	    	TrieNode result=root.find(prefix, 0);
	    	return result != null;
	    }
	}
}
