//center point emulation 
public class LongestPanlindrom {
	public String longestPanlindrom(String s) {
		if(s==null || s.length()==0) {
			return "";
		}
		
		int start=0, len=0, longest=0;
		for(int i=0; i<s.length();i++) {
			len=findLengthPanlindrom(s,i,i);
			if(len>longest) {
				longest=len;
				start=i-len/2;
			}
			
			len=findLengthPanlindrom(s,i,i+1);
			if(len>longest) {
				longest=len;
				start=i-len/2+1;
			}
		}
		return s.substring(start, start+longest);
			
	}
	
	private int findLengthPanlindrom(String s, int left, int right) {
		int len=0;
			while(left>=0 && right<s.length()) {
				if(s.charAt(left) != s.charAt(right)) {
					break;
				}				
				len+= left == right ? 1:2;
				left--;
				right++;
			}
			return len;
	}

}
