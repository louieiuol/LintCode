/* 
 * @(#)BiasedRandom.java	1.0 1/27/2003
 *
 * COPYRIGHT EVAN MACHUSAK (UNIVERSITY OF MARYLAND, COLLEGE PARK), 2003
 * ALL RIGHTS RESERVED
 */
 
package cmsc420.util;

/** A random number generator whose <tt>nextBoolean</tt> method returns <tt>true</tt> with a given
  * probability.  Other methods of <tt>Random</tt> are unaffected.
  *
  * @author Evan Machusak (<a href="mailto:emach@cs.umd.edu">emach@cs.umd.edu</a>)
  * @version 1.0
  * @see java.util.Random
  */
public class BiasedRandom extends java.util.Random {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6688693505932098978L;
	
	/** The probability that <tt>nextBoolean</tt> will return <tt>true</tt>.
	  * @since 1.0
	  */
	protected final double p;

	/** Creates a new instance of <tt>BiasedRandom</tt> with the specified probability.
	  * @param p the probability that <tt>nextBoolean</tt> will retun <tt>true</tt>
	  * @since 1.0
	  */
	public BiasedRandom(float p) { this.p = p; }
	/** Creates a new instance of <tt>BiasedRandom</tt> with the specified probability.
	  * @param p the probability that <tt>nextBoolean</tt> will retun <tt>true</tt>
	  * @since 1.0
	  */
	public BiasedRandom(double p) { this. p = p; }
	
	/** Creates a new instance of <tt>BiasedRandom</tt> with the specified probability and seed.
	  * @param p the probability that <tt>nextBoolean</tt> will retun <tt>true</tt>
	  * @param seed the random seed for this generator
	  * @since 1.0
	  */	
	public BiasedRandom(float p, long seed) { this(p); this.setSeed(seed); }

	/** Creates a new instance of <tt>BiasedRandom</tt> with the specified probability and seed.
	  * @param p the probability that <tt>nextBoolean</tt> will retun <tt>true</tt>
	  * @param seed the random seed for this generator
	  * @since 1.0
	  */	
	public BiasedRandom(double p, long seed) { this(p); this.setSeed(seed); }
	
	/** Returns <tt>true</tt> with the probability given by <tt>p</tt>.
	  * @return <tt>true</tt> with the probability <i>p</i>, 
	  * 	<tt>false</tt> with the probability <i>1-p</i>
	  * @since 1.0
	  */
	public boolean nextBoolean() { return (this.nextDouble() < p); }
}
		
