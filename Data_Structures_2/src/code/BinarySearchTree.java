package code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import given.iMap;
import given.Entry;
import given.iBinarySearchTree;

/*
 * Implement a vanilla binary search tree using a linked tree representation
 * Use the BinaryTreeNode as your node class
 */

public class BinarySearchTree<Key, Value> implements iBinarySearchTree<Key, Value>, iMap<Key, Value> {

	BinaryTreeNode<Key, Value> root = new BinaryTreeNode<Key, Value>(null, null);
	int size;
	Comparator<Key> comparator;

	public BinarySearchTree() {

	}

	@Override
	public Value get(Key k) {
		BinaryTreeNode<Key, Value> tmp = treeSearch(root, k);
		return tmp.getValue();
	}

	public BinaryTreeNode<Key, Value> treeSearch(BinaryTreeNode<Key, Value> node, Key k) {
		if (node.isExternal()) {
			return node;
		}
		int comparison = comparator.compare(k, node.getKey());
		if (comparison < 0) {
			return treeSearch(node.getLeft(), k);
		} else if (comparison == 0) {
			return node;
		} else {
			return treeSearch(node.getRight(), k);
		}
	}

	@Override
	public Value put(Key k, Value v) {
		// TODO Auto-generated method stub
		if(k == null) {
			return null;
		}
		BinaryTreeNode<Key, Value> newNode = treeSearch(root, k);
		if (newNode.isExternal()) {
			newNode.setKey(k);
			newNode.setValue(v);
			newNode.setLeft(new BinaryTreeNode<Key, Value>(null, null, newNode));
			newNode.setRight(new BinaryTreeNode<Key, Value>(null, null, newNode));
			size++;
			return null;
		} else {
			Value oldValue = newNode.getValue();
			newNode.setValue(v);
			return oldValue;
		}
	}

	@Override
	public Value remove(Key k) {
		if(k == null) {
			return null;
		}
		BinaryTreeNode<Key, Value> tmp = treeSearch(root, k);
		if (tmp.isExternal()) {
			return null;
		}
		size--;
		return removeNode(tmp);
	}

	private Value removeNode(BinaryTreeNode<Key, Value> tmp) {
		Value val = tmp.getValue();
		if (tmp.getLeft().isExternal() && tmp.getRight().isExternal()) {
			if (tmp == root) {
				root.setKey(null);
				root.setValue(null);
				root.setLeft(null);
				root.setRight(null);
			} else if (isRightChild(tmp)) {
				tmp.getParent().setRight(new BinaryTreeNode<Key, Value>(null, null, tmp));
			} else { // left child
				tmp.getParent().setLeft(new BinaryTreeNode<Key, Value>(null, null, tmp));
			}
		} else if (tmp.getLeft().isExternal() || tmp.getRight().isExternal()) {
			BinaryTreeNode<Key, Value> toReplace;
			if(tmp.getLeft().isExternal()) {
				toReplace = tmp.getRight();
			} else {
				toReplace = tmp.getLeft();
			}
			toReplace.setParent(tmp.getParent());
			if (tmp == root) {
				root = toReplace;
			} else if (isRightChild(tmp)) {
				tmp.getParent().setRight(toReplace);
			} else { // left child
				tmp.getParent().setLeft(toReplace);
			}
		} else {
			BinaryTreeNode<Key, Value> minNode = min(tmp.getRight());
			tmp.setKey(minNode.getKey());
			tmp.setValue(minNode.getValue());
			removeNode(minNode);
		}
		return val;
	}

	public BinaryTreeNode<Key, Value> min(BinaryTreeNode<Key, Value> tmp) {
		if (tmp.isExternal() || tmp.getLeft().isExternal()) {
			return tmp;
		} else {
			return min(tmp.getLeft());
		}
	}

	@Override
	public Iterable<Key> keySet() {
		ArrayList<Key> set = new ArrayList<Key>();
		inOrder(root, set);
		return set;
	}

