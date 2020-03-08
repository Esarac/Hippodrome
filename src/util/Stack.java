package util;

import exception.EmptyListException;

public class Stack<E> implements List<E> {
	
	private int size;
	private Node<E> first;
	
	public Stack() {
		
		size = 0;
		first = null;
	}
	
	public void push(E element) {
		
		Node<E> aux = first;
		first = new Node<>(element);
		first.setNext(aux);
		size++;
	}
	
	public E pop() {
		
		if(isEmpty())
			throw new EmptyListException();
		E element = first.getElement();
		first = first.getNext();
		size--;
		return element;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public E peek() {
		
		if(isEmpty())
			throw new EmptyListException();
		return first.getElement();
	}
	
	@Override
	public boolean isEmpty() {
		return first == null;
	}

}