/** 
*The purpose of this class is to model a television
*Your name and today’s date
*/
public class Television {
	private final String manufacture;
	/*The manufacturer attribute will hold the brand name.*/
	private final float screenSize;
	/*The screenSize attribute will hold the size of the television screen.*/
	private boolean powerOn;
	/*The powerOn attribute will hold the value true if the power is on, 
	 * and false if the power is off.*/
	private int channel, volume;
	/*The channel attribute will hold the value of the station that the
	 *  television is showing, and the volume attribute will hold a number 
	 *  value representing the loudness
	 * */
	public Television(String manufacture, float screenSize){
		this.manufacture=manufacture;
		this.screenSize=screenSize;
		this.powerOn=false;
		this.volume=20;
		this.channel=2;
	}
	/* Create a constructor definition that has two parameters, a manufacturer’s brand and a screen size. 
	 * assign the values taken in from the parameters to the corresponding fields.
	 * Initialize the powerOn field to false (power is off), 
	 * the volume to 20, and the channel to 2.*/
	
	public int getVolume() {
		return this.volume;
	}
	public int getChannel() {
		return this.channel;
	}
	
	public String getManufacturer() {
		return this.manufacture;
	}
	
	public float getScreenSize() {
		return this.screenSize;
	}
	/*Define accessor methods called getVolume, getChannel, getManufacturer, and
      getScreenSize that return the value of the corresponding field.*/
	
	public void setChannel(int channel) {
		this.channel=channel;
	}
	/*Define a mutator method called setChannel accepts a value to be stored in the
     channel field*/
	
	public void power() {
			this.powerOn=!this.powerOn;
	}
	
	public void increaseVolume() {
			this.volume++;
	}
	
	public void decreaseVolume() {
			this.volume--;
	}
	
	
}
