//473. Add and Search Word - Data structure design
//中文English
//Design a data structure that supports the following two operations: addWord(word) and search(word)
//
//search(word) can search a literal word or a regular expression string containing only letters a-z or ..
//
//A . means it can represent any one letter.
//
//Example
//Example 1:
//
//Input:
//  addWord("a")
//  search(".")
//Output:
//  true
//Example 2:
//
//Input:
//  addWord("bad")
//  addWord("dad")
//  addWord("mad")
//  search("pad")  
//  search("bad")  
//  search(".ad")  
//  search("b..")  
//Output:
//  false
//  true
//  true
//  true
//Notice
//You may assume that all words are consist of lowercase letters a-z.

public class AddandSearchWord {
		public class TrieNode{
			TrieNode[] children;
			boolean hasWord;
			public TrieNode() {
				this.children=new TrieNode[26];
				hasWord=false;
			}
		}
		TrieNode root=new TrieNode();
	    /*
	     * @param word: Adds a word into the data structure.
	     * @return: nothing
	     */
	    public void addWord(String word) {
	        // write your code here
	    	if(word == null || word.length() == 0) return;
	    	TrieNode curr=root;
	    	for(int i=0; i<word.length(); i++) {
	    		int pos=word.charAt(i)-'a';
	    		if(curr.children[pos] == null) {
	    			curr.children[pos] =new TrieNode();
	    		}
	    		curr=curr.children[pos];
	    	}
	    	curr.hasWord=true;
	    }

	    /*
	     * @param word: A word could contain the dot character '.' to represent any one letter.
	     * @return: if the word is in the data structure.
	     */
	    public boolean search(String word) {
	        // write your code here
	    	if(word == null || word.length() == 0) return false;
	    	return find(word, 0, root);
	    }
	    
	    private boolean find(String word, int index, TrieNode curr) {
	    	if(index == word.length()) {
	    		return curr.hasWord;
	    	}
	    	char c=word.charAt(index);
	    	if(c == '.') {
	    		for(int i=0; i< 26 ;i++) {
	    			if(curr.children[i] != null) {
	    				if(find(word, index+1, curr.children[i])) {
	    					return true;
	    				}
	    			}
	    		}
	    	}else {
	    		int pos=c-'a';
	    		if(curr.children[pos] != null) {
	    			return find(word, index+1, curr.children[pos]);
	    		}
	    	}
	    	return false;
	    }
}
