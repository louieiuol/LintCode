
public class SumAllDigits {
	//很简单，一个数字，求所有位数的乘积减去所有位数的和。 230 -》 0 - 6 = -6
	static int sumAllDigits(int i) {
		int product = 1;
		int sum = 0;
		while(i != 0) {
		int digit = i % 10;
		i /= 10;
		product *= digit;
		sum += digit;
		}
		return product - sum;
		}
}
