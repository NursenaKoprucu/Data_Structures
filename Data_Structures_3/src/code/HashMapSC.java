package code;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import given.AbstractHashMap;
import given.HashEntry;

/*
 * The file should contain the implementation of a hashmap with:
 * - Separate Chaining for collision handling
 * - Multiply-Add-Divide (MAD) for compression: (a*k+b) mod p
 * - Java's own linked lists for the secondary containers
 * - Resizing (to double its size) and rehashing when the load factor gets above a threshold
 *   Note that for this type of hashmap, load factor can be higher than 1
 * 
 * Some helper functions are provided to you. We suggest that you go over them.
 * 
 * You are not allowed to use any existing java data structures other than for the buckets (which have been 
 * created for you) and the keyset method
 */

public class HashMapSC<Key, Value> extends AbstractHashMap<Key, Value> {

	// The underlying array to hold hash entry Lists
	private LinkedList<HashEntry<Key, Value>>[] buckets;

	// Note that the Linkedlists are still not initialized!
	@SuppressWarnings("unchecked")
	protected void resizeBuckets(int newSize) {
		// Update the capacity
		N = nextPrime(newSize);
		buckets = (LinkedList<HashEntry<Key, Value>>[]) Array.newInstance(LinkedList.class, N);
	}

	/*
	 * ADD MORE FIELDS IF NEEDED
	 * 
	 */

	// The threshold of the load factor for resizing
	protected float criticalLoadFactor;

	/*
	 * ADD A NESTED CLASS IF NEEDED
	 * 
	 */

	public int hashValue(Key key, int iter) {
		return hashValue(key);
	}

	public int hashValue(Key key) {
		// TODO: Implement the hashvalue computation with the MAD method. Will be almost
		// the same as the primaryHash method of HashMapDH
		int k = Math.abs(key.hashCode());
		return Math.abs((a * k + b) % P) % N;
	}

	// Default constructor
	public HashMapSC() {
		this(101);
	}

	public HashMapSC(int initSize) {
		// High criticalAlpha for representing average items in a secondary container
		this(initSize, 10f);
	}

	public HashMapSC(int initSize, float criticalAlpha) {
		N = initSize;
		criticalLoadFactor = criticalAlpha;
		resizeBuckets(N);

		// Set up the MAD compression and secondary hash parameters
		updateHashParams();

		/*
		 * ADD MORE CODE IF NEEDED
		 * 
		 */
	}

	/*
	 * ADD MORE METHODS IF NEEDED
	 * 
	 */

	@Override
	public Value get(Key k) {
		if (k == null) {
			return null;
		}
		int h = hashValue(k);
		if (buckets[h] == null) {
			return null;
		}
		for (HashEntry<Key, Value> entry : buckets[h]) {
			if (entry.getKey().equals(k)) {
				return entry.getValue();
			}
		}
		return null;
	}

	@Override
	public Value put(Key k, Value v) {
		// TODO Auto-generated method stub
		// Do not forget to resize if needed!
		// Note that the linked lists are not initialized!
		if (k == null) {
			return null;
		}
		checkAndResize();
		int h = hashValue(k);
		if (buckets[h] == null) {
			buckets[h] = new LinkedList<HashEntry<Key, Value>>();
		}
		for (HashEntry<Key, Value> entry : buckets[h]) {
			if (entry.getKey().equals(k)) {
				Value ret = entry.getValue();
				entry.setValue(v);
				return ret;
			}
		}
		buckets[h].add(new HashEntry<>(k, v));
		n++;
		return null;
	}

	@Override
	public Value remove(Key k) {
		if (k == null) {
			return null;
		}
		int h = hashValue(k);
		if (buckets[h] == null) {
			return null;
		}
		Value val = get(k);
		if (val == null) {
			return null;
		}
		buckets[h].remove(new HashEntry<Key, Value>(k, val));
		n--;
		return val;
	}

	@Override
	public Iterable<Key> keySet() {
		Set<Key> keys = new java.util.HashSet<>();
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] != null) {
				for (HashEntry<Key, Value> entry : buckets[i]) {
					keys.add(entry.getKey());
				}
			}
		}
		return keys;
	}

	/**
	 * checkAndResize checks whether the current load factor is greater than the
	 * specified critical load factor. If it is, the table size should be increased
	 * to 2*N and recreate the hash table for the keys (rehashing). Do not forget to
	 * re-calculate the hash parameters and do not forget to re-populate the new
	 * array!
	 */
	protected void checkAndResize() {
		if (loadFactor() > criticalLoadFactor) {
			LinkedList<HashEntry<Key, Value>>[] oldArray = buckets;
			resizeBuckets(2 * N);
			updateHashParams();
			for (int i = 0; i < oldArray.length; i++) {
				if (oldArray[i] != null) {
					for (HashEntry<Key, Value> entry : oldArray[i]) {
						int h = hashValue(entry.getKey());
						if (buckets[h] == null) {
							buckets[h] = new LinkedList<HashEntry<Key, Value>>();

						}
						buckets[h].add(entry);
					}
				}
			}
		}
	}
}
