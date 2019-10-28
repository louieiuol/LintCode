//927. Reverse Words in a String II
//中文English
//Given an input character array, reverse the array word by word. A word is defined as a sequence of non-space characters.
//
//The input character array does not contain leading or trailing spaces and the words are always separated by a single space.
//
//Example
//Example1
//
//Input: s = "the sky is blue"
//Output: "blue is sky the"
//Example2
//
//Input: "a b c"
//Output: "c b a"
//Challenge
//Could you do it in-place without allocating extra space?

//推荐使用这种做法 in-place O(n)

public class ReverseWordsInStringII {
    public char[] reverseWords(char[] str) {
        // write your code here
        if(str == null || str.length == 0) return str;
        reverse(str, 0, str.length-1);
        int index=0;
        for(int i=0; i<str.length; i++){
            if(Character.isWhitespace(str[i])){
                reverse(str, index, i-1);
                index=i+1;
            }
        }
        reverse(str, index, str.length-1);
        return str;
    }
    
    private void reverse(char[] str, int start, int end){
        while(start<=end){
            char temp=str[start];
            str[start]=str[end];
            str[end]=temp;
            start++;
            end--;
        }
    }
}
