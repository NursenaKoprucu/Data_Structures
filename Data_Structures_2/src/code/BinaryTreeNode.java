package code;

import given.Entry;

/*
 * The binary node class which extends the entry class.
 * This will be used in linked tree implementations
 * 
 */
public class BinaryTreeNode<Key, Value> extends Entry<Key, Value> {

	/*
	 * 
	 * YOUR CODE HERE
	 * 
	 */
	private BinaryTreeNode<Key, Value> right;
	private BinaryTreeNode<Key, Value> left;
	private BinaryTreeNode<Key, Value> parent;

	public BinaryTreeNode(Key k, Value v) {
		super(k, v);
		left = null;
		right = null;

		/*
		 * 
		 * This constructor is needed for the autograder. You can fill the rest to your
		 * liking. YOUR CODE AFTER THIS:
		 * 
		 */
	}

	public BinaryTreeNode(Key key, Value value, BinaryTreeNode<Key, Value> parent) {
		this(key, value);

		this.parent = parent;
	}

	public BinaryTreeNode<Key, Value> getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode<Key, Value> parent) {
		this.parent = parent;
	}

	public boolean isExternal() {
		return left == null && right == null;
	}

	public BinaryTreeNode<Key, Value> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<Key, Value> right) {
		this.right = right;
	}

	public BinaryTreeNode<Key, Value> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<Key, Value> left) {
		this.left = left;
	}

}
