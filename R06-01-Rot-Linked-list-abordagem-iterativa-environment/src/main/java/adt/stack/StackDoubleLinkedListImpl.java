package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		
		if ( this.isFull() ) {
			throw new StackOverflowException(); //lança o erro 
			
		}
		
		this.top.insert(element);

	}

	@Override
	public T pop() throws StackUnderflowException {
		
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		}
		
		@SuppressWarnings("unchecked") //protege
		T node = (T) ((DoubleLinkedListImpl<T>) this.top).getLast();
		this.top.removeLast();
		
		return node;
	}

	@Override
	public T top() {
		
		@SuppressWarnings("unchecked")
		T node = (T) ((DoubleLinkedListImpl<T>) this.top).getHead();
		return node;
		
	}

	@Override
	public boolean isEmpty() {
		
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		
		return this.top.size()  == this.size;
	}

}
