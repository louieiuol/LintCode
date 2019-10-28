	/**
	 * 
	 * @author Guanhua Liu
	 * 
	 * Basic Position interface defines 5 get methods
	 * 
	 * 3d position class can inherit from this.
	 * 
	 * Once a position has been generated, it cannot be modified.
	 * 
	 */

package interfaces;

public interface PositionInter {
	/**
	 * 
	 * @return String  
	 * getPos() will return an String contains current position.
	 * Each order must has its Position.
	 *  
	 */
	public String getPos() ;
	
	/**
	 * 
	 * @return long  
	 * getOrderDistanceInMinutes() will return current position
	 * related to origin as a long counted in minutes
	 * Each order must has its Position.
	 *  
	 */
	
	public long getOrderDistanceInMinute();
	
	/**
	 * 
	 * @return long  
	 * getOrderDistance() will return current position
	 * related to origin as a long counted in seconds
	 * Each order must has its Position.
	 *  
	 */ 
	
	public long getOrderDistanceInSecond();
	
	/**
	 * 
	 * @return long  
	 * getxCoord() will return current position's x coordinate
	 * related to origin as a long counted in seconds
	 * Not used in this project, but may need in future.
	 *  
	 */
	
	public long getxCoord();

	/**
	 * 
	 * @return long  
	 * getyCoord() will return current position's y coordinate
	 * related to origin as a long counted in seconds
	 * Not used in this project, but may need in future.
	 *  
	 */
	
	public long getyCoord();
	
}
