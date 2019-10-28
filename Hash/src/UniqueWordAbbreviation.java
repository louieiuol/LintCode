//648. Unique Word Abbreviation
//中文English
//An abbreviation of a word follows the form . Below are some examples of word abbreviations:
//
//a) it                      --> it    (no abbreviation)
//
//     1
//b) d|o|g                   --> d1g
//
//              1    1  1
//     1---5----0----5--8
//c) i|nternationalizatio|n  --> i18n
//
//              1
//     1---5----0
//d) l|ocalizatio|n          --> l10n
//Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
//
//Example
//Example1
//
//Input:
//[ "deer", "door", "cake", "card" ]
//isUnique("dear")
//isUnique("cart")
//Output:
//false
//true
//Explanation:
//Dictionary's abbreviation is ["d2r", "d2r", "c2e", "c2d"].
//"dear" 's abbreviation is "d2r" , in dictionary.
//"cart" 's abbreviation is "c2t" , not in dictionary.
//Example2
//
//Input:
//[ "deer", "door", "cake", "card" ]
//isUnique("cane")
//isUnique("make")
//Output:
//false
//true
//Explanation:
//Dictionary's abbreviation is ["d2r", "d2r", "c2e", "c2d"].
//"cane" 's abbreviation is "c2e" , in dictionary.
//"make" 's abbreviation is "m2e" , not in dictionary.

import java.util.*;
public class UniqueWordAbbreviation {
    /*
    * @param dictionary: a list of words
    */
	HashMap<String, Integer> originDict;
	HashMap<String, Integer> abbrDict;
	
	//需要两个字典 一个来装字典里原字符串出现的次数 一个来装缩字典里缩写写字符串出现的次数
	//用HashMap来储存每个字符串出现次数
	public UniqueWordAbbreviation(String[] dictionary) {
		// do intialization if necessary
		originDict=new HashMap<>();
		abbrDict=new HashMap<>();
		if(dictionary.length > 0) {
			for(String str: dictionary) {
				originDict.put(str, originDict.getOrDefault(str, 0)+1);
				//用新的JAVA8写法加入字典
				String abbr=getAbbr(str);
				abbrDict.put(abbr, abbrDict.getOrDefault(abbr, 0)+1);
			}
		}
	}
	
    private String getAbbr(String str) {
		//返回一个字符串的缩写
    	if(str.length() >=0 && str.length() <=2) return str;
    	return ""+str.charAt(0)+str.substring(0,str.length()-1).length()+str.charAt(str.length()-1);
	}

	/*
     * @param word: a string
     * @return: true if its abbreviation is unique or false
     */
	public boolean isUnique(String word) {
		//根据性质 如果元素所在原字符串出现次数 等于 元素所在的缩写字符串出现次数 那么证明是Unique
		//如果不等 则不是Unique
		return originDict.get(word) == abbrDict.get(getAbbr(word));
	}
	
}
