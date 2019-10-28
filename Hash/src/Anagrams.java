/*171. Anagrams
中文English
Given an array of strings, return all groups of strings that are anagrams.

Example
Example 1:

Input:["lint", "intl", "inlt", "code"]
Output:["lint", "inlt", "intl"]
Example 2:

Input:["ab", "ba", "cd", "dc", "e"]
Output: ["ab", "ba", "cd", "dc"]*/
import java.util.*;
public class Anagrams {
    List<String> res=new ArrayList<String>();
    public List<String> anagrams(String[] strs) {
    	if(strs == null || strs.length == 0) return res;
        HashMap<String,ArrayList<String>> map=new HashMap<>();
        //建立哈希表 key装排序好的string 作为查找 value装数组中出现的相同element的本体
        for(int i=0; i<strs.length; i++){
            char[] charArray=strs[i].toCharArray();
            //转换成 char array
            Arrays.sort(charArray);
            //进行排序
            String temp=new String(charArray);
            if(!map.containsKey(temp)){
            	//如果没出现该key 创建新的key-value
                map.put(temp,new ArrayList<String>());
            }
            map.get(temp).add(strs[i]);
            //把当前元素加入
        }
        for(Map.Entry<String, ArrayList<String>> entry: map.entrySet()){
            if(entry.getValue().size()>=2){
            	//寻找长度大于等于2 的表单，加入所有元素
                res.addAll(entry.getValue());
            }
        }
        return res;
    }
}
