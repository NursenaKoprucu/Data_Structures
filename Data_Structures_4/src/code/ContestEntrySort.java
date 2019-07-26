package code;

import code.QuickSort.indexPair;
import given.AbstractArraySort;

/*
 * Your sorting algorithm for the sorting spree! 
 * You do not need to use the swap and compare methods of AbstractArraySort here
 * Only the speed of the code and the correctness of the output will be checked
 * 
 * We suggest that you use a hybrid algorithm!
 * 
 */

public class ContestEntrySort<K extends Comparable<K>> extends AbstractArraySort<K> {

	private CountingSort<K> countingSorter = new CountingSort<K>();

	public static class indexPair {
		public final int p1, p2;

		indexPair(int pos1, int pos2) {
			p1 = pos1;
			p2 = pos2;
		}
	}

	public ContestEntrySort() {
		// Change the name with your ID!
		name = "59843";

		// Initialize anything else here
	}

	@Override
	public void sort(K[] inputArray) {
		if (isSorted(inputArray)) {
			return;
		}

		if (inputArray[0] instanceof Integer) {
			countingSorter.sort(inputArray);
			return;
		}

		int depthLimit = (int) (2 * Math.floor(Math.log(inputArray.length) / Math.log(2)));
		introsort(inputArray, 0, inputArray.length - 1, depthLimit);
	}

	private boolean isSorted(K[] inputArray) {
		for (int i = 0; i < inputArray.length - 1; i++) {
			if (inputArray[i].compareTo(inputArray[i + 1]) > 0) {
				return false;
			}
		}
		return true;
	}

	private void introsort(K[] input, int lo, int hi, int depth) {
		while (lo < hi) {
			if (hi - lo < 16) {
				if (hi - lo == 0) {
					// Nothing :)
				} else if (hi - lo == 1) {
					swapWhenGreater(input, lo, hi);
				} else if (hi - lo == 2) {
					swapWhenGreater(input, lo, lo + 1);
					swapWhenGreater(input, lo, lo + 2);
					swapWhenGreater(input, lo + 1, lo + 2);
				} else {
					insertionSort(input, lo, hi);
				}
				return;
			} else {
				if (depth == 0) {
					heapsort(input, lo, hi);
				}
				--depth;
				int p = pickPivot(input, lo, hi);
				indexPair pivots = partition(input, lo, hi, p);
				introsort(input, pivots.p2, hi, depth);
				hi = pivots.p1;
			}
		}
	}

	private int pickPivot(K[] input, int lo, int hi) {
		int length = hi - lo;
		int second = medianOf3(input, lo + length / 3, lo + 2 * length / 3);
		return second;
	}

	private int medianOf3(K[] input, int lo, int hi) {
		int mid = (lo + hi) / 2;
		swapWhenGreater(input, lo, mid);
		swapWhenGreater(input, mid, hi);
		swapWhenGreater(input, lo, hi);
		return mid;
	}

	private indexPair partition(K[] inputArray, int lo, int hi, int p) {
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

	private void heapsort(K[] inputArray, int lo, int hi) {
		heapify(inputArray, lo, hi);
		int k = hi;
		while (lo < k) {
			swap(inputArray, 0, k);
			k = k - 1;
			downheap(inputArray, 0, k);
		}
	}

	private void heapify(K[] inputArray, int lo, int hi) {
		for (int i = (hi - lo + 1) / 2 - 1; i >= lo; i--) {
			downheap(inputArray, i, hi);
		}
	}

	private void downheap(K[] inputArray, int i, int k) {
		int ind = i;
		int lc = 2 * i + 1;
		int rc = 2 * i + 2;
		if (lc <= k && compare(inputArray[ind], inputArray[lc]) < 0) {
			ind = lc;
		}
		if (rc <= k && compare(inputArray[ind], inputArray[rc]) < 0) {
			ind = rc;
		}
		if (ind != i) {
			swap(inputArray, i, ind);
			downheap(inputArray, ind, k);
		}
	}

	private void insertionSort(K[] input, int lo, int hi) {
		for (int i = lo; i <= hi; i++) {
			for (int j = i; j > 0 && compare(input[j - 1], input[j]) > 0; j--) {
				swap(input, j, j - 1);
			}
		}
	}

	private void swapWhenGreater(K[] arr, int pos1, int pos2) {
		if (arr[pos1].compareTo(arr[pos2]) > 0) {
			mySwap(arr, pos1, pos2);
		}
	}

	private void mySwap(K[] arr, int pos1, int pos2) {
		K temp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = temp;
	}

}
