package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if ( this.isFull() ) {
			throw new StackOverflowException();
		}
		
		this.top.insert(element);
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		}
		
		T[] array = this.top.toArray();
		T node = array[this.top.size()-1];
		
		this.top.removeLast();
		
		return node;
	}

	@Override
	public T top() {
		
		T node;
		if (this.isEmpty()) {
			node = null;
		} else {
			T[] array = this.top.toArray();
			node = array[this.top.size()-1];
		}
		
		return node;
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.top.size() == size;
	}

}
