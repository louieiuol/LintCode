//419. Roman to Integer
//中文English
//Given a roman numeral, convert it to an integer.
//
//The answer is guaranteed to be within the range from 1 to 3999.
//
//Example
//Example 1:
//
//Input: "IV"
//Output: 4
//Example 2:
//
//Input: "XCIX"
//Output: 99
//Clarification
//What is Roman Numeral?
public class IntegerToRoman {
	 public String intToRoman(int n) {
		 if(n<1 || n>3999) return "";
		 String[] M= {"","M","MM","MMM"}; //罗列 千位上的4种情况 0 1000 2000 3000 此时不考虑其他位
		 String[] C= {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"}; //罗列 百位上的10种情况 0 100 200 300 400 ... 800 900
		 String[] X= {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"}; //罗列 十位上的10种情况 0 10 20 30 40 ... 80 90
		 String[] I= {"","I","II","III","IV","V","VI","VII","VIII","IX"}; //罗列 个位上的10种情况 0 1 2 ... 8 9
		 return M[(n/1000)%10]+C[(n/100)%10]+X[(n/10)%10]+I[n%10];
		 //n/1000 %10 拿到千位上的数 消去多少位用/10^(n) %10是拿到当前位置的数 一起连用
		 //n/100 %10 拿到百位上的数
		 //n/10 %10 拿到十位上的数
		 //n/1 %10 拿到个位上的数 
	 }
}
