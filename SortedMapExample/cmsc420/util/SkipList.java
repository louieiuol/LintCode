/* 
 * @(#)SkipList.java	1.0 12/31/2003
 *
 * COPYRIGHT EVAN MACHUSAK (UNIVERSITY OF MARYLAND, COLLEGE PARK), 2003
 * ALL RIGHTS RESERVED
 */

// useful unicode characters:
// 920 = theta
// 937 = omega
package cmsc420.util;

import java.awt.Color;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

//import cmsc420.drawing.CanvasPlus;
//import cmsc420.drawing.Drawable2D;

/**
 * <style>.u { font-family : "Arial Unicode MS" }</style> An implementation of
 * <tt>SortedMap</tt> based on <a href="http://www.cs.umd.edu/~pugh">Bill Pugh</a>'s
 * skip list data structure. For more information on skip lists, see <a
 * href="ftp://ftp.cs.umd.edu/pub/skipLists/skiplists.pdf"> Skip Lists: A
 * Probabilistic Alternative to Balanced Trees</a>, (<i>Communications of the
 * ACM</i>, 33(6):668--676, June 1990). To quote, &quot;Skip lists are a data
 * structure that can be used in place of balanced trees. Skip lists use
 * probabilistic balancing rather than strictly enforced balancing and as a
 * result the algorithms for insertion and deletion in skip lists are much
 * simpler and significantly faster than equivalent algorithms for balanced
 * trees.&quot;
 * <p />
 * 
 * While skip lists do not <i>guarantee</i> logarithmic time for
 * <tt>containsKey</tt>, <tt>get</tt>, <tt>put</tt>, and
 * <tt>remove</tt> operations, the probability that these operations will take
 * longer than logarithmic time is insignificant. Because this data structure is
 * probabilistic with regard to tower height, randomization is involved. Thus,
 * the <tt>Seedable</tt> interface is implemented to allow users to recreate
 * exact copies of previously known skip lists both in content and structure. By
 * setting the random seed (via <tt>setSeed()</tt>), users can direct the
 * sequence of tower heights to be created during <tt>put</tt> operations to
 * this <tt>SkipList</tt>.
 * <p>
 * 
 * This implementation of skip lists allow users to specify potentially biased
 * random generators. As per the specification for skip lists, let <i>p</i>
 * equal the probability that, during link tower generation, the tower will grow
 * by one element. This decision is based on the return value of the random
 * generator's <tt>nextBoolean()</tt> method; if <tt>true</tt> is returned,
 * the tower grows; if <tt>false</tt> is returned, it does not.
 * {@link java.util.Random}'s <tt>nextBoolean()</tt> method returns
 * <tt>true</tt> with a probability of 0.5, thus we say a skip list using
 * <tt>Random</tt> as its random number generator has the property <i>p = 1/2</i>.
 * <p />
 * 
 * In Pugh's paper, he suggests using a probability <i>p = 1/4</i>. This can be
 * accomplished by passing a subclass of <tt>Random</tt> to this
 * <tt>SkipList</tt> at instantiation time whose <tt>nextBoolean()</tt>
 * method has been overidden to return <tt>true</tt> only 1 out of 4 times.
 * See {@link cmsc420.util.BiasedRandom}. Creating a <tt>BiasedRandom</tt>
 * instance with <i>p = 0</i> would cause this <tt>SkipList</tt> to be
 * structurally equivalent to a linked list, which would automatically create a
 * guaranteed linear-time <tt>Map</tt>.
 * 
 * @author Evan Machusak
 * @version 1.0
 * @see cmsc420.util.BiasedRandom
 */

public class SkipList implements SortedMap, Seedable, Serializable/*, Drawable2D*/ {

	/**
	 * 
	 */
	private static final long serialVersionUID = -603676045420303186L;

	/**
	 * The random generator used by this <tt>SkipList</tt>.
	 * 
	 * @see #generateTower()
	 * @since 1.0
	 */
	protected final Random rand;

	/**
	 * The seed used by the random generator associated with this
	 * <tt>SkipList</tt>.
	 * 
	 * @since 1.0
	 */
	protected long seed;

	/**
	 * The comparator used to sort the keys added to this map.
	 * 
	 * @since 1.0
	 */
	protected Comparator comp;

	/**
	 * The number of mappings present in this map.
	 * 
	 * @since 1.0
	 */
	protected int size;

	/**
	 * The index node of this <tt>SkipList</tt>, whose logical position in
	 * this list is -1.
	 * 
	 * @since 1.0
	 */
	protected final Node head;

	/**
	 * Creates a new, empty <tt>SkipList</tt> using the given ordering, random
	 * generator, and seed.
	 * 
	 * @param comp
	 *            the <tt>Comparator</tt> to associate with this
	 *            <tt>SkipList</tt>, which imposes ordering on this
	 *            <tt>SkipList</tt>'s keyset
	 * @param randomGenerator
	 *            the instance of <tt>Random</tt> whose <tt>nextBoolean()</tt>
	 *            method will dictate this <tt>SkipList</tt>'s tower heights
	 * @param seed
	 *            the seed to apply to this <tt>SkipList</tt>'s random
	 *            generator
	 * @since 1.0
	 */
	public SkipList(Comparator comp, Random randomGenerator, long seed) {
		this.comp = comp;
		this.rand = randomGenerator;
		this.rand.setSeed(seed);

		this.size = 0;
		this.head = new Node(null, null);
		this.head.next = new Node[] { null };
	}

	/**
	 * Creates a link tower with random height adhering to the rules specified
	 * by the random generator associated with this <tt>SkipList</tt>. There
	 * is no upper bound imposed on the height of the generated tower.
	 * 
	 * @return a <tt>Node</tt> array of random height whose entries are
	 *         <tt>null</tt>
	 * @since 1.0
	 */
	protected Node[] generateTower() {
		int height = 1;
		while (rand.nextBoolean())
			++height;
		return new Node[height];
	}

	/**
	 * Increases the height of the index node's tower.
	 * 
	 * @param newHeight
	 *            the desired height of the index tower (must be strictly
	 *            greater than the current height)
	 * @since 1.0
	 */
	private void growHead(int newHeight) {
		// if (newHeight <= head.next.length) throw new
		// IllegalArgumentException("new index height must be greater than
		// current index height");
		Node[] newHead = new Node[newHeight];
		System.arraycopy(head.next, 0, newHead, 0, head.next.length);
		head.next = newHead;
	}

	/**
	 * Returns the seed currently associated with this <tt>SkipList</tt>'s
	 * random generator.
	 * 
	 * @return the seed used by this <tt>SkipList</tt>'s random generator.
	 * @since 1.0
	 */
	public long getSeed() {
		return seed;
	}

	/**
	 * Sets the seed associated with this <tt>SkipList</tt>'s random
	 * generator.
	 * 
	 * @param seed
	 *            the new seed for this <tt>SkipList</tt>'s random generator
	 *            to use
	 * @since 1.0
	 */
	public void setSeed(long seed) {
		rand.setSeed(this.seed = seed);
	}

	/**
	 * Removes all mappings from this map.
	 * 
	 * @since 1.0
	 */
	public void clear() {
		head.next = new Node[] { null };
		size = 0;
	}

	/**
	 * Returns true if this map contains a mapping for the specified key. This
	 * method performs in probabilistic &#920;(log n) time; its formal
	 * complexity is &#937;(1), O(n).
	 * 
	 * @param key
	 *            the key to locate in this <tt>SkipList</tt>
	 * @return <tt>true</tt> if the specified key is present in this
	 *         <tt>SkipList</tt>, <tt>false</tt> otherwise
	 * @since 1.0
	 */
	public boolean containsKey(Object key) {
		return (locate(key) != null);
	}

	/**
	 * Returns true if this map maps one or more keys to the specified value.
	 * This method 's complexity is &#937;(1), O(n).
	 * 
	 * @param value
	 *            the value to locate in this <tt>SkipList</tt>. Equality is
	 *            determined via <tt>value</tt>'s <tt>equals()</tt> method.
	 *            <tt>null</tt> is a legal value for <tt>value</tt>.
	 * @return <tt>true</tt> if <tt>value</tt> is mapped to by some key
	 *         present in this <tt>SkipList</tt>, <tt>false</tt> otherwise.
	 * @since 1.0
	 */
	public boolean containsValue(Object value) {
		if (value == null)
			value = new Object() {
				public boolean equals(Object o) {
					return o == null;
				}
			};
		Node current = head.next[0];
		while (current != null) {
			if (value.equals(current.getValue()))
				return true;
			else
				current = current.next[0];
		}
		return false;
	}

	/**
	 * Returns an iterator over the entries of this <tt>SkipList</tt>.
	 * 
	 * @return an <tt>Iterator</tt> over the entries of this <tt>SkipList</tt>.
	 *         The returned <tt>Iterator</tt>'s <tt>next()</tt> method will
	 *         return objects of type {@link SkipList.Node}
	 * @since 1.0
	 */
	protected Iterator entryIterator() {
		return new EntryIterator();
	}

