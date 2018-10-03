package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	//dica de matheus, não entendi pq ainda
	public DoubleLinkedListImpl() {
		super.head = new DoubleLinkedListNode<>();
	}

	@Override
	public void insertFirst(T element) {
		
		SingleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, (DoubleLinkedListNode<T>) super.head, new DoubleLinkedListNode<>());
		super.head = node;

	}

	@Override
	public void removeFirst() {
		super.head = super.head.getNext();
	}

	@Override
	public void removeLast() {
		//faz a troca de posições de todos os casos
		if (super.size() == 1) {
			super.setHead(new DoubleLinkedListNode<>());
			this.setLast(new DoubleLinkedListNode<>());
			
		} else if (super.size() == 2) {
			super.head.setNext(new DoubleLinkedListNode<>());
			this.setLast((DoubleLinkedListNode<T>) super.head);
			
		} else {
			this.setLast(this.last.getPrevious());
			this.last.setNext(new DoubleLinkedListNode<>());
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
	
	@Override
	public void remove(T element) {
		
		if (!this.isEmpty()) {
			if (this.head.getData().equals(element)) {
				
				if ( this.head.equals(this.last) ) {
					this.head = new DoubleLinkedListNode();
					this.last = (DoubleLinkedListNode<T>) this.head;
					
				} else {
					((DoubleLinkedListNode<T>) this.head.getNext()).setPrevious(((DoubleLinkedListNode<T>) this.head).getPrevious());
					this.head = ((DoubleLinkedListNode<T>) this.head.getNext());
					
				}
			} else {
				DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.head;
				
				while (!node.isNIL() && !node.getData().equals(element)) { //vai percorrer todo array
					node = (DoubleLinkedListNode<T>) node.getNext();
				}
				
				if (node.equals(this.last)) {
					this.last.getPrevious().setNext(new DoubleLinkedListNode<>());
					this.last = this.last.getPrevious();//troca de locais
					
				} else {
					((DoubleLinkedListNode<T>) node.getNext()).setPrevious(node.getPrevious());
					node.getPrevious().setNext(node.getNext());//troca de locais
					
				}
			}
		}
	}
	
	@Override//vamos testar se isso dá certo
	public void insert(T element) {
		if (super.isEmpty()) {
			super.head = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), new DoubleLinkedListNode<>());
			this.last = (DoubleLinkedListNode<T>) super.head;
			
		} else if (super.size() == 1) {
			this.last = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), (DoubleLinkedListNode<T>) super.head);
			super.head.setNext(this.last);
			
		} else {
			SingleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), this.last);
			this.last.setNext(node);
			this.setLast((DoubleLinkedListNode<T>) this.last.getNext());
			
		}
	}

}
