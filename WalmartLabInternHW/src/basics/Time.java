package basics;
/**
 * 
 * @author Guanhua Liu
 * This class is used for implementing Time interface
 * 
 */
public class Time {
	private long time;
	private int hour;
	private int minute;
	private int second;
	// a Time contains a absolute time in second
	//, and three relative time as hour, minute, and second
	public Time(Long time) {
        if(time >= 24 * 60 * 60 ) {
        	//time initialize out of bounds
            throw new IndexOutOfBoundsException();
        } else {
        	this.time=time;
        	
        	//conversion
            this.hour = (int) (time/3600);
            this.minute = (int) (time - this.hour * 3600)/60;
            this.second = (int) (time-this.hour * 3600-this.minute * 60);
        }
	}
	
	//initialize time as together
	public Time(String time){
		//match time to HH:MM:SS
		if(time.matches("\\d{2}:\\d{2}:\\d{2}")) {
			this.time = strToInt(time);
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	//initialize time as hour, minute, and second
	public Time(int hour, int minute, int second) {
        if(hour >= 0 && hour < 24 && minute >= 0 && minute < 60 && second >=0 && second < 60) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
            this.time= hour * 3600 + minute * 60 + second;
        }else {
        	throw new IndexOutOfBoundsException();
        }
    }

	//input string convert to Time object
	public int strToInt(String time) {
		
		String[] temp = time.split(":");
		this.hour = Integer.parseInt(temp[0]);
		this.minute = Integer.parseInt(temp[1]);
		this.second = Integer.parseInt(temp[2]);
		// check whether the parts are valid
		if(hour >= 0 && hour < 24 && minute >= 0 && minute < 60 && second >=0 && second < 60) {
			int deadline = hour * 3600 + minute * 60 + second;
			return deadline;
		} else {
			throw new IndexOutOfBoundsException();
		}	
	}
	
	
	
	public int getHour() {
        return hour;
    }

	public String toHour() {
		String res="";
        if(hour>=0 && hour<10) {
        	res ="0"+Integer.toString(hour);
        	return res;
        }else {
        	return Integer.toString(hour);
        }
    }

    public int getMinute() {
        return minute;
    }

    public String toMinute() {
		String res="";
        if(minute>=0 && minute<10) {
        	res ="0"+Integer.toString(minute);
        	return res;
        }else {
        	return Integer.toString(minute);
        }
    }

    public int getSecond() {
        return second;
    }
    
	public String toSecond() {
		String res="";
        if(second>=0 && second<10) {
        	res ="0"+Integer.toString(second);
        	return res;
        }else {
        	return Integer.toString(second);
        }
	}
	
	public long getAbsoluteTime() {
		return this.time;
	}

	public boolean isValid() {
		if(this.getAbsoluteTime() > 22*60*60) {
			return false;
		}
		return true;
	}

}
