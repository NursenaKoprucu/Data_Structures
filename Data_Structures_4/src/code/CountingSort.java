package code;

import given.AbstractArraySort;

/*
 * Implement the c algorithm here. You can look at the slides for the pseudo-codes.
 * You do not have to use swap or compare from the AbstractArraySort here
 * 
 * You may need to cast any K to Integer
 * 
 */

public class CountingSort<K extends Comparable<K>> extends AbstractArraySort<K> {

	// Add any fields here

	public CountingSort() {
		name = "Countingsort";
	}

	@Override
	public void sort(K[] inputArray) {

		if (inputArray == null) {
			System.out.println("Null array");
			return;
		}
		if (inputArray.length < 1) {
			System.out.println("Empty array");
			return;
		}

		if (!(inputArray[0] instanceof Integer)) {
			System.out.println("Can only sort integers!");
			return;
		}
		
		// TODO:: Implement the counting-sort algorithm here
		int minKey = min(inputArray);
		int maxKey = max(inputArray);
		int k = maxKey - minKey + 1;
		int[] counts = new int[k];
		@SuppressWarnings("unchecked")
		K[] copyA = (K[]) new Comparable[inputArray.length];
		for (int i = 0; i < inputArray.length; i++) {
			copyA[i] = inputArray[i];
		}
		for (int i = 0; i < inputArray.length; i++) {
			counts[(int) inputArray[i] - minKey]++;
		}
		for (int i = 1; i < k; i++) {
			counts[i] = counts[i] + counts[i - 1];
		}
		for (int i = inputArray.length - 1; i >= 0; i--) {
			inputArray[counts[(int) copyA[i] - minKey] - 1] = copyA[i];
			counts[(int) copyA[i] - minKey]--;
		}
		
	}

	private int max(K[] inputArray) {
		// TODO Auto-generated method stub
		int max = (int) inputArray[0];

		for (int i = 1; i < inputArray.length; i++) {
			if (max < (int) inputArray[i]) {
				max = (int) inputArray[i];
			}

		}
		return max;
	}

	private int min(K[] inputArray) {
		int min = (int) inputArray[0];

		for (int i = 1; i < inputArray.length; i++) {
			if (min > (int) inputArray[i]) {
				min = (int) inputArray[i];
			}

		}
		return min;
	}

}
