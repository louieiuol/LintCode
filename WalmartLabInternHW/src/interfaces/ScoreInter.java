package interfaces;
/**
 * 
 * @author Guanhua Liu
 * 
 * Basic Score interface defines 1 get method.
 * 
 * Muti-factor score class can inherit from this.
 * 
 * Every Order has been delivered must have a score.
 * 
 */
public interface ScoreInter {
	
	/**
	 * 
	 * @return integer
	 * 
	 * Rank is based on waiting time.
	 * Waiting Time = Time user get the goods - Time when user make an order
	 * Waiting Time < 0 : Invalid
	 * 0 <= Waiting Time <= 1 hour 30 minutes                 : Promoter
	 * 1 hour 30 minutes < Waiting Time <= 3 hours 40 minutes : Neutral
	 * 3 hours 40 minutes < Waiting Time                      : Detractor
	 * 
	 * if it is promoter return 2
	 * if it is neutral return 1
	 * if it is detractor return 0
	 * 
	 */
	public int getRank();
}
