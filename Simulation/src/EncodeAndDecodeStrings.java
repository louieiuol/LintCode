//659. Encode and Decode Strings
//中文English
//Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
//
//Please implement encode and decode
import java.util.*;
public class EncodeAndDecodeStrings {
//1. ["lint","code"] encode as "4:lint4:code"
    public String encode1(List<String> strs) {
        // write your code here
        StringBuilder sb=new StringBuilder();
        //String 和StringBuilder不同 String比较大可能会超内存
        for(String str: strs){
            int len=str.length();
            sb.append(len);
            sb.append(":");
            sb.append(str);
        }
        return sb.toString();
    }
    public List<String> decode1(String str) {
        // write your code here
        List<String> res=new ArrayList<String>();
        char[] cArray=str.toCharArray();
        int counter=0;
        for(int i=0; i<cArray.length; i++){
            if(cArray[i] == ':'){
            	//find where char is ":"
                int len=Integer.parseInt(str.substring(counter,i));
                //find length of string should read
                //original string from counter to i
                String curr=str.substring(i+1,i+1+len);
                //read length of string after ":" +1
                //original string from i+1 to i+1+length
                res.add(curr);
                counter=i+1+len;
                //move counter to the position is length of string + location of ":" +1
            }
        }
        //要考虑字符串中自带冒号的可能性 但是由于我们是从左往右去找冒号 所以找到第一个冒号后会读数 会把对应长度的字符（可能带冒号给读到List里进）
        //所以这个方法可以解决这个问题
        return res;
    }
    //2. 用两个字符的转译字符表示连接符 用 :表转译 ;表分隔  遇到:时候要看后面的是什么符号 :: -> :本身 :; -> ;
    //eg: abc def -> abc:;def:;
    //    ab:c def -> ab::c:;def:;
    //    ab:;c def -> ab::;c:;def:; 用一个:可以连接两个 :;
    
    public String encode2(List<String> strs) {
    	StringBuilder sb=new StringBuilder();
    	for(String str: strs) {
    		for(char c: str.toCharArray()) {
    			//拆解成一个个字符
    			if(c == ':') {
    				//如果遇到:连接两个::
    				sb.append("::");
    			}else {
    				//如果不是则直接连
    				sb.append(c);
    			}
    		}
    		//每个结尾连接:;
    		sb.append(":;");
    	}
    	return sb.toString();
    }
    
    public List<String> decode(String str){
    	List<String> res=new ArrayList<String>();
    	char[] sc=str.toCharArray();
    	StringBuilder sb=new StringBuilder();
    	int counter=0;
    	while(counter<sc.length) {
    		//因为counter此时不一定每次+1 所以用while 
    		if(sc[counter] == ':') {
    			//遇到转译字符要看后面的是什么
    			if(sc[counter+1] == ';') {
    				//代表空行 那么加入result 
    				res.add(sb.toString());
    				//把string设为空 
    				sb=new StringBuilder();
    				counter +=2;
    				//counter往前移动2位
    			}else {
    				//代表遇到了:符号 
    				sb.append(sc[counter+1]);
    			    //直接加入该符号
    				counter+=2;
    				//counter往前移动2位
    			}
    		}else {
    			sb.append(sc[counter]);
    			//是普通字符直接加入
    			counter+=1;
    			//counter 往前移动一位
    		}
    	}
    	return res;
    }
    
}
