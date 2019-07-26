package code;

import java.lang.reflect.Array;

import given.AbstractArraySort;

/*
 * Implement the merge-sort algorithm here. You can look at the slides for the pseudo-codes.
 * Make sure to use the swap and compare functions given in the AbstractArraySort!
 * 
 * You may need to create an Array of K (Hint: the auxiliary array). 
 * Look at the previous assignments on how we did this!
 * 
 */

public class MergeSort<K extends Comparable<K>> extends AbstractArraySort<K> {

	K[] auxA;

	public MergeSort() {
		name = "Mergesort";

		// Initialize anything else here
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sort(K[] inputArray) {
		// TODO: Implement the merge-sort algorithm
		auxA = (K[]) new Comparable[inputArray.length];
		mergeSort(inputArray, 0, inputArray.length - 1);
	}

	public void mergeSort(K[] inputArray, int lo, int hi) {
		if (lo < hi) {
			int mid = (lo + hi) / 2;
			mergeSort(inputArray, lo, mid);
			mergeSort(inputArray, mid + 1, hi);
			merge(inputArray, lo, mid, hi);
		}
	}

	// Public since we are going to check its output!
	public void merge(K[] inputArray, int lo, int mid, int hi) {
		System.arraycopy(inputArray, lo, auxA, lo, hi - lo + 1);
		int i = lo;
		int k = lo;
		int j = mid + 1;
		while (k <= hi) {
			if (j > hi || (i <= mid && compare(auxA[i], auxA[j]) <= 0)) {
				inputArray[k] = auxA[i];
				k++;
				i++;
			}
			else {
				inputArray[k] = auxA[j];
				k++;
				j++;
			}
		}

	}

	// Feel free to add more methods
}
