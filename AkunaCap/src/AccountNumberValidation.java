
public class AccountNumberValidation {
	
	public boolean isValid(String accountNumber) {
		if(accountNumber == null || accountNumber.length() != 8) return false;
		String number=accountNumber.substring(2,8);
		int dec=Integer.parseInt(number, 16);
		int sum=0;
		for(char c: Integer.toString(dec).toCharArray()) {
			sum+=Character.getNumericValue(c);
		}
		return accountNumber.substring(0,2).equals(Integer.toHexString(sum).toUpperCase());
	}
}
