
public class ThreeStepReverse {
	public String threeStepLeftReverse(String s, int k) {
		int len=s.length();
		s=reverse(s,0,len-1);
		s=reverse(s,0,len-k-1);
		s=reverse(s,len-k,len-1);
		return s;
	}
	public String threeStepRightReverse(String s, int k) {
		int len=s.length();
		s=reverse(s,0,len-1);
		s=reverse(s,0,k-1);
		s=reverse(s,k,len-1);
		return s;
	}

	public boolean rotateCompare(String s1, String s2) {
		int i=0;
		while(i < s1.length()) {
			if(threeStepLeftReverse(s1,i).compareTo(s2)==0) {
				return true;
			}
			i++;
		}
		return false;
	}
	
	private String reverse(String s, int i, int j) {
		//two pointers method
		
		while(i<j) {
			s=s.substring(0,i) + s.charAt(j)+s.substring(i+1,j)
			+s.charAt(i)+s.substring(j+1,s.length());
			i++;
			j--;
		}
		return s;
	}
}
