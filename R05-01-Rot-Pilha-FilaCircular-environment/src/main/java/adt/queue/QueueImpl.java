package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		return this.array[0];
	}

	@Override
	public boolean isEmpty() {
		return tail == -1;
	}

	@Override
	public boolean isFull() {
		return tail == array.length - 1;
	}

	private void shiftLeft() {
		T aux;
		for(int i = 0; i < this.array.length - 1; i++ ) {
			aux = array[i+1];
			array[i] = aux;
		}
		
		this.tail -= 1;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull())
			throw new QueueOverflowException();
		tail += 1;
		this.array[this.tail] = element;
		
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) 
			throw new QueueUnderflowException();
		
		T saida = array[0];
		shiftLeft();
		return saida;
	}

}
