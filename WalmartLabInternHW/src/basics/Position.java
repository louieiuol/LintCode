package basics;

import interfaces.PositionInter;
/**
 * 
 * @author Guanhua Liu
 * This class is used for implementing Position interface
 * 
 */
public class Position implements PositionInter {
	private char[] direction={'N','S','E','W'};
	private String dir;
	private long dis;
	private long xCoord;
	private long yCoord;
	//Position consists of x, y coordinates, direction and absolute distance to origin
	public Position(String dir){
		this.dir=dir;
		int i=1;
		for(;;) {
			//search for distance in give string
			if(dir.charAt(i) == direction[0]|| dir.charAt(i) == direction[1]
					|| dir.charAt(i) == direction[2] || dir.charAt(i) == direction[3]) {
				break;
			}
			i++;
		}
			yCoord = Integer.parseInt(dir.substring(1, i)); //y coordinate set as N S 
			xCoord = Integer.parseInt(dir.substring(i+1));  //x coordinate set as E W
		dis = xCoord + yCoord;
		if(dir.charAt(0) ==direction[3]) {
			xCoord= xCoord *(-1);
		}
		
		// Assume E is positive and W is negative,
		// Change current x coordinates from positive to negative if it is W
		
		if(dir.charAt(i) == direction[1]) {
			yCoord=yCoord *(-1);
		}
		
		// Assume N is positive and S is negative,
		// Change current y coordinates from positive to negative if it is S
		
		
	}
	
	public String getPos() {
		return dir;
	}
	
	public long getOrderDistanceInMinute() {
		return dis;
	}
	public long getOrderDistanceInSecond() {
		return dis*60;
	}
	
	public long getxCoord() {
		return xCoord;
	}
	
	public long getyCoord() {
		return yCoord;
	}

}
