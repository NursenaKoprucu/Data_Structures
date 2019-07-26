package code;

import java.util.List;

import given.Entry;
import given.iAdaptablePriorityQueue;

/*
 * Implement a binary search tree based priority queue
 * Do not try to create heap behavior (e.g. no need for a last node)
 * Just use default binary search tree properties
 */

public class BSTBasedPQ<Key, Value> extends BinarySearchTree<Key, Value>
		implements iAdaptablePriorityQueue<Key, Value> {

	@Override
	public void insert(Key k, Value v) {
		// TODO Auto-generated method stub
		put(k, v);
	}

	@Override
	public Entry<Key, Value> pop() {
		// TODO Auto-generated method stub
		BinaryTreeNode<Key, Value> entry = min(root);
		if(entry.isExternal()) {
			return null;
		}
		Entry<Key, Value> toReturn = new Entry<Key, Value>(entry.getKey(), entry.getValue());
		remove(entry.getKey());
		return toReturn;
	}

	@Override
	public Entry<Key, Value> top() {
		// TODO Auto-generated method stub
		BinaryTreeNode<Key, Value> entry = min(root);
		if(entry.isExternal()) {
			return null;
		}
		return entry;
	}

	@Override
	public Key replaceKey(Entry<Key, Value> entry, Key k) {
		// TODO Auto-generated method stub
		Key ret = entry.getKey();
		Value val = entry.getValue();
		BinaryTreeNode<Key, Value> node = getNode(ret);
		if (node.isExternal() || !entry.getValue().equals(node.getValue())) {
			return null;
		}
		remove(ret);
		put(k, val);
		return ret;
	}

	@Override
	public Key replaceKey(Value v, Key k) {
		List<BinaryTreeNode<Key, Value>> set = getNodesInOrder();
		for (BinaryTreeNode<Key, Value> binaryTreeNode : set) {
			if (binaryTreeNode.getValue().equals(v)) {
				return replaceKey(binaryTreeNode, k);
			}
		}
		return null;
	}

	@Override
	public Value replaceValue(Entry<Key, Value> entry, Value v) {
		Key ret = entry.getKey();
		BinaryTreeNode<Key, Value> node = getNode(ret);
		if (node.isExternal() || !entry.getValue().equals(node.getValue())) {
			return null;
		}
		return put(ret, v);
	}
	/*
	 * 
	 * YOUR CODE HERE YOU CAN START WITH "add unimplemented methods" feature of
	 * eclipse if you are using it
	 * 
	 */

}
