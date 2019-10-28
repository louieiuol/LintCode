
public class LastAndSecondLast {

	public String lastLetters(String word) {
		if(word == null || word.length() == 0) return "";
		if(word.length() == 1) return word;
		char[] result=new char[3];
		result[2]=word.charAt(word.length()-1);
		result[1]=' ';
		result[0]=word.charAt(word.length()-2);
		return new String(result);
	}
}