	public void inOrder(BinaryTreeNode<Key, Value> node, ArrayList<Key> set) {
		if (!node.isExternal()) {
			inOrder(node.getLeft(), set);
			set.add(node.getKey());
			inOrder(node.getRight(), set);
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public BinaryTreeNode<Key, Value> getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public BinaryTreeNode<Key, Value> getParent(BinaryTreeNode<Key, Value> node) {
		// TODO Auto-generated method stub
		return node.getParent();
	}

	@Override
	public boolean isInternal(BinaryTreeNode<Key, Value> node) {
		return !node.isExternal();
	}

	@Override
	public boolean isExternal(BinaryTreeNode<Key, Value> node) {
		return node.isExternal();
	}

	@Override
	public boolean isRoot(BinaryTreeNode<Key, Value> node) {
		// TODO Auto-generated method stub
		return node == root;
	}

	@Override
	public BinaryTreeNode<Key, Value> getNode(Key k) {
		// TODO Auto-generated method stub
		return treeSearch(root, k);
	}

	@Override
	public Value getValue(Key k) {
		// TODO Auto-generated method stub
		return get(k);
	}

	@Override
	public BinaryTreeNode<Key, Value> getLeftChild(BinaryTreeNode<Key, Value> node) {
		// TODO Auto-generated method stub
		return node.getLeft();
	}

	@Override
	public BinaryTreeNode<Key, Value> getRightChild(BinaryTreeNode<Key, Value> node) {
		// TODO Auto-generated method stub
		return node.getRight();
	}

	@Override
	public BinaryTreeNode<Key, Value> sibling(BinaryTreeNode<Key, Value> node) {

		if (node == null || isRoot(node)) {
			return null;
		} else if (isLeftChild(node)) {
			return node.getParent().getRight();
		} else {
			return node.getParent().getLeft();

		}
	}

	@Override
	public boolean isLeftChild(BinaryTreeNode<Key, Value> node) {
		return node != null && !isRoot(node) && node.getParent().getLeft() == node;
	}

	@Override
	public boolean isRightChild(BinaryTreeNode<Key, Value> node) {

		return node != null && !isRoot(node) && node.getParent().getRight() == node;
	}

	@Override
	public List<BinaryTreeNode<Key, Value>> getNodesInOrder() {
		List<BinaryTreeNode<Key, Value>> set = new ArrayList<BinaryTreeNode<Key, Value>>();
		inOrder2(root, set);
		return set;
	}

	public void inOrder2(BinaryTreeNode<Key, Value> node, List<BinaryTreeNode<Key, Value>> set) {
		if (!node.isExternal()) {
			inOrder2(node.getLeft(), set);
			set.add(node);
			inOrder2(node.getRight(), set);
		}
	}

	@Override
	public void setComparator(Comparator<Key> C) {
		this.comparator = C;
	}

	@Override
	public Comparator<Key> getComparator() {
		return comparator;
	}

	@Override
	public BinaryTreeNode<Key, Value> ceiling(Key k) {
		// TODO Auto-generated method stub
		BinaryTreeNode<Key, Value> temp = treeSearch(root, k);

		if (!isExternal(temp)) {
			return temp;
		}
		while (isRightChild(temp)) {
			temp = temp.getParent();
		}
		if (isLeftChild(temp)) {
			return temp.getParent();
		} else {
			return null;
		}

	}

	@Override
	public BinaryTreeNode<Key, Value> floor(Key k) {
		BinaryTreeNode<Key, Value> temp = treeSearch(root, k);

		if (!isExternal(temp)) {
			return temp;
		}
		while (isLeftChild(temp)) {
			temp = temp.getParent();
		}
		if (isRightChild(temp)) {
			return temp.getParent();
		} else {
			return null;
		}

	}

	/*
	 * 
	 * YOUR CODE HERE YOU CAN START WITH "add unimplemented methods" feature of
	 * eclipse if you are using it
	 * 
	 * 
	 */
}
