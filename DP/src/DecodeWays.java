//512. Decode Ways
//中文English
//A message containing letters from A-Z is being encoded to numbers using the following mapping:
//
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
//Given an encoded message containing digits, determine the total number of ways to decode it.
//
//Example
//Example 1:
//
//Input: "12"
//Output: 2
//Explanation: It could be decoded as AB (1 2) or L (12).
//Example 2:
//
//Input: "10"
//Output: 1

public class DecodeWays {
	public int numDecodings(String s) {
		if(s == null || s.length() == 0) return 0;
		char[] sArray=s.toCharArray();
		int[] dp=new int[s.length()+1]; 
		//新建dp array由于是f(n+1)=f(n)(s[i]!=0)+f(n-1)(two digit within 10-26)的关系 我们需要确定 dp[0] 和 dp[1]的初始值 所以需要额外的一个位置来记录初始
		dp[0]=1; //初始值设为1 默认至少有一种分解方式
		dp[1]=sArray[0] == '0'? 0:1; //单个的0是没有任何分解方式的 如果非0则有一种分解方式
		for(int i=1; i<sArray.length; i++) {
			if(sArray[i] != '0') {
				//当值不为0时 先考虑个位数的加和 就是把dp[i]的可能性的加入当前dp[i+1]
				dp[i+1]+=dp[i];
				//dp array比string array长1 所以当string 走到i时 对应的坐标是i+1 
			}
			//当值位0是我们只考虑他两位数时的加和不考虑个位数的加和了
			int two=Integer.parseInt(String.valueOf(sArray[i-1]))*10+Integer.parseInt(String.valueOf(sArray[i]));
			if(two >=10 && two <=26) {
				//当前面和当前两位数的值在10~26之间时需要加入dp[i-1]前面一位的数值 加入给当前dp[i+1]
				dp[i+1]+=dp[i-1];
				//之前已经加过dp[i]了 （如果不为0的情况 我们直接加dp[i] 之后在考虑两位数的情况）
			}
		}
		return dp[dp.length-1];
	}
}
