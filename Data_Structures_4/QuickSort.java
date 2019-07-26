package code;

import given.AbstractArraySort;

/*
 * Implement the quick-sort algorithm here. You can look at the slides for the pseudo-codes.
 * Make sure to use the swap and compare functions given in the AbstractArraySort!
 * 
 */

public class QuickSort<K extends Comparable<K>> extends AbstractArraySort<K> {
	// Add any fields here

	public QuickSort() {
		name = "Quicksort";

		// Initialize anything else here
	}

	// useful if we want to return a pair of indices from the partition function.
	// You do not need to use this if you are just returning and integer from the
	// partition
	public class indexPair {
		public int p1, p2;

		indexPair(int pos1, int pos2) {
			p1 = pos1;
			p2 = pos2;
		}

		public String toString() {
			return "(" + Integer.toString(p1) + ", " + Integer.toString(p2) + ")";
		}
	}

	@Override
	public void sort(K[] inputArray) {

		quicksort(inputArray, 0, inputArray.length-1);
	}

	private void quicksort(K[] inputArray, int lo, int hi) {
		if (lo < hi) {
			int p = pickPivot(inputArray, lo, hi);
			indexPair ret = partition(inputArray, lo, hi, p);
			quicksort(inputArray, lo, ret.p1);
			quicksort(inputArray, ret.p2, hi);

		}
	}

	// Public since we are going to check its output!
	public indexPair partition(K[] inputArray, int lo, int hi, int p) {
		int e = lo;
		int u = lo;
		int g = hi + 1;
		K pivotElement = inputArray[p];
		while (u < g) {
			if (compare(inputArray[u], pivotElement) < 0) {
				swap(inputArray, u, e);
				u++;
				e++;
			} else if (compare(inputArray[u], pivotElement) == 0) {
				u++;
			} else {
				g--;
				swap(inputArray, u, g);
			}
		}
		indexPair ret;
		ret = new indexPair(e, g);
		return ret;
	}

	/*
	 * Alternative, if you are just returning an integer public int partition(K[]
	 * inputArray, int lo, int hi, int p) { //TODO:: Implement a partitioning
	 * function here return null; }
	 */

	// The below methods are given given as suggestion. You do not need to use them.
	// Feel free to add more methods
	protected int pickPivot(K[] inputArray, int lo, int hi) {

		if (compare(inputArray[lo], inputArray[(lo + hi) / 2]) > 0) {
			if (compare(inputArray[(lo + hi) / 2], inputArray[hi]) > 0) {
				return (lo + hi) / 2;
			} else if (compare(inputArray[lo], inputArray[hi]) > 0) {
				return hi;
			} else {
				return lo;
			}
		} else {
			if (compare(inputArray[lo], inputArray[hi]) > 0) {
				return lo;
			} else if (compare(inputArray[(lo + hi) / 2], inputArray[hi]) > 0) {
				return hi;
			} else {
				return (lo + hi) / 2;
			}
		}
	}
}
