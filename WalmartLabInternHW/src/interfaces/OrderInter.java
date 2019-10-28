	/**
	 * 
	 * @author Guanhua Liu
	 * Basic Order interface defines 4 getters methods, 3 checking methods
	 * 
	 * Other sub-type orders can inherit from this.
	 * Other sub-type Order interface can extend
	 * from this interface.
	 * 
	 * Once a order has been generated, it cannot be modified.
	 */

package interfaces;

import basics.Order;
import basics.Position;
import basics.Time;

public interface OrderInter {
	
	/**
	 * @return Position 
	 * getPos() will return an Position type.
	 * Each order must has its Position.
	 * 
	 */
	public Position getPos() ;
	
	/**
	 * @return Time 
	 * getTime() will return an Time type.
	 * Each order must has its Time.
	 * 
	 */
	
	public Time getTime();
	
	/**
	 * @return String
	 * getId() will return an string.
	 * Each order must has its id.
	 * 
	 */
	
	public String getId();
	
	/**
	 * @return String
	 * getId() will return an string contains only number.
	 * Each order must has its id.
	 * 
	 */
	
	public int getParsedId();
	
	
	/**
	 * @param Order 
	 * @return String 
	 * compare(Order) will return 1 if this has time greater than other,
	 *  return -1 if this has time less than other,
	 *  return 0 if this two order has same time.
	 */
	public int compare(Order o);

	
	/**
	 * 
	 * @return boolean 
	 *  isDeliverable() will return true if this has time 
	 *  less than or equal to day end-time,
	 *  otherwise return false.
	 *  
	 */
	
	public boolean isDeliverable() ;

	/**
	 * @param long
	 * @return boolean 
	 *  isDeliverable() has a param long type, 
	 *  will overload previous method.
	 *  It will return true if its deliverable
	 *  within given param time,  
	 *  otherwise return false.
	 *  
	 */
	
	
	public boolean isDeliverable(long start);

	/**
	 * 
	 * @return String
	 * Override Object toString() method.
	 * It will return a order string.
	 *  
	 */
	@Override
	public String toString();
}
