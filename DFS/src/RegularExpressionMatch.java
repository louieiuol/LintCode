
public class RegularExpressionMatch {
    public boolean isMatch(String s, String p) {
        // write your code here
        if(s.length() == 0) {
			return emptyStringCheck(p);
		}
        //if s is empty, we require p is empty or p is even length and all odd index char are *
		
		if(p.length() == 0) {
			return false;
		}
		//if s not empty, but p is empty return false
		
		if(p.length()<=1) {
			//if p only contains one char
			if(s.length()>1) {
				return false;
			}
			//if s length greater than p return false
			return compare(s.charAt(0),p.charAt(0));
			//compare first char in s and p
		}
		
		if(p.charAt(1)=='*') {
			//if second index of char in p is *
			if(compare(s.charAt(0), p.charAt(0))) {
				//compare whether first char can match
				return (isMatch(s.substring(1), p) || isMatch(s, p.substring(2)));
				//if match return either s remove first char or p remove first two char
			}else {
				return isMatch(s, p.substring(2));
				//if not match return p remove first two char
			}
		}else {
			//if second index of char in p is not *
			if(compare(s.charAt(0),p.charAt(0))) {
				//compare the first element 
				return isMatch(s.substring(1), p.substring(1));
				//if true move to next substring
			}else {
				return false;
				//if false return false
			}
		}
    }
    
    
    	//compare two chars 
    	private boolean compare(char s, char p) {
		// TODO Auto-generated method stub
		if(s == p || p == '.') {
			//if s is same as p or p is '.', return true
			return true;
		}
		return false;
		//return false
	}

	private boolean emptyStringCheck(String p) {
		// TODO Auto-generated method stub
		if(p.length()==0) {
			return true;
		}
		//if p is 0 length return true;
		if(p.length() %2 != 0) {
			return false;
			//require p is even length
		}
		
		for(int i=1; i<p.length(); i+=2) {
			//require in each odd index p is *
			if(p.charAt(i)!= '*') {
				return false;
			}
		}
		return true;
	}
}
