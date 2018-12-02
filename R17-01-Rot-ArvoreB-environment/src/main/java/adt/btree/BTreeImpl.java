package adt.btree;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		int altura = -1;
		if (isEmpty()) return altura;
		
		return height(this.root, altura);
	}

	private int height(BNode<T> node, int height) {
		if (node.isLeaf()) return ++height;
		
		return height(node.getChildren().getFirst(), ++height);
	}

	private int height(BNode<T> node) {
		if(isEmpty()) return -1;
		return height(node, -1);
	}


	
	@SuppressWarnings("unchecked")
	@Override
	public BNode<T>[] depthLeftOrder() {
		BNode<T>[] array = new BNode[countNodes(root)];

		depthLeftOrder(array, 0, this.root);

		return array;
	}

	private int countNodes(BNode<T> node) {
		if (node.isEmpty())
			return 0;

		int count = 1;

		for (int i = 0; i < node.getChildren().size(); i++)
			count += countNodes(node.getChildren().get(i));
		
		return count;
	}

	private int depthLeftOrder(BNode<T> array[], int indice, BNode<T> node) {
		if (!node.isEmpty()) {
			array[indice++] = node;
			for (int i = 0; i < node.children.size(); i++)
				indice = depthLeftOrder(array, indice, node.children.get(i));
		}

		return indice;
	}

	@Override
	public int size() {
		return size(root);
	}

	private int size(BNode<T> no) {
		if (no.isEmpty()) return 0;

		int size = no.size();
		for (int i = 0; i < no.getChildren().size() ; i++) {
			size += size( no.getChildren().get(i) );
		}

		return size;
	}
	
	@Override
	public BNodePosition<T> search(T element) {
		if (element == null)
			return new BNodePosition<T>();

		return search(root, element);
	}

	private BNodePosition<T> search(BNode<T> no, T element) {
		int indice = 0;

		while (indice < no.getElements().size() && element.compareTo(no.getElementAt(indice)) > 0)
			indice += 1;
		
		if (indice < no.getElements().size() && element.compareTo(no.getElementAt(indice)) == 0)
			return new BNodePosition<T>(no, indice);
		if (no.isLeaf())
			return new BNodePosition<T>();

		return search(no.getChildren().get(indice), element);
	}
	
	@Override
	public void insert(T element) {
		if (element != null)
			insert(root, element);
	}

	private void insert(BNode<T> no, T element) {
		if (no.getChildren().isEmpty())
			no.addElement(element);
		
		else {
			int i = 0; //contador
			
			while (i < no.size()) {
				T e = no.getElements().get(i);
				if (e.compareTo(element) > 0) {
					insert(no.getChildren().get(i), element);
					break;
				}
				i += 1;
			}
			if (i == no.size()) {
				while (no.getChildren().size() <= i) {
					BNode<T> newNode = new BNode<T>(order);
					newNode.setParent(no);
					no.getChildren().add(newNode);
				}
				
				insert(no.getChildren().get(i), element); //chamada recursiva
			}
		}
		
		if (no.size() >= order) {
			split(no);
			if (no.getParent() != null) 	no = no.getParent();
		}
		
	}

	private void split(BNode<T> node) {
		BNode<T> direita = new BNode<>(order);
		BNode<T> esquerda = new BNode<>(order);

		T meio = node.getElements().get((order - 1) / 2);
		boolean addNaEsquerda = true;
		
		for (T i : node.getElements()) {
			if (i.equals(meio)) addNaEsquerda = false;		
			else if (addNaEsquerda) esquerda.addElement(i);
			else direita.addElement(i);
		}

		if (!node.equals(root))	promote(node, esquerda, direita);
		
		else {
			for (int i = 0; i < node.getChildren().size(); i++) {
				if (i <= (order - 1) / 2) {
					esquerda.addChild(i, node.getChildren().get(i));
				} else {
					direita.addChild(i - ((order - 1) / 2) - 1, node.getChildren().get(i));
				}
			}
			
			//ajeita todo mundo no seu lugar 
			
			esquerda.setParent(node);
			direita.setParent(node);

			node.getChildren().clear();
			node.addChild(0, esquerda);
			node.addChild(1, direita);

			node.getElements().clear();
			node.addElement(meio);
		}
	}
	
	private void promote(BNode<T> node, BNode<T> leftChild, BNode<T> rightChild) {
		BNode<T> pai = node.getParent();
		
		T element = node.getElementAt((order - 1) / 2);
		pai.addElement(element);

		int pos = pai.getElements().indexOf(element);
		pai.removeChild(node);

		leftChild.setParent(pai);
		rightChild.setParent(pai);

		node.parent.removeChild(node);
		node.parent.addChild(pos, rightChild);
		node.parent.addChild(pos, leftChild);
	}
	
	
	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