	/**
	 * Returns a set view of the mappings contained in this map. All public
	 * methods of <tt>Set</tt> are supported in the returned object, but users
	 * of this return value should take caution to avoid
	 * <tt>ClassCastException</tt>s on certain methods (for example, this
	 * returned <tt>Set</tt>'s <tt>add</tt> method expects an instance of
	 * <tt>Map.Entry</tt>).
	 * 
	 * @return an anonymous subclass of <tt>AbstractSet</tt> backed by this
	 *         <tt>SkipList</tt>
	 * @since 1.0
	 */
	public Set entrySet() {
		return new AbstractSet() {
			// Adds the specified element to this set if it is not already
			// present.
			public boolean add(Object o) {
				Object key = ((Map.Entry) o).getKey();
				Object value = ((Map.Entry) o).getValue();
				boolean add = SkipList.this.containsKey(key);
				SkipList.this.put(key, value);
				return add;
			}

			// Adds all of the elements in the specified collection to this set
			// if they're not already present.
			public boolean addAll(Collection c) {
				Iterator i = c.iterator();
				boolean add = false;
				while (i.hasNext())
					add = add || this.add(i.next());
				return add;
			}

			// Removes all of the elements from this set.
			public void clear() {
				SkipList.this.clear();
			}

			// Returns true if this set contains the specified element.
			public boolean contains(Object o) {
				Object key = ((Map.Entry) o).getKey();
				return SkipList.this.locate(key).equals(o);
			}

			// Returns true if this set contains all of the elements of the
			// specified collection.
			public boolean containsAll(Collection c) {
				Iterator i = c.iterator();
				while (i.hasNext()) {
					Map.Entry me = (Map.Entry) i.next();
					if (!SkipList.this.locate(me.getKey()).equals(me))
						return false;
				}
				return true;
			}

			// Compares the specified object with this set for equality.
			public boolean equals(Object o) {
				if (!(o instanceof Collection))
					return false;
				Collection s = (Collection) o;
				if (s.size() == SkipList.this.size && this.containsAll(s))
					return true;
				return false;
			}

			// Returns the hash code value for this set.
			public int hashCode() {
				return System.identityHashCode(this);
			}

			// Returns true if this set contains no elements.
			public boolean isEmpty() {
				return SkipList.this.size == 0;
			}

			// Returns an iterator over the elements in this set.
			public Iterator iterator() {
				return SkipList.this.entryIterator();
			}

			// Removes the specified element from this set if it is present.
			public boolean remove(Object o) {
				Object key = ((Map.Entry) o).getKey();
				if (SkipList.this.containsKey(key)) {
					SkipList.this.remove(key);
					return true;
				}
				return false;
			}

			// Removes from this set all of its elements that are contained in
			// the specified collection.
			public boolean removeAll(Collection c) {
				Iterator i = c.iterator();
				boolean rem = true;
				while (i.hasNext()) {
					Map.Entry me = (Map.Entry) i.next();
					Object key = me.getKey();
					if (SkipList.this.containsKey(key))
						SkipList.this.remove(key);
					else
						rem = false;
				}
				return rem;
			}

			// Retains only the elements in this set that are contained in the
			// specified collection.
			public boolean retainAll(Collection c) {
				int ps = SkipList.this.size;
				clear();
				addAll(c);
				return ps != SkipList.this.size;
			}

			// Returns the number of elements in this set (its cardinality).
			public int size() {
				return SkipList.this.size;
			}

			// Returns an array containing all of the elements in this set.
			public Object[] toArray() {
				Object[] o = new Object[SkipList.this.size];
				int i = 0;
				Node curr = SkipList.this.head.next[0];
				while (curr != null) {
					o[i] = curr;
					curr = curr.next[0];
					++i;
				}
				return o;
			}

			// Returns an array containing all of the elements in this set; the
			// runtime type of the returned array is that of the specified
			// array.
			public Object[] toArray(Object[] a) {
				if (a.length < SkipList.this.size)
					a = (Object[]) java.lang.reflect.Array.newInstance(a
							.getClass().getComponentType(), SkipList.this.size);
				int i = 0;
				Node curr = SkipList.this.head.next[0];
				while (curr != null) {
					a[i] = curr;
					curr = curr.next[0];
					++i;
				}
				if (a.length > SkipList.this.size)
					a[SkipList.this.size] = null;

				return a;
			}
		};
	}

	/**
	 * Returns <tt>true</tt> if <tt>o</tt> is an instance of <tt>Map</tt>,
	 * contains the same number of mappings as this <tt>SkipList</tt>, and
	 * all mappings in <tt>o</tt> are preset in this <tt>SkipList</tt>.
	 * Mappings are equivalent if both their keys and their values are
	 * equivalent. Equivalence for keys is determined by the comparator bound to
	 * this map, while equivalence for values is determined by their
	 * <tt>equals()</tt> methods.
	 * <p>
	 * 
	 * The complexity of this method is &#937;(1), O(m * n), where m is the
	 * number of mappings in <tt>o</tt>. In the average case, this method
	 * will probabalistically perform in &#920;(m log n) time.
	 * 
	 * @param o
	 *            an instance of <tt>Map</tt> to compare against this
	 *            <tt>SkipList</tt> for equality
	 * @return <tt>true</tt> if this <tt>SkipList</tt> contains exactly the
	 *         mappings in <tt>o</tt>
	 * @since 1.0
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Map))
			return false;
		else {
			Iterator i = ((Map) o).entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				Node n = SkipList.this.locate(me.getKey());
				if (n == null || !n.equals(me))
					return false;
			}
			return true;
		}
	}

	/**
	 * Returns the value to which this map maps the specified key. This method
	 * performs in probabilistic &#920;(log n) time; its formal complexity is
	 * &#937;(1), O(n).
	 * 
	 * @return the value mapped to by <tt>key</tt>, or <tt>null</tt> if
	 *         such a mapping does not exist in this <tt>SkipList</tt>.
	 * @since 1.0
	 */
	public Object get(Object key) {
		Node n = locate(key);
		return (n == null ? null : n.getValue());
	}

	/**
	 * Returns the hash code value for this map.
	 * 
	 * @return the hash code value for this map
	 * @since 1.0
	 */
	public int hashCode() {
		return System.identityHashCode(this);
	}

	/**
	 * Returns <tt>true</tt> if this map contains no key-value mappings.
	 * 
	 * @return <tt>true</tt> if this <tt>SkipList</tt> is empty,
	 *         <tt>false</tt> otherwise.
	 * @since 1.0
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns an iterator over the keys in this <tt>SkipList</tt>. The
	 * returned <tt>Iterator</tt>'s <tt>next()</tt> method will return
	 * objects of potentially varied types.
	 * 
	 * @return an <tt>Iterator</tt> over the keys in this <tt>SkipList</tt>
	 * @since 1.0
	 */
	protected Iterator keyIterator() {
		return new KeyIterator();
	}

	/**
	 * Returns a set view of the keys contained in this map. Most public methods
	 * of <tt>Set</tt> are supported by the returned object. However
	 * <tt>add</tt>, and <tt>addAll</tt> will throw
	 * <tt>UnsupportedOperationException</tt>s if invoked.
	 * 
	 * @return an anonymous subclass of <tt>AbstractSet</tt> backed by this
	 *         <tt>SkipList</tt>
	 * @since 1.0
	 */
	public Set keySet() {
		return new AbstractSet() {
			// Adds the specified element to this set if it is not already
			// present.
			public boolean add(Object o) {
				throw new UnsupportedOperationException();
			}

			// Adds all of the elements in the specified collection to this set
			// if they're not already present.
			public boolean addAll(Collection c) {
				throw new UnsupportedOperationException();
			}

			// Removes all of the elements from this set.
			public void clear() {
				SkipList.this.clear();
			}

			// Returns true if this set contains the specified element.
			public boolean contains(Object o) {
				return SkipList.this.containsKey(o);
			}

			// Returns true if this set contains all of the elements of the
			// specified collection.
			public boolean containsAll(Collection c) {
				Iterator i = c.iterator();
				while (i.hasNext())
					if (contains(i.next()) == false)
						return false;
				return true;
			}

			// Compares the specified object with this set for equality.
			public boolean equals(Object o) {
				if (!(o instanceof Collection))
					return false;
				Collection s = (Collection) o;
				if (s.size() == SkipList.this.size && this.containsAll(s))
					return true;
				return false;
			}

			// Returns the hash code value for this set.
			public int hashCode() {
				return System.identityHashCode(this);
			}

			// Returns true if this set contains no elements.
			public boolean isEmpty() {
				return SkipList.this.size == 0;
			}

			// Returns an iterator over the elements in this set.
			public Iterator iterator() {
				return SkipList.this.keyIterator();
			}

			// Removes the specified element from this set if it is present.
			public boolean remove(Object o) {
				if (SkipList.this.containsKey(o)) {
					SkipList.this.remove(o);
					return true;
				}
				return false;
			}

			// Removes from this set all of its elements that are contained in
			// the specified collection.
			public boolean removeAll(Collection c) {
				Iterator i = c.iterator();
				boolean rem = true;
				while (i.hasNext()) {
					Object key = i.next();
					if (SkipList.this.containsKey(key))
						SkipList.this.remove(key);
					else
						rem = false;
				}
				return rem;
			}

			// Retains only the elements in this set that are contained in the
			// specified collection.
			public boolean retainAll(Collection c) {
				int ps = SkipList.this.size;
				Node curr = head.next[0];
				while (curr != null) {
					if (!c.contains(curr.key))
						SkipList.this.remove(curr.key);
					curr = curr.next[0];
				}
				return ps != SkipList.this.size;
			}

			// Returns the number of elements in this set (its cardinality).
			public int size() {
				return SkipList.this.size;
			}

			// Returns an array containing all of the elements in this set.
			public Object[] toArray() {
				Object[] o = new Object[SkipList.this.size];
				int i = 0;
				Node curr = SkipList.this.head.next[0];
				while (curr != null) {
					o[i] = curr.key;
					curr = curr.next[0];
					++i;
				}
				return o;
			}

			// Returns an array containing all of the elements in this set; the
			// runtime type of the returned array is that of the specified
			// array.
			public Object[] toArray(Object[] a) {
				if (a.length < SkipList.this.size)
					a = (Object[]) java.lang.reflect.Array.newInstance(a
							.getClass().getComponentType(), SkipList.this.size);
				int i = 0;
				Node curr = SkipList.this.head.next[0];
				while (curr != null) {
					a[i] = curr.key;
					curr = curr.next[0];
					++i;
				}
				if (a.length > SkipList.this.size)
					a[SkipList.this.size] = null;

				return a;
			}
		};

	}

	/**
	 * Associates the specified value with the specified key in this map.
	 * 
	 * This method performs in probabilistic &#920;(log n) time; its formal
	 * complexity is &#937;(1), O(n).
	 * 
	 * @param key
	 *            the key with which the specified value is to be associated.
	 * @param value
	 *            the value to be associated with the specified key.
	 * @return the previous value associated with specified key, or null if
	 *         there was no mapping for key. A null return can also indicate
	 *         that the map previously associated null with the specified key.
	 * @throws NullPointerException
	 *             if <tt>key</tt> is <tt>null</tt> (<tt>null</tt> keys
	 *             are not supported by <tt>SkipList</tt>).
	 * @since 1.0
	 */
	public Object put(Object key, Object value) {
		if (key == null)
			throw new NullPointerException(
					"Null keys are not supported by this map.");
		Node newNode = new Node(key, value);
		if (newNode.next.length > head.next.length)
			growHead(newNode.next.length);
		Node[] update = new Node[head.next.length];
		Node curr = head;
		for (int i = (head.next.length - 1); i >= 0; --i) {
			while ((curr.next[i] != null)
					&& (comp.compare(key, curr.next[i].key) >= 0))
				curr = curr.next[i];
			update[i] = curr;
		}

		if (curr != head && comp.compare(key, curr.key) == 0)
			return curr.setValue(value);

		for (int i = 0; i < newNode.next.length; ++i) {
			newNode.next[i] = update[i].next[i];
			update[i].next[i] = newNode;
		}
		++size;
		return null;
	}

