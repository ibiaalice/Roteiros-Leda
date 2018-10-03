package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) throw new QueueOverflowException();
		array[tail] = element;
		tail = (tail+1) % array.length;
		elements++;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) throw new QueueUnderflowException();
		T aux = array[head];
		this.head = (head + 1) % array.length;
		this.elements --;
		return aux;
	}

	@Override
	public T head() {
		if(isEmpty()) return null;
		return array[head];
	}

	@Override
	public boolean isEmpty() {
		return (elements == -1);
	}

	@Override
	public boolean isFull() {
		return this.elements == this.tail;
	}

}
