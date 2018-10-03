package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		
		if (this.isFull()) {
			throw new QueueOverflowException();
		}
		
		list.insert(element); //adiciona com meu método
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		
		@SuppressWarnings("unchecked") //vê se dá o erro
		T node = (T) ((DoubleLinkedListImpl<T>) list).getHead();
		list.removeFirst();
		return node;
	}

	@Override
	public T head() {
		@SuppressWarnings("unchecked")
		T node = (T) ((DoubleLinkedListImpl<T>) list).getHead(); //cast
		return node;
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.list.size() == this.size;
	}

}
