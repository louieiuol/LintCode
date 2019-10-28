	/**
	 * 
	 * @author Guanhua Liu
	 * Basic Time interface defines 5 get methods, 4 convert methods, and 1 checking method.
	 * 
	 * Year/Month/Date and other sub-type time methods can inherent from this.
	 * 
	 * Every order has been created must have a time. 
	 * 
	 */

package interfaces;

public interface TimeInter {
		/**
		 * 
		 * @param String
		 * @return int 
		 * strToInt() will convert from string to 
		 * integer contains current time in hour, 
		 * minute, and second.
		 * 
		 * The input string cannot be null and valid format
		 *  
		 */
		public int strToInt(String time);
		
		/**
		 * 
		 * @return String
		 * getHour() will return a integer as in current hour format
		 * 
		 */		
		
		public int getHour();

		/**
		 * 
		 * @return String
		 * longToStr() will convert current time from 
		 * long to hour in String
		 * 
		 */
		
		public String toHour();
		
		
		/**
		 * 
		 * @return String
		 * getHour() will return a integer as in current minute format
		 * 
		 */	
		
	    public int getMinute();

		/**
		 * 
		 * @return String
		 * longToStr() will convert current time from 
		 * long to minute in String
		 * 
		 */
	    public String toMinute();

		/**
		 * 
		 * @return String
		 * getHour() will return a integer as in current second format
		 * 
		 */	
	    public int getSecond() ;
	    

		/**
		 * 
		 * @return String
		 * longToStr() will convert current time from 
		 * long to minute in String
		 * 
		 */
	    
		public String toSecond();
		
		/**
		 * @return long
		 * getAbsoluteTime() will convert current hour:minute:second
		 * as a value in long type 
		 * 
		 */
		public long getAbsoluteTime();

		/**
		 * @return boolean
		 * isValid() will check Time object is in valid format
		 * HH:MM:SS
		 *  0<= hour <24
		 *  0<= minute < 60
		 *  0<= second < 60
		 *  
		 *  if all are in valid format return true
		 *  otherwise return false
		 */
		
		public boolean isValid();

}
