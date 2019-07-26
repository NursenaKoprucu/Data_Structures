package code;

/* 
 * ASSIGNMENT 2
 * AUTHOR:  <Insert Student Name>
 * Class : LLDeque
 *
 * You are not allowed to use Java containers!
 * You must implement the linked list yourself
 * Note that it should be a doubly linked list
 *
 * MODIFY 
 * 
 * */

import given.iDeque;

import java.util.Iterator;
import given.Util;

//If you have been following the class, it should be obvious by now how to implement a Deque wth a doubly linked list
public class LLDeque<E> implements iDeque<E> {

	//Use sentinel nodes. See slides if needed
	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;


	/*
	 * ADD FIELDS IF NEEDED
	 */

	// The nested node class, provided for your convenience. Feel free to modify
	private class Node<T> {
		private T element;
		private Node<T> next;
		private Node<T> prev;
		/*
		 * ADD FIELDS IF NEEDED
		 */

		Node(T d, Node<T> n, Node<T> p) {
			element = d;
			next = n;
			prev = p;
		}

		public T getElement() {
			return element;
		}

		public T setElement(T element) {
			T tmp = this.element;
			this.element = element;
			return tmp;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		public Node<T> getPrev() {
			return prev;
		}

		public void setPrev(Node<T> prev) {
			this.prev = prev;
		}




	}

	public LLDeque() {
		//Remember how we initialized the sentinel nodes
		header  = new Node<E>(null, null, header);
		trailer = new Node<E>(null, trailer, header);
		header.next = trailer;
		trailer.prev= header;

		/*
		 * ADD CODE IF NEEDED
		 */
	}

	public String toString() {
		if(isEmpty())
			return "";
		StringBuilder sb = new StringBuilder(1000);
		sb.append("[");
		Node<E> tmp = header.next;
		while(tmp.next != trailer) {
			sb.append(tmp.element.toString());
			sb.append(", ");
			tmp = tmp.next;
		}
		sb.append(tmp.element.toString());
		sb.append("]");
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

		Node hnext = header.getNext() ;
		Node newNode = new Node(o, hnext, header);
		hnext.setPrev(newNode);
		header.setNext(newNode);

		size++;

	}

	@Override
	public E removeFront() {

		if(isEmpty()) {
			return null;
		}
		E res = header.getNext().getElement();
		Node pnext = header.getNext().getNext();
		pnext.setPrev(header);
		header.setNext(pnext);
		size--;
		return res;


	}

	@Override
	public E front() {
		if(isEmpty()) {
			return null;

		}
		return header.getNext().getElement();
	}

	@Override
	public void addBehind(E o) {

		Node oPrev= trailer.getPrev();
		Node newNode = new Node(o, trailer, oPrev);
		trailer.setPrev(newNode);
		oPrev.setNext(newNode);
		size++;
	}

	@Override
	public E removeBehind() {
		if(isEmpty()) {
			return null;
		}
		E res= trailer.getPrev().getElement();

		Node prev = trailer.getPrev().getPrev();
		trailer.setPrev(prev);
		prev.setNext(trailer);
		size--;
		return res;
	}

	@Override
	public E behind() {

		if(isEmpty()) {
			return null;
		}
		return trailer.getPrev().getElement();
	}

	@Override
	public void clear() {
		header.setNext(trailer);
		trailer.setPrev(header);
		size = 0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		//Hint: Fill in the LLDequeIterator given below and return a new instance of it
		return new LLDequeIterator();
	}

	private final class LLDequeIterator implements Iterator<E> {

		/*
		 * 
		 * ADD A CONSTRUCTOR IF NEEDED
		 * Note that you can freely access everything about the outer class!
		 * 
		 */
		private Node<E> curr= header;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return curr.getNext()!=trailer;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if(!hasNext()) {
				return null;
			}
			curr= curr.getNext();
			return curr.getElement();
		}        
	}

}
