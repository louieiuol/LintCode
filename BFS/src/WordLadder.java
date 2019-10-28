//120. Word Ladder
//中文English
//Given two words (start and end), and a dictionary, find the shortest transformation sequence from start to end, output the length of the sequence.
//Transformation rule such that:
//
//Only one letter can be changed at a time
//Each intermediate word must exist in the dictionary. (Start and end words do not need to appear in the dictionary )
//Example
//Example 1:
//
//Input：start = "a"，end = "c"，dict =["a","b","c"]
//Output：2
//Explanation：
//"a"->"c"
//Example 2:
//
//Input：start ="hit"，end = "cog"，dict =["hot","dot","dog","lot","log"]
//Output：5
//Explanation：
//"hit"->"hot"->"dot"->"dog"->"cog"
//Notice
//Return 0 if there is no such transformation sequence.
//All words have the same length.
//All words contain only lowercase alphabetic characters.
//You may assume no duplicates in the word list.
//You may assume beginWord and endWord are non-empty and are not the same.


import java.util.*;


public class WordLadder {
	public int ladderLength(String start, String end, Set<String> dict) {
		if(start !=null && end !=null && dict!=null) {
			if(dict.size()!=0) {
				if(start.equals(end)) {
					return 0;
				}
				//如果相等则返回0 不需要转化
				int counter=0;
				//如果不等则收尾需要转换至少两次
				dict.add(end);
				//dict中要加入end 因为有可能直接就转化了
				Queue<String> queue=new LinkedList<String>();
				ArrayList<String> lst=new ArrayList<String>();
				queue.add(start);
				lst.add(start);
				while(!queue.isEmpty()) {
					int size=queue.size();
					counter++;
					//每走一层是走了一步
					for(int i=0; i< size; i++) {
						String str=queue.poll();
						if(str.equals(end)) {
							return counter;
						}
						for(String s: findNext(str,dict)) {
							//找到所能走的String列表 对每个元素进行检查
							if(!lst.contains(s)) {
								queue.add(s);
								lst.add(s);
							}
						}
					}
				}
			}
		}
		return -1;
	}

	private ArrayList<String> findNext(String str, Set<String> dict) {
		ArrayList<String> lst=new ArrayList<String>();
		//生成一个潜在的可能列表
			for(int i=0; i< str.length();i++) {
				//对于每个位置的转化的可能性进行遍历
				for(char c='a'; c <='z'; c++) {
			    //对于转化的可能性 a-z 进行遍历
				char[] array=str.toCharArray();
				//每次变一个元素 我们生成一个新的array
				array[i]=c;
				//把i处的值换成我们可能的元素
				if(c!=str.charAt(i)){
					//如果转变的新元素和原来不同
				    String temp=new String(array);
				    //利用array生成一个新的string
				    if(dict.contains(temp)){
				    	//如果字典中包括这个string 我们加入可能的列表
					    lst.add(temp);
				    }
				}
			}
		}
		return lst;
	}
	
	//这种方法improve了hasNext的方法 因为hasNext的检查是对于整个dictionary的长度进行遍历 去找可能有的值加入队列 
	//而通过每个位置的改变去找可能的队列只用遍历str的长度
	
}
