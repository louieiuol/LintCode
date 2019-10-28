//643. Longest Absolute File Path
//中文English
//Suppose we abstract our file system by a string in the following manner:
//
//The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
//
//dir
//    subdir1
//    subdir2
//        file.ext
//The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
//The string
//
//"dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
//represents:
//
//dir
//    subdir1
//        file1.ext
//        subsubdir1
//    subdir2
//        subsubdir2
//            file2.ext
//The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
//
//We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).
//
//Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.
//
//Example
//Example1
//
//Input: "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
//Output: 20
//Explanation:
//See description for details.
//Example2
//
//Input: "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
//Output: 32
//Explanation:
//See description for details.
//Notice
//The name of a file contains at least a . and an extension.
//The name of a directory or sub-directory will not contain a ..
//Time complexity required: O(n) where n is the size of the input string.
//Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.


public class LongestAbsoluteFilePath {
	 public int lengthLongestPath(String input) {
		 if(input == null) return 0;
		 if(input.length() == 0) return 0;
		 //因为会用到split() 需要确定字符串不为空
		 int res=0;
		 int[] prefix=new int[input.length()+1];
		 //开一个前缀和数组 记录每次更新的长度和 大小是因为要能装下最多level的可能性
		 //index 是第level层数 value是上次访问的路径的长度 每走到下一步覆盖之前的长度
		 //对每次level中的path长度 进行求和 该前缀和数组只保留路径的长度 但不保留文件名
		 for(String str: input.split("\n")) {
			 //根据\n来分离一个个的行数
			 int level=str.lastIndexOf("\t")+2; //找到最后一个\t的位置 因为\t都在每个line开始的前面 几个\t就说明在第几行
			 //+2因为利用了前缀和数组 如果为空的时候会返回-1+2后是第0行 对应前缀和数组的index 1
			 int len=str.length()-level+1; //该绝对路径的长度是等于长度减去\t的位置 +1是因为我们多减了一个0的位置
			 if(str.contains(".")) {
				 //如果是文件名 我们计算长度比并且与最大值比较 计算长度通过上个前缀和数组的位置：意思是之前到上个文件夹下的路径总长度+当前length
				 res=Math.max(res, prefix[level-1]+len);
			 }else {
				 //如果是路径 我们只更新前缀和数组 是上次前缀和加上当前值 +1 因为考虑路径符号'/'
				 prefix[level]=prefix[level-1]+len+1;
			 }
		 }
		 return res;
	 }
}
