package code;

/* 
 * ASSIGNMENT 2
 * AUTHOR:  <Insert Student Name>
 * Class : ArrayDeque
 *
 * You are not allowed to use Java containers!
 * You must implement the Array Deque yourself
 *
 * MODIFY 
 * 
 * */

import given.iDeque;
import java.util.Arrays;
import java.util.Iterator;

import given.Util;

public class ArrayDeque<E> implements iDeque<E> {

	private E[] A; // Do not change this name!
	private int size = 0;
	private int front;
	private int rear;

	/*
	 * ADD FIELDS IF NEEDED
	 */

	public ArrayDeque() {
		this(1000);
		/*
		 * ADD CODE IF NEEDED
		 */
	}

	public ArrayDeque(int initialCapacity) {
		if (initialCapacity < 1)
			throw new IllegalArgumentException();
		A = createNewArrayWithSize(initialCapacity);
		front = 0;
		rear = 0;

		/*
		 * ADD CODE IF NEEDED
		 */
	}

	// This is given to you for your convenience since creating arrays of generics
	// is not straightforward in Java
	@SuppressWarnings({ "unchecked" })
	private E[] createNewArrayWithSize(int size) {
		return (E[]) new Object[size];
	}

	// Bonus: Modify this such that the dequeue prints from front to back!
	// Hint, after you implement the iterator, use that!
	public String toString() {
		if (isEmpty()) {
			return ("");
		}

		StringBuilder sb = new StringBuilder(1000);
		sb.append("[");
		Iterator<E> iter = this.iterator();
		while (iter.hasNext()) {
			E e = iter.next();
			if (e == null)
				continue;
			sb.append(e);
			if (!iter.hasNext())
				sb.append("]");
			else
				sb.append(", ");
		}
		return sb.toString();
	}

	/*
	 * ADD METHODS IF NEEDED
	 */

	/*
	 * Below are the interface methods to be overriden
	 */

	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	@Override
	public void addFront(E o) {
		if (size == A.length) {
			A = resize();
		}
		front = (front - 1 + A.length) % A.length;
		A[front] = o;
		size++;
	}

	@Override
	public E removeFront() {
		if (isEmpty()) {
			return null;
		}
		E o = A[front];
		A[front] = null;
		front = (front + 1) % A.length;
		size--;
		return o;
	}

	@Override
	public E front() {
		if (isEmpty()) {
			return null;
		}
		return A[front];
	}

	@Override
	public void addBehind(E o) {
		if (size == A.length) {
			A = resize();
		}
		A[rear] = o;
		rear = (rear + 1) % A.length;
		size++;
	}

	public E[] resize() {
		E[] tmp = createNewArrayWithSize(2 * A.length);
		for (int i = front; i < (front + size); i++) {
			tmp[i] = A[i % A.length];
		}
		return tmp;
	}

	@Override
	public E removeBehind() {
		if (isEmpty()) {
			return null;
		}
		rear = (rear - 1 + A.length) % A.length;
		E o = A[rear];
		A[rear] = null;
		size--;
		return o;

	}

	@Override
	public E behind() {
		if (isEmpty()) {
			return null;
		}
		return A[(rear - 1 + A.length) % A.length];
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		A = createNewArrayWithSize(1000);
		front = 0;
		rear = 0;
		size = 0;
	}

	// Must print from front to back
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		// Hint: Fill in the ArrayDequeIterator given below and return a new instance of
		// it
		return new ArrayDequeIterator();
	}

	private final class ArrayDequeIterator implements Iterator<E> {

		/*
		 * 
		 * ADD A CONSTRUCTOR IF NEEDED Note that you can freely access everything about
		 * the outer class!
		 * 
		 */
		private int curr = (front - 1 + A.length) % A.length;

		@Override
		public boolean hasNext() {
			return (curr + 1) % A.length != rear;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				return null;
			}
			curr = (curr + 1) % A.length;
			return A[curr];
		}
	}
}
