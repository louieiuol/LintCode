
public class ReverseWord {
	public char[] reverseWord(char[] str) {
        // write your code here
        int len=str.length;
        str=reverse(str,0,len-1);
        //first reverse entire array
        //len-1 is make sure reverse in bound
        int i=0, j=1;
        while(j<str.length){
            if(Character.isWhitespace(str[j])){
            	//reverse phase again by each whitespace
                str=reverse(str,i,j-1);
                i=++j;
            }
            j++;
        }
        str= reverse(str,i,len-1);
        //reverse the last section seperate by whitespace
        return str;
    }
    
    private char[] reverse(char[] str,int i ,int j){
        while(i<j){
            char tmp=str[i];
            str[i]=str[j];
            str[j]=tmp;
            i++;
            j--;
        }
        return str;
    }
}
