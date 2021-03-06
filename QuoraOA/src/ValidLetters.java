//输入一组words 和一组valid letters，判断有多少个words 是valid。判断条件是words
//里的所有upper and lower letter 必须在valid letters 里面。如果word 里面有special
//character 不用管。注意valid letter 只有小写，但是words 里面有大写的也算valid。比如words
//= [hEllo##, This^^], valid letter = [h, e, l, 0, t, h, s]; "hello##" 就是valid，因为h，e，l，o
//都在valid letter 里面， “This^^” 不valid, 因为i 不在valid letter 里面

import java.util.*;
public class ValidLetters {
	public int countValidLetters(String[] words, char[] validLetters) {
		HashSet<Character> set=new HashSet<>();
		for(char c: validLetters) {
			set.add(c);
		}
		int count=0;
		for(String word : words) {
			boolean isValid=true;
			for(int i=0; i<word.length(); i++) {
				if(Character.isAlphabetic(word.toLowerCase().charAt(i))) {
					if(!set.contains(word.charAt(i))) {
						isValid=false;
						break;
					}
				}
			}
			if(isValid) {
				count++;
			}
		}
		return count;
	}
}
