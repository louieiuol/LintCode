package data;
/**
 * 
 * @author Guanhua Liu
 * This class is used for validate input data 
 * 
 */


public class DataValidator {
	private String id;
	private String location;
	private String time;
	//Initialize variables
	public DataValidator(String id, String location, String time) {
		this.id=id;
		this.location=location;
		this.time=time;
	}
	
	public boolean validate() {
		//cannot be null
		if(id == null && location ==null && time==null) {
			return false;
		}
		
		//each length has own restrictions
		if(id.length() < 3 && location.length() <4 && time.length() != 8) {
			return false;
		}
		//checking if direction format is valid
		if(id.charAt(0) != 'W' || id.charAt(1) !='M' ) {
			return false;
		}
		
		if(!id.substring(2,id.length()).matches("^[0-9]+$")) {
			return false;
		}

		if(location.charAt(0) != 'N' && location.charAt(0) !='S') {
			return false;
		}
		//checking location doesn't contains other characters
		for(int i=1; i<location.length() ; i++) {
			char c=location.charAt(i);
			if(Character.isDigit(c) || Character.isLetter(c)) {
				if(Character.isLetter(c)) {
					if(c != 'E' && c !='W') {
						return false;
					}
				}
			}else {
				return false;
			}
		}
		//time should match HH:MM:SS format
		if(!time.matches("^(?:(?:([01]?\\d|2[0-3]):)?([0-5]?\\d):)?([0-5]?\\d)$")) {
			return false;
		}
		return  true;
	}
}
