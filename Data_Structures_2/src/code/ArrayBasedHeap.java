package code;

import java.util.ArrayList;
import java.util.Comparator;

import given.Entry;
import given.iAdaptablePriorityQueue;
import given.iBinarySearchTree;

/*
 * Implement an array based heap
 * Note that you can just use Entry here!
 * 
 */

public class ArrayBasedHeap<Key, Value> implements iAdaptablePriorityQueue<Key, Value> {

	// Use this arraylist to store the nodes of the heap.
	// This is required for the autograder.
	// It makes your implementation more verbose (e.g. nodes[i] vs nodes.get(i)) but
	// then you do not have to deal with dynamic resizing

	protected ArrayList<Entry<Key, Value>> nodes = new ArrayList<>();
	int n = 0;
	Comparator<Key> comparator;

	private int parentOf(int i) {
		return (i - 1) / 2;
	}

	private int leftChildOf(int i) {
		return 2 * i + 1;
	}

	private int rightChildOf(int i) {
		return 2 * i + 2;
	}

	private boolean hasLeftChild(int i) {
		return leftChildOf(i) < nodes.size();
	}

	private boolean hasRightChild(int i) {
		return rightChildOf(i) < nodes.size();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return nodes.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return nodes.isEmpty();
	}

	@Override
	public void setComparator(Comparator<Key> C) {
		// TODO Auto-generated method stub
		this.comparator = C;
	}

	@Override
	public Comparator<Key> getComparator() {
		// TODO Auto-generated method stub
		return comparator;
	}

	private void swap(int i, int j) {
		Entry<Key, Value> e = nodes.get(i);
		nodes.set(i, nodes.get(j));
		nodes.set(j, e);
	}

	private void upHeap(int i) {
		while (i > 0) {
			int p = parentOf(i);

			int comparison = comparator.compare(nodes.get(i).getKey(), nodes.get(p).getKey());
			if (comparison >= 0) {
				break;
			}
			swap(i, p);
			i = p;
		}
	}

	private void downHeap(int i) {
		while (hasLeftChild(i)) {
			int smallChild = leftChildOf(i);
			if (hasRightChild(i)) {
				int rc = rightChildOf(i);
				int comparison = comparator.compare(nodes.get(rc).getKey(), nodes.get(smallChild).getKey());
				if (comparison < 0) {
					smallChild = rc;
				}
			}
			int comparison2 = comparator.compare(nodes.get(smallChild).getKey(), nodes.get(i).getKey());
			if (comparison2 >= 0) {
				break;
			}
			swap(i, smallChild);
			i = smallChild;
		}
	}

	@Override
	public void insert(Key k, Value v) {
		// TODO Auto-generated method stub
		Entry<Key, Value> newestEntry = new Entry<>(k, v);
		nodes.add(newestEntry);
		upHeap(n);
		n++;
	}

	@Override
	public Entry<Key, Value> pop() {
		if (isEmpty()) {
			return null;
		}
		Entry<Key, Value> ret = nodes.get(0);
		swap(0, n - 1);
		nodes.remove(n - 1);
		n--;
		downHeap(0);
		return ret;
	}

	@Override
	public Entry<Key, Value> top() {
		// TODO Auto-generated method stub
		return isEmpty() ? null : nodes.get(0);
	}

	@Override
	public Value remove(Key k) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getKey().equals(k)) {
				Value ret = nodes.get(i).getValue();
				swap(i, n - 1);
				nodes.remove(n - 1);
				n--;
				if(i == n) {
					return ret;
				}
				if(i == 0) {
					downHeap(i);
					return ret;
				}
				int p = parentOf(i);
				int comparison2 = comparator.compare(nodes.get(p).getKey(), nodes.get(i).getKey());
				if (comparison2 < 0) {
					downHeap(i);
				} else {
					upHeap(i);
				}
				return ret;
			}
		}
		return null;
	}

	@Override
	public Key replaceKey(Entry<Key, Value> entry, Key k) {
		// TODO Auto-generated method stub

		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getKey().equals(entry.getKey()) && nodes.get(i).getValue().equals(entry.getValue())) {
				Key oldKey = entry.getKey();
				nodes.get(i).setKey(k);
				if(i == 0) {
					downHeap(i);
					return oldKey;
				}
				int p = parentOf(i);
				int comparison2 = comparator.compare(nodes.get(p).getKey(), nodes.get(i).getKey());
				if (comparison2 < 0) {
					downHeap(i);
				} else {
					upHeap(i);
				}
				return oldKey;
			}
		}
		return null;
	}

	@Override
	public Key replaceKey(Value v, Key k) {

		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getValue().equals(v)) {
				Key old = nodes.get(i).getKey();
				nodes.get(i).setKey(k);
				if(i == 0) {
					downHeap(i);
					return old;
				}
				int p = parentOf(i);
				int comparison2 = comparator.compare(nodes.get(p).getKey(), nodes.get(i).getKey());
				if (comparison2 < 0) {
					downHeap(i);
				} else {
					upHeap(i);
				}
				return old;
			}
		}
		return null;

		
	}

	@Override
	public Value replaceValue(Entry<Key, Value> entry, Value v) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getKey().equals(entry.getKey()) && nodes.get(i).getValue().equals(entry.getValue())) {
				Value oldValue = entry.getValue();
				nodes.get(i).setValue(v);;
				return oldValue;
				}
		}
		return null;
	}

	/*
	 * 
	 * YOUR CODE HERE YOU CAN START WITH "add unimplemented methods" feature of
	 * eclipse if you are using it
	 * 
	 */
}
