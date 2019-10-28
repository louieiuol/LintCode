import java.util.*;
public class BeforeAndAfterPuzzle {
	public String[] beforeAndAfterPuzzle(String[] input) {
		List<String> result=new ArrayList<>();
		if(input == null || input.length == 0) return new String[]{};
		HashMap<String, List<String>> map=new HashMap<>();
		for(String str: input) {
			if(str.length() != 0) {
				String[] word=str.split(" ");
				map.putIfAbsent(word[0], new ArrayList<>());
				map.get(word[0]).add(str.substring(word[0].length()));
			}
		}
		for(String str: input) {
			if(str.length() != 0) {
				if(str.length() != 0) {
					String[] word=str.split(" ");
					String last=word[word.length-1];
					if(map.containsKey(last)) {
						for(String s: map.get(last)) {
							String temp=str.concat(s);
							result.add(temp);
						}
					}
				}
			}
		}
		String[] output=new String[result.size()];
		for(int i=0; i<output.length; i++) {
			output[i]=result.get(i);
		}
		return output;
	}
}