	/**
	 * Copies all of the mappings from the specified map to this map.
	 * 
	 * The complexity of this method is &#937;(1), O(m + n), where m is the
	 * number of mappings in <tt>o</tt>. In the average case, this method
	 * will probabalistically perform in &#920;(m log n) time.
	 * 
	 * @param t
	 *            the map whose entries will be copied into this
	 *            <tt>SkipList</tt>
	 * @since 1.0
	 */
	public void putAll(Map t) {
		Iterator i = t.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			this.put(me.getKey(), me.getValue());
		}
	}

	/**
	 * Removes the mapping for this key from this map if it is present. This
	 * method performs in probabilistic &#920;(log n) time; its formal
	 * complexity is &#937;(1), O(n).
	 * 
	 * @param key
	 *            the key of the entry to remove from this <tt>SkipList</tt>
	 * @return previous value associated with specified key, or null if there
	 *         was no mapping for key. A <tt>null</tt> return can also
	 *         indicate that the map previously associated <tt>null</tt> with
	 *         the specified key.
	 * @since 1.0
	 */
	public Object remove(Object key) {
		if (key == null)
			return null;
		Node[] update = new Node[head.next.length];
		Node curr = head;
		for (int i = (head.next.length - 1); i >= 0; --i) {
			while ((curr.next[i] != null)
					&& (comp.compare(key, curr.next[i].key) > 0))
				curr = curr.next[i];
			update[i] = curr;
		}
		curr = curr.next[0];
		if (comp.compare(curr.key, key) == 0) {
			for (int i = 0; i < curr.next.length; ++i)
				if (update[i].next[i] != curr)
					break;
				else
					update[i].next[i] = curr.next[i];
			--size;
			return curr;
		}
		return null;
	}

	/**
	 * Returns the number of key-value mappings in this map.
	 * 
	 * This method performs in &#920;(1) time.
	 * 
	 * @return the number of key-value mappings in this map (this
	 *         <tt>SkipList</tt>'s <i>cardinality</i>)
	 * @since 1.0
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns an iterator over the values mapped to in this <tt>SkipList</tt>.
	 * The returned <tt>Iterator</tt>'s <tt>next()</tt> method will return
	 * objects of potentially varied types, or <tt>null</tt>.
	 * 
	 * @return an <tt>Iterator</tt> over the keys in this <tt>SkipList</tt>
	 * @since 1.0
	 */
	protected Iterator valueIterator() {
		return new ValueIterator();
	}

	/**
	 * Returns a set view of the values mapped to in this map. Most public
	 * methods of <tt>Set</tt> are supported by the returned object. However
	 * <tt>add</tt>, and <tt>addAll</tt> will throw
	 * <tt>UnsupportedOperationException</tt>s if invoked.
	 * 
	 * @return an anonymous subclass of <tt>AbstractSet</tt> backed by this
	 *         <tt>SkipList</tt>
	 * @since 1.0
	 */
	public Collection values() {
		return new AbstractCollection() {
			protected Node locate(Object value) {
				if (value == null)
					value = new Object() {
						public boolean equals(Object o) {
							return o == null;
						}
					};
				Node current = head.next[0];
				while (current != null) {
					if (value.equals(current.value))
						return current;
					else
						current = current.next[0];
				}
				return null;
			}

			// Adds the specified element to this set if it is not already
			// present.
			public boolean add(Object o) {
				throw new UnsupportedOperationException();
			}

			// Adds all of the elements in the specified collection to this set
			// if they're not already present.
			public boolean addAll(Collection c) {
				throw new UnsupportedOperationException();
			}

			// Removes all of the elements from this set.
			public void clear() {
				SkipList.this.clear();
			}

			// Returns true if this set contains the specified element.
			public boolean contains(Object o) {
				return locate(o) != null;
			}

			// Returns true if this set contains all of the elements of the
			// specified collection.
			public boolean containsAll(Collection c) {
				Iterator i = c.iterator();
				while (i.hasNext())
					if (locate(i.next()) == null)
						return false;
				return true;
			}

			// Compares the specified object with this set for equality.
			public boolean equals(Object o) {
				if (!(o instanceof Collection))
					return false;
				Collection s = (Collection) o;
				if (s.size() == SkipList.this.size && this.containsAll(s))
					return true;
				return false;
			}

			// Returns the hash code value for this set.
			public int hashCode() {
				return System.identityHashCode(this);
			}

			// Returns true if this set contains no elements.
			public boolean isEmpty() {
				return SkipList.this.size == 0;
			}

			// Returns an iterator over the elements in this set.
			public Iterator iterator() {
				return SkipList.this.valueIterator();
			}

			// Removes the specified element from this set if it is present.
			public boolean remove(Object o) {
				Node n = locate(o);
				if (n == null)
					return false;
				Object key = n.key;
				if (SkipList.this.containsKey(key)) {
					SkipList.this.remove(key);
					return true;
				}
				return false;
			}

			// Removes from this set all of its elements that are contained in
			// the specified collection.
			public boolean removeAll(Collection c) {
				Iterator i = c.iterator();
				boolean rem = true;
				while (i.hasNext())
					rem = rem && remove(i.next());
				return rem;
			}

			// Retains only the elements in this set that are contained in the
			// specified collection.
			public boolean retainAll(Collection c) {
				int ps = SkipList.this.size;
				Node curr = head.next[0];
				while (curr != null) {
					if (!c.contains(curr.value))
						SkipList.this.remove(curr.key);
					curr = curr.next[0];
				}
				return ps != SkipList.this.size;
			}

			// Returns the number of elements in this set (its cardinality).
			public int size() {
				return SkipList.this.size;
			}

			// Returns an array containing all of the elements in this set.
			public Object[] toArray() {
				Object[] o = new Object[SkipList.this.size];
				int i = 0;
				Node curr = SkipList.this.head.next[0];
				while (curr != null) {
					o[i] = curr.value;
					curr = curr.next[0];
					++i;
				}
				return o;
			}

			// Returns an array containing all of the elements in this set; the
			// runtime type of the returned array is that of the specified
			// array.
			public Object[] toArray(Object[] a) {
				if (a.length < SkipList.this.size)
					a = (Object[]) java.lang.reflect.Array.newInstance(a
							.getClass().getComponentType(), SkipList.this.size);
				int i = 0;
				Node curr = SkipList.this.head.next[0];
				while (curr != null) {
					a[i] = curr.value;
					curr = curr.next[0];
					++i;
				}
				if (a.length > SkipList.this.size)
					a[SkipList.this.size] = null;

				return a;
			}
		};
	}

	/* SortedMap implementors */

	/**
	 * Returns the comparator associated with this sorted map, or <tt>null</tt>
	 * if it uses its keys' natural ordering.
	 * 
	 * @return an instance of <tt>Comparator</tt> if one is associated with
	 *         this <tt>SkipList</tt>, <tt>null</tt> otherwise
	 * @since 1.0
	 */
	public Comparator comparator() {
		return comp;
	}

	/**
	 * Returns the first (lowest) key currently in this sorted map.
	 * 
	 * @return the first key currently in this <tt>SkipList</tt>.
	 * @since 1.0
	 */
	public Object firstKey() {
		return (head.next[0] == null ? null : head.next[0].key);
	}

	/**
	 * Returns the last (highest) key currently in this sorted map. This method
	 * performs in probabilistic &#920;(log n) time; its formal complexity is
	 * &#937;(1), O(n).
	 * 
	 * @return the last key currently in this <tt>SkipList</tt>.
	 * @since 1.0
	 */
	public Object lastKey() {
		Node curr = head.next[head.next.length - 1];
		for (int i = head.next.length - 1; i >= 0; --i)
			while (curr.next[i] != null)
				curr = curr.next[i];
		return curr.key;
	}

	/**
	 * Returns a view of the portion of this sorted map whose keys are strictly
	 * less than <tt>toKey</tt>. This method returns an object whose type is
	 * SkipList.SubMap, a subclass of <tt>AbstractSortedMap</tt>. All methods
	 * of <tt>SortedMap</tt> are supported by the returned sub map, but users
	 * of this return value should be aware that keys falling outside of the
	 * range defined by the value of <tt>firstKey()</tt> and <tt>toKey</tt>
	 * will result in an <tt>IllegalArgumentException</tt> being thrown, as
	 * per the contract specified in <tt>SortedMap</tt>.
	 * 
	 * @param toKey
	 *            all keys in the returned map will be strictly less than this
	 *            key
	 * @return an instance of <tt>SortedMap</tt> backed by this
	 *         <tt>SkipList</tt>, and whose keys are strictly less than
	 *         <tt>toKey</tt>
	 * @since 1.0
	 */
	public SortedMap headMap(Object toKey) {
		return new SubMap(firstKey(), toKey);
	}

	/**
	 * Returns a view of the portion of this sorted map whose keys range from
	 * fromKey, inclusive, to toKey, exclusive. This method returns an object
	 * whose type is SkipList.SubMap, a subclass of <tt>AbstractSortedMap</tt>.
	 * All methods of <tt>SortedMap</tt> are supported by the returned sub
	 * map, but users of this return value should be aware that keys falling
	 * outside of the range defined by <tt>firstKey</tt> and <tt>toKey</tt>
	 * will result in an <tt>IllegalArgumentException</tt> being thrown, as
	 * per the contract specified in <tt>SortedMap</tt>.
	 * 
	 * @param fromKey
	 *            all keys in the returned map will be greater than or equal to
	 *            this key
	 * @param toKey
	 *            all keys in the returned map will be strictly less than this
	 *            key
	 * @return an instance of <tt>SortedMap</tt> backed by this
	 *         <tt>SkipList</tt>, and whose keys are strictly less than
	 *         <tt>toKey</tt>
	 * @since 1.0
	 */
	public SortedMap subMap(Object fromKey, Object toKey) {
		return new SubMap(fromKey, toKey);
	}

	/**
	 * Returns a view of the portion of this sorted map whose keys are greater
	 * than or equal to fromKey. This method returns an object whose type is
	 * SkipList.SubMap, a subclass of <tt>AbstractSortedMap</tt>. All methods
	 * of <tt>SortedMap</tt> are supported by the returned sub map, but users
	 * of this return value should be aware that keys falling outside of the
	 * range defined by <tt>fromKey</tt> and the value of <tt>lastKey()</tt>
	 * will result in an <tt>IllegalArgumentException</tt> being thrown, as
	 * per the contract specified in <tt>SortedMap</tt>.
	 * 
	 * @param fromKey
	 *            all keys in the returned map will be greater than or equal to
	 *            this key
	 * @return an instance of <tt>SortedMap</tt> backed by this
	 *         <tt>SkipList</tt>, and whose keys are strictly less than
	 *         <tt>toKey</tt>
	 * @since 1.0
	 */
	public SortedMap tailMap(Object fromKey) {
		return new SubMap(fromKey, lastKey());
	}

	/**
	 * Returns a string representation of the key-value pairings present in this
	 * <tt>SkipList</tt>.
	 * 
	 * @return a string representing this <tt>SkipList</tt>
	 * @since 1.0
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Node curr = head.next[0];
		while (curr != null) {
			sb.append("[" + curr.key + ":" + curr.getValue() + "]\n");
			curr = curr.next[0];
		}
		return sb.toString();
	}

	/**
	 * Locates the given key in this <tt>SkipList</tt>. This method performs
	 * in probabilistic &#920;(log n) time; its formal complexity is &#937;(1),
	 * O(n).
	 * 
	 * @param key
	 *            the key to locate
	 * @return the <tt>Node</tt> to which <tt>key</tt> belongs or
	 *         <tt>null</tt> if the key does not exist in this
	 *         <tt>SkipList</tt>
	 * @since 1.0
	 */
	protected Node locate(Object key) {
		if (size <= 0)
			return null;
		Node curr = head;
		for (int i = (head.next.length - 1); i >= 0; --i) {
			while ((curr.next[i] != null)
					&& (comp.compare(key, curr.next[i].key) >= 0))
				curr = curr.next[i];
		}
		return (comp.compare(key, curr.key) == 0 ? curr : null);
	}

	/**
	 * An iterator over the entries in this <tt>SkipList</tt>. This
	 * <tt>Iterator</tt>'s <tt>next()</tt> method returns objects of type
	 * <tt>Map.Entry</tt>.
	 * 
	 * @author Evan Machusak (<a
	 *         href="mailto:emach@cs.umd.edu">emach@cs.umd.edu</a>)
	 * @since 1.0
	 * @version 1.0
	 */
	protected class EntryIterator implements java.util.Iterator {
		protected Node curr;

		protected EntryIterator() {
			curr = head.next[0];
		}

		public Object next() {
			if (curr == null)
				throw new NoSuchElementException();
			Node n = curr;
			curr = curr.next[0];
			return n;
		}

		public boolean hasNext() {
			return curr != null;
		}

		public void remove() {
			SkipList.this.remove(curr.key);
		}
	}

	/**
	 * An iterator over the values in this <tt>SkipList</tt>. This
	 * <tt>Iterator</tt>'s <tt>next()</tt> method returns objects of type
	 * <tt>Object</tt>, or <tt>null</tt>.
	 * 
	 * @author Evan Machusak (<a
	 *         href="mailto:emach@cs.umd.edu">emach@cs.umd.edu</a>)
	 * @since 1.0
	 * @version 1.0
	 */
	protected class ValueIterator implements java.util.Iterator {
		protected Node curr;

		protected ValueIterator() {
			curr = head.next[0];
		}

		public Object next() {
			if (curr == null)
				throw new NoSuchElementException();
			Node n = curr;
			curr = curr.next[0];
			return n.getValue();
		}

		public boolean hasNext() {
			return curr != null;
		}

		public void remove() {
			SkipList.this.remove(curr.key);
		}
	}

	/**
	 * An iterator over the keys in this <tt>SkipList</tt>. This
	 * <tt>Iterator</tt>'s <tt>next()</tt> method returns objects of type
	 * <tt>Object</tt>.
	 * 
	 * @author Evan Machusak (<a
	 *         href="mailto:emach@cs.umd.edu">emach@cs.umd.edu</a>)
	 * @since 1.0
	 * @version 1.0
	 */
	protected class KeyIterator implements java.util.Iterator {
		protected Node curr;

		protected KeyIterator() {
			curr = head.next[0];
		}

		public Object next() {
			if (curr == null)
				throw new NoSuchElementException();
			Node n = curr;
			curr = curr.next[0];
			return n.key;
		}

		public boolean hasNext() {
			return curr != null;
		}

		public void remove() {
			SkipList.this.remove(curr.key);
		}
	}

	/**
	 * An iterator over the entries in this <tt>SkipList</tt>, whose values
	 * fall between the range of keys specified in its constructors. This
	 * <tt>Iterator</tt>'s <tt>next()</tt> method returns objects of type
	 * <tt>Map.Entry</tt>.
	 * 
	 * @author Evan Machusak (<a
	 *         href="mailto:emach@cs.umd.edu">emach@cs.umd.edu</a>)
	 * @since 1.0
	 * @version 1.0
	 */
	protected class RangeIterator implements java.util.Iterator {
		protected Node curr;

		protected Object maxKey;

		protected RangeIterator() {
			curr = head.next[0];
			maxKey = SkipList.this.lastKey();
		}

		protected RangeIterator(Object minKey) {
			curr = SkipList.this.locate(minKey);
			if (curr == null)
				throw new IllegalArgumentException(
						"SkipList.RangeIterator.RangeIterator(Object minKey): minKey must be present in the list.");
			maxKey = SkipList.this.lastKey();
		}

		protected RangeIterator(Object minKey, Object maxKey) {
			curr = SkipList.this.locate(minKey);
			if (curr == null)
				throw new IllegalArgumentException(
						"SkipList.RangeIterator.RangeIterator(Object minKey, Object maxKey): minKey must be present in the list.");
			maxKey = SkipList.this.locate(maxKey).key;
			if (curr == null)
				throw new IllegalArgumentException(
						"SkipList.RangeIterator.RangeIterator(Object minKey, Object maxKey): maxKey must be present in the list.");
		}

		public Object next() {
			if (curr == null)
				throw new NoSuchElementException();
			Node n = curr;
			curr = curr.next[0];
			return n;
		}

		public boolean hasNext() {
			return ((curr != null) && (SkipList.this.comp.compare(curr.key,
					maxKey) < 0));
		}

		public void remove() {
			SkipList.this.remove(curr.key);
		}
	}

	/**
	 * A single node in this <tt>SkipList</tt>. <tt>Node</tt> implements
	 * <tt>Map.Entry</tt> and is the object referred to as an entry of this
	 * sorted map. A <tt>Node</tt> contains the key-value mapping and the
	 * structural information of the <tt>SkipList</tt>, specifically the link
	 * tower.
	 * 
	 * @author Evan Machusak
	 * @since 1.0
	 * @version 1.0
	 */
	public class Node implements Map.Entry, Comparable {
		/**
		 * The key portion of this map entry.
		 * 
		 * @since 1.0
		 */
		protected Object key;

		/**
		 * The value portion of this map entry.
		 * 
		 * @since 1.0
		 */
		protected Object value;

		/**
		 * The link tower of this skip list node.
		 * 
		 * @since 1.0
		 */
		protected Node[] next;

		/**
		 * Creates a new skip list <tt>Node</tt> with the specified key-value
		 * mapping, and with a link tower of random height.
		 * 
		 * @param key
		 *            the key portion of this map entry
		 * @param value
		 *            the value portion of this map entry
		 * @since 1.0
		 */
		public Node(Object key, Object value) {
			this.key = key;
			this.value = value;
			next = SkipList.this.generateTower();
		}

		/**
		 * Compares the specified object with this entry for equality.
		 * 
		 * @return <tt>true</tt> if this object is an instance of
		 *         <tt>Map.Entry</tt> and both its key and its value match the
		 *         key and value specified by this mapping.
		 * @since 1.0
		 */
		public boolean equals(Object o) {
			if (o instanceof Map.Entry) {
				Map.Entry me = (Map.Entry) o;
				Object val = me.getValue();
				if (val == null)
					val = new Object() {
						public boolean equals(Object o) {
							return o == null;
						}
					};
				return (SkipList.this.comp.compare(me.getKey(), key) == 0 && val
						.equals(value));
			} else
				return false;
		}

		/**
		 * Returns the key corresponding to this entry.
		 * 
		 * @return the key associated with this mapping
		 * @since 1.0
		 */
		public Object getKey() {
			return key;
		}

		/**
		 * Returns the value corresponding to this entry.
		 * 
		 * @return the value associated with this mapping
		 * @since 1.0
		 */
		public Object getValue() {
			return value;
		}

		/**
		 * Returns the hash code value for this map entry.
		 * 
		 * @return the default hashcode provided by Java for this mapping
		 * @since 1.0
		 */
		public int hashCode() {
			return super.hashCode();
		}

		/**
		 * Replaces the value corresponding to this entry with the specified
		 * value.
		 * 
		 * @param value
		 *            the new value to associate with this mapping
		 * @since 1.0
		 */
		public Object setValue(Object value) {
			Object prev = this.value;
			this.value = value;
			return prev;
		}

		/**
		 * Returns this <tt>Node</tt>'s <i>natural ordering</i> in relation
		 * to the specified object. Nodes are compared based solely on their
		 * keys. Two nodes with the same key and different values would be
		 * considered equal (and thus elicit a return value of 0). <b>Note:</b>
		 * this behavior differs from that of <tt>equals()</tt>.
		 * 
		 * @param o
		 *            an instance of <tt>Map.Entry</tt> to compare this
		 *            <tt>Node</tt> against
		 * @return a value <tt>x</tt>, such that
		 *         <tt>x == SkipList.this.comparator().compare(this.key, ((Map.Entry)o).getKey())</tt>
		 * @throws ClassCastException
		 *             if <tt>o</tt> is not an instance of <tt>Map.Entry</tt>
		 * @see SkipList.Node#equals(Object)
		 * @since 1.0
		 */
		public int compareTo(Object o) {
			if (o instanceof Map.Entry)
				return SkipList.this.comp
						.compare(key, ((Map.Entry) o).getKey());
			else
				throw new ClassCastException("SkipList.Node.compareTo("
						+ o.getClass().getName()
						+ "): expecting instance of Map.Entry");
		}
	}

	/**
	 * A view of this <tt>SkipList</tt> constrained only to the entries which
	 * fall within a given range within this sorted map's keyspace.
	 * <tt>SubMap</tt> is, in and of itself, a full implementation of
	 * <tt>SortedMap</tt>, though it merely wraps the enclosing instance of
	 * <tt>SkipList</tt>. Changes made to any subsequent <tt>SubMap</tt>
	 * instances derived from this parent <tt>SkipList</tt> will be reflected
	 * in that parent.
	 * 
	 * @author Evan Machusak (<a
	 *         href="mailto:emach@cs.umd.edu">emach@cs.umd.edu</a>)
	 * @since 1.0
	 * @version 1.0
	 */
	protected class SubMap implements SortedMap {
		/**
		 * The key in this <tt>SubMap</tt>'s backing <tt>SkipList</tt> for
		 * which all keys in this <tt>SubMap</tt> are greater than or equal to
		 * this key.
		 * 
		 * @since 1.0
		 */
		protected Object fromKey;

		/**
		 * The key in this <tt>SubMap</tt>'s backing <tt>SkipList</tt> for
		 * which all keys in this <tt>SubMap</tt> are strictly less than this
		 * key.
		 * 
		 * @since 1.0
		 */
		protected Object toKey;

		/**
		 * Creates a new instance of <tt>SubMap</tt> whose keys are between
		 * <tt>fromKey</tt>, inclusive, to <tt>toKey</tt>, exclusive.
		 * 
		 * @param fromKey
		 *            the key for which all keys in this <tt>SubMap</tt> are
		 *            greater than or equal to this key
		 * @param toKey
		 *            the key for which all keys in this <tt>SubMap</tt> are
		 *            strictly less than this key
		 * @since 1.0
		 */
		public SubMap(Object fromKey, Object toKey) {
			if (fromKey == null || toKey == null)
				throw new NullPointerException(
						"SkipList.SubMap.SubMap(fromKey, toKey): this map does not support null keys");
			if (SkipList.this.comp.compare(fromKey, toKey) > 0)
				throw new IllegalArgumentException(
						"SkipList.SubMap.SubMap(fromKey, toKey): fromKey comes after toKey");
			this.fromKey = fromKey;
			this.toKey = toKey;
		}

		/**
		 * Removes all key-value mappings in this <tt>SubMap</tt> from its
		 * backing <tt>SkipList</tt>.
		 * 
		 * @since 1.0
		 */
		public void clear() {
			Node curr = findFirst();
			while ((curr != null)
					&& (SkipList.this.comp.compare(curr.key, toKey) < 0)) {
				Object k = curr.key;
				curr = curr.next[0];
				SkipList.this.remove(k);
			}
		}

		/**
		 * Returns <tt>true</tt> if this map contains a mapping for the
		 * specified key.
		 * 
		 * @param key
		 *            the key to locate
		 * @return <tt>true</tt> if <tt>key</tt> is present in this
		 *         <tt>SubMap</tt>, <tt>false</tt> otherwise
		 * @since 1.0
		 */
		public boolean containsKey(Object key) {
			return (inRange(key) && SkipList.this.containsKey(key));
		}

		/**
		 * Returns <tt>true</tt> if this map maps one or more keys to the
		 * specified value. This method 's complexity is &#937;(1), O(n).
		 * 
		 * @param value
		 *            the value to locate in this <tt>SubMap</tt>. Equality
		 *            is determined via <tt>value</tt>'s <tt>equals()</tt>
		 *            method. <tt>null</tt> is a legal value for
		 *            <tt>value</tt>.
		 * @return <tt>true</tt> if <tt>value</tt> is mapped to by some key
		 *         present in this <tt>SubMap</tt>, <tt>false</tt>
		 *         otherwise.
		 * @since 1.0
		 */
		public boolean containsValue(Object value) {
			Node curr = findFirst();
			while ((curr != null)
					&& (SkipList.this.comp.compare(curr.key, toKey) < 0)) {
				if (curr.value.equals(value))
					return true;
				curr = curr.next[0];
			}
			return false;
		}

		/**
		 * Returns a set view of the mappings contained in this map. All public
		 * methods of <tt>Set</tt> are supported in the returned object, but
		 * users of this return value should take caution to avoid
		 * <tt>ClassCastException</tt>s on certain methods (for example, this
		 * returned <tt>Set</tt>'s <tt>add</tt> method expects an instance
		 * of <tt>Map.Entry</tt>).
		 * 
		 * @return an anonymous subclass of <tt>AbstractSet</tt> backed by
		 *         this <tt>SkipList</tt>
		 * @since 1.0
		 */
		public Set entrySet() {
			// *sigh* ...
			return new AbstractSet() {
				// Adds the specified element to this set if it is not already
				// present.
				public boolean add(Object o) {
					Object key = ((Map.Entry) o).getKey();
					if (!inRange(key))
						throw new IllegalArgumentException(
								"SkipList.SubMap.entrySet().add(): attempting to add a Map.Entry whose key violates the bounds of this SubMap");
					Object value = ((Map.Entry) o).getValue();
					boolean add = SkipList.this.containsKey(key);
					SkipList.this.put(key, value);
					return add;
				}

				// Adds all of the elements in the specified collection to this
				// set if they're not already present.
				public boolean addAll(Collection c) {
					Iterator i = c.iterator();
					boolean add = false;
					while (i.hasNext())
						try {
							add = add || this.add(i.next());
						} catch (IllegalArgumentException iae) {
							add = false;
						}
					return add;
				}

				// Removes all of the elements from this set.
				public void clear() {
					SubMap.this.clear();
				}

				// Returns true if this set contains the specified element.
				public boolean contains(Object o) {
					return SubMap.this.containsKey(o);
				}

				// Returns true if this set contains all of the elements of the
				// specified collection.
				public boolean containsAll(Collection c) {
					Iterator i = c.iterator();
					while (i.hasNext())
						if (contains(i.next()) == false)
							return false;
					return true;
				}

				// Compares the specified object with this set for equality.
				public boolean equals(Object o) {
					if (!(o instanceof Collection))
						return false;
					Collection s = (Collection) o;
					if (s.size() == SubMap.this.size() && this.containsAll(s))
						return true;
					return false;
				}

				// Returns the hash code value for this set.
				public int hashCode() {
					return System.identityHashCode(this);
				}

				// Returns true if this set contains no elements.
				public boolean isEmpty() {
					return SubMap.this.size() == 0;
				}

				// Returns an iterator over the elements in this set.
				public Iterator iterator() {
					return SubMap.this.keyIterator();
				}

				// Removes the specified element from this set if it is present.
				public boolean remove(Object o) {
					if (SubMap.this.containsKey(o)) {
						SubMap.this.remove(o);
						return true;
					}
					return false;
				}

				// Removes from this set all of its elements that are contained
				// in the specified collection.
				public boolean removeAll(Collection c) {
					Iterator i = c.iterator();
					boolean rem = true;
					while (i.hasNext()) {
						Object key = i.next();
						if (SubMap.this.containsKey(key))
							SubMap.this.remove(key);
						else
							rem = false;
					}
					return rem;
				}

				// Retains only the elements in this set that are contained in
				// the specified collection.
				public boolean retainAll(Collection c) {
					int ps = SubMap.this.size();
					Node curr = SubMap.this.findFirst();
					while (curr != null) {
						if (!c.contains(curr.key))
							SubMap.this.remove(curr.key);
						curr = curr.next[0];
					}
					return ps != SubMap.this.size();
				}

				// Returns the number of elements in this set (its cardinality).
				public int size() {
					return SubMap.this.size();
				}

				// Returns an array containing all of the elements in this set.
				public Object[] toArray() {
					Object[] o = new Object[SubMap.this.size()];
					int i = 0;
					Node curr = SubMap.this.findFirst();
					while (curr != null) {
						o[i] = curr;
						curr = curr.next[0];
						++i;
					}
					return o;
				}

				// Returns an array containing all of the elements in this set;
				// the runtime type of the returned array is that of the
				// specified array.
				public Object[] toArray(Object[] a) {
					if (a.length < SkipList.this.size)
						a = (Object[]) java.lang.reflect.Array.newInstance(a
								.getClass().getComponentType(),
								SkipList.this.size);
					int i = 0;
					Node curr = SubMap.this.findFirst();
					while (curr != null) {
						a[i] = curr;
						curr = curr.next[0];
						++i;
					}
					if (a.length > SkipList.this.size)
						a[SkipList.this.size] = null;

					return a;
				}
			};

		}

		/**
		 * Returns <tt>true</tt> if <tt>o</tt> is an instance of
		 * <tt>Map</tt>, contains the same number of mappings as this
		 * <tt>SubMap</tt>, and all mappings in <tt>o</tt> are preset in
		 * this <tt>SubMap</tt>. Mappings are equivalent if both their keys
		 * and their values are equivalent (according to their <tt>equals()</tt>
		 * methods).
		 * <p>
		 * 
		 * The complexity of this method is &#937;(1), O(m + n), where m is the
		 * number of mappings in <tt>o</tt>. In the average case, this method
		 * will probabalistically perform in &#920;(m log n) time.
		 * 
		 * @param o
		 *            an instance of <tt>Map</tt> to compare against this
		 *            <tt>SubMap</tt> for equality
		 * @return <tt>true</tt> if this <tt>SubMap</tt> contains exactly
		 *         the mappings in <tt>o</tt>
		 * @since 1.0
		 */
		public boolean equals(Object o) {
			if (!(o instanceof Map))
				return false;
			else {
				Iterator i = ((Map) o).entrySet().iterator();
				while (i.hasNext()) {
					Map.Entry me = (Map.Entry) i.next();
					if (inRange(me.getKey())) {
						Node n = SkipList.this.locate(me.getKey());
						if (n == null || !n.equals(me))
							return false;
					} else
						return false;
				}
				return true;
			}
		}

		/**
		 * Returns the value to which this map maps the specified key. This
		 * method performs in probabilistic &#920;(log n) time; its formal
		 * complexity is &#937;(1), O(n).
		 * 
		 * @return the value mapped to by <tt>key</tt>, or <tt>null</tt> if
		 *         such a mapping does not exist in this <tt>SubMap</tt>.
		 * @since 1.0
		 */
		public Object get(Object key) {
			if (!inRange(key))
				return null;
			Node n = SkipList.this.locate(key);
			if (n != null)
				return n.value;
			else
				return null;
		}

		/**
		 * Returns the hash code value for this map.
		 * 
		 * @return the hash code value for this map
		 * @since 1.0
		 */
		public int hashCode() {
			return System.identityHashCode(this);
		}

		/**
		 * Returns <tt>true</tt> if this map contains no key-value mappings.
		 * 
		 * @return <tt>true</tt> if this <tt>SkipList</tt> is empty,
		 *         <tt>false</tt> otherwise.
		 * @since 1.0
		 */
		public boolean isEmpty() {
			return size() == 0;
		}

		/**
		 * Returns a set view of the keys contained in this map. Most public
		 * methods of <tt>Set</tt> are supported by the returned object.
		 * However <tt>add</tt>, and <tt>addAll</tt> will throw
		 * <tt>UnsupportedOperationException</tt>s if invoked.
		 * 
		 * @return an anonymous subclass of <tt>AbstractSet</tt> backed by
		 *         this <tt>SubMap</tt>
		 * @since 1.0
		 */
		public Set keySet() {
			return new AbstractSet() {
				// Throws an UnsupportedOperationException.
				public boolean add(Object o) {
					throw new UnsupportedOperationException();
				}

				// Throws an UnsupportedOperationException.
				public boolean addAll(Collection c) {
					throw new UnsupportedOperationException();
				}

				// Removes all of the elements from this set).
				public void clear() {
					SubMap.this.clear();
				}

				// Returns true if this set contains the specified element.
				public boolean contains(Object o) {
					return SubMap.this.containsKey(o);
				}

				// Returns true if this set contains all of the elements of the
				// specified collection.
				public boolean containsAll(Collection c) {
					Iterator i = c.iterator();
					while (i.hasNext())
						if (contains(i.next()) == false)
							return false;
					return true;
				}

				// Compares the specified object with this set for equality.
				public boolean equals(Object o) {
					if (!(o instanceof Collection))
						return false;
					Collection s = (Collection) o;
					if (s.size() == SubMap.this.size() && this.containsAll(s))
						return true;
					return false;
				}

				// Returns the hash code value for this set.
				public int hashCode() {
					return System.identityHashCode(this);
				}

				// Returns true if this set contains no elements.
				public boolean isEmpty() {
					return SubMap.this.size() == 0;
				}

				// Returns an iterator over the elements in this set.
				public Iterator iterator() {
					return SubMap.this.keyIterator();
				}

				// Removes the specified element from this set if it is present.
				public boolean remove(Object o) {
					if (SubMap.this.containsKey(o)) {
						SubMap.this.remove(o);
						return true;
					}
					return false;
				}

				// Removes from this set all of its elements that are contained
				// in the specified collection.
				public boolean removeAll(Collection c) {
					Iterator i = c.iterator();
					boolean rem = true;
					while (i.hasNext()) {
						Object key = i.next();
						if (SubMap.this.containsKey(key))
							SubMap.this.remove(key);
						else
							rem = false;
					}
					return rem;
				}

				// Retains only the elements in this set that are contained in
				// the specified collection).
				public boolean retainAll(Collection c) {
					int ps = SubMap.this.size();
					Node curr = head.next[0];
					while (curr != null) {
						if (!c.contains(curr.key))
							SubMap.this.remove(curr.key);
						curr = curr.next[0];
					}
					return ps != SubMap.this.size();
				}

				// Returns the number of elements in this set (its cardinality).
				public int size() {
					return SubMap.this.size();
				}

				// Returns an array containing all of the elements in this set.
				public Object[] toArray() {
					Object[] o = new Object[SubMap.this.size()];
					int i = 0;
					Node curr = SubMap.this.findFirst();
					while (curr != null) {
						o[i] = curr.key;
						curr = curr.next[0];
						++i;
					}
					return o;
				}

				// Returns an array containing all of the elements in this set;
				// the runtime type of the returned array is that of the
				// specified array.
				public Object[] toArray(Object[] a) {
					if (a.length < SkipList.this.size)
						a = (Object[]) java.lang.reflect.Array.newInstance(a
								.getClass().getComponentType(),
								SkipList.this.size);
					int i = 0;
					Node curr = SubMap.this.findFirst();
					while (curr != null) {
						a[i] = curr.key;
						curr = curr.next[0];
						++i;
					}
					if (a.length > SkipList.this.size)
						a[SkipList.this.size] = null;

					return a;
				}
			};

		}

		/**
		 * Associates the specified value with the specified key in this map.
		 * 
		 * This method performs in probabilistic &#920;(log n) time; its formal
		 * complexity is &#937;(1), O(n).
		 * 
		 * @param key
		 *            the key with which the specified value is to be
		 *            associated.
		 * @param value
		 *            the value to be associated with the specified key.
		 * @return the previous value associated with specified key, or null if
		 *         there was no mapping for key. A null return can also indicate
		 *         that the map previously associated null with the specified
		 *         key.
		 * @throws NullPointerException
		 *             if <tt>key</tt> is <tt>null</tt> (<tt>null</tt>
		 *             keys are not supported by <tt>SubMap</tt>).
		 * @since 1.0
		 */
		public Object put(Object key, Object value) {
			if (!inRange(key))
				throw new IllegalArgumentException(
						"SkipList.SubMap.put(key, value): key is outside of the range allowed by this submap");
			else
				return SkipList.this.put(key, value);
		}

		/**
		 * Copies all of the mappings from the specified map to this map.
		 * 
		 * The complexity of this method is &#937;(1), O(m + n), where m is the
		 * number of mappings in <tt>o</tt>. In the average case, this method
		 * will probabalistically perform in &#920;(m log n) time.
		 * 
		 * @param t
		 *            the map whose entries will be copied into this
		 *            <tt>SubMap</tt>
		 * @since 1.0
		 */
		public void putAll(Map t) {
			Iterator i = t.keySet().iterator();
			while (i.hasNext())
				if (!inRange(i.next()))
					throw new IllegalArgumentException(
							"SkipList.SubMap.put(Map t): one or more key(s) in this map are outside of the range allowed by this submap");
			i = t.entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				this.put(me.getKey(), me.getValue());
			}
		}

		/**
		 * Removes the mapping for this key from this map if it is present. This
		 * method performs in probabilistic &#920;(log n) time; its formal
		 * complexity is &#937;(1), O(n).
		 * 
		 * @param key
		 *            the key of the entry to remove from this <tt>SubMap</tt>
		 * @return previous value associated with specified key, or null if
		 *         there was no mapping for key. A <tt>null</tt> return can
		 *         also indicate that the map previously associated
		 *         <tt>null</tt> with the specified key.
		 * @since 1.0
		 */
		public Object remove(Object key) {
			if (!inRange(key))
				return null;
			else
				return SkipList.this.remove(key);
		}

		/**
		 * Returns a set view of the values mapped to in this map. Most public
		 * methods of <tt>Set</tt> are supported by the returned object.
		 * However <tt>add</tt>, and <tt>addAll</tt> will throw
		 * <tt>UnsupportedOperationException</tt>s if invoked.
		 * 
		 * @return an anonymous subclass of <tt>AbstractSet</tt> backed by
		 *         this <tt>SubMap</tt>
		 * @since 1.0
		 */
		public Collection values() {
			return new AbstractCollection() {
				protected Node locate(Object value) {
					if (value == null)
						value = new Object() {
							public boolean equals(Object o) {
								return o == null;
							}
						};
					Node current = findFirst();
					while (current != null) {
						if (value.equals(current.value))
							return current;
						else
							current = current.next[0];
					}
					return null;
				}

				// Throws an UnsupportedOperationException.
				public boolean add(Object o) {
					throw new UnsupportedOperationException();
				}

				// Throws an UnsupportedOperationException.
				public boolean addAll(Collection c) {
					throw new UnsupportedOperationException();
				}

				// Removes all of the elements from this set.
				public void clear() {
					SubMap.this.clear();
				}

				// Returns true if this set contains the specified element.
				public boolean contains(Object o) {
					return locate(o) != null;
				}

				// Returns true if this set contains all of the elements of the
				// specified collection.
				public boolean containsAll(Collection c) {
					Iterator i = c.iterator();
					while (i.hasNext())
						if (locate(i.next()) == null)
							return false;
					return true;
				}

				// Compares the specified object with this set for equality.
				public boolean equals(Object o) {
					if (!(o instanceof Collection))
						return false;
					Collection s = (Collection) o;
					if (s.size() == SubMap.this.size() && this.containsAll(s))
						return true;
					return false;
				}

				// Returns the hash code value for this set.
				public int hashCode() {
					return System.identityHashCode(this);
				}

				// Returns true if this set contains no elements.
				public boolean isEmpty() {
					return SubMap.this.size() == 0;
				}

				// Returns an iterator over the elements in this set.
				public Iterator iterator() {
					return SubMap.this.valueIterator();
				}

				// Removes the specified element from this set if it is present.
				public boolean remove(Object o) {
					Node n = locate(o);
					if (n == null)
						return false;
					Object key = n.key;
					if (SkipList.this.containsKey(key)) {
						SubMap.this.remove(key);
						return true;
					}
					return false;
				}

				// Removes from this set all of its elements that are contained
				// in the specified collection.
				public boolean removeAll(Collection c) {
					Iterator i = c.iterator();
					boolean rem = true;
					while (i.hasNext())
						rem = rem && remove(i.next());
					return rem;
				}

				// Retains only the elements in this set that are contained in
				// the specified collection.
				public boolean retainAll(Collection c) {
					int ps = SubMap.this.size();
					Node curr = SubMap.this.findFirst();
					while (curr != null) {
						if (!c.contains(curr.value))
							SubMap.this.remove(curr.key);
						curr = curr.next[0];
					}
					return ps != SubMap.this.size();
				}

				// Returns the number of elements in this set (its cardinality).
				public int size() {
					return SubMap.this.size();
				}

				// Returns an array containing all of the elements in this set.
				public Object[] toArray() {
					Object[] o = new Object[SubMap.this.size()];
					int i = 0;
					Node curr = SubMap.this.findFirst();
					while (curr != null) {
						o[i] = curr.value;
						curr = curr.next[0];
						++i;
					}
					return o;
				}

				// Returns an array containing all of the elements in this set;
				// the runtime type of the returned array is that of the
				// specified array.
				public Object[] toArray(Object[] a) {
					if (a.length < SkipList.this.size)
						a = (Object[]) java.lang.reflect.Array.newInstance(a
								.getClass().getComponentType(),
								SkipList.this.size);
					int i = 0;
					Node curr = SubMap.this.findFirst();
					while (curr != null) {
						a[i] = curr.value;
						curr = curr.next[0];
						++i;
					}
					if (a.length > SkipList.this.size)
						a[SkipList.this.size] = null;

					return a;
				}
			};
		}

		/**
		 * Returns the number of key-value mappings in this map.
		 * 
		 * This method performs in &#937;(n) time.
		 * 
		 * @return the number of key-value mappings in this map (this
		 *         <tt>SubMap</tt>'s <i>cardinality</i>)
		 * @since 1.0
		 */
		public int size() {
			int subsize = 0;
			Node curr = findFirst();
			while ((curr != null)
					&& (SkipList.this.comp.compare(curr.key, toKey) < 0)) {
				++subsize;
				curr = curr.next[0];
			}
			return subsize;
		}

		/**
		 * Returns the comparator associated with this sorted map, or
		 * <tt>null</tt> if it uses its keys' natural ordering.
		 * 
		 * @return an instance of <tt>Comparator</tt> if one is associated
		 *         with this <tt>SubMap</tt>, <tt>null</tt> otherwise
		 * @since 1.0
		 */
		public Comparator comparator() {
			return SkipList.this.comparator();
		}

		/**
		 * Returns the first (lowest) key currently in this sorted map.
		 * 
		 * @return the first key currently in this <tt>SubMap</tt>.
		 * @since 1.0
		 */
		public Object firstKey() {
			Node n = findFirst();
			if (n == null)
				return null;
			else
				return n.key;
		}

		/**
		 * Returns a view of the portion of this sorted map whose keys are
		 * strictly less than <tt>toKey</tt>. This method returns an object
		 * whose type is {@link SkipList.SubMap}, a subclass of
		 * <tt>AbstractSortedMap</tt>. All methods of <tt>SortedMap</tt>
		 * are supported by the returned sub map, but users of this return value
		 * should be aware that keys falling outside of the range defined by the
		 * value of <tt>firstKey()</tt> and <tt>toKey</tt> will result in an
		 * <tt>IllegalArgumentException</tt> being thrown, as per the contract
		 * specified in <tt>SortedMap</tt>.<p/>
		 * 
		 * If <tt>toKey</tt> is outside of the range of this <tt>SubMap</tt>,
		 * an <tt>IllegalArgumentException</tt> will be thrown.
		 * 
		 * @param toKey
		 *            all keys in the returned map will be strictly less than
		 *            this key
		 * @return an instance of <tt>SortedMap</tt> backed by this
		 *         <tt>SubMap</tt>, and whose keys are strictly less than
		 *         <tt>toKey</tt>
		 * @throws IllegalArgumentException
		 *             if <tt>toKey</tt> is outside the range of this
		 *             <tt>SubMap</tt>
		 * @since 1.0
		 */
		public SortedMap headMap(Object toKey) {
			if (SkipList.this.comp.compare(this.toKey, toKey) < 0)
				throw new IllegalArgumentException(
						"SkipList.SubMap.headMap(toKey): toKey is outside of the range allowed by this submap");
			else
				return new SubMap(this.fromKey, toKey);
		}

		/**
		 * Returns the last (highest) key currently in this sorted map. This
		 * method performs in probabilistic &#920;(log n) time; its formal
		 * complexity is &#937;(1), O(n).
		 * 
		 * @return the last key currently in this <tt>SubMap</tt>.
		 * @since 1.0
		 */
		public Object lastKey() {
			Node curr = head.next[head.next.length - 1];
			for (int i = head.next.length - 1; i >= 0; --i)
				while ((curr.next[i] != null)
						&& (SkipList.this.comp.compare(curr.key, this.toKey) < 0))
					curr = curr.next[i];
			return (inRange(curr.key) ? curr.key : null);
		}

		/**
		 * Returns a view of the portion of this sorted map whose keys range
		 * from fromKey, inclusive, to toKey, exclusive. This method returns an
		 * object whose type is {@link SkipList.SubMap}, a subclass of
		 * <tt>AbstractSortedMap</tt>. All methods of <tt>SortedMap</tt>
		 * are supported by the returned sub map, but users of this return value
		 * should be aware that keys falling outside of the range defined by
		 * <tt>firstKey</tt> and <tt>toKey</tt> will result in an
		 * <tt>IllegalArgumentException</tt> being thrown, as per the contract
		 * specified in <tt>SortedMap</tt>.
		 * <p />
		 * 
		 * If <tt>toKey</tt> or <tt>fromKey</tt> is outside of the range of
		 * this <tt>SubMap</tt>, an <tt>IllegalArgumentException</tt> will
		 * be thrown.
		 * 
		 * @param fromKey
		 *            all keys in the returned map will be greater than or equal
		 *            to this key
		 * @param toKey
		 *            all keys in the returned map will be strictly less than
		 *            this key
		 * @return an instance of <tt>SortedMap</tt> backed by this
		 *         <tt>SubMap</tt>, and whose keys are greater than or equal
		 *         to <tt>fromKey</tt> and strictly less than <tt>toKey</tt>
		 * @throws IllegalArgumentException
		 *             if <tt>fromKey</tt> or <tt>toKey</tt> is outside the
		 *             range of this <tt>SubMap</tt>.
		 * @since 1.0
		 */
		public SortedMap subMap(Object fromKey, Object toKey) {
			if ((SkipList.this.comp.compare(this.fromKey, toKey) > 0)
					|| (SkipList.this.comp.compare(this.toKey, toKey) < 0))
				throw new IllegalArgumentException(
						"SkipList.SubMap.subMap(fromKey, toKey): one or both key(s) is outside of the range allowed by this submap");
			else
				return new SubMap(fromKey, toKey);
		}

		/**
		 * Returns a view of the portion of this sorted map whose keys are
		 * greater than or equal to fromKey. This method returns an object whose
		 * type is {@link SkipList.SubMap}, a subclass of
		 * <tt>AbstractSortedMap</tt>. All methods of <tt>SortedMap</tt>
		 * are supported by the returned sub map, but users of this return value
		 * should be aware that keys falling outside of the range defined by
		 * <tt>fromKey</tt> and the value of <tt>lastKey()</tt> will result
		 * in an <tt>IllegalArgumentException</tt> being thrown, as per the
		 * contract specified in <tt>SortedMap</tt>.
		 * <p />
		 * 
		 * If <tt>toKey</tt> is outside of the range of this <tt>SubMap</tt>,
		 * an <tt>IllegalArgumentException</tt> will be thrown.
		 * 
		 * @param fromKey
		 *            all keys in the returned map will be greater than or equal
		 *            to this key
		 * @return an instance of <tt>SortedMap</tt> backed by this
		 *         <tt>SkipList</tt>, and whose keys are strictly less than
		 *         <tt>toKey</tt>
		 * @throws IllegalArgumentException
		 *             if <tt>fromKey</tt> is outside the range of this
		 *             <tt>SubMap</tt>.
		 * @since 1.0
		 */
		public SortedMap tailMap(Object fromKey) {
			if (SkipList.this.comp.compare(this.fromKey, toKey) > 0)
				throw new IllegalArgumentException(
						"SkipList.SubMap.tailMap(fromKey): fromKey is outside of the range allowed by this submap");
			else
				return new SubMap(fromKey, this.toKey);
		}

		/**
		 * Locates the first element in the backing <tt>SkipList</tt> greater
		 * than or equal to <tt>fromKey</tt>.
		 * 
		 * @return the first <tt>Map.Entry</tt> in this <tt>SkipList</tt>
		 *         whose key is greater than or equal to <tt>fromKey</tt>
		 * @since 1.0
		 */
		protected Node findFirst() {
			// if fromKey is removed from this SubMap, we'll need a new "first"
			// element.
			// So really, we want find the first element greater than or equal
			// to fromKey
			if (size() <= 0)
				return null;
			Node curr = SkipList.this.head;
			for (int i = (SkipList.this.head.next.length - 1); i >= 0; --i) {
				while ((curr.next[i] != null)
						&& (SkipList.this.comp.compare(fromKey,
								curr.next[i].key) >= 0))
					curr = curr.next[i];
			}
			return curr.next[0];
		}

		/**
		 * Returns <tt>true</tt> if <tt>key</tt>'s ordering, as per this
		 * <tt>SubMap</tt>'s comparator, places it between <tt>fromKey</tt>,
		 * inclusive, to <tt>toKey</tt>, exclusive.
		 * 
		 * @return <tt>true</tt> if <tt>key</tt> falls within [<tt>fromKey</tt>,<tt>toKey</tt>),
		 *         <tt>false</tt> otherwise
		 * @since 1.0
		 */
		protected boolean inRange(Object key) {
			if (key == null)
				return false;
			return ((SkipList.this.comp.compare(key, fromKey) >= 0) && (SkipList.this.comp
					.compare(key, toKey) < 0));
		}

		/**
		 * Returns an iterator over the entries of this <tt>SubMap</tt>.
		 * 
		 * @return an <tt>Iterator</tt> over the entries of this
		 *         <tt>SubMap</tt>. The returned <tt>Iterator</tt>'s
		 *         <tt>next()</tt> method will return objects of type
		 *         {@link SkipList.Node}
		 * @since 1.0
		 */
		protected Iterator entryIterator() {
			return new EntryIterator();
		}

		/**
		 * An iterator over the entries in this <tt>SubMap</tt>. This
		 * <tt>Iterator</tt>'s <tt>next()</tt> method returns objects of
		 * type <tt>Map.Entry</tt>.
		 * 
		 * @author Evan Machusak (<a
		 *         href="mailto:emach@cs.umd.edu">emach@cs.umd.edu</a>)
		 * @since 1.0
		 * @version 1.0
		 */
		protected class EntryIterator implements java.util.Iterator {
			protected Node curr;

			protected EntryIterator() {
				curr = SubMap.this.findFirst();
			}

			public Object next() {
				if (curr == null)
					throw new NoSuchElementException();
				Node n = curr;
				curr = curr.next[0];
				return n;
			}

			public boolean hasNext() {
				return curr != null;
			}

			public void remove() {
				SubMap.this.remove(fromKey);
			}
		}

		/**
		 * Returns an iterator over the values mapped to in this <tt>SubMap</tt>.
		 * The returned <tt>Iterator</tt>'s <tt>next()</tt> method will
		 * return objects of potentially varied types, or <tt>null</tt>.
		 * 
		 * @return an <tt>Iterator</tt> over the keys in this <tt>SubMap</tt>
		 * @since 1.0
		 */
		protected Iterator valueIterator() {
			return new ValueIterator();
		}

		/**
		 * An iterator over the values in this <tt>SubMap</tt>. This
		 * <tt>Iterator</tt>'s <tt>next()</tt> method returns objects of
		 * type <tt>Object</tt>, or <tt>null</tt>.
		 * 
		 * @author Evan Machusak (<a
		 *         href="mailto:emach@cs.umd.edu">emach@cs.umd.edu</a>)
		 * @since 1.0
		 * @version 1.0
		 */
		protected class ValueIterator implements java.util.Iterator {
			protected Node curr;

			protected ValueIterator() {
				curr = SubMap.this.findFirst();
			}

			public Object next() {
				if (curr == null)
					throw new NoSuchElementException();
				Node n = curr;
				curr = curr.next[0];
				return n.getValue();
			}

			public boolean hasNext() {
				return curr != null;
			}

			public void remove() {
				SubMap.this.remove(curr.key);
			}
		}

		/**
		 * Returns an iterator over the keys in this <tt>SubMap</tt>. The
		 * returned <tt>Iterator</tt>'s <tt>next()</tt> method will return
		 * objects of potentially varied types.
		 * 
		 * @return an <tt>Iterator</tt> over the keys in this <tt>SubMap</tt>
		 * @since 1.0
		 */
		protected Iterator keyIterator() {
			return new KeyIterator();
		}

		/**
		 * An iterator over the keys in this <tt>SubMap</tt>. This
		 * <tt>Iterator</tt>'s <tt>next()</tt> method returns objects of
		 * type <tt>Object</tt>.
		 * 
		 * @author Evan Machusak (<a
		 *         href="mailto:emach@cs.umd.edu">emach@cs.umd.edu</a>)
		 * @since 1.0
		 * @version 1.0
		 */
		protected class KeyIterator implements java.util.Iterator {
			protected Node curr;

			protected KeyIterator() {
				curr = SubMap.this.findFirst();
			}

			public Object next() {
				if (curr == null)
					throw new NoSuchElementException();
				Node n = curr;
				curr = curr.next[0];
				return n.key;
			}

			public boolean hasNext() {
				return curr != null;
			}

			public void remove() {
				SubMap.this.remove(curr.key);
			}
		}
	}

	/**
	 * Draws this <tt>SkipList</tt> on the specified <tt>CanvasPlus</tt>.
	 * 
	 * @param cp
	 *            the canvas on which to draw this <tt>SkipList</tt>
	 * @since 1.0
	 */
	/*public void draw(CanvasPlus cp) {

		// some aesthetics
		int baseY = 30;
		int x = 15;
		int y = baseY;
		int rectWidth = 40;
		int rectHeight = 20;
		int nodeSpacing = 20;

		// Need to keep this info to store the links
		java.awt.geom.Point2D.Float[] pts = new java.awt.geom.Point2D.Float[head.next.length];
		Node[] update = new Node[head.next.length];

		Color color = new Color(0f, 0f, 0.5f);
		// draw the head tower
		for (int i = 0; i < head.next.length; ++i) {
			y += rectHeight;
			// draw the rectangle for the link
			cp.addRectangle(x, y, rectWidth, rectHeight, color, false);
		}
		// draw the 0-level link for the head
		cp.addLine(x + rectWidth, baseY + (rectHeight >> 1), x + nodeSpacing
				+ rectWidth, baseY + (rectHeight >> 1), Color.BLACK);
		// initialize them bitches
		x += rectWidth;
		int h = baseY + (rectHeight >> 1);
		for (int i = 0; i < pts.length; ++i) {
			pts[i] = new java.awt.geom.Point2D.Float(x, h);
			update[i] = head.next[i];
			h += rectHeight;
		}
		x += nodeSpacing;
		Node curr = head.next[0];
		while (curr != null) {
			y = baseY + rectHeight;

			// first, draw a rectangle to hold the key
			cp.addRectangle(x, y, rectWidth, rectHeight, Color.BLACK, false);
			cp.addText(curr.getKey().toString(), x + 5, baseY
					+ (rectHeight >> 1) - 5);

			// now draw the 0-level link, if this isn't the last node
			if (curr.next[0] != null)
				cp.addLine(x + rectWidth, baseY + (rectHeight >> 1), x
						+ nodeSpacing + rectWidth, baseY + (rectHeight >> 1),
						Color.BLACK);

			// now, draw the tower
			for (int i = 1; i < curr.next.length; ++i) {
				y += rectHeight;
				// draw the rectangle
				cp
						.addRectangle(x, y, rectWidth, rectHeight, Color.BLACK,
								false);
				// if the next link is null, draw a line through the link, as
				// per convention
				if (curr.next[i] == null) {
					cp.addLine(x, y - rectHeight, x + rectWidth, y, Color.GRAY);
				}
				if (update[i] == curr)
					cp.addLine(pts[i].x, pts[i].y, x, y - (rectHeight >> 1),
							Color.BLACK);
				update[i] = curr.next[i];
				pts[i].x = x + rectWidth;
				pts[i].y = y - (rectHeight >> 1);

			}

			x += nodeSpacing + rectWidth;
			curr = curr.next[0];
		}
	}*/

	/**
	 * Constructs a perfectly balanced skip list from the key-value mappings in
	 * <tt>t</tt>. The resulting tree will have a maximum tower height of
	 * <font class="u">&#8968;</font><tt>log<sub>2</sub>n</tt><font
	 * class="u">&#8969;</font> where <tt>n = t.size()</tt>.
	 * 
	 * @param t
	 *            the map whose entries will be the basis of this
	 *            <tt>SkipList</tt>
	 * @since 1.0
	 */
	protected void buildPerfectList(Map t) {
		Set s = t.entrySet();
		Node[] nodes = new Node[t.size()];
		int n = 0;
		for (Iterator i = s.iterator(); i.hasNext();) {
			Map.Entry me = (Map.Entry) i.next();
			if (me.getKey() != null)
				nodes[n] = new Node(me.getKey(), me.getValue());
			else
				throw new NullPointerException(
						"SkipList does not support null keys.");
			nodes[n].next = null;
			++n;
		}
		if (!(t instanceof SortedMap))
			Arrays.sort(nodes, this.comp);

		// take the ceiling of the log, and then add 1
		// the highest link will give theta(1) time in accessing the last key in
		// our perfect list
		if (nodes.length == 1)
			this.head.next = new Node[1];
		else
			this.head.next = new Node[(int) (log2(nodes.length) + 0.5d)];

		// make the last node have a tower height equal to the head
		nodes[nodes.length - 1].next = new Node[head.next.length];
		for (int i = 0; i < head.next.length; ++i)
			nodes[nodes.length - 1].next[i] = null; // initialize to null

		// the initial value for the last param, n, is the length of the head -
		// 2 since we want the param to equal
		// a valid index, not be 1 greater
		// since the largest tower created after this will be 1 smaller than
		// head, we pass an initial n smaller
		// than head.length
		buildAndLink(nodes, 0, nodes.length - 1, head.next.length - 1);

		// skip half the list each successive turn
		int skip = (nodes.length - 1) >> 1;
		for (int i = head.next.length - 1; i > 0; --i) {
			head.next[i] = (skip < nodes.length ? nodes[skip]
					: nodes[nodes.length - 1]);
			skip = (skip >> 1);
		}
		head.next[0] = nodes[0];
	}

	/**
	 * Helper function for <tt>buildPerfectList</tt>.
	 * 
	 * @param nodes
	 *            an array representation of the nodes contained in this
	 *            <tt>SkipList</tt>
	 * @param start
	 *            the starting index of the considered portion of <tt>nodes</tt>
	 * @param end
	 *            the end index of the considered portion of <tt>nodes</tt>
	 * @param n
	 *            the current recursive depth of this method
	 * @since 1.0
	 */
	private void buildAndLink(Node[] nodes, int start, int end, int n) {
		int len = (end + 1) - start;
		if (len == 1) {
			if (nodes[start].next == null)
				nodes[start].next = new Node[] { (start < (nodes.length - 1) ? nodes[start + 1]
						: null) };
		} else {
			int middle = ((len >> 1) - 1) + start + (len % 2);
			nodes[middle].next = new Node[n + 1];

			// The top link always skips the entire distance spanned from middle
			// to end
			nodes[middle].next[n] = nodes[end];

			// The length of subsequent links relies on the distance between the
			// end and the middle
			int mlen = end - middle + 1;
			int skip = mlen >> 1;
			for (int i = n - 1; i >= 1; --i) {
				nodes[middle].next[i] = nodes[middle + skip];
				// if the length was odd, add one, since the taller towers are
				// right-biased
				skip = (skip % 2) + (skip >> 1);
			}
			// Set the bottom link to be the next element, or null if this is
			// the last element
			// nodes[middle].next[0] = ((middle+1) < nodes.length ?
			// nodes[middle+1] : null);
			nodes[middle].next[0] = nodes[middle + 1];
			int newn = (n > 0 ? n - 1 : 0);
			buildAndLink(nodes, start, middle, newn);
			buildAndLink(nodes, middle + 1, end, newn);
		}
	}

	/**
	 * Calculates <tt>log<sub>2</sub>d</tt>.
	 * 
	 * @param d
	 *            the double precision number whose base-2 logarithm should be
	 *            calculated
	 * @return <tt>log<sub>2</sub>d</tt>
	 * @since 1.0
	 */
	protected double log2(double d) {
		return Math.log(d) * 1.442695d;
	}
}