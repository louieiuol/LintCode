//1305. Integer to English Words
//中文English
//Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
//
//Example
//123 -> "One Hundred Twenty Three"
//12345 -> "Twelve Thousand Three Hundred Forty Five"
//1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"


public class IntegerToEnglishWords {
	String[] in_twenty={"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
			"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen","Sixteen",
			"Seventeen", "Eighteen", "Nineteen", "Twenty"};
	String[] in_hundred={"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	String[] over_thousand={"", "Thousand", "Million", "Billion"};
	public String numberToWords(int num) {
		// Write your code here
		if(num == 0) return "Zero";
		//0单独拿出来 因为其他位上的0不单独转换为英文
		String res="";
		int times=0;
		while(num > 0){
			//到0为止停止循环 最大可以到billion 所以有3次
			String pre=getWithinThousandNumber(num % 1000);
			//计算出每个1千以内的数转换成英文的词 
			if(pre != ""){
				//如果该1000位内数字不为0
				//因为我们把最新结果加到前面 其实是从小往大加的 第一次是thousand  之后 million 之后 billion 
				res=pre+" "+over_thousand[times]+" "+res;
			}
			// /1000确定下一个千位的数字 
			num/=1000;
			//看到底是第几个千位了
			times++;
		}
		//返回记得trim() 掉多余空格
		return res.trim();
	}

	private String getWithinThousandNumber(int n){
		String s="";
		//如果为0直接省略 
		if(n < 20){
			//20以内直接找对应的
			s=in_twenty[n];
		}else if(n < 100){
			// /10确定十位上数字 %10确定个位上数字 
			s=in_hundred[n/10]+" "+in_twenty[n%10];
		}else{
			// /100确定一百位上数字 %100确定百位一下的数字 注意如果不使用迭代 要判断是不是空string 
			s=in_twenty[n/100]+" Hundred "+getWithinThousandNumber(n%100);
		}
		//每次trim掉空的值 
		return s.trim();
	}
}
