//132. Word Search II
//中文English
//Given a matrix of lower alphabets and a dictionary. Find all words in the dictionary that can be found in the matrix. 
//A word can start from any position in the matrix and go left/right/up/down to the adjacent position. One character only be used once in one word. No same word in dictionary
//
//Example
//Example 1:
//
//Input：["doaf","agai","dcan"]，["dog","dad","dgdg","can","again"]
//Output：["again","can","dad","dog"]
//Explanation：
//  d o a f
//  a g a i
//  d c a n
//search in Matrix，so return ["again","can","dad","dog"].
//Example 2:
//
//Input：["a"]，["b"]
//Output：[]
//Explanation：
// a
//search in Matrix，return [].
//Challenge
//Using trie to implement your algorithm.
import java.util.*;

public class WordSearchII {
	class TrieNode{
		TrieNode[] children;
		String word;
		TrieNode(){
			this.children=new TrieNode[26];
			this.word=null;
		}
		void insert(String word, int index) {
			if(index == word.length()) {
				this.word=word;
				return;
			}
			int pos=word.charAt(index)-'a';
			if(this.children[pos] == null) {
				this.children[pos]=new TrieNode();
			}
			this.children[pos].insert(word, index+1);
		}
	}
	
	TrieNode root=new TrieNode();
	List<String> result=new ArrayList<>();
	int[] dx= {0,0,-1,1};
	int[] dy= {-1,1,0,0};
    public List<String> wordSearchII(char[][] board, List<String> words) {
    	if(board == null || board.length == 0) return result;
    	for(String word: words) {
    		root.insert(word, 0);
    	}
    	for(int i=0; i<board.length; i++) {
    		for(int j=0; j<board[0].length; j++) {
    			//进去后处理情况的话不需要外面回溯 
    			search(board, i, j, root);
    		}
    	}
    	return result;
    }
    
    private void search(char[][] board, int x, int y, TrieNode curr) {
    	//一进来先判断是否可行 
    	if(curr.children[board[x][y]] == null) {
    		return;
    	}
    	if(curr.word != null && !result.contains(curr.word)) {
    		result.add(curr.word);
    		//注意找到一个不要return 可能在同一prefix下有多个 
    	}
    	//当前格子回溯 
    	char temp=board[x][y];
    	board[x][y]=0;
    	for(int i=0; i<4; i++) {
    		int nextX=x+dx[i];
    		int nextY=y+dy[i];
    		if(nextX >=0 && nextX <board.length && nextY >=0 && nextY < board[0].length && board[x][y]!=0) {
    			//这里直接带入参数即可 不要回溯 
    			search(board, nextX, nextY, curr.children[board[x][y]]);
    		}
    	}
    	board[x][y]=temp;
    }
}
